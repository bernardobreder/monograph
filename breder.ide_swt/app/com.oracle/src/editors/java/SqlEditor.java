package editors.java;

import editor.Editor;

/**
 * Editor de c�digo java.
 * 
 * 
 * @author Tecgraf
 */
public class SqlEditor extends Editor {

  /**
   * Construtor padr�o
   */
  public SqlEditor() {
    super.setScanner(new SqlScanner());
  }

}
