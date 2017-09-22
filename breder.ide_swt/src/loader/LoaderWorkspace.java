package loader;

import java.io.File;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import util.Task;
import workspace.Workspace;
import engine.IDE;

public class LoaderWorkspace extends Task {

	protected final ApplicationClassLoader classloader;

	protected Workspace workspace;

	public LoaderWorkspace(LoaderApplication parent, File root) {
		super(parent);
		this.classloader = new ApplicationClassLoader(this.getClass()
				.getClassLoader(), root);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void action() throws Throwable {
		Class<?> configuratorClass = this.classloader
				.loadClass("configurator.MyWorkspace");
		workspace = (Workspace) configuratorClass.newInstance();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateUI() {
		ToolBar toolbar = this.getParent().getDesktop().getToolbar();
		ToolItem item = new ToolItem(toolbar, SWT.PUSH);
		item.setText(workspace.getName());
		item.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {
				IDE.getInstance().setWorkspace(workspace);
			}

		});
		this.getParent().getDesktop().getShell().layout();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public LoaderApplication getParent() {
		return (LoaderApplication) super.getParent();
	}

}
