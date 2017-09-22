package frame.tree.node;

import java.util.ArrayList;
import java.util.List;

import logic.instruction.Instruction;
import logic.struct.JPMethod;
import breder.util.swing.tree.AbstractTreeNode;
import breder.util.swing.tree.DirectoryTreeNode;

public class InstructionTreeNode extends DirectoryTreeNode {
	
	private final JPMethod method;
	
	public InstructionTreeNode(AbstractTreeNode parent, JPMethod method) {
		super(parent);
		this.method = method;
	}
	
	@Override
	public AbstractTreeNode[] getChildren() {
		List<AbstractTreeNode> list = new ArrayList<AbstractTreeNode>();
		List<Instruction> insts = this.method.getInsts();
		for (int n = 0; n < insts.size(); n++) {
			Instruction inst = insts.get(n);
			String value = String.format("[%d] %s", method.getMemIndex() + n, inst);
			list.add(new SimpleFileTreeNode(this, value));
		}
		return list.toArray(new AbstractTreeNode[0]);
	}
	
	@Override
	public String toString() {
		return "inst";
	}
	
}
