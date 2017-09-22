package editor.action;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabItem;

import util.InputStreamUtil;
import util.Task;
import editor.Editor;
import editor.EditorKeyEvent;
import engine.IDE;
import frame.GenericShell;

public class OpenEditorAction extends Task {
	
	private final Editor editor;
	
	private final InputStream input;
	
	private String id;
	
	private String title;
	
	private String content;
	
	private static final Map<String, Editor> OPENED = new HashMap<String, Editor>();
	
	public OpenEditorAction(Editor editor, String id, String title, InputStream input) {
		super();
		this.editor = editor;
		this.id = id;
		this.input = input;
		this.title = title;
	}
	
	@Override
	public void action() throws Throwable {
		Editor editor = OPENED.get(id);
		if (editor != null) {
			GenericShell.getDisplay().asyncExec(new Runnable() {
				
				@Override
				public void run() {
					Editor editor = OPENED.get(id);
					gotFocus(editor);
				}
				
			});
			this.interrupt();
		} else {
			OPENED.put(id, this.editor);
			this.content = new String(InputStreamUtil.getBytes(input));
		}
	}
	
	@Override
	public void updateUI() {
		TabItem tabItem = IDE.getInstance().getDesktop().addComponent(editor);
		tabItem.setData(id);
		tabItem.setText(title);
		editor.getUi().setTabItem(tabItem);
		{
			Composite c = new Composite(tabItem.getParent(), SWT.NONE);
			c.setLayout(new FillLayout());
			editor.getUi().setText(new StyledText(c, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL));
			editor.getUi().getText().setText(content);
			new EditorKeyEvent(editor);
			tabItem.setControl(c);
		}
		this.gotFocus(editor);
	}
	
	protected void gotFocus(Editor editor) {
		TabItem tab = editor.getUi().getTabItem();
		tab.getParent().setSelection(tab);
		editor.getUi().getText().forceFocus();
	}
	
}
