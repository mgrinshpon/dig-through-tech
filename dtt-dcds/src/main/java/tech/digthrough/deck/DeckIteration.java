package tech.digthrough.deck;

import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DeckIteration {
  private Long key;
  private final String name;
  private final Date creationTime;
  private final Map<String, Integer> maindeckChanges;
  private final Map<String, Integer> sideboardChanges;
  private final Set<Long> previous;
  private final Set<Long> next;

  public DeckIteration(final String name, final Map<String, Integer> maindeckChanges,
      final Map<String, Integer> sideboardChanges) {
    this(null, name, Date.from(Instant.now()), maindeckChanges, sideboardChanges, new HashSet<>(),
        new HashSet<>());
  }

  public DeckIteration(final Long key, final String name,
      final Map<String, Integer> maindeckChanges, final Map<String, Integer> sideboardChanges) {
    this(key, name, Date.from(Instant.now()), maindeckChanges, sideboardChanges, new HashSet<>(),
        new HashSet<>());
  }

  public DeckIteration(final Long key, final String name, final Date creationTime,
      final Map<String, Integer> maindeckChanges, final Map<String, Integer> sideboardChanges,
      final Set<Long> previous, final Set<Long> next) {
    this.key = key;
    this.name = name;
    this.creationTime = creationTime;
    this.maindeckChanges = maindeckChanges;
    this.sideboardChanges = sideboardChanges;
    this.previous = previous;
    this.next = next;
  }

  public Long getKey() {
    return key;
  }

  public void setKey(final Long key) {
    this.key = key;
  }

  public String getName() {
    return name;
  }

  public Date getCreationTime() {
    return creationTime;
  }

  public Map<String, Integer> getMaindeckChanges() {
    return maindeckChanges;
  }

  public Map<String, Integer> getSideboardChanges() {
    return sideboardChanges;
  }

  public Set<Long> getPrevious() {
    return previous;
  }

  public boolean hasPrevious() {
    return getPrevious() != null && !getPrevious().isEmpty();
  }

  public Set<Long> getNext() {
    return next;
  }
}
