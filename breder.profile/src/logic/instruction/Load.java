package logic.instruction;

public class Load extends Instruction {

	public Load(int inst) {
		super(inst);
	}
	
	@Override
	public String toString() {
		return String.format("%s %d", super.toString(), this.getOAAA(1));
	}
	

}
