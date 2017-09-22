package breder.processor.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import breder.processor.model.IArchModel;
import breder.processor.model.breder.ArchModel;
import breder.util.swing.BFrame;
import breder.util.swing.GBC;

public class MainFrame extends BFrame {

	private static final MainFrame instance = new MainFrame();

	private ArchPanel archPanel;

	private final IArchModel model = new ArchModel();

	private MainFrame() {
		super(null);
		this.setTitle("Processor");
		this.build();
		this.pack();
		this.setMinimumSize(this.getSize());
		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

	private void build() {
		this.setLayout(new BorderLayout());
		this.setJMenuBar(this.buildMenu());
		this.add(this.buildNorth(), BorderLayout.NORTH);
		this.add(this.buildCenter(), BorderLayout.CENTER);
		this.add(this.buildSouth(), BorderLayout.SOUTH);
		this.add(this.buildLeft(), BorderLayout.WEST);
		this.add(this.buildRight(), BorderLayout.EAST);
	}

	private JMenuBar buildMenu() {
		return new MainFrameMenu();
	}

	private Component buildNorth() {
		return new MainFrameTool();
	}

	private Component buildCenter() {
		this.archPanel = new ArchPanel(model);
		JScrollPane scroll = new JScrollPane(archPanel);
		scroll.setPreferredSize(new Dimension(320, 240));
		return scroll;
	}

	private Component buildSouth() {
		JPanel panel = new JPanel();
		return panel;
	}

	private Component buildLeft() {
		JPanel panel = new JPanel(new GridBagLayout());
		{
			JPanel p = new JPanel(new BorderLayout());
			p.setBorder(new TitledBorder("Program Memory"));
			p.add(new RowList(new String[] { "pushnill", "sjump 1", "half", "load 0", "load 1", "store 1", "load 1", "ojump 2", "not", "sum", "store 1", "ret 1", "try", "ttry", "sjump0", "ret 4" }));
			panel.add(p, new GBC(0, 0).both());
		}
		{
			JPanel p = new JPanel(new BorderLayout());
			p.setBorder(new TitledBorder("Object Stack"));
			p.add(new RowList(new Object[] { "0" }));
			panel.add(p, new GBC(0, 1).both());
		}
		{
			JPanel p = new JPanel(new BorderLayout());
			p.setBorder(new TitledBorder("Memory Stack"));
			p.add(new RowList(new Object[] { "0" }));
			panel.add(p, new GBC(0, 2).both());
		}
		return panel;
	}

	private Component buildRight() {
		JPanel panel = new JPanel(new GridBagLayout());
		{
			JPanel spanel = new JPanel(new GridBagLayout());
			{
				JPanel p = new JPanel(new BorderLayout());
				p.setBorder(new TitledBorder("Program Throw Stack"));
				p.add(new RowList(new Object[] { "0" }));
				spanel.add(p, new GBC(0, 0).both());
			}
			{
				JPanel p = new JPanel(new BorderLayout());
				p.setBorder(new TitledBorder("Object Throw Stack"));
				p.add(new RowList(new Object[] { "0" }));
				spanel.add(p, new GBC(1, 0).both());
			}
			{
				JPanel p = new JPanel(new BorderLayout());
				p.setBorder(new TitledBorder("Memory Throw Stack"));
				p.add(new RowList(new Object[] { "0" }));
				spanel.add(p, new GBC(2, 0).both());
			}
			panel.add(spanel, new GBC(0, 0).both());
		}
		{
			JPanel p = new JPanel(new BorderLayout());
			p.setBorder(new TitledBorder("Object"));
			p.add(new RowList(new Object[] { "0" }));
			panel.add(p, new GBC(0, 1).both());
		}
		{
			JPanel p = new JPanel(new BorderLayout());
			p.setBorder(new TitledBorder("Object Data"));
			p.add(new RowList(new Object[] { "0" }));
			panel.add(p, new GBC(0, 2).both());
		}
		{
			JPanel p = new JPanel(new BorderLayout());
			p.setBorder(new TitledBorder("Field Index Cache"));
			p.add(new RowList(new Object[] { "0" }));
			panel.add(p, new GBC(0, 3).both());
		}
		{
			JPanel p = new JPanel(new BorderLayout());
			p.setBorder(new TitledBorder("Method Index Cache"));
			p.add(new RowList(new Object[] { "0" }));
			panel.add(p, new GBC(0, 4).both());
		}
		return panel;
	}

	public IArchModel getModel() {
		return model;
	}

	public ArchPanel getArchPanel() {
		return archPanel;
	}

	public static MainFrame getInstance() {
		return instance;
	}

}
