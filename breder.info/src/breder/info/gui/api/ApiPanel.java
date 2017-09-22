package breder.info.gui.api;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ApiPanel extends JPanel {

	public ApiPanel() {
		this.setLayout(new BorderLayout());
		this.add(new JScrollPane(new ApiTree()), BorderLayout.CENTER);
	}

}
