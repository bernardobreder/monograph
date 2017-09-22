package org.breder.gui.component;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.breder.gui.event.UIMouseEvent;

public class UIFrameManager extends JFrame implements UIFrameInterface {

	private Panel panel;

	public UIFrameManager() {
		this.add(panel = new Panel());
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		this.doLayout();
		this.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				panel.onResizeAction();
			}
		});
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				panel.onResizeAction();
			}
		});
	}

	public UIFrameManager setFrame(UIFrame frame) {
		this.panel.setFrame(frame);
		frame.setSO(this);
		return this;
	}

	public UIFrameManager start() {
		this.setVisible(true);
		return this;
	}

	private class Panel extends JPanel {

		private UIFrame frame;

		public Panel() {
			this.addMouseMotionListener(new MouseMotionListener() {

				@Override
				public void mouseMoved(MouseEvent e) {
					onMouseMoved(e);
				}

				@Override
				public void mouseDragged(MouseEvent e) {
				}
			});
			this.addMouseListener(new MouseListener() {

				@Override
				public void mouseReleased(MouseEvent e) {
					onMouseReleased(e);
				}

				@Override
				public void mousePressed(MouseEvent e) {
					onMousePressed(e);
				}

				@Override
				public void mouseExited(MouseEvent e) {
					onMouseExited(e);
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					onMouseEntered(e);
				}

				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub

				}
			});
		}

		public void onResizeAction() {
			frame.width = this.getWidth();
			frame.height = this.getHeight();
			frame.layout();
			this.repaint();
		}

		protected void onMousePressed(MouseEvent e) {
			this.frame.fireMousePressed(new UIMouseEvent(e));
		}

		protected void onMouseReleased(MouseEvent e) {
			this.frame.fireMouseReleased(new UIMouseEvent(e));
		}

		protected void onMouseExited(MouseEvent e) {
			this.frame.fireMouseExited(new UIMouseEvent(e));
		}

		protected void onMouseEntered(MouseEvent e) {
			this.frame.fireMouseEntered(new UIMouseEvent(e));
		}

		protected void onMouseMoved(MouseEvent e) {
			this.frame.fireMouseMoved(new UIMouseEvent(e));
		}

		@Override
		public void paint(Graphics g) {
			this.frame.paint(new UIGraphic((Graphics2D) g));
		}

		public void setFrame(UIFrame frame) {
			this.frame = frame;
		}

	}

	public static void main(String[] args) {
		UIFrame uiframe = new UIFrame(new UITextField());
		new UIFrameManager().setFrame(uiframe).start();
	}

}
