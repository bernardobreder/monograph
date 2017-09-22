package org.breder.lng.node.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.breder.lng.node.AbstractNode;
import org.breder.lng.util.AbstractDataInputStream;
import org.breder.lng.util.AbstractDataOutputStream;
import org.breder.lng.util.AbstractOpcodeOutputStream;

public class BlockNode extends CommandNode {

	private final List<CommandNode> list;

	public BlockNode(List<CommandNode> list) {
		this.list = list;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void build(AbstractOpcodeOutputStream output) throws IOException {
		for (int n = 0; n < list.size(); n++) {
			CommandNode node = list.get(n);
			node.build(output);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public static <E extends AbstractNode> E read(AbstractDataInputStream input)
			throws IOException {
		int size = input.readIndex();
		List<CommandNode> list = new ArrayList<CommandNode>();
		for (int n = 0; n < size; n++) {
			list.add(AbstractNode.<CommandNode> read(input));
		}
		return (E) new BlockNode(list);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void write(AbstractDataOutputStream output) throws IOException {
		output.writeIndex(BLOCK_CMD);
		output.writeIndex(this.list.size());
		for (int n = 0; n < this.list.size(); n++) {
			this.list.get(n).write(output);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + list.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BlockNode other = (BlockNode) obj;
		if (!list.equals(other.list))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "do " + this.list + " end";
	}

}
