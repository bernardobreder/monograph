package breder.processor.model;

public abstract class AbstractObject implements IObject {

	private final int x;

	private final int y;

	public AbstractObject(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

}
