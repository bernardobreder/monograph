package ui.tree;

import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.widgets.TreeItem;

public class TreeKeyListener extends TreeGenericListener implements KeyListener {

	public TreeKeyListener(TreeModel model) {
		super(model);
		this.getModel().getTree().addKeyListener(this);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.keyCode == 13) {
			TreeItem[] itens = this.getModel().getTree().getSelection();
			for (TreeItem item : itens) {
				Object data = item.getData();
				if (data instanceof ITreeNodeAction) {
					ITreeNodeAction action = (ITreeNodeAction) data;
					action.open();
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
