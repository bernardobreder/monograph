package view;

import org.eclipse.swt.widgets.Composite;

import component.Component;
import component.ComponentAlign;

public abstract class View extends Component {
	
	private ViewUI ui;
	
	private String name;
	
	public View(ComponentAlign align) {
		this.ui = new ViewUI(this, align);
		this.name = "";
	}
	
	public abstract void buildComponents(Composite c);
	
	@Override
	public ViewUI getUi() {
		return ui;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
}
