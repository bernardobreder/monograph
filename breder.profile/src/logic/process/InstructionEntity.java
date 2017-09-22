package logic.process;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InstructionEntity {
	
	private Integer index;
	
	private List<StackEntity> stacks = new ArrayList<StackEntity>();
	
	private List<String> stacktraces = new ArrayList<String>();
	
	public int getIndex() {
		return index;
	}
	
	public void setIndex(Integer index) {
		if (this.index != null) {
			throw new IllegalStateException();
		}
		this.index = index;
	}
	
	public List<StackEntity> getStacks() {
		return Collections.unmodifiableList(stacks);
	}
	
	public void addStack(StackEntity stack) {
		this.stacks.add(stack);
	}
	
	public void addStackTrace(String method) {
		this.stacktraces.add(method);
	}
	
	public List<String> getStackTraces() {
		return Collections.unmodifiableList(stacktraces);
	}
	
}
