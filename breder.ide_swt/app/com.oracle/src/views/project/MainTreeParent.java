package views.project;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ui.tree.RootTreeParent;
import ui.tree.TreeData;

public class MainTreeParent extends RootTreeParent<ProjectTreeNode> {

  /**
   * {@inheritDoc}
   */
  @Override
  public TreeData<ProjectTreeNode>[] getChildren() {
    List<TreeData<ProjectTreeNode>> list =
      new ArrayList<TreeData<ProjectTreeNode>>();
    try {
      list.add(new LocalConnectionTreeParent("bbreder", "bbreder"));
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    return list.toArray(new TreeData[0]);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getName() {
    return "Schema";
  }

}
