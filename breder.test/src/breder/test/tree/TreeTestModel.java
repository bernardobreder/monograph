package breder.test.tree;

import java.util.List;

import breder.test.model.ITest;
import breder.test.model.TestModel;
import breder.util.swing.tree.AbstractTreeNode;
import breder.util.swing.tree.DirectoryTreeNode;

public class TreeTestModel extends DirectoryTreeNode {

	public TreeTestModel() {
		super(null);
	}

	@Override
	public AbstractTreeNode[] getChildren() {
		List<ITest> tests = TestModel.getInstance().getTests();
		TestTreeNode[] children = new TestTreeNode[tests.size()];
		for (int n = 0; n < tests.size(); n++) {
			children[n] = new TestTreeNode(this, tests.get(n));
		}
		return children;
	}

	@Override
	public String toString() {
		return "";
	}

}
