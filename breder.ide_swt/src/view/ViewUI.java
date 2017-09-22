package view;

import view.action.OpenViewAction;

import component.ComponentAlign;
import component.ComponentUI;

public class ViewUI extends ComponentUI {

	public ViewUI(View view, ComponentAlign align) {
		super(view, align);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void open() {
		new OpenViewAction(this.getComponent()).start();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public View getComponent() {
		return (View) super.getComponent();
	}

}
