package org.breder.gui.component;

import org.breder.gui.event.UIMouseEvent;

public abstract class UIComponent {

	protected int x;

	protected int y;

	protected int width;

	protected int height;

	protected UIComponent parent;

	public UIFrame getFrame() {
		UIComponent c = this;
		while (c != null && c instanceof UIFrame == false) {
			c = c.parent;
		}
		return (UIFrame) c;
	}

	public int getAbsoluteX() {
		if (this.parent != null) {
			return this.parent.getAbsoluteX() + this.x;
		} else {
			return this.x;
		}
	}

	public int getAbsoluteY() {
		if (this.parent != null) {
			return this.parent.getAbsoluteY() + this.y;
		} else {
			return this.y;
		}
	}

	public boolean isFocued(UIFrame frame) {
		if (frame == null) {
			frame = this.getFrame();
		}
		return frame.getFocus() == this;
	}

	public abstract void paint(UIGraphic g);

	public abstract void fireMouseMoved(UIMouseEvent e);

	public abstract void fireMouseEntered(UIMouseEvent e);

	public abstract void fireMouseExited(UIMouseEvent e);

	public abstract void fireMouseReleased(UIMouseEvent e);

	public abstract void fireMousePressed(UIMouseEvent e);

}
