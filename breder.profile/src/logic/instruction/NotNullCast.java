package logic.instruction;

public class NotNullCast extends Instruction {

	public NotNullCast(int inst) {
		super(inst);
	}

	@Override
	public String toString() {
		return String.format("%s %d", super.toString(), this.getOAAA(1));
	}

}
