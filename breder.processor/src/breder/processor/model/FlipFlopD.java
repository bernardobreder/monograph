package breder.processor.model;

import java.awt.Color;
import java.awt.Graphics;

import breder.processor.model.breder.AbstractBox;

public class FlipFlopD extends AbstractBox {

	public FlipFlopD(int x, int y) {
		super(x, y, 50, 50);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.BLACK);
		int w = this.getWidth();
		int h = this.getHeight();
		int grid = ObjectConstant.GRID_SIZE;
		int qw = g.getFontMetrics().stringWidth("Q");
		int nqw = g.getFontMetrics().stringWidth("!Q");
		int sh = g.getFontMetrics().getHeight();
		g.drawString("D", 2, h / 2 + sh / 2);
		g.drawString("Q", w - qw - 2, h / 4 + sh / 2);
		g.drawString("!Q", w - nqw - 2, 3 * h / 4 + sh / 2);
		g.drawLine(-grid, h / 2, 0, h / 2);
		g.drawLine(w, h / 4, w + grid, h / 4);
		g.drawLine(w, 3 * h / 4, w + grid, 3 * h / 4);
	}

}
