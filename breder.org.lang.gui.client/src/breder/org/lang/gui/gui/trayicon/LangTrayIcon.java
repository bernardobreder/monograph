package breder.org.lang.gui.gui.trayicon;

import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.TrayIcon;

import breder.org.lang.gui.resource.LangResource;

public class LangTrayIcon extends TrayIcon {

	public LangTrayIcon() {
		super(LangResource.getInstance().getLogo().getImage(),
				"Breder Language", new InfoMarkaPopupMenu());
	}

	public static class InfoMarkaPopupMenu extends PopupMenu {

		public InfoMarkaPopupMenu() {
			{
				MenuItem item = new MenuItem("Logout");
				item.addActionListener(new LogoutTask());
				this.add(item);
			}
			this.addSeparator();
			{
				MenuItem item = new MenuItem("Document");
				item.addActionListener(new DocumentTask());
				this.add(item);
			}
			this.addSeparator();
			{
				MenuItem item = new MenuItem("Quit");
				item.addActionListener(new QuitTask());
				this.add(item);
			}
		}
	}

}
