package breder.ide.editor;

import java.util.HashMap;
import java.util.Map;

/**
 * Gerenciador de Editor
 * 
 * 
 * @author Bernardo Breder
 */
public class EditorManager {

  /** Instancia única */
  private static final EditorManager instance = new EditorManager();
  /** Editores */
  private Map<String, AbstractEditor> editors =
    new HashMap<String, AbstractEditor>();

  /**
   * Construtor
   */
  private EditorManager() {
  }

  /**
   * Adiciona um editor
   * 
   * @param extension
   * @param editor
   */
  public void addEditor(String extension, AbstractEditor editor) {
    if (!extension.startsWith(".")) {
      extension = "." + extension;
    }
    this.editors.put(extension, editor);
  }

  /**
   * Retorna o editor baseado no nome do arquivo
   * 
   * @param filename
   * @return editor
   */
  public AbstractEditor getEditor(String filename) {
    for (String key : this.editors.keySet()) {
      if (filename.endsWith(key)) {
        return this.editors.get(key);
      }
    }
    return null;
  }

  /**
   * Retorna
   * 
   * @return instance
   */
  public static EditorManager getInstance() {
    return instance;
  }

}
