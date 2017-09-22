package breder.ide.task;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;

import breder.ide.IdeEditorPane;
import breder.ide.MainFrame;
import breder.util.util.InputStreamUtil;

public class SaveTask extends GenericTask {
	
	public SaveTask() {
		super("Salvando o Arquivo");
	}
	
	@Override
	public void perform() throws Throwable {
		IdeEditorPane pane = MainFrame.getInstance().getTextField();
		if (pane == null)
			return;
		File file = pane.getFile();
		FileOutputStream output = new FileOutputStream(file);
		ByteArrayInputStream input = new ByteArrayInputStream(pane.getText().getBytes());
		InputStreamUtil.copyStream(input, output);
		new BuildTask(pane.getProjectNode().getProject()).start();
	}
	
	@Override
	public void updateUI() throws Throwable {
	}
	
}
