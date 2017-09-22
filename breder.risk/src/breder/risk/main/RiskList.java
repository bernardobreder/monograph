package breder.risk.main;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import breder.risk.model.IRisk;
import breder.risk.model.ModelLocator;
import breder.risk.view.ViewTask;
import breder.util.swing.BList;
import breder.util.swing.GBC;
import breder.util.swing.table.AbstractOpenCellListener;

public class RiskList extends BList<IRisk> {

	public RiskList() {
		super(new DefaultListModel());
		this.setCellRenderer(new MyListCellRenderer());
		this.addOpenCellListener(new AbstractOpenCellListener<IRisk>() {
			@Override
			public void actionPerformed(int row, IRisk cell) {
				new ViewTask(cell).start();
			}
		});
		this.refresh();
	}

	public void refresh() {
		this.getModel().removeAllElements();
		for (IRisk risk : this.accept(ModelLocator.getInstance().getRisks())) {
			this.getModel().addElement(risk);
		}
	}

	protected IRisk[] accept(IRisk[] risks) {
		return risks;
	}

	@Override
	public DefaultListModel getModel() {
		return (DefaultListModel) super.getModel();
	}

	private class MyListCellRenderer extends JPanel implements ListCellRenderer {

		private JLabel label;

		public MyListCellRenderer() {
			this.setLayout(new GridBagLayout());
			this.add(this.label = new JLabel("..."), new GBC(0, 0).horizontal());
		}

		@Override
		public Component getListCellRendererComponent(JList list, Object value,
				int index, boolean isSelected, boolean cellHasFocus) {
			IRisk risk = (IRisk) value;
			this.label.setText(risk.getName());
			this.label.setIcon(risk.getCategory().getIcon());
			this.setOpaque(isSelected);
			return this;
		}

	}

}
