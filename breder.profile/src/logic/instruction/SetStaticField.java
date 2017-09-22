package logic.instruction;

public class SetStaticField extends Instruction {

	public SetStaticField(int inst) {
		super(inst);
	}

	@Override
	public String toString() {
		return String.format("%s %d", super.toString(), this.getOAAA(1));
	}

}
