package tech.digthrough.card;

import org.junit.BeforeClass;
import org.junit.Test;

public class CanLoadCardsTest {
  @BeforeClass
  public static void getInstance() {
    Cards.instance();
  }

  @Test
  public void canGetCounterspell() {}

  @Test
  public void canGetLittleGirl() {
    Cards.get("Little Girl");
  }
}
