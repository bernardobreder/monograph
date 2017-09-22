package org.breder.lng.util.btree;

import static org.breder.lng.util.btree.IOTree.T;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

public class IOTreeNode {

	private final IOTreeService service;

	public int id;

	public int size;

	public int[] keys;

	public Object[] values;

	public IOTreeNodeReference[] nodes;

	public boolean leaf;

	public IOTreeNode(IOTreeService service) {
		this.service = service;
		this.id = service.buildSequence();
		this.keys = new int[2 * T - 1];
		this.values = new Object[2 * T - 1];
		this.nodes = new IOTreeNodeReference[2 * T];
	}

	/**
	 * Construtor
	 * 
	 * @param service
	 * @param parent
	 * @param id
	 * @throws IOException
	 */
	public IOTreeNode(IOTreeService service, int id) throws IOException {
		this.service = service;
		this.id = id;
		InputStream inputStream = service.getTreeNodeInputStream(id);
		try {
			DataInputStream input = new DataInputStream(inputStream);
			if (input.readInt() != id) {
				throw new IllegalArgumentException();
			}
			this.size = input.readInt();
			this.leaf = input.readBoolean();
			this.keys = new int[2 * T];
			for (int n = 0; n < size; n++) {
				this.keys[n] = input.readInt();
			}
			this.nodes = new IOTreeNodeReference[2 * T + 1];
			for (int n = 0; n < size + 1; n++) {
				int ref = input.readInt();
				if (ref < 0) {
					this.nodes[n] = null;
				} else {
					this.nodes[n] = new IOTreeNodeReference(service, ref);
				}
			}
			this.values = new Object[2 * T];
			for (int n = 0; n < size; n++) {
				values[n] = service.readValue(input);
			}
		} finally {
			service.closeTreeNodeStream(inputStream);
		}
	}

	/**
	 * Realiza a escrita do cabeçalho do node
	 * 
	 * @param output
	 * @throws IOException
	 */
	public void write(DataOutputStream output) throws IOException {
		output.writeInt(id);
		output.writeInt(size);
		output.writeBoolean(leaf);
		for (int n = 0; n < size; n++) {
			output.writeInt(keys[n]);
		}
		for (int n = 0; n < size + 1; n++) {
			IOTreeNodeReference ref = nodes[n];
			if (ref == null) {
				output.writeInt(-1);
			} else {
				output.writeInt(ref.id);
			}
		}
		for (int n = 0; n < size; n++) {
			this.service.writeValue(output, this.values[n]);
		}
	}

	/**
	 * Indica o tamanho do cabeçalho em bytes
	 * 
	 * @return tamanho do cabeçalho em bytes
	 */
	public int getHeadSize() {
		return 4 + 4 + 1 + T * 2 * 4 + (T * 2 + 1) * 4;
	}

	int binarySearch(int key) {
		int leftIndex = 0;
		int rightIndex = size - 1;
		while (leftIndex <= rightIndex) {
			final int middleIndex = leftIndex + ((rightIndex - leftIndex) / 2);
			if (keys[middleIndex] < key) {
				leftIndex = middleIndex + 1;
			} else if (keys[middleIndex] > key) {
				rightIndex = middleIndex - 1;
			} else {
				return middleIndex;
			}
		}
		return -1;
	}

	void remove(int index, int leftOrRightChild) {
		if (index >= 0) {
			int i;
			for (i = index; i < size - 1; i++) {
				keys[i] = keys[i + 1];
				values[i] = values[i + 1];
				if (!leaf) {
					if (i >= index + leftOrRightChild) {
						nodes[i] = nodes[i + 1];
					}
				}
			}
			keys[i] = 0;
			values[i] = null;
			if (!leaf) {
				if (i >= index + leftOrRightChild) {
					nodes[i] = nodes[i + 1];
				}
				nodes[i + 1] = null;
			}
			size--;
		}
	}

	void shiftRightByOne() {
		if (!leaf) {
			nodes[size + 1] = nodes[size];
		}
		for (int i = size - 1; i >= 0; i--) {
			keys[i + 1] = keys[i];
			values[i + 1] = values[i];
			if (!leaf) {
				nodes[i + 1] = nodes[i];
			}
		}
	}

	int subtreeRootNodeIndex(int key) {
		for (int i = 0; i < size; i++) {
			if (key < keys[i]) {
				return i;
			}
		}
		return size;
	}

	public static class IOTreeNodeReference {

		private final IOTreeService service;

		public final int id;

		protected Reference<IOTreeNode> ref;

		/**
		 * Construtor
		 * 
		 * @param ref
		 */
		public IOTreeNodeReference(IOTreeService service, int id) {
			this.service = service;
			this.id = id;
		}

		public IOTreeNodeReference(IOTreeService service, int id, IOTreeNode ref) {
			this.service = service;
			this.id = id;
			this.ref = new WeakReference<IOTreeNode>(ref);
		}

		/**
		 * {@inheritDoc}
		 */
		public IOTreeNode get() throws IOException {
			IOTreeNode node = this.ref == null ? null : this.ref.get();
			if (node == null) {
				node = new IOTreeNode(service, id);
				this.ref = new WeakReference<IOTreeNode>(node);
			}
			return node;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public String toString() {
			return "" + id;
		}

	}

}
