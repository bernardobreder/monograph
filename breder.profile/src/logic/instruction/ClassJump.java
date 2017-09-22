package logic.instruction;

public class ClassJump extends Instruction {

	public ClassJump(int inst) {
		super(inst);
	}

	@Override
	public String toString() {
		return String.format("%s mem=%d classindex=%d", super.toString(), this
				.getOABB(1), this.getOABB(2));
	}

}
