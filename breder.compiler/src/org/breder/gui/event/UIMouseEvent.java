package org.breder.gui.event;

import java.awt.event.MouseEvent;

public class UIMouseEvent extends UIEvent {

	private final int x;

	private final int y;

	private final int click;

	private final int button;

	private final boolean altPressed;

	private final boolean controlPressed;

	private final boolean shiftPressed;

	private final boolean metaPressed;

	public UIMouseEvent(MouseEvent e) {
		this.x = e.getX();
		this.y = e.getY();
		this.click = e.getClickCount();
		this.button = e.getButton();
		this.altPressed = e.isAltDown();
		this.controlPressed = e.isControlDown();
		this.metaPressed = e.isMetaDown();
		this.shiftPressed = e.isShiftDown();
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getClick() {
		return click;
	}

	public int getButton() {
		return button;
	}

	public boolean isAltPressed() {
		return altPressed;
	}

	public boolean isControlPressed() {
		return controlPressed;
	}

	public boolean isShiftPressed() {
		return shiftPressed;
	}

	public boolean isMetaPressed() {
		return metaPressed;
	}

}
