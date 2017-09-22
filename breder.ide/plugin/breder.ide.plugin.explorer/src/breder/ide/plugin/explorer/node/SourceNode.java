package breder.ide.plugin.explorer.node;

import breder.io.IFolder;
import breder.util.swing.tree.AbstractTreeNode;

/**
 * Node de fonte
 * 
 * 
 * @author Bernardo Breder
 */
public class SourceNode extends FolderNode {

  /**
   * Construtor
   * 
   * @param parent
   * @param folder
   */
  public SourceNode(AbstractTreeNode parent, IFolder folder) {
    super(parent, folder);
  }

}
