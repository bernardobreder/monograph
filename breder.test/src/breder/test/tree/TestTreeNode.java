package breder.test.tree;

import javax.swing.Icon;

import breder.test.model.ITest;
import breder.test.resource.BrederTestResource;
import breder.util.swing.tree.AbstractTreeNode;
import breder.util.swing.tree.FileTreeNode;

public class TestTreeNode extends FileTreeNode {

	private ITest test;

	public TestTreeNode(AbstractTreeNode parent, ITest test) {
		super(parent);
		this.test = test;
	}

	@Override
	public Icon getIcon() {
		if (!this.test.isEnabled()) {
			return BrederTestResource.getInstance().getShieldImage();
		}
		Boolean ok = this.test.getOk();
		if (ok == null) {
			return null;
		} else if (ok == true) {
			return BrederTestResource.getInstance().getTrueImage();
		} else {
			return BrederTestResource.getInstance().getFalseImage();
		}
	}

	@Override
	public String toString() {
		return test.getName();
	}

	public ITest getTest() {
		return test;
	}

}
