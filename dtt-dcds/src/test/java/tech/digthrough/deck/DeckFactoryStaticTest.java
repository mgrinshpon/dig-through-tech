package tech.digthrough.deck;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.junit.Test;

public class DeckFactoryStaticTest {
  @Test
  public void canSortList() {
    final DeckIteration di0 = iter(0L, new Long[] {}, new Long[] {1L, 42L});
    final DeckIteration di1 = iter(1L, new Long[] {0L, 66L}, new Long[] {2L});
    final DeckIteration di2 = iter(2L, new Long[] {1L}, new Long[] {});
    List<DeckIteration> iters = new ArrayList<>();
    iters.add(di1);
    iters.add(di2);
    iters.add(di0);

    DeckFactory.sortDeckIters().accept(iters, 2L);

    assertEquals(0L, iters.get(0).getKey().longValue());
    assertEquals(1L, iters.get(1).getKey().longValue());
    assertEquals(2L, iters.get(2).getKey().longValue());
  }

  @Test
  public void cannotSortUnrelatedLists() {

  }

  /**
   * Quick deck iterations for sorting tests
   */
  private static DeckIteration iter(Long key, Long[] prev, Long[] next) {
    DeckIteration retval = new DeckIteration("", new HashMap<>(), new HashMap<>());
    retval.setKey(key);
    retval.getPrevious().addAll(Arrays.asList(prev));
    retval.getNext().addAll(Arrays.asList(next));
    return retval;
  }
}
