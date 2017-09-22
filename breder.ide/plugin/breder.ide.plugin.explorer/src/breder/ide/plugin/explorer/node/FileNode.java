package breder.ide.plugin.explorer.node;

import breder.io.IFile;
import breder.util.swing.tree.AbstractTreeNode;
import breder.util.swing.tree.FileTreeNode;

/**
 * Nó de arquivo
 * 
 * 
 * @author Bernardo Breder
 */
public class FileNode extends FileTreeNode {

  /** Arquivo */
  private final IFile file;

  /**
   * Construtor
   * 
   * @param parent
   * @param file
   */
  public FileNode(AbstractTreeNode parent, IFile file) {
    super(parent);
    this.file = file;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return this.file.getName();
  }

  /**
   * Retorna o arquivo
   * 
   * @return arquivo
   */
  public IFile getFile() {
    return file;
  }

}
