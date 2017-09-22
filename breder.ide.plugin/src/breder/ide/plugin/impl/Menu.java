package breder.ide.plugin.impl;

import breder.ide.plugin.IMenu;


public class Menu implements IMenu {

	private final IMenu parent;

	private final String name;

	public Menu(String name) {
		this(null, name);
	}

	public Menu(IMenu parent, String name) {
		this.parent = parent;
		this.name = name;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public IMenu getParent() {
		return this.parent;
	}

}
