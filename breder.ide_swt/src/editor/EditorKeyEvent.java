package editor;

import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;

public class EditorKeyEvent extends KeyAdapter {
	
	private final Editor editor;
	
	public EditorKeyEvent(Editor editor) {
		super();
		this.editor = editor;
		this.editor.getUi().getText().addKeyListener(this);
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		String str = editor.getUi().getText().getText();
		int length = str.length();
		editor.getScanner().build(0, length);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
	}
	
}
