package editors.java;

import editor.Editor;

/**
 * Editor de código java.
 * 
 * 
 * @author Tecgraf
 */
public class SqlEditor extends Editor {

  /**
   * Construtor padrão
   */
  public SqlEditor() {
    super.setScanner(new SqlScanner());
  }

}
