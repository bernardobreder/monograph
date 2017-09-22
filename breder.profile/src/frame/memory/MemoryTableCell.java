package frame.memory;

import logic.instruction.Instruction;

public class MemoryTableCell {
	
	private boolean isCurrent;
	
	private Instruction instruction;
	
	public MemoryTableCell(boolean isCurrent, Instruction instruction) {
		super();
		this.isCurrent = isCurrent;
		this.instruction = instruction;
	}
	
	public boolean isCurrent() {
		return isCurrent;
	}
	
	public void setCurrent(boolean isCurrent) {
		this.isCurrent = isCurrent;
	}
	
	public Instruction getInstruction() {
		return instruction;
	}
	
	public void setInstruction(Instruction instruction) {
		this.instruction = instruction;
	}
	
}
