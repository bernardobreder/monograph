import javax.swing.SwingUtilities;

import breder.util.lookandfeel.LookAndFeel;
import frame.BrederProfileFrame;

/**
 * Classe inicializadora
 * 
 * @author Bernardo Breder
 * 
 */
public class BrederProfile {
	
	/**
	 * M�todo inicializador
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				LookAndFeel.getInstance().installNimbus();
				BrederProfileFrame.getInstance().setVisible(true);
			}
		});
	}
	
}
