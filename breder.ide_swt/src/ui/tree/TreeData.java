package ui.tree;

import util.Task;

public class TreeData {

	private TreeFolder parent;

	private Task expandTask;

	public void setParent(TreeFolder parent) {
		this.parent = parent;
	}

	public TreeFolder getParent() {
		return parent;
	}

	public Object getAdapter(Class<?> key) {
		return null;
	}

	public Task getExpandTask() {
		return expandTask;
	}

	public void setExpandTask(Task expandTask) {
		this.expandTask = expandTask;
	}

}
