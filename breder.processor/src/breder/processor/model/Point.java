package breder.processor.model;

import java.awt.Color;
import java.awt.Graphics;

public class Point extends AbstractObject implements IPoint {

	public static final int SIZE = 6;

	public Point(int x, int y) {
		super(x, y);
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillArc(-SIZE / 2, -SIZE / 2, SIZE, SIZE, 0, 360);
	}

}
