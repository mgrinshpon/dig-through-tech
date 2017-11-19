package tech.digthrough.deck;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.IntStream;
import tech.digthrough.Deck;

public class DeckFactory {
  private final DeckIterationDao dao;

  public DeckFactory(final DeckIterationDao dao) {
    this.dao = dao;
  }

  /**
   * @param deck
   * @return
   */
  public Long saveIteration(final Deck newDeck) {
    Deck.validate(newDeck);
    if (newDeck.hasPrevious()) {
      final List<Deck> decks =
          getLineage(newDeck.getPrevious().get(newDeck.getPrevious().size() - 1));
      return saveIteration(newDeck, decks.get(decks.size() - 1));
    }
    // TODO
    return null;
  }

  public Long saveIteration(final Deck newDeck, final Deck previous) {
    Deck.validate(newDeck);
    Deck.validate(previous);
    // TODO
    return null;
  }

  /**
   * Given a single Id, retrieve the entire past lineage of a deck
   *
   * @param headId newest deck to retrieve
   * @return a list of decks in order
   */
  public List<Deck> getLineage(final Long deckId) {
    final Long id = Optional.ofNullable(deckId).orElseThrow(
        () -> new IllegalArgumentException("deckId must be non-null when retrieving deck lineage"));
    final List<Deck> retval = new ArrayList<>();
    try {
      final DeckIteration iter0 = dao.getDeckIteration(id)[0];
      final List<DeckIteration> iterations = new ArrayList<>();
      if (iter0.hasPrevious()) {
        iterations.addAll(Arrays.asList(dao
            .getDeckIterations(iter0.getPrevious().toArray(new Long[iter0.getPrevious().size()]))));
      }
      iterations.add(iter0);
      sortDeckIterations().accept(iterations, iter0.getKey());
      for (int i = 1; i <= iterations.size(); ++i) {
        retval.add(deckFromIterations(iterations.subList(0, i)));
      }
    } catch (final SQLException e) {
      throw new IllegalArgumentException("deckId was not found in Deck Database. deckId=" + id);
    }
    return retval;
  }

  /**
   * Sort the deck iterations by descending chronological order in place using an insertion sort.
   */
  static BiConsumer<List<DeckIteration>, Long> sortDeckIterations() {
    return (toSort, headId) -> {
      if(toSort.size() < 2) {
        return;
      }
      int indexUnderExamination = IntStream.range(0, toSort.size())
          .filter(index -> toSort.get(index).getKey().equals(headId)).findAny()
          .orElseThrow(() -> new IllegalArgumentException(
              "Unable to find headId=" + headId + " during sort"));
      final DeckIteration swap = toSort.get(indexUnderExamination);
      toSort.remove(indexUnderExamination);
      toSort.add(swap);
      Long nextIdToFind = headId;
      
      for (int i = toSort.size() - 2; i >= 0; --i) {
        final Long nextId = nextIdToFind;
        // Move up the unsorted list
        for (int j = i; j >= 0; --j) {
          final DeckIteration temp = toSort.get(j);
          if (!temp.getNext().stream().anyMatch(val -> val.equals(nextId))) {
            // If this index doesn't contain the previous headId, ignore it unless the trees are unrelated
            if(j == 0) {
              throw new IllegalArgumentException("Unrelated iterations passed in. Unable to sort. Requested headId = {}, unrelatedIds={}, sortedIds={}");
            }
            continue;
          }
          nextIdToFind = temp.getKey();
          toSort.remove(j);
          toSort.add(i, temp);
        }
      }
    };
  }

  public static Deck deckFromIterations(final List<DeckIteration> iters) {

    final Map<String, Integer> mainDeck =
        iters.stream().map(iteration -> iteration.getMaindeckChanges())
            .flatMap(kvps -> kvps.entrySet().stream()).collect(new DecklistCollector());
    final Map<String, Integer> sideboard =
        iters.stream().map(iteration -> iteration.getSideboardChanges())
            .flatMap(kvps -> kvps.entrySet().stream()).collect(new DecklistCollector());

    return null;
  }

  /**
   * Aggregate every entry in a series of decklist iterations into a single chunk of card entries.
   */
  private static class DecklistCollector
      implements Collector<Entry<String, Integer>, Map<String, Integer>, Map<String, Integer>> {

    @Override
    public Supplier<Map<String, Integer>> supplier() {
      return () -> new HashMap<>();
    }

    @Override
    public BiConsumer<Map<String, Integer>, Entry<String, Integer>> accumulator() {
      return (ret, element) -> {
        final String key = element.getKey();
        if (ret.containsKey(key)) {
          ret.put(key, ret.get(key) + element.getValue());
        } else {
          ret.put(key, element.getValue());
        }
      };
    }

    @Override
    public BinaryOperator<Map<String, Integer>> combiner() {
      return (a, b) -> {
        b.entrySet().forEach(kvp -> accumulator().accept(a, kvp));
        return a;
      };
    }

    @Override
    public Function<Map<String, Integer>, Map<String, Integer>> finisher() {
      return a -> a;
    }

    @Override
    public Set<Characteristics> characteristics() {
      final Set<Characteristics> chars = new HashSet<>();
      chars.add(Characteristics.UNORDERED);
      chars.add(Characteristics.IDENTITY_FINISH);
      return Collections.unmodifiableSet(chars);
    }
  }
}
