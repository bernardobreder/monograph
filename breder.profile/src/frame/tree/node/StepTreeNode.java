package frame.tree.node;

import java.util.ArrayList;
import java.util.List;

import logic.instruction.Instruction;
import logic.process.InstructionEntity;
import breder.util.swing.tree.AbstractTreeNode;

public class StepTreeNode extends AbstractTreeNode {
	
	private InstructionEntity inst;
	
	private Instruction instruction;
	
	public StepTreeNode(AbstractTreeNode parent, InstructionEntity inst) {
		super(parent);
		this.inst = inst;
		this.instruction = RootTreeNode.getInstance().getBinary().getVm().getInstructions().get(
				this.getInst().getIndex());
	}
	
	@Override
	public AbstractTreeNode[] getChildren() {
		List<AbstractTreeNode> list = new ArrayList<AbstractTreeNode>();
		return list.toArray(new AbstractTreeNode[0]);
	}
	
	public InstructionEntity getInst() {
		return inst;
	}
	
	@Override
	public String toString() {
		return String.format("%s", inst.getIndex());
	}
	
}
