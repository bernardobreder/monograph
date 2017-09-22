package logic.instruction;

public class BConst extends Instruction {
	
	public BConst(int inst) {
		super(inst);
	}
	
	@Override
	public String toString() {
		return String.format("%s %d", super.toString(), this.getOAAA(1));
	}
	
}
