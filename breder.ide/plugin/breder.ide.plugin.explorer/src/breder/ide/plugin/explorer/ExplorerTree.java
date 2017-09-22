package breder.ide.plugin.explorer;

import javax.swing.JPopupMenu;

import breder.ide.plugin.IEditorManager;
import breder.ide.plugin.explorer.node.FileNode;
import breder.io.IFile;
import breder.util.swing.table.IOpenCellListener;
import breder.util.swing.tree.AbstractTreeNode;
import breder.util.swing.tree.BTree;

/**
 * Arquivote de Explorer
 * 
 * 
 * @author Bernardo Breder
 */
public class ExplorerTree extends BTree {

  /**
   * Construtor
   */
  public ExplorerTree() {
    super(new ExplorerModel());
    this.add(new IOpenCellListener<AbstractTreeNode>() {
      @Override
      public JPopupMenu getPopupMenu(int row, AbstractTreeNode cell) {
        // TODO Auto-generated method stub
        return null;
      }

      @Override
      public void actionPerformed(int row, AbstractTreeNode cell) {
        onOpenAction();
      }
    });
  }

  /**
   * Ação de abrir um arquivo
   */
  protected void onOpenAction() {
    AbstractTreeNode selectNode = this.getSelectNode();
    if (selectNode instanceof FileNode) {
      FileNode node = (FileNode) selectNode;
      IFile file = node.getFile();
      IEditorManager.DEFAULT.open(file);
    }
  }

}
