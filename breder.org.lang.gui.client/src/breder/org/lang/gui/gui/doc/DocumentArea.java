package breder.org.lang.gui.gui.doc;

import javax.swing.JTextArea;

public class DocumentArea extends JTextArea {

	private String original;

	@Override
	public void setText(String t) {
		super.setText(t);
		if (t.trim().length() == 0) {
			this.original = null;
		} else {
			this.original = t;
		}
	}

	public boolean isChange() {
		if (this.original == null) {
			return false;
		}
		return !this.getText().equals(this.original);
	}

}
