package org.breder.gui.component;

import org.breder.gui.event.UIMouseEvent;

public class UIFrame extends UIPanel {

	protected UIComponent component;

	protected UIComponent focus;

	private UIFrameManager so;

	public UIFrame(UIComponent component) {
		this.component = component;
		this.component.parent = this;
	}

	@Override
	public void paint(UIGraphic g) {
		this.component.paint(g);
	}

	@Override
	public void layout() {
		this.component.width = this.width;
		this.component.height = this.height;
	}

	public UIComponent getFocus() {
		return focus;
	}

	public void setFocus(UIComponent focus) {
		this.focus = focus;
	}

	@Override
	public void fireMouseMoved(UIMouseEvent e) {
		this.component.fireMouseMoved(e);
	}

	@Override
	public void fireMouseEntered(UIMouseEvent e) {
		this.component.fireMouseEntered(e);
	}

	@Override
	public void fireMouseExited(UIMouseEvent e) {
		this.component.fireMouseExited(e);
	}

	@Override
	public void fireMouseReleased(UIMouseEvent e) {
		this.component.fireMouseReleased(e);
	}

	@Override
	public void fireMousePressed(UIMouseEvent e) {
		this.component.fireMousePressed(e);
	}

	public void repaint(int x, int y, int width, int height) {
		this.so.repaint(x, y, width, height);
	}

	public void setSO(UIFrameManager so) {
		this.so = so;
	}

}
