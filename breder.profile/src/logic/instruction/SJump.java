package logic.instruction;

public class SJump extends Instruction {

	public SJump(int inst) {
		super(inst);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return String.format("%s %d", super.toString(), this.getOAAA(1));
	}

}
