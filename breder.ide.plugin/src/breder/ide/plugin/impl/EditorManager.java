package breder.ide.plugin.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import breder.ide.plugin.IEditorManager;
import breder.io.IFile;
import breder.util.util.InputStreamUtil;

public class EditorManager implements IEditorManager {

	public static final EditorManager DEFAULT = new EditorManager();

	private Map<String, byte[]> bytes = new HashMap<String, byte[]>();

	@Override
	public synchronized void open(IFile file) {
		try {
			byte[] bytes = InputStreamUtil.getBytes(file.getInputStream());
			this.bytes.put(file.getAbsoluteName(), bytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
