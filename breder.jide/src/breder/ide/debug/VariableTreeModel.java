package breder.ide.debug;

import breder.util.swing.tree.BTreeModel;

public class VariableTreeModel extends BTreeModel {
	
	public VariableTreeModel() {
		super(new VariableRootNode());
	}
	
}
