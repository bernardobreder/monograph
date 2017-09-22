package views.project;

import java.io.FileNotFoundException;

import ui.tree.ITreeNodeAction;
import breder.io.BFile;
import editor.Editor;
import editor.action.OpenEditorAction;
import engine.IDE;

public class JavaTreeFile extends FileTreeFile implements ITreeNodeAction {
	
	public JavaTreeFile(BFile file) {
		super(file);
	}
	
	@Override
	public String toString() {
		String name = this.getFile().getName();
		return name.substring(0, name.length() - ".java".length());
	}
	
	@Override
	public void open() {
		Editor editor = IDE.getInstance().getWorkspace().getEditor().newInstance();
		try {
			new OpenEditorAction(editor, this.getFile().getAbsoluteName(), this.toString(), this
					.getFile().getInputStream()).start();
		} catch (FileNotFoundException e) {
		}
	}
}
