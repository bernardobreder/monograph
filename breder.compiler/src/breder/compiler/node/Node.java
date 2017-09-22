
package breder.compiler.node;

public abstract class Node implements INode {

	private INode parent;

	@Override
	public INode getParent() {
		return this.parent;
	}

	@Override
	public void setParent(INode parent) {
		this.parent = parent;
	}

}
