package frame;

import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import breder.util.swing.GBC;
import frame.cache.CacheTree;
import frame.memory.MemoryTable;
import frame.stackobject.StackObjectTable;
import frame.tree.VMTree;

public class BrederProfileFrame extends JFrame {

	private static final BrederProfileFrame instance = new BrederProfileFrame();

	private StackObjectTable stackTable;

	private MemoryTable memoryTable;

	private CacheTree cacheTree;

	private BrederProfileFrame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.build();
		this.pack();
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setLocationRelativeTo(null);
	}

	private void build() {
		this.setLayout(new GridBagLayout());
		{
			VMTree tree = new VMTree();
			JScrollPane scroll = new JScrollPane(tree);
			scroll.setPreferredSize(new Dimension(300, 0));
			this.add(scroll, new GBC(0, 0).gridwh(1, 2).vertical());
		}
		{
			this.memoryTable = new MemoryTable();
			this.add(memoryTable, new GBC(1, 0).both());
		}
		{
			this.stackTable = new StackObjectTable();
			this.add(stackTable, new GBC(1, 1).horizontal());
		}
		if (false) {
			this.cacheTree = new CacheTree();
			this.cacheTree.setPreferredSize(new Dimension(0, 200));
			this.add(stackTable, new GBC(0, 1).horizontal());
		}
	}

	public StackObjectTable getStackTable() {
		return stackTable;
	}

	public MemoryTable getMemoryTable() {
		return memoryTable;
	}

	public static BrederProfileFrame getInstance() {
		return instance;
	}

}
