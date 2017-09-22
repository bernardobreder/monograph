package breder.risk.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import breder.risk.model.IRisk;
import breder.util.swing.BAction;
import breder.util.swing.BFrame;
import breder.util.task.FinishFrameTask;

public class ViewFrame extends BFrame {

	private ViewRiskPanel panel;

	private JButton build;

	private IRisk risk;

	public ViewFrame(BFrame parent, IRisk risk) {
		super(parent);
		this.risk = risk;
		this.setTitle("Breder Risk - View Risk");
		this.build();
		this.pack();
		this.setMinimumSize(this.getSize());
		this.setSize(800, 600);
		this.setLocationRelativeTo(parent);
	}

	private void build() {
		this.setLayout(new BorderLayout());
		this.add(this.buildCenter(), BorderLayout.CENTER);
		this.add(this.buildSouth(), BorderLayout.SOUTH);
	}

	private Component buildCenter() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(this.panel = new ViewRiskPanel(risk), BorderLayout.CENTER);
		return panel;
	}

	private Component buildSouth() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panel.add(this.build = new JButton(new BAction("Save",
				new UpdateRiskTask(this))));
		panel.add(new JButton(new BAction("Close", new FinishFrameTask(this))));
		this.getRootPane().setDefaultButton(this.build);
		return panel;
	}

	public ViewRiskPanel getPanel() {
		return panel;
	}

	public IRisk getRisk() {
		return risk;
	}

}
