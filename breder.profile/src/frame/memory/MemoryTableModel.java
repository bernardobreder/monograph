package frame.memory;

import java.util.HashMap;
import java.util.Map;

import logic.Binary;
import logic.BinaryManager;
import logic.instruction.Instruction;
import logic.listener.BinaryListener;
import logic.struct.JPClass;
import logic.struct.JPMethod;
import breder.util.swing.model.StaticObjectModel;

public class MemoryTableModel extends StaticObjectModel<MemoryTableCell> {
	
	private final Map<Integer, JPMethod> methods = new HashMap<Integer, JPMethod>();
	
	private final Map<Integer, JPClass> classes = new HashMap<Integer, JPClass>();
	
	private Binary binary;
	
	public MemoryTableModel() {
		super("instruções", "class", "method");
		BinaryManager.getInstance().addBinaryListener(new BinaryListener() {
			
			@Override
			public void changeBinary(Binary binary) {
				MemoryTableModel.this.binary = binary;
				MemoryTableModel.this.refresh();
				{
					classes.clear();
					methods.clear();
					int index = 0;
					for (JPClass clazz : binary.getVm().getClasses()) {
						for (JPMethod method : clazz.getMethods()) {
							for (Instruction inst : method.getInsts()) {
								classes.put(index, clazz);
								methods.put(index, method);
								index++;
							}
						}
					}
				}
			}
		});
	}
	
	@Override
	public MemoryTableCell getRow(int index) {
		return new MemoryTableCell(false, this.binary.getVm().getInstructions().get(index));
	}
	
	@Override
	public int getSize() {
		if (this.binary == null) {
			return 0;
		} else {
			return this.binary.getVm().getInstructions().size();
		}
	}
	
	@Override
	public Comparable<?> getValueAt(MemoryTableCell element, int row, int column) {
		if (column == 0) {
			return String.format("[%d] %s", row, element.getInstruction().toString());
		} else if (column == 1) {
			if (row == 0) {
				return "";
			} else {
				return classes.get(row - 1).getName();
			}
		} else if (column == 2) {
			if (row == 0) {
				return "";
			} else {
				return methods.get(row - 1).getName();
			}
		} else
			throw new RuntimeException();
	}
}
