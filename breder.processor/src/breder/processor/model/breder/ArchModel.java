package breder.processor.model.breder;

import java.util.ArrayList;
import java.util.List;

import breder.processor.model.BlackBox;
import breder.processor.model.IArchModel;
import breder.processor.model.IObject;

public class ArchModel implements IArchModel {

	private final KernelModel kernel = new KernelModel();

	private final List<IObject> objects = new ArrayList<IObject>();

	public static final int SPACE = 10;

	public ArchModel() {
		this.objects.add(new BlackBox(50, 50, kernel));
		this.objects.add(new Controller(500, 100, 200, 200));
	}

	@Override
	public int getHeight() {
		return 1000;
	}

	@Override
	public List<IObject> getObjects() {
		return this.objects;
	}

	@Override
	public int getWidth() {
		return 1000;
	}

}
