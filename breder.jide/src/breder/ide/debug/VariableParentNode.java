package breder.ide.debug;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import breder.ide.lang.c.GdbProcess;
import breder.util.swing.tree.AbstractTreeNode;
import breder.util.swing.tree.DirectoryTreeNode;
import breder.util.swing.tree.SimpleAbstractTreeNode;

public class VariableParentNode extends DirectoryTreeNode {
	
	private String key;
	
	private VariableParentNode nparent;
	
	private String type;
	
	private String value;
	
	public VariableParentNode(AbstractTreeNode parent, VariableParentNode nparent, String key,
			String type, String value) {
		super(parent);
		this.nparent = nparent;
		this.key = key;
		this.type = type;
		this.value = value;
	}
	
	@Override
	public AbstractTreeNode[] getChildren() {
		List<GdbProcess> processes = DebugManager.getInstance().getProcesses();
		if (processes.size() == 0) {
			return new AbstractTreeNode[0];
		}
		List<AbstractTreeNode> list = new ArrayList<AbstractTreeNode>();
		GdbProcess process = processes.get(0);
		Map<String, String> map;
		try {
			map = process.getVariables(this.getReference());
		} catch (IOException e) {
			map = new HashMap<String, String>();
		}
		for (String key : map.keySet()) {
			String value = map.get(key);
			if (value.startsWith("0x")) {
				String type = "";
				try {
					String[] lines = DebugManager.getInstance().getProcess().println(
							"p " + this.getReference(key));
					String line0 = lines[0];
					int index = line0.indexOf('(');
					int index2 = line0.indexOf(')');
					type = line0.substring(index + 1, index2).trim();
					value = line0.substring(index2 + 1).trim();
				} catch (IOException e) {
					
				}
				list.add(new VariableParentNode(this, this, key, type, value));
			} else {
				list.add(new SimpleAbstractTreeNode(this, String.format("%s = %s", key, value)));
			}
		}
		return list.toArray(new AbstractTreeNode[0]);
	}
	
	public String getReference(String key) {
		return this.getReference() + "->" + key;
	}
	
	public String getReference() {
		if (this.nparent != null) {
			return nparent.getReference() + "->" + this.key;
		} else {
			return this.key;
		}
	}
	
	@Override
	public String toString() {
		return String.format("%s %s = %s", type, key, value);
	}
	
}
