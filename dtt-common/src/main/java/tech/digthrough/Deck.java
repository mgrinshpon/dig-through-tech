package tech.digthrough;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class Deck implements Serializable {
  private static final long serialVersionUID = 1L;
  private Long deckId;
  private final String name;
  private final Map<String, Integer> maindeck;
  private final Map<String, Integer> sideboard;
  private final Map<String, Integer> commandZone;
  private final Map<String, Integer> contraptionDeck;
  private final List<Long> previous;
  private final List<Long> next;

  public List<Long> getPrevious() {
    return previous;
  }

  public boolean hasPrevious() {
    return previous != null && !previous.isEmpty();
  }

  public static void validate(final Deck toValidate) {}
}
