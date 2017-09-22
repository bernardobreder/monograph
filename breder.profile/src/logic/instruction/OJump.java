package logic.instruction;

public class OJump extends Instruction {

	public OJump(int inst) {
		super(inst);
	}

	@Override
	public String toString() {
		return String.format("%s param=%d index=%d", super.toString(), this.getOABB(1),this.getOABB(2));
	}

}
