package logic.process;

import logic.struct.JPClass;

public class BasicStackEntity extends StackEntity {
	
	private final JPClass clazz;
	
	private final Integer id;
	
	public BasicStackEntity(JPClass clazz, Integer id) {
		super();
		this.clazz = clazz;
		this.id = id;
	}
	
	public JPClass getClazz() {
		return clazz;
	}
	
	public Integer getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return String.format("%s %s", this.getClazz().getName(), this.getId());
	}
	
}
