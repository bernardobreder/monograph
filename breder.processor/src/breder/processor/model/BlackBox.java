package breder.processor.model;

import java.awt.Color;
import java.awt.Graphics;

public class BlackBox extends GroupComponent {

	private final int width;

	private final int height;

	public BlackBox(int x, int y, IArchModel model) {
		this(x, y, model.getWidth(), model.getHeight(), model.getObjects().toArray(new IObject[0]));
	}

	public BlackBox(int x, int y, int width, int height, IObject[] objects) {
		super(x, y, objects);
		this.width = width;
		this.height = height;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, width, height);
	}

}
