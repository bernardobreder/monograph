package logic.instruction;

public class ConstC extends Instruction {

	public ConstC(int inst) {
		super(inst);
	}

	@Override
	public String toString() {
		return String.format("%s %d", super.toString(), this.getOAAA(1));
	}
	

}
