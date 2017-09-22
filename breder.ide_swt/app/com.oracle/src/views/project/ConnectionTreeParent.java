package views.project;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ui.tree.StaticTreeParent;
import ui.tree.TreeData;
import db.OracleConnection;

public class ConnectionTreeParent extends StaticTreeParent<ProjectTreeNode> {

  protected final String host;

  protected final String username;

  protected OracleConnection connection;

  public ConnectionTreeParent(String host, String username, String password)
    throws SQLException {
    super();
    this.host = host;
    this.username = username;
    this.connection = new OracleConnection(host, username, password);
  }

  @Override
  public TreeData[] getChildren() {
    List<TreeData<ProjectTreeNode>> list =
      new ArrayList<TreeData<ProjectTreeNode>>();

    return list.toArray(new TreeData[0]);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return "Local";
  }
}
