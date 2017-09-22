package breder.info.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JTabbedPane;

import breder.info.gui.api.ApiPanel;
import breder.info.resource.BResource;
import breder.util.swing.BFrame;

public class MainFrame extends BFrame {

	public static final MainFrame instance = new MainFrame();

	private MainFrame() {
		super(null);
		this.setTitle("Breder Info");
		this.setSize(new Dimension(300, 600));
		this.setPreferredSize(this.getSize());
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.buildMenu();
		this.buildComponents();
		this.pack();
	}

	private void buildMenu() {
		JMenuBar bar = new JMenuBar();
		this.setJMenuBar(bar);
	}

	private void buildComponents() {
		this.setLayout(new BorderLayout());
		this.buildCenter();
		this.buildSouth();
	}

	private void buildSouth() {
		this.add(new TailPanel(), BorderLayout.SOUTH);
	}

	private void buildCenter() {
		JTabbedPane tab = new JTabbedPane();
		tab.addTab("News", BResource.NEWS_ICON, new NewsPanel());
		tab.addTab("Doc", BResource.DOC_ICON, new DocPanel());
		tab.addTab("", BResource.API_ICON, new ApiPanel());
		tab.addTab("Bni", BResource.BNI_ICON, new BniPanel());
		tab.addTab("Down", BResource.DOWN_ICON, new DownloadPanel());
		this.add(tab, BorderLayout.CENTER);
	}

	public static MainFrame getInstance() {
		return instance;
	}

}
