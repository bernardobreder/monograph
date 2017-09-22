package breder.test.execute;

import java.util.ArrayList;
import java.util.List;

public class BVMOpcode {

	private final int pc;

	private final String opcode;

	private final List<String> stackTrace = new ArrayList<String>();

	private final List<String> stackObject = new ArrayList<String>();

	private final List<String> output = new ArrayList<String>();

	public BVMOpcode(int pc, String opcode) {
		super();
		this.pc = pc;
		this.opcode = opcode;
	}

	public int getPc() {
		return pc;
	}

	public String getOpcode() {
		return opcode;
	}

	public List<String> getStackTrace() {
		return stackTrace;
	}

	public List<String> getStackObject() {
		return stackObject;
	}

	public List<String> getOutput() {
		return output;
	}

}
