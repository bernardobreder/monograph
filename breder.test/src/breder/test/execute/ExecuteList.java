package breder.test.execute;

import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListCellRenderer;
import javax.swing.border.TitledBorder;

import breder.util.swing.BList;
import breder.util.swing.GBC;
import breder.util.util.StringUtil;

public class ExecuteList extends BList<BVMOpcode> {

	public ExecuteList(BVMProcess process) {
		super(new DefaultListModel());
		this.setCellRenderer(new MyListCellRenderer());
		for (BVMOpcode opcode : process.getOpcodes()) {
			this.getModel().addElement(opcode);
		}
	}

	@Override
	public DefaultListModel getModel() {
		return (DefaultListModel) super.getModel();
	}

	private class MyListCellRenderer extends JPanel implements ListCellRenderer {

		private JTextArea stackTraceList;

		private JTextArea stackObjectList;

		private JTextArea consoleText;

		public MyListCellRenderer() {
			this.setLayout(new GridBagLayout());
			JPanel panel = new JPanel(new GridLayout(1, 2));
			panel.add(new JScrollPane(this.stackTraceList = new JTextArea()),
					new GBC(0, 0).both());
			panel.add(new JScrollPane(this.stackObjectList = new JTextArea()),
					new GBC(1, 0).both());
			this.add(panel, new GBC(0, 0).both());
			this.add(new JScrollPane(this.consoleText = new JTextArea()),
					new GBC(0, 1).horizontal());
		}

		@Override
		public Component getListCellRendererComponent(JList list, Object value,
				int index, boolean isSelected, boolean cellHasFocus) {
			BVMOpcode opcode = (BVMOpcode) value;
			this.setBorder(new TitledBorder(String.format("[%d] %s",
					opcode.getPc(), opcode.getOpcode())));
			this.stackTraceList.setText(StringUtil.joinLine(opcode
					.getStackTrace()));
			this.stackObjectList.setText(StringUtil.joinLine(opcode
					.getStackObject()));
			this.consoleText.setText(StringUtil.joinLine(opcode.getOutput()));
			return this;
		}
	}

}
