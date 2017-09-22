package logic.instruction;

public class Desc extends Instruction {

	public Desc(int inst) {
		super(inst);
	}

	@Override
	public String toString() {
		return String.format("%s %d", super.toString(), this.getOAAA(1));
	}
	
}
