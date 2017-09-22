package breder.test.main;

import javax.swing.JTextArea;

public class ConsoleText extends JTextArea {

	public ConsoleText() {
		this.setEditable(false);
	}

	public void append(String text) {
		if (this.getText().length() == 0) {
			this.setText(text);
		} else {
			this.setText(this.getText() + "\n" + text);
		}
	}

	public void clean() {
		this.setText("");
	}

}
