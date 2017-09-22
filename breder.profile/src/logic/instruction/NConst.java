package logic.instruction;

public class NConst extends Instruction {

	public NConst(int inst) {
		super(inst);
	}

	@Override
	public String toString() {
		return String.format("%s %d", super.toString(), this.getOAAA(1));
	}
	

}
