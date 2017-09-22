package logic.instruction;

public class Inc extends Instruction {

	public Inc(int inst) {
		super(inst);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return String.format("%s %d", super.toString(), this.getOAAA(1));
	}
	
}
