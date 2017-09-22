package breder.ide.plugin.explorer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import breder.ide.plugin.explorer.io.IdeFileSystem;
import breder.ide.plugin.explorer.node.FolderNode;
import breder.io.IFolder;
import breder.io.IResource;
import breder.util.swing.tree.AbstractTreeNode;
import breder.util.swing.tree.DirectoryTreeNode;

/**
 * Modelo do Explorer
 * 
 * 
 * @author Bernardo Breder
 */
public class ExplorerModel extends DirectoryTreeNode {

  /**
   * Construtor
   */
  public ExplorerModel() {
    super(null);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractTreeNode[] getChildren() {
    IResource[] resources;
    try {
      resources = IdeFileSystem.getDefault().list();
    }
    catch (IOException e) {
      return new AbstractTreeNode[0];
    }
    List<AbstractTreeNode> result =
      new ArrayList<AbstractTreeNode>(resources.length);
    for (int n = 0; n < resources.length; n++) {
      IResource resource = resources[n];
      if (!resource.isHidden()) {
        if (resource.isFolder()) {
          result.add(new FolderNode(this, (IFolder) resource));
        }
      }
    }
    return result.toArray(new AbstractTreeNode[result.size()]);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return "";
  }

}
