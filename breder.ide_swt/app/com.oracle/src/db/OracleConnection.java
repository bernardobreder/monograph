package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleConnection {

  protected final Connection connection;

  public OracleConnection(String host, String username, String password)
    throws SQLException {
    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
    this.connection =
      DriverManager.getConnection("jdbc:oracle:thin:@" + host, username,
        password);
  }

  public Connection getConnection() {
    return connection;
  }

}
