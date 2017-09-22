package configurator;

import view.View;
import views.project.ProjetView;
import workspace.Workspace;
import editors.java.JavaEditor;

public class MyWorkspace extends Workspace {
	
	public MyWorkspace() {
		super.setName("Java");
		super.setEditor(new JavaEditor());
		super.setViews(new View[] { new ProjetView() });
	}
	
}
