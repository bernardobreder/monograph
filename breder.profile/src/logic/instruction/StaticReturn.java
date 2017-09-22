package logic.instruction;

public class StaticReturn extends Instruction {

	public StaticReturn(int inst) {
		super(inst);
	}

	@Override
	public String toString() {
		return String.format("%s %d", super.toString(), this.getOAAA(1));
	}
	
}
