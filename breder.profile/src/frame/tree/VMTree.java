package frame.tree;

import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPopupMenu;

import logic.Binary;
import logic.BinaryManager;
import logic.listener.BinaryListener;
import breder.util.swing.table.IOpenCellListener;
import breder.util.swing.tree.AbstractTreeNode;
import breder.util.swing.tree.BTree;
import frame.BrederProfileFrame;
import frame.tree.node.ProcessRootTreeNode;
import frame.tree.node.RootTreeNode;
import frame.tree.node.StepTreeNode;

public class VMTree extends BTree {

	public VMTree() {
		super(RootTreeNode.getInstance());
		BinaryManager.getInstance().addBinaryListener(new BinaryListener() {

			@Override
			public void changeBinary(Binary binary) {
				RootTreeNode.getInstance().setBinary(binary);
				ProcessRootTreeNode.getInstance().start();
				VMTree.this.refresh();
			}
		});
		this.add(new IOpenCellListener<AbstractTreeNode>() {
			@Override
			public JPopupMenu getPopupMenu(int row, AbstractTreeNode cell) {
				return null;
			}

			@Override
			public void actionPerformed(int row, AbstractTreeNode cell) {
				if (cell instanceof StepTreeNode) {
					StepTreeNode node = (StepTreeNode) cell;
					BrederProfileFrame.getInstance().getStackTable().getModel()
							.setEntity(node.getInst());
					BrederProfileFrame.getInstance().getStackTable().getModel()
							.refresh();
					BrederProfileFrame
							.getInstance()
							.getMemoryTable()
							.getTable()
							.getSelectionModel()
							.setSelectionInterval(node.getInst().getIndex(),
									node.getInst().getIndex());
				}
				BrederProfileFrame.getInstance().validate();
			}
		});
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				fireOpenCellListener();
			}
		});
		this.setRootVisible(false);
	}
}
