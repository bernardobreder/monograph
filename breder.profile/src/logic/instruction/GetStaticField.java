package logic.instruction;

public class GetStaticField extends Instruction {

	public GetStaticField(int inst) {
		super(inst);
	}

	@Override
	public String toString() {
		return String.format("%s %d", super.toString(), this.getOAAA(1));
	}

}
