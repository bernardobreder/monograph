package breder.ide.gui;

import breder.ide.plugin.IView;
import breder.ide.view.ViewManager;
import breder.util.swing.tree.AbstractTreeNode;
import breder.util.swing.tree.DirectoryTreeNode;

/**
 * Modelo da visão
 * 
 * 
 * @author Bernardo Breder
 */
public class ViewModel extends DirectoryTreeNode {

  /**
   * Construtor
   */
  public ViewModel() {
    super(null);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractTreeNode[] getChildren() {
    IView[] gets = ViewManager.getDefault().gets();
    AbstractTreeNode[] result = new AbstractTreeNode[gets.length];
    for (int n = 0; n < gets.length; n++) {
      result[n] = new ViewNode(this, gets[n]);
    }
    return result;
  }

  /**
   * Modelo da visão {@inheritDoc}
   */
  @Override
  public String toString() {
    return "";
  }

}
