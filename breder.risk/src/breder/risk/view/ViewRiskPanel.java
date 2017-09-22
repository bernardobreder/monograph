package breder.risk.view;

import java.awt.Dimension;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import breder.risk.model.IRisk;
import breder.risk.model.RiskEnum;
import breder.util.swing.BLabelComboBox;
import breder.util.swing.BLabelTextField;
import breder.util.swing.layout.VerticalLayout;

public class ViewRiskPanel extends JPanel {

	private BLabelTextField name;

	private BLabelComboBox category;

	private JTextArea solution;

	private JTextArea describer;

	public ViewRiskPanel() {
		this("", null, "", "");
	}

	public ViewRiskPanel(IRisk risk) {
		this(risk.getName(), risk.getCategory(), risk.getDescriber(), risk
				.getSolution());
	}

	public ViewRiskPanel(String name, RiskEnum category, String describer,
			String solution) {
		this.setLayout(new VerticalLayout(10));
		this.add(this.name = new BLabelTextField("Nome : ", name));
		this.add(this.category = new BLabelComboBox("Impacto : ",
				new DefaultComboBoxModel(new Object[] {
						RiskEnum.CRITICAL.toString(),
						RiskEnum.MODERAL.toString(),
						RiskEnum.NORMAL.toString(), RiskEnum.LIGHT.toString(),
						RiskEnum.ZERO.toString() })));
		this.category.getField().setSelectedIndex(
				category == null ? 0 : category.ordinal());
		{
			JScrollPane scroll;
			this.add(scroll = new JScrollPane(this.describer = new JTextArea(
					describer)));
			this.describer.setWrapStyleWord(true);
			scroll.setPreferredSize(new Dimension(100, 300));
			scroll.setBorder(new TitledBorder("Descrição : "));
		}
		{
			JScrollPane scroll;
			this.add(scroll = new JScrollPane(this.solution = new JTextArea(
					solution)));
			this.describer.setWrapStyleWord(true);
			scroll.setPreferredSize(new Dimension(100, 300));
			scroll.setBorder(new TitledBorder("Solução : "));
		}
	}

	public JTextField getNameField() {
		return name.getField();
	}

	public JComboBox getCategory() {
		return category.getField();
	}

	public JTextArea getSolution() {
		return solution;
	}

	public JTextArea getDescriber() {
		return describer;
	}

}