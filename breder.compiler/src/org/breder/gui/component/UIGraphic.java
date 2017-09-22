package org.breder.gui.component;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

public class UIGraphic {

	private Graphics2D g;

	public UIGraphic(Graphics2D g) {
		this.g = g;
		g.setFont(new Font("Arial", Font.BOLD, 36));
	}

	public void drawRectangle(int x, int y, int w, int h, int c) {
		g.setColor(new Color(c));
		g.drawRect(x, y, w - 1, h - 1);
	}

	public void fillRectangle(int x, int y, int w, int h, int c) {
		g.setColor(new Color(c));
		g.fillRect(x, y, w - 1, h - 1);
	}

	public int getFontHeight() {
		return g.getFontMetrics().getHeight();
	}

	public int getFontWidthString(String text) {
		return g.getFontMetrics().stringWidth(text);
	}

	public int getFontWidthCharacter(char c) {
		return g.getFontMetrics().charWidth(c);
	}

	public void drawText(int x, int y, String text, int c) {
		FontMetrics metrics = g.getFontMetrics();
		int ofy = metrics.getAscent() + metrics.getLeading();
		g.setColor(new Color(c));
		g.drawString(text, x, y + ofy);
	}

	public void drawLine(int x, int y, int x2, int y2, int c) {
		g.setColor(new Color(c));
		g.drawLine(x, y, x2, y2);
	}

}
