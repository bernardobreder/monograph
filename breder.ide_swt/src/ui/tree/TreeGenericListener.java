package ui.tree;

public abstract class TreeGenericListener {
	
	private final TreeModel model;

	public TreeGenericListener(TreeModel model) {
		super();
		this.model = model;
	}

	public TreeModel getModel() {
		return model;
	}


}
