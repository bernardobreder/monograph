package breder.processor.gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

public class RowList extends JScrollPane {

	private Object[] values;

	public RowList(Object[] values) {
		this.values = values;
		this.setViewportView(new JList(values));
		this.setRowHeaderView(new List());
		this.setPreferredSize(new Dimension(this.getPreferredSize().width, 50));
	}

	private class List extends JList {

		public List() {
			super(new RowModel());
			this.setBackground(Color.LIGHT_GRAY);
			this.setSelectionBackground(this.getBackground());
			this.setSelectionForeground(this.getForeground());
		}

	}

	private class RowModel implements ListModel {

		@Override
		public void removeListDataListener(ListDataListener l) {
		}

		@Override
		public int getSize() {
			return values.length;
		}

		@Override
		public Object getElementAt(int index) {
			return "" + index;
		}

		@Override
		public void addListDataListener(ListDataListener l) {
		}

	}

}
