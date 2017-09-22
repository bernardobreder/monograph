package frame;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * Janela gen�rica
 * 
 * 
 * @author Tecgraf
 */
public abstract class GenericShell<E extends GenericFrameInterface> {
	
	/** Display padr�o para todas as janelas */
	protected static final Display display = new Display();
	
	/** Shell da janela corrente */
	protected final Shell shell = new Shell(display);
	
	/** Shell principal */
	protected static Shell mainShell;
	
	/** A��es da interface gr�fica */
	protected E action;
	
	/**
	 * Construtor padr�o
	 * 
	 * @param action
	 */
	public GenericShell(E action) {
		super();
		this.action = action;
		this.action.setFrame(this);
	}
	
	/**
	 * Inicializa a janela
	 */
	public void open() {
		shell.open();
		if (mainShell == null) {
			mainShell = shell;
			while (!mainShell.isDisposed()) {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			}
		}
	}
	
	/**
	 * Retorna o Display do aplicativo
	 * 
	 * @return display do aplicativo
	 */
	public static Display getDisplay() {
		return display;
	}
	
	/**
	 * Retorna o shell da janela
	 * 
	 * @return shell da janela
	 */
	public Shell getShell() {
		return shell;
	}
	
}
