package breder.org.lang.gui.gui.desktop;


import javax.swing.JMenu;

import breder.org.lang.gui.task.FinishProgramTask;
import breder.org.lang.gui.task.LogoutTask;
import breder.util.swing.BMenuItem;

public class GeneralMenu extends JMenu {

	public GeneralMenu() {
		super("Geral");
		this.add(new BMenuItem("Trocar de Usuário", new LogoutTask(), "ctrl Q"));
		this.addSeparator();
		this.add(new BMenuItem("Sair", new FinishProgramTask(true),
				"ctrl shift Q"));
	}

}
