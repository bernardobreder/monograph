package frame.stackobject;

import logic.process.BasicStackEntity;
import logic.process.InstructionEntity;
import logic.process.NullStackEntity;
import logic.process.StackEntity;
import logic.process.ValuableStackEntity;
import breder.util.swing.model.StaticObjectModel;

public class StackObjectModel extends StaticObjectModel<StackEntity> {
	
	private InstructionEntity entity;
	
	public StackObjectModel() {
		super("class", "id", "value");
	}
	
	@Override
	public StackEntity getRow(int index) {
		return this.entity.getStacks().get(index);
	}
	
	@Override
	public int getSize() {
		if (entity == null) {
			return 0;
		} else {
			return entity.getStacks().size();
		}
	}
	
	@Override
	public Comparable<?> getValueAt(StackEntity element, int row, int column) {
		if (column == 0) {
			if (element instanceof BasicStackEntity) {
				return ((BasicStackEntity) element).getClazz().getName();
			} else {
				return "";
			}
		} else if (column == 1) {
			if (element instanceof BasicStackEntity) {
				return ((BasicStackEntity) element).getId();
			} else {
				return "";
			}
		} else if (column == 2) {
			if (element instanceof ValuableStackEntity) {
				return ((ValuableStackEntity) element).getValue().toString();
			} else if (element instanceof NullStackEntity) {
				return element.toString();
			} else {
				return "";
			}
		} else
			throw new RuntimeException();
	}
	
	public void setEntity(InstructionEntity entity) {
		this.entity = entity;
	}
}
