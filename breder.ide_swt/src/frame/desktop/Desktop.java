package frame.desktop;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import component.Component;
import component.ComponentAlign;

import engine.IDE;
import frame.GenericShell;

public class Desktop extends GenericShell<DesktopInterface> {
	
	protected SashForm mainForm;
	
	protected ToolBar toolbar;
	
	protected TabFolder leftTab;
	
	protected TabFolder centerTab;
	
	protected TabFolder rightTab;
	
	/**
	 * Construtor padr�o
	 * 
	 * @param action
	 */
	public Desktop(DesktopInterface action) {
		super(action);
		this.build();
		action.setToolBar(toolbar);
		shell.setMaximized(true);
		IDE.getInstance().setDesktop(this);
		action.onInitWindowAction();
		this.open();
	}
	
	private void build() {
		shell.setLayout(new GridLayout());
		buildMenu();
		buildToolBar();
		buildEditor();
	}
	
	private void buildMenu() {
		Menu menu = new Menu(shell, SWT.BAR);
		buildMenuFile(menu);
		buildMenuEdit(menu);
		shell.setMenuBar(menu);
	}
	
	private void buildMenuSeparator(Menu menu) {
		new MenuItem(menu, SWT.SEPARATOR);
	}
	
	private void buildMenuFile(Menu menubar) {
		Menu menu = new Menu(shell, SWT.DROP_DOWN);
		MenuItem item = new MenuItem(menubar, SWT.CASCADE);
		item.setText("&File");
		buildMenuFileNew(menu);
		buildMenuSeparator(menu);
		buildMenuFileQuit(menu);
		item.setMenu(menu);
	}
	
	private void buildMenuFileNew(Menu menu) {
		MenuItem item = new MenuItem(menu, SWT.PUSH);
		item.setText("&New File\tCtrl+N");
		item.setAccelerator(SWT.CTRL + 'N');
		item.addListener(SWT.Selection, new Listener() {
			
			@Override
			public void handleEvent(Event arg0) {
				action.onNewFileAction();
			}
			
		});
	}
	
	private void buildMenuFileQuit(Menu menu) {
		MenuItem item = new MenuItem(menu, SWT.PUSH);
		item.setText("&Quit\tCtrl+Q");
		item.setAccelerator(SWT.CTRL + 'Q');
		item.addListener(SWT.Selection, new Listener() {
			
			@Override
			public void handleEvent(Event arg0) {
				action.onQuitAction();
			}
			
		});
	}
	
	private void buildMenuEdit(Menu menubar) {
		new Menu(shell, SWT.DROP_DOWN);
		MenuItem item = new MenuItem(menubar, SWT.CASCADE);
		item.setText("&Edit");
	}
	
	private void buildToolBar() {
		toolbar = new ToolBar(shell, SWT.FLAT | SWT.TOP);
		buildToolItemNew(toolbar);
		toolbar.pack();
	}
	
	private void buildToolItemNew(ToolBar toolbar) {
		ToolItem item = new ToolItem(toolbar, SWT.PUSH);
		item.setText("New");
		item.addListener(SWT.Selection, new Listener() {
			
			@Override
			public void handleEvent(Event arg0) {
				action.onNewFileAction();
			}
			
		});
	}
	
	private void buildEditor() {
		mainForm = new SashForm(shell, SWT.HORIZONTAL);
		{
			leftTab = new TabFolder(mainForm, SWT.LEFT);
		}
		{
			centerTab = new TabFolder(mainForm, SWT.LEFT);
			// Text text = new Text(mainForm, SWT.MULTI | SWT.V_SCROLL |
			// SWT.H_SCROLL);
		}
		{
			rightTab = new TabFolder(mainForm, SWT.RIGHT);
		}
		mainForm.setLayoutData(new GridData(GridData.FILL_BOTH));
	}
	
	public ToolBar getToolbar() {
		return toolbar;
	}
	
	public TabFolder getLeftTab() {
		return leftTab;
	}
	
	public TabFolder getRightTab() {
		return rightTab;
	}
	
	public TabFolder getCenterTab() {
		return centerTab;
	}
	
	public TabItem addComponent(Component component) {
		ComponentAlign align = component.getUi().getAlign();
		TabItem item = null;
		if (align == ComponentAlign.LEFT) {
			item = new TabItem(this.getLeftTab(), SWT.NONE);
		} else if (align == ComponentAlign.CENTER) {
			item = new TabItem(this.getCenterTab(), SWT.NONE);
		} else if (align == ComponentAlign.RIGHT) {
			item = new TabItem(this.getRightTab(), SWT.NONE);
		} else
			throw new IllegalArgumentException();
		return item;
	}
	
}
