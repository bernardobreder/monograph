package breder.processor.gui;

import javax.swing.SwingUtilities;

import breder.util.lookandfeel.LookAndFeel;

public class Main {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				LookAndFeel.getInstance().installNimbus();
				MainFrame.getInstance().setVisible(true);
			}
		});
	}
}
