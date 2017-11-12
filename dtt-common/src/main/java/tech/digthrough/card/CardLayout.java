package tech.digthrough.card;

public enum CardLayout {
  NORMAL("Normal"),
  DOUBLE_FACED("Double-faced"),
  FLIP("Flip"),
  LEVELER("Leveler"),
  MELD("Meld"),
  SPLIT("Split"),
  TOKEN("Token"),
  PHENOMENON("Phenomenon"),
  PLANE("Plane"),
  SCHEME("Scheme"),
  VANGUARD("Vanguard");

  private final String toString;

  CardLayout(final String toString) {
    this.toString = toString;
  }

  public String toCardString() {
    return toString;
  }

  static CardLayout fromJsonString(final String string) {
    switch (string) {
      case "normal":
        return NORMAL;
      case "double-faced":
        return DOUBLE_FACED;
      case "flip":
        return FLIP;
      case "leveler":
        return LEVELER;
      case "meld":
        return MELD;
      case "split":
        return SPLIT;
      case "token":
        return TOKEN;
      case "phenomenon":
        return PHENOMENON;
      case "plane":
        return PLANE;
      case "scheme":
        return SCHEME;
      case "vanguard":
        return VANGUARD;
      default:
        // TODO Log error
        return NORMAL;
    }
  }
}
