package logic.instruction;

public class New extends Instruction {

	public New(int inst) {
		super(inst);
	}

	@Override
	public String toString() {
		return String.format("%s %d", super.toString(), this.getOAAA(1));
	}

}