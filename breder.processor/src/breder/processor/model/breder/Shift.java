package breder.processor.model.breder;

public class Shift extends Box {

	public static final int WIDTH = 50;

	public static final int HEIGHT = 20;

	public static final int IN_COUNT = 2;

	public static final int DELTA_Y = (int) (HEIGHT / IN_COUNT);

	public Shift(int x, int y) {
		super(x, y, WIDTH, HEIGHT, "SHIFT");
	}

}
