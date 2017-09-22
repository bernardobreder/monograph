package views.project;

import java.sql.SQLException;

public class LocalConnectionTreeParent extends ConnectionTreeParent {

  public LocalConnectionTreeParent(String username, String password)
    throws SQLException {
    super("localhost:1521:xe", username, password);
  }

}
