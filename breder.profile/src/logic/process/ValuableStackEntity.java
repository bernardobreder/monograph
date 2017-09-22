package logic.process;

import logic.struct.JPClass;

public class ValuableStackEntity extends BasicStackEntity {
	
	private final Object value;
	
	public ValuableStackEntity(JPClass clazz, Integer id, Object value) {
		super(clazz, id);
		this.value = value;
	}
	
	public Object getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return String.format("%s = %s", super.toString(), this.getValue().toString());
	}
	
}
