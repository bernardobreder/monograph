package frame.stackobject;

import logic.process.StackEntity;
import breder.util.swing.table.BTable;

public class StackObjectTable extends BTable<StackEntity> {
	
	public StackObjectTable() {
		super(new StackObjectModel());
	}
	
	@Override
	public StackObjectModel getModel() {
		return (StackObjectModel) super.getModel();
	}
	
}
