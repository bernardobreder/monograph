package breder.ide.gui;

import breder.ide.plugin.IView;
import breder.util.swing.tree.AbstractTreeNode;
import breder.util.swing.tree.FileTreeNode;

/**
 * Node da Visão
 * 
 * 
 * @author Bernardo Breder
 */
public class ViewNode extends FileTreeNode {

  /** Visão */
  private final IView view;

  /**
   * Construtor
   * 
   * @param parent
   * @param view
   */
  public ViewNode(AbstractTreeNode parent, IView view) {
    super(parent);
    this.view = view;
  }

  /**
   * Retorna a visão
   * 
   * @return visão
   */
  public IView getView() {
    return this.view;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return this.view.getName();
  }

}
