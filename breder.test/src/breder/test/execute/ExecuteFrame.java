package breder.test.execute;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import breder.util.swing.BAction;
import breder.util.swing.BFrame;
import breder.util.task.FinishFrameTask;

public class ExecuteFrame extends BFrame {

	public ExecuteFrame(BFrame parent, BVMProcess process) {
		super(parent);
		this.setTitle("Breder Test - Execute Test");
		this.build(process);
		this.pack();
		this.setMinimumSize(this.getSize());
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setLocationRelativeTo(parent);
	}

	private void build(BVMProcess process) {
		this.setLayout(new BorderLayout());
		this.add(this.buildNorth(), BorderLayout.NORTH);
		this.add(this.buildCenter(process), BorderLayout.CENTER);
		this.add(this.buildSouth(), BorderLayout.SOUTH);
	}

	private Component buildNorth() {
		JPanel panel = new JPanel();
		return panel;
	}

	private Component buildCenter(BVMProcess process) {
		JPanel panel = new JPanel(new BorderLayout());
		JScrollPane scroll;
		panel.add(scroll = new JScrollPane(new ExecuteList(process)),
				BorderLayout.CENTER);
		scroll.setPreferredSize(new Dimension(800, 600));
		return panel;
	}

	private Component buildSouth() {
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton closeButton;
		panel.add(closeButton = new JButton(new BAction("Close",
				new FinishFrameTask(this))));
		this.getRootPane().setDefaultButton(closeButton);
		return panel;
	}

}
