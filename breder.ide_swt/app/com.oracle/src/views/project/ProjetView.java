package views.project;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;

import ui.tree.TreeModel;
import view.View;

import component.ComponentAlign;

public class ProjetView extends View {

  protected TreeModel<ProjectTreeNode> model;

  public ProjetView() {
    super(ComponentAlign.LEFT);
    super.setName("Schema");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void buildComponents(Composite c) {
    c.setLayout(new FillLayout());
    MainTreeParent root = new MainTreeParent();
    model = new TreeModel<ProjectTreeNode>(new Tree(c, SWT.NONE), root);
  }

}
