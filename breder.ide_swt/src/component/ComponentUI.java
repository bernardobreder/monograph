package component;

import org.eclipse.swt.widgets.TabItem;

public abstract class ComponentUI {
	
	private final ComponentAlign align;
	
	private final Component component;
	
	private TabItem tabItem;
	
	public ComponentUI(Component component, ComponentAlign align) {
		super();
		this.component = component;
		this.align = align;
	}
	
	public abstract void open();
	
	public void close() {
		this.getTabItem().dispose();
	}
	
	public ComponentAlign getAlign() {
		return align;
	}
	
	public Component getComponent() {
		return component;
	}
	
	public TabItem getTabItem() {
		return tabItem;
	}
	
	public void setTabItem(TabItem tabItem) {
		this.tabItem = tabItem;
	}
	
}
