package editors.java;

import editor.Editor;

/**
 * Editor de código java.
 * 
 * 
 * @author Tecgraf
 */
public class JavaEditor extends Editor {

	/**
	 * Construtor padrão
	 */
	public JavaEditor() {
		super.setScanner(new JavaScanner(this));
	}

	@Override
	public Editor newInstance() {
		return new JavaEditor();
	}

}
