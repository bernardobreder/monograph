package breder.processor.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

import breder.processor.model.IArchModel;
import breder.processor.model.IObject;

public class ArchPanel extends JPanel {

	private final IArchModel model;

	private int grid;

	private boolean gridVisible;

	public ArchPanel(IArchModel model) {
		super();
		this.grid = 10;
		this.model = model;
		this.setPreferredSize(new Dimension(model.getWidth(), model.getHeight()));
	}

	@Override
	public void paint(Graphics g) {
		this.paintBackground(g);
		if (this.gridVisible) {
			this.paintGrid(g);
		}
		this.paintObjects(g);
	}

	private void paintBackground(Graphics g) {
		int w = this.getSize().width;
		int h = this.getSize().height;
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, w, h);
	}

	private void paintGrid(Graphics g) {
		int mw = this.getModel().getWidth();
		int mh = this.getModel().getHeight();
		int xsize = mw / grid;
		g.setColor(new Color(225, 225, 225));
		for (int x = 1; x <= xsize; x++) {
			g.drawLine(x * grid, 0, x * grid, mh);
		}
		int ysize = mh / grid;
		for (int y = 1; y <= ysize; y++) {
			g.drawLine(0, y * grid, mw, y * grid);
		}
	}

	private void paintObjects(Graphics g) {
		List<IObject> objects = this.getModel().getObjects();
		for (IObject object : objects) {
			g.translate(object.getX(), object.getY());
			object.paint(g);
			g.translate(-object.getX(), -object.getY());
		}
	}

	public IArchModel getModel() {
		return model;
	}

	public void setGridVisible(boolean selected) {
		this.gridVisible = selected;
	}

}
