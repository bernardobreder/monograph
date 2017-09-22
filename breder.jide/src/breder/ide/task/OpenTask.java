package breder.ide.task;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.JTabbedPane;

import breder.ide.IdeEditorPane;
import breder.ide.MainFrame;
import breder.ide.projects.RootProjectNode;
import breder.util.swing.tree.AbstractTreeNode;
import breder.util.swing.tree.GenericFileTreeNode;
import breder.util.task.LocalTask;
import breder.util.util.InputStreamUtil;

public class OpenTask extends LocalTask {
	
	private final GenericFileTreeNode node;
	
	private Integer line;
	
	private IdeEditorPane pane;
	
	public OpenTask(GenericFileTreeNode node) {
		this.node = node;
	}
	
	public OpenTask(File file) {
		File appDir = new File("app");
		if (file.getAbsolutePath().startsWith(appDir.getAbsolutePath())) {
			AbstractTreeNode node = RootProjectNode.getInstance();
			String name = file.getAbsolutePath().substring(appDir.getAbsolutePath().length() + 1);
			while (true) {
				int index = name.indexOf(File.separatorChar);
				String dir = name;
				if (index != -1) {
					dir = name.substring(0, index);
					name = name.substring(index + 1);
				}
				boolean found = false;
				for (AbstractTreeNode child : node.getChildren()) {
					if (child.getName().equals(dir)) {
						node = child;
						found = true;
						break;
					}
				}
				if (!found) {
					throw new RuntimeException("File not found in the Tree");
				}
				if (index == -1)
					break;
			}
			this.node = (GenericFileTreeNode) node;
		} else {
			throw new RuntimeException("File not found in the Tree");
		}
	}
	
	@Override
	public void updateUI() throws Throwable {
		this.selectEditor();
		if (this.line != null) {
			this.selectLine();
		}
	}
	
	private void selectEditor() throws IOException {
		JTabbedPane tab = MainFrame.getInstance().getTabbedPane();
		for (int n = 0; n < tab.getTabCount(); n++) {
			this.pane = (IdeEditorPane) tab.getComponent(n);
			if (pane.getFile().equals(node.getFile())) {
				tab.setSelectedIndex(n);
				return;
			}
		}
		{
			String text = new String(InputStreamUtil.getBytes(new FileInputStream(node.getFile())));
			String title = node.getFile().getName();
			this.pane = new IdeEditorPane(node, text);
			tab.addTab(title, pane);
			tab.setSelectedIndex(tab.getComponentCount() - 1);
			return;
		}
	}
	
	private void selectLine() {
		this.pane.selectLine(this.getLine() - 1);
	}
	
	public Integer getLine() {
		return line;
	}
	
	public void setLine(Integer line) {
		this.line = line;
	}
	
}
