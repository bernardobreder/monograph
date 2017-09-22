package frame.tree.node;

import java.util.ArrayList;
import java.util.List;

import logic.Binary;
import logic.process.ExecuteProcess;
import logic.process.InstructionEntity;
import breder.util.swing.tree.AbstractTreeNode;
import breder.util.swing.tree.DirectoryTreeNode;

public class ProcessRootTreeNode extends DirectoryTreeNode {

	private static final ProcessRootTreeNode instance = new ProcessRootTreeNode(
			RootTreeNode.getInstance());

	private ExecuteProcess process;

	private ProcessRootTreeNode(AbstractTreeNode parent) {
		super(parent);
	}

	@Override
	public AbstractTreeNode[] getChildren() {
		if (this.process == null) {
			return new AbstractTreeNode[0];
		}
		List<AbstractTreeNode> list = new ArrayList<AbstractTreeNode>();
		for (InstructionEntity inst : this.process.getSteps()) {
			list.add(new StepTreeNode(this, inst));
		}
		return list.toArray(new AbstractTreeNode[0]);
	}

	@Override
	public String toString() {
		return "process";
	}

	public static ProcessRootTreeNode getInstance() {
		return instance;
	}

	public void start() {
		Binary binary = RootTreeNode.getInstance().getBinary();
		process = new ExecuteProcess(binary);
		// try {
		// process.start();
		// } catch (IOException e) {
		// JOptionPane.showMessageDialog(BrederProfileFrame.getInstance(),
		// e.getMessage());
		// process = null;
		// }
	}

	public ExecuteProcess getProcess() {
		return process;
	}

}
