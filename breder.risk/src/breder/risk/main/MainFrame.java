
package breder.risk.main;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import breder.risk.model.IRisk;
import breder.util.swing.BFrame;
import breder.util.swing.BPanel;
import breder.util.swing.GBC;

public class MainFrame extends BFrame {

	private static final MainFrame instance = new MainFrame();

	private JLabel statusLabel;

	private RiskList list;

	private RiskTopList topList;

	private MainFrame() {
		super(null);
		this.setTitle("Breder Risk - Main");
		this.setJMenuBar(new MainFrameMenu(this));
		this.build();
		this.pack();
		this.setMinimumSize(this.getSize());
		this.setSize(400, 600);
		this.setLocationRelativeTo(null);
		this.refresh();
	}

	private void build() {
		this.setLayout(new BorderLayout());
		this.add(this.buildCenter(), BorderLayout.CENTER);
		this.add(this.buildTip(), BorderLayout.SOUTH);
	}

	private Component buildTip() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel.add(this.statusLabel = new JLabel("..."));
		return panel;
	}

	private Component buildCenter() {
		JPanel panel = new JPanel(new GridBagLayout());
		panel.add(new BPanel("Top Risk").addComponent(this.buildTopList(), BorderLayout.CENTER),
				new GBC(0, 0).horizontal());
		panel.add(new BPanel("All Risk").addComponent(this.buildList(), BorderLayout.CENTER), new GBC(0, 1).both());
		return panel;
	}

	private Component buildTopList() {
		return new JScrollPane(this.topList = new RiskTopList());
	}

	private Component buildList() {
		return new JScrollPane(this.list = new RiskList());
	}

	public static MainFrame getInstance() {
		return instance;
	}

	public void openTest(IRisk test) {
	}

	public IRisk getRisk() {
		return (IRisk) this.list.getSelectedValue();
	}

	public void appendConsole(String text) {
	}

	public void cleanConsole() {
	}

	public void refresh() {
		this.list.refresh();
		this.topList.refresh();
	}

	public IRisk[] getTreeRisks() {
		List<IRisk> list = new ArrayList<IRisk>();
		for (int index : this.list.getSelectedIndices()) {
			list.add((IRisk) this.list.getModel().getElementAt(index));
		}
		return list.toArray(new IRisk[list.size()]);
	}

}
