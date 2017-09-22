package logic.instruction;

public class IncTestJump extends Instruction {

	public IncTestJump(int inst) {
		super(inst);
	}

	@Override
	public String toString() {
		return String.format("%s %d", super.toString(), this.getOAAA(1));
	}
	
}
