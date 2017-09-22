package configurator;

import java.sql.SQLException;

import view.View;
import views.project.ConnectionTreeParent;
import views.project.ProjetView;
import workspace.Workspace;
import editors.java.SqlEditor;

public class MyWorkspace extends Workspace {

  public MyWorkspace() {
    super.setName("Sql");
    super.setEditor(new SqlEditor());
    super.setViews(new View[] { new ProjetView() });
  }

  public static void main(String[] args) throws SQLException {
    ConnectionTreeParent parent =
      new ConnectionTreeParent("localhost:1521:xe", "bbreder", "bbreder");
  }

}
