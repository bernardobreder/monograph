package breder.risk.create;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import breder.risk.view.ViewRiskPanel;
import breder.util.swing.BAction;
import breder.util.swing.BFrame;
import breder.util.swing.BLabelTextField;
import breder.util.task.FinishFrameTask;

public class CreateFrame extends BFrame {

	private JButton compileButton;

	private ViewRiskPanel panel;

	private BLabelTextField nameField;

	private JButton build;

	public CreateFrame(BFrame parent) {
		super(parent);
		this.setTitle("Breder Risk - Create Risk");
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
		panel.add(this.panel = new ViewRiskPanel(), BorderLayout.CENTER);
		return panel;
	}

	private Component buildSouth() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panel.add(this.build = new JButton(new BAction("Build",
				new BuildRiskTask(this))));
		panel.add(new JButton(new BAction("Close", new FinishFrameTask(this))));
		this.getRootPane().setDefaultButton(this.build);
		return panel;
	}

	public ViewRiskPanel getPanel() {
		return panel;
	}

}
