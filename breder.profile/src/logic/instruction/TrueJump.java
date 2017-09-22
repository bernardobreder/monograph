package logic.instruction;

public class TrueJump extends Instruction {

	public TrueJump(int inst) {
		super(inst);
	}

	@Override
	public String toString() {
		return String.format("%s %d", super.toString(), this.getOAAA(1));
	}
	
}
