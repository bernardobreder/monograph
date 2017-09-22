package breder.processor.model.breder;

public class Amux extends Box {

	public static final int IN_COUNT = 4;

	public static final int OUT_COUNT = 16;

	public static final int WIDTH = OUT_COUNT * ArchModel.SPACE;

	public static final int HEIGHT = 40;

	public static final int DELTA_X = (int) (WIDTH / OUT_COUNT);

	public static final int DELTA_Y = (int) (HEIGHT / IN_COUNT);

	public Amux(int x, int y) {
		super(x, y, WIDTH, HEIGHT, "AMUX");
	}

}
