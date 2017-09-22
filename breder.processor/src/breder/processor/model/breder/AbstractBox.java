package breder.processor.model.breder;

import java.awt.Color;
import java.awt.Graphics;

import breder.processor.model.Component;

public abstract class AbstractBox extends Component {

	private int width;

	private int height;

	public AbstractBox(int x, int y, int w, int h) {
		super(x, y);
		this.width = w;
		this.height = h;
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, width, height);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}
