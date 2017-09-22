package breder.ide.plugin;

import breder.ide.plugin.impl.EditorManager;
import breder.io.IFile;

public interface IEditorManager {

	public static final IEditorManager DEFAULT = EditorManager.DEFAULT;

	public void open(IFile file);

}
