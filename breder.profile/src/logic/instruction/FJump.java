package logic.instruction;

public class FJump extends Instruction {

	public FJump(int inst) {
		super(inst);
	}

	@Override
	public String toString() {
		return String.format("%s %d", super.toString(), this.getOAAA(1));
	}
	
}
