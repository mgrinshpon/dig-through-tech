package tech.digthrough.deck;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DeckIterationDaoImpl implements DeckIterationDao {
  private final String url;
  private final String username;
  private final String password;

  public DeckIterationDaoImpl(final String url, final String username, final String password) {
    this.url = url;
    this.username = username;
    this.password = password;
    getConnection();
  }

  private Connection getConnection() {
    try {
      // TODO Register driver
      return DriverManager.getConnection(url, username, password);
    } catch (final SQLException ex) {
      throw new RuntimeException("Unable to connect to database", ex);
    }
  }

  @Override
  public DeckIteration[] getDeckIteration(final Long id) throws SQLException {
    final Connection connection = getConnection();
    connection.createStatement();
    return null;
  }

  @Override
  public DeckIteration[] getDeckIterations(final Long[] ids) {
    return null;
  }

  @Override
  public void deleteIterations(final DeckIteration[] iterations) {}

  @Override
  public Long addIteration(final DeckIteration previousIteration, final DeckIteration newIteration)
      throws SQLException {
    return null;
  }
}
