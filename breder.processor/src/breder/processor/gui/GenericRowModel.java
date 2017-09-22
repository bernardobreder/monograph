package breder.processor.gui;

import breder.util.swing.model.StaticObjectModel;

public class GenericRowModel extends StaticObjectModel<String> {

	public GenericRowModel() {
		super("", "");
	}

	@Override
	public String getRow(int index) {
		return null;
	}

	@Override
	public int getSize() {
		return 0;
	}

}
