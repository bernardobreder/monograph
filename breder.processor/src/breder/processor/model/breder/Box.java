package breder.processor.model.breder;

import java.awt.Graphics;

public class Box extends AbstractBox {

	private final String name;

	public Box(int x, int y, int w, int h, String name) {
		super(x, y, w, h);
		this.name = name;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		int sw = g.getFontMetrics().stringWidth(name);
		int sh = g.getFontMetrics().getHeight();
		g.drawString(name, this.getWidth() / 2 - sw / 2, this.getHeight() / 2 + sh / 2);
	}

}
