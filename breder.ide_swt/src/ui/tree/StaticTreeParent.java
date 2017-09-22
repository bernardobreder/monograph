package ui.tree;

public abstract class StaticTreeParent extends TreeFolder {

	@Override
	public void addChild(TreeData child) {
		throw new IllegalStateException("Diretório imutável");
	}

	@Override
	public void removeChild(TreeData child) {
		throw new IllegalStateException("Diretório imutável");
	}

}
