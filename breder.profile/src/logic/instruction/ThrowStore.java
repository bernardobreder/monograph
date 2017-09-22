package logic.instruction;

public class ThrowStore extends Instruction {

	public ThrowStore(int inst) {
		super(inst);
	}

	@Override
	public String toString() {
		return String.format("%s %d", super.toString(), this.getOAAA(1));
	}

}
