package breder.ide.debug;

import java.util.ArrayList;
import java.util.List;

import breder.ide.lang.c.GdbProcess;
import breder.util.swing.tree.AbstractTreeNode;
import breder.util.swing.tree.DirectoryTreeNode;
import breder.util.swing.tree.SimpleAbstractTreeNode;

public class VariableRootNode extends DirectoryTreeNode {
	
	public VariableRootNode() {
		super(null);
	}
	
	@Override
	public AbstractTreeNode[] getChildren() {
		List<GdbProcess> processes = DebugManager.getInstance().getProcesses();
		if (processes.size() == 0) {
			return new AbstractTreeNode[0];
		}
		List<AbstractTreeNode> list = new ArrayList<AbstractTreeNode>();
		GdbProcess process = processes.get(0);
		for (String key : process.getVariables().keySet()) {
			String value = process.getVariables().get(key);
			if (value.startsWith("(")) {
				int index = value.indexOf(')');
				String type = value.substring(1, index).trim();
				value = value.substring(index + 1).trim();
				list.add(new VariableParentNode(this, null, key, type, value));
			} else {
				list.add(new SimpleAbstractTreeNode(this, String.format("%s = %s", key, value)));
			}
		}
		return list.toArray(new AbstractTreeNode[0]);
	}
	
	@Override
	public String toString() {
		return "";
	}
	
}
