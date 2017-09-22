package breder.ide.plugin.impl;

import breder.ide.plugin.IMenu;
import breder.ide.plugin.IMenuItem;


public class MenuItem implements IMenuItem {

	private final String name;

	private final IMenu menu;

	public MenuItem(IMenu menu, String name) {
		super();
		this.menu = menu;
		this.name = name;
	}

	@Override
	public IMenu getMenu() {
		return this.menu;
	}

	@Override
	public String getName() {
		return this.name;
	}

}
