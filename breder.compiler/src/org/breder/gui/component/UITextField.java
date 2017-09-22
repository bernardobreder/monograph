package org.breder.gui.component;

import java.awt.Color;

import org.breder.gui.event.UIMouseEvent;

public class UITextField extends UIComponent {

	protected StringBuilder text;

	protected int beginCursor;

	protected int endCursor;

	protected int borderColor;

	protected int cursorColor;

	protected int fontColor;

	protected int ofx = 10;

	public UITextField() {
		this.text = new StringBuilder();
		this.beginCursor = 1;
		this.endCursor = 5;
		this.borderColor = Color.DARK_GRAY.getRGB();
		this.cursorColor = Color.BLACK.getRGB();
		this.fontColor = Color.BLACK.getRGB();
		this.ofx = 10;
	}

	@Override
	public void paint(UIGraphic g) {
		int fontHeight = g.getFontHeight();
		int inHeightBorder = this.height - 2;
		int inWidthBorder = this.width - 2;
		int topYText = inHeightBorder / 2 - fontHeight / 2;
		int bottomYText = inHeightBorder / 2 + fontHeight / 2;
		String text = "abcdefghijklmnopqrstuvxywz";
		UIFrame frame = this.getFrame();
		g.drawRectangle(0, 0, this.width, this.height, borderColor);
		g.fillRectangle(1, 1, inWidthBorder, inHeightBorder,
				Color.WHITE.getRGB());
		if (this.isFocued(frame)) {
			if (this.beginCursor != this.endCursor) {
				int xBeginCursor = g.getFontWidthString(text.substring(0,
						this.beginCursor));
				int xEndCursor = g.getFontWidthString(text.substring(0,
						this.endCursor));
				int width = xEndCursor - xBeginCursor + 2;
				g.fillRectangle(ofx + xBeginCursor, topYText, width,
						g.getFontHeight(), Color.YELLOW.getRGB());
			}
		}
		g.drawText(ofx, topYText, text, fontColor);
		if (this.isFocued(frame)) {
			int xBeginCursor = g.getFontWidthString(text.substring(0,
					this.beginCursor));
			int xEndCursor = g.getFontWidthString(text.substring(0,
					this.endCursor));
			if (this.beginCursor == this.endCursor) {
				g.drawLine(ofx + xBeginCursor, topYText, ofx + xBeginCursor,
						bottomYText, cursorColor);
			} else {
				g.drawLine(ofx + xBeginCursor, topYText, ofx + xBeginCursor,
						bottomYText, cursorColor);
				g.drawLine(ofx + xEndCursor, topYText, ofx + xEndCursor,
						bottomYText, cursorColor);
			}
		}
	}

	@Override
	public void fireMouseMoved(UIMouseEvent e) {
	}

	@Override
	public void fireMouseEntered(UIMouseEvent e) {
	}

	@Override
	public void fireMouseExited(UIMouseEvent e) {
	}

	@Override
	public void fireMouseReleased(UIMouseEvent e) {
	}

	@Override
	public void fireMousePressed(UIMouseEvent e) {
		UIFrame frame = this.getFrame();
		if (this.isFocued(frame)) {
			if (e.isShiftPressed()) {

			} else {

			}
		} else {
			frame.setFocus(this);
		}
		frame.repaint(this.getAbsoluteX(), this.getAbsoluteY(), this.width,
				this.height);
	}

}
