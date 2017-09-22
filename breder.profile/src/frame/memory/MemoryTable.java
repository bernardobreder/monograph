package frame.memory;

import breder.util.swing.table.BTable;

public class MemoryTable extends BTable<MemoryTableCell> {
	
	public MemoryTable() {
		super(new MemoryTableModel());
	}
	
}
