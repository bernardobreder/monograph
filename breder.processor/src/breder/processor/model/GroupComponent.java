package breder.processor.model;

import java.awt.Graphics;

public class GroupComponent extends AbstractObject {

	private IObject[] children;

	public GroupComponent(int x, int y, IObject[] objects) {
		super(x, y);
		this.children = objects;
	}

	@Override
	public void paint(Graphics g) {
		for (IObject object : this.children) {
			g.translate(object.getX(), object.getY());
			object.paint(g);
			g.translate(-object.getX(), -object.getY());
		}
	}

}
