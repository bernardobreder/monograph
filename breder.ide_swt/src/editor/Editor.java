package editor;

import org.eclipse.swt.events.KeyAdapter;

import component.Component;

import editor.scanner.Scanner;

public abstract class Editor extends Component {

	/** Scanner para a colora��o */
	protected Scanner scanner;
	/** Componente gr�fico da estrutura */
	private EditorUI ui;

	/**
	 * Construtor padr�o
	 */
	public Editor() {
		super();
		this.ui = new EditorUI(this);
	}
	
	public abstract Editor newInstance();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EditorUI getUi() {
		return ui;
	}

	public Scanner getScanner() {
		return scanner;
	}

	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
		this.scanner.setEditor(this);
	}

	class Key extends KeyAdapter {

	}

	public void open(byte[] bytes) {
		ui.getText().setText(new String(bytes));
	}

}
