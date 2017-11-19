package tech.digthrough.deck;

import static org.junit.Assert.assertArrayEquals;
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
    final List<DeckIteration> iters = new ArrayList<>();
    iters.add(di1);
    iters.add(di2);
    iters.add(di0);

    DeckFactory.sortDeckIterations().accept(iters, 2L);
    
    assertArrayEquals(new Long[] {0L, 1L, 2L}, iters.stream().map(iteration -> iteration.getKey()).toArray(Long[]::new));
  }

    @Test(expected = IllegalArgumentException.class)
    public void cannotSortUnrelatedLists() {
      final DeckIteration di0 = iter(0L, new Long[] {}, new Long[] {1L, 42L});
      final DeckIteration di1 = iter(1L, new Long[] {0L, 66L}, new Long[] {2L});
      final DeckIteration di2 = iter(2L, new Long[] {1L}, new Long[] {});
      final DeckIteration di3 = iter(1L, new Long[] {}, new Long[] {4L, 100L});
      final DeckIteration di4 = iter(2L, new Long[] {4L}, new Long[] {});
      final List<DeckIteration> iters = new ArrayList<>();
      iters.add(di3);
      iters.add(di1);
      iters.add(di2);
      iters.add(di0);
      iters.add(di4);

      DeckFactory.sortDeckIterations().accept(iters, 2L);
    }
    
    @Test
    public void fastSortShortLengths() {
      final DeckIteration di = iter(0L, new Long[] {}, new Long[] {});
      List<DeckIteration> iters = new ArrayList<>();
      iters.add(di);
      
      DeckFactory.sortDeckIterations().accept(iters, 0L);
      
      assertArrayEquals(new Long[] { 0L }, iters.stream().map(iteration -> iteration.getKey()).toArray(Long[]::new));
    }

    /**
     * Quick deck iterations for sorting tests
     */
    private static DeckIteration iter(final Long key, final Long[] prev, final Long[] next) {
        final DeckIteration retval = new DeckIteration("", new HashMap<>(), new HashMap<>());
        retval.setKey(key);
        retval.getPrevious().addAll(Arrays.asList(prev));
        retval.getNext().addAll(Arrays.asList(next));
        return retval;
    }
}
