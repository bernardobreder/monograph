package logic.struct;

import java.util.ArrayList;
import java.util.List;

public class JPClass {

	private String name;

	private int fields;

	private final List<JPMethod> methods = new ArrayList<JPMethod>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getFields() {
		return fields;
	}

	public void setFields(int fields) {
		this.fields = fields;
	}

	public void add(JPMethod method) {
		methods.add(method);
	}

	public List<JPMethod> getMethods() {
		return methods;
	}

	@Override
	public String toString() {
		return String.format("%s", name);
	}

}
