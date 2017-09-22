package breder.processor.model;

import java.awt.Color;
import java.awt.Graphics;

public class Line extends AbstractObject implements ILine {

	private int x0;

	private int y0;

	private int x1;

	private int y1;

	public Line(int x0, int y0, int x1, int y1) {
		super(0, 0);
		this.x0 = x0;
		this.y0 = y0;
		this.x1 = x1;
		this.y1 = y1;
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawLine(x0, y0, x1, y1);
	}

}
