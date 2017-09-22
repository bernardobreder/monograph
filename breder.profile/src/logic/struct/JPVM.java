package logic.struct;

import java.util.ArrayList;
import java.util.List;

import logic.instruction.Instruction;

public class JPVM {

	private List<JPClass> classes = new ArrayList<JPClass>();

	private final List<Instruction> instructions = new ArrayList<Instruction>();

	private final List<String> constants = new ArrayList<String>();

	private final List<JPMethod> methods = new ArrayList<JPMethod>();

	public List<JPClass> getClasses() {
		return classes;
	}

	public void setClasses(List<JPClass> clazzs) {
		this.classes = clazzs;
	}

	public List<Instruction> getInstructions() {
		return instructions;
	}

	public List<String> getConstants() {
		return constants;
	}

	public List<JPMethod> getMethods() {
		return methods;
	}

}
