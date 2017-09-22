package logic.struct;

import java.util.ArrayList;
import java.util.List;

import logic.instruction.Instruction;

public class JPMethod {

	private boolean isNative;

	private boolean isAbstract;

	private boolean isConstructor;

	private String name;

	private int paramCount;

	private int memIndex;

	private final List<Instruction> insts = new ArrayList<Instruction>();

	public boolean isConstructor() {
		return isConstructor;
	}

	public void setConstructor(boolean isConstructor) {
		this.isConstructor = isConstructor;
	}

	public boolean getIsNative() {
		return isNative;
	}

	public void setIsNative(boolean isNative) {
		this.isNative = isNative;
	}

	public boolean isAbstract() {
		return isAbstract;
	}

	public void setAbstract(boolean isAbstract) {
		this.isAbstract = isAbstract;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getParamCount() {
		return paramCount;
	}

	public void setParamCount(int paramCount) {
		this.paramCount = paramCount;
	}

	public int getMemIndex() {
		return memIndex;
	}

	public void setMemIndex(int memIndex) {
		this.memIndex = memIndex;
	}

	public List<Instruction> getInsts() {
		return insts;
	}

	@Override
	public String toString() {
		return String.format("%s", name);
	}

	public void setIsStatic(int readInt) {

	}

	public void setReturns(int readInt) {
		// TODO Auto-generated method stub

	}

	public void setClassIndex(int readInt) {
		// TODO Auto-generated method stub
		
	}

}
