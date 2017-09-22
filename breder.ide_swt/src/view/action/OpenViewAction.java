package view.action;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabItem;

import util.TaskUI;
import view.View;
import engine.IDE;

public class OpenViewAction extends TaskUI {
	
	private final View view;
	
	public OpenViewAction(View view) {
		super();
		this.view = view;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateUI() {
		TabItem tabItem = IDE.getInstance().getDesktop().addComponent(view);
		tabItem.setText(view.getName());
		view.getUi().setTabItem(tabItem);
		{
			Composite c = new Composite(tabItem.getParent(), SWT.NONE);
			c.setLayout(new RowLayout());
			view.buildComponents(c);
			tabItem.setControl(c);
		}
	}
	
}
