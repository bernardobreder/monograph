package breder.test.execute;

public class BVMProcess {

	private String name;

	private BVMOpcode[] opcodes;

	public BVMProcess(String name, BVMOpcode[] opcodes) {
		super();
		this.name = name;
		this.opcodes = opcodes;
	}

	public String getName() {
		return name;
	}

	public BVMOpcode[] getOpcodes() {
		return opcodes;
	}

}
