package ui.tree;

public abstract class StaticTreeParent extends TreeFolder {

	@Override
	public void addChild(TreeData child) {
		throw new IllegalStateException("Diret�rio imut�vel");
	}

	@Override
	public void removeChild(TreeData child) {
		throw new IllegalStateException("Diret�rio imut�vel");
	}

}
