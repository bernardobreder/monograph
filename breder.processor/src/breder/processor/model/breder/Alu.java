package breder.processor.model.breder;

public class Alu extends Box {

	public static final int WIDTH = Register.WIDTH + 2 * ArchModel.SPACE + Amux.WIDTH;

	public static final int HEIGHT = 40;

	public static final int IN_COUNT = 3;

	public static final int DELTA_Y = HEIGHT / IN_COUNT;

	public Alu(int x, int y) {
		super(x, y, WIDTH, HEIGHT, "ALU");
	}

}
