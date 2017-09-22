package breder.ide.plugin.explorer.node;

import breder.io.IFolder;
import breder.util.swing.tree.AbstractTreeNode;

/**
 * Node de projeto
 * 
 * 
 * @author Bernardo Breder
 */
public class ProjectNode extends FolderNode {

  /**
   * Construtor
   * 
   * @param parent
   * @param folder
   */
  public ProjectNode(AbstractTreeNode parent, IFolder folder) {
    super(parent, folder);
  }

}
