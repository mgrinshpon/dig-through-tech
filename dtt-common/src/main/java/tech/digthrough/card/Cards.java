package tech.digthrough.card;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class Cards {
  private static Cards instance = null;
  private static final Map<String, Supplier<Card>> ALL_CARDS = new HashMap<>();

  private Cards() {
    System.out.println("We made it");
    final String allCards = Cards.class.getResource("AllCards.json").getFile();
    System.out.println(allCards);

    //    try (final InputStream in = Cards.class.getResourceAsStream("AllCards.json")) {
    //      assert in != null;
    //      try (InputStreamReader isr = new InputStreamReader(in)) {
    //      } catch (final IOException e) {
    //        System.out.println("FUCKORUM TWO!");
    //        e.printStackTrace();
    //      }
    //    } catch (final IOException e1) {
    //      System.out.println("FUCKORUM!");
    //      e1.printStackTrace();
    //    }
  }

  public static void instance() {
    System.out.println("We made it");
    if (instance == null) {
      instance = new Cards();
    }
  }

  /**
   * @param cardName English name of the card.
   * @return a new instance of the desired card.
   * @throws IllegalArgumentException if the cardName is not found
   */
  public static Card get(final String cardName) {
    Cards.instance(); // Initialize

    final boolean contains = ALL_CARDS.containsKey(cardName);
    if (contains) {
      return ALL_CARDS.get(cardName).get();
    } else {
      throw new IllegalArgumentException("Card name \"" + cardName + "\" not found");
    }
  }
}
