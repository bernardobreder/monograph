package editors.java;

import editor.Editor;

/**
 * Editor de c�digo java.
 * 
 * 
 * @author Tecgraf
 */
public class JavaEditor extends Editor {

	/**
	 * Construtor padr�o
	 */
	public JavaEditor() {
		super.setScanner(new JavaScanner(this));
	}

	@Override
	public Editor newInstance() {
		return new JavaEditor();
	}

}
