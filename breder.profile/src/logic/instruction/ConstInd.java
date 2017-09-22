package logic.instruction;

public class ConstInd extends Instruction {

	public ConstInd(int inst) {
		super(inst);
	}

	@Override
	public String toString() {
		return String.format("%s %d", super.toString(), this.getOAAA(1));
	}
	

}
