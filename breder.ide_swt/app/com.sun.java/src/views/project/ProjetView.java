package views.project;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;

import ui.tree.TreeModel;
import view.View;
import breder.io.BDirectory;
import breder.io.BResource;
import breder.io.BResourceManager;

import component.ComponentAlign;

public class ProjetView extends View {
	
	protected TreeModel model;
	
	public ProjetView() {
		super(ComponentAlign.LEFT);
		this.setName("Project");
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void buildComponents(Composite c) {
		c.setLayout(new FillLayout());
		BResource resource = BResourceManager.getInstance().get("prj");
		if (resource.isDirectory()) {
			BDirectory directory = (BDirectory) resource;
			ProjectsTreeParent root = new ProjectsTreeParent(directory);
			model = new TreeModel(new Tree(c, SWT.NONE), root);
		}
	}
	
}
