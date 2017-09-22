package logic.instruction;

public class ConstNat extends Instruction {

	public ConstNat(int inst) {
		super(inst);
	}

	@Override
	public String toString() {
		return String.format("%s %d", super.toString(), this.getOAAA(1));
	}
	

}
