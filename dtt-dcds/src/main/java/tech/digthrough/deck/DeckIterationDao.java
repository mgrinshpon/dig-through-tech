package tech.digthrough.deck;

import java.sql.SQLException;

public interface DeckIterationDao {
  public DeckIteration[] getDeckIteration(Long id) throws SQLException;

  public DeckIteration[] getDeckIterations(Long[] ids) throws SQLException;

  public void deleteIterations(DeckIteration[] iterations) throws SQLException;

  /**
   * @param newIteration New iteration minus the unique key
   * @param previousIteration Previous iteration plus the unique key
   * @return new ID of the added deck
   */
  public Long addIteration(DeckIteration previousIteration, DeckIteration newIteration)
      throws SQLException;
}
