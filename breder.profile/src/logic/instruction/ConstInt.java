package logic.instruction;

public class ConstInt extends Instruction {

	public ConstInt(int inst) {
		super(inst);
	}

	@Override
	public String toString() {
		return String.format("%s %d", super.toString(), this.getOAAA(1));
	}
	

}
