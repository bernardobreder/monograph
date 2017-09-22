package logic.instruction;

public class FalseJump extends Instruction {

	public FalseJump(int inst) {
		super(inst);
	}

	@Override
	public String toString() {
		return String.format("%s %d", super.toString(), this.getOAAA(1));
	}
	
}
