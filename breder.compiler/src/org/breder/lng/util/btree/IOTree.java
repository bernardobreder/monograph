package org.breder.lng.util.btree;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Set;

import org.breder.lng.util.btree.IOTreeNode.IOTreeNodeReference;

/**
 * Arvore B
 * 
 * @author bernardobreder
 * 
 */
public class IOTree {

	/** Número de elementos minimos em um nó */
	static final int T = 4;
	/** Armazena as mudanças */
	private final Set<IOTreeNode> changes;
	/** Node root */
	private IOTreeNode root;
	/** Serviços */
	private final IOTreeService service;

	/**
	 * Construtor
	 * 
	 * @throws IOException
	 */
	public IOTree(IOTreeService service) throws IOException {
		this.service = service;
		if (service.exists()) {
			InputStream finput = service.getTreeInputStream();
			try {
				DataInputStream input = new DataInputStream(finput);
				int id = input.readInt();
				this.root = new IOTreeNode(service, id);
			} finally {
				service.closeTreeStream(finput);
			}
		} else {
			this.root = new IOTreeNode(service);
			this.root.leaf = true;
		}
		this.changes = new HashSet<IOTreeNode>();
	}

	/**
	 * Realiza o commit das modificações
	 * 
	 * @throws IOException
	 */
	public void commit() throws IOException {
		for (IOTreeNode node : changes) {
			OutputStream outputStream = this.service.getTreeNodeOutputStream(node.id);
			try {
				DataOutputStream output = new DataOutputStream(outputStream);
				node.write(output);
				output.flush();
			} finally {
				this.service.closeTreeNodeStream(outputStream);
			}
		}
		{
			OutputStream outputStream = this.service.getTreeOutputStream();
			try {
				DataOutputStream output = new DataOutputStream(outputStream);
				output.writeInt(this.root.id);
				output.flush();
			} finally {
				this.service.closeTreeNodeStream(outputStream);
			}
		}
		this.changes.clear();
	}

	/**
	 * Insere um elemento na arvore
	 * 
	 * @param key
	 * @param object
	 * @throws IOException
	 */
	public void add(int key, Object object) throws IOException {
		IOTreeNode rootNode = root;
		if (!update(root, key, object)) {
			if (rootNode.size == (2 * T - 1)) {
				root = new IOTreeNode(service);
				root.leaf = false;
				root.nodes[0] = new IOTreeNodeReference(service, rootNode.id, rootNode);
				splitChildNode(root, 0, rootNode);
				addIntoNonFullNode(root, key, object);
				this.changes.add(root);
				this.changes.add(rootNode);
			} else {
				addIntoNonFullNode(rootNode, key, object);
			}
		}
	}

	/**
	 * Adiciona o elemento
	 * 
	 * @param node
	 * @param key
	 * @param object
	 * @return
	 * @throws IOException
	 */
	private boolean update(IOTreeNode node, int key, Object object) throws IOException {
		while (node != null) {
			int i = 0;
			while (i < node.size && key > node.keys[i]) {
				i++;
			}
			if (i < node.size && key == node.keys[i]) {
				node.values[i] = object;
				this.changes.add(node);
				return true;
			}
			if (node.leaf) {
				return false;
			} else {
				node = node.nodes[i].get();
			}
		}
		return false;
	}

	private void addIntoNonFullNode(IOTreeNode node, int key, Object object) throws IOException {
		int i = node.size - 1;
		if (node.leaf) {
			while (i >= 0 && key < node.keys[i]) {
				node.keys[i + 1] = node.keys[i];
				node.values[i + 1] = node.values[i];
				i--;
			}
			i++;
			node.keys[i] = key;
			node.values[i] = object;
			node.size++;
			this.changes.add(node);
		} else {
			while (i >= 0 && key < node.keys[i]) {
				i--;
			}
			i++;
			if (node.nodes[i].get().size == (2 * T - 1)) {
				splitChildNode(node, i, node.nodes[i].get());
				if (key > node.keys[i]) {
					i++;
				}
			}
			addIntoNonFullNode(node.nodes[i].get(), key, object);
		}
	}

	private void splitChildNode(IOTreeNode parent, int index, IOTreeNode node) {
		IOTreeNode newNode = new IOTreeNode(service);
		newNode.leaf = node.leaf;
		newNode.size = T - 1;
		for (int j = 0; j < T - 1; j++) {
			newNode.keys[j] = node.keys[j + T];
			newNode.values[j] = node.values[j + T];
		}
		if (!newNode.leaf) {
			for (int j = 0; j < T; j++) {
				newNode.nodes[j] = node.nodes[j + T];
			}
			for (int j = T; j <= node.size; j++) {
				node.nodes[j] = null;
			}
		}
		for (int j = T; j < node.size; j++) {
			node.keys[j] = 0;
			node.values[j] = null;
		}
		node.size = T - 1;
		for (int j = parent.size; j >= index + 1; j--) {
			parent.nodes[j + 1] = parent.nodes[j];
		}
		parent.nodes[index + 1] = new IOTreeNodeReference(service, newNode.id, newNode);
		for (int j = parent.size - 1; j >= index; j--) {
			parent.keys[j + 1] = parent.keys[j];
			parent.values[j + 1] = parent.values[j];
		}
		parent.keys[index] = node.keys[T - 1];
		parent.values[index] = node.values[T - 1];
		node.keys[T - 1] = 0;
		node.values[T - 1] = null;
		parent.size++;
		this.changes.add(newNode);
		this.changes.add(node);
	}

	/**
	 * Remove um elemento na arvore
	 * 
	 * @param key
	 * @throws IOException
	 */
	public void remove(int key) throws IOException {
		remove(root, key);
	}

	/**
	 * Remover os nodes
	 * 
	 * @param node
	 * @param key
	 * @throws IOException
	 */
	private void remove(IOTreeNode node, int key) throws IOException {
		if (node.leaf) {
			int i = node.binarySearch(key);
			if (i >= 0) {
				node.remove(i, 0);
				this.changes.add(node);
			}
		} else {
			int i = node.binarySearch(key);
			if (i >= 0) {
				IOTreeNode leftChildNode = node.nodes[i].get();
				IOTreeNode rightChildNode = node.nodes[i + 1].get();
				if (leftChildNode.size >= T) {
					IOTreeNode predecessorNode = leftChildNode;
					IOTreeNode erasureNode = predecessorNode;
					while (!predecessorNode.leaf) {
						erasureNode = predecessorNode;
						predecessorNode = predecessorNode.nodes[node.size - 1].get();
					}
					node.keys[i] = predecessorNode.keys[predecessorNode.size - 1];
					node.values[i] = predecessorNode.values[predecessorNode.size - 1];
					remove(erasureNode, node.keys[i]);
					this.changes.add(node);
				} else if (rightChildNode.size >= T) {
					IOTreeNode successorNode = rightChildNode;
					IOTreeNode erasureNode = successorNode;
					while (!successorNode.leaf) {
						erasureNode = successorNode;
						successorNode = successorNode.nodes[0].get();
					}
					node.keys[i] = successorNode.keys[0];
					node.values[i] = successorNode.values[0];
					remove(erasureNode, node.keys[i]);
					this.changes.add(node);
				} else {
					int medianKeyIndex = mergeNodes(leftChildNode, rightChildNode);
					moveKey(node, i, 1, leftChildNode, medianKeyIndex);
					remove(leftChildNode, key);
				}
			} else {
				i = node.subtreeRootNodeIndex(key);
				IOTreeNode childNode = node.nodes[i].get();
				if (childNode.size == T - 1) {
					IOTreeNode leftChildSibling = (i - 1 >= 0) ? node.nodes[i - 1].get() : null;
					IOTreeNode rightChildSibling = (i + 1 <= node.size) ? node.nodes[i + 1].get() : null;
					if (leftChildSibling != null && leftChildSibling.size >= T) {
						childNode.shiftRightByOne();
						childNode.keys[0] = node.keys[i - 1];
						childNode.values[0] = node.values[i - 1];
						if (!childNode.leaf) {
							childNode.nodes[0] = leftChildSibling.nodes[leftChildSibling.size];
						}
						childNode.size++;
						node.keys[i - 1] = leftChildSibling.keys[leftChildSibling.size - 1];
						node.values[i - 1] = leftChildSibling.values[leftChildSibling.size - 1];
						leftChildSibling.remove(leftChildSibling.size - 1, 1);
						this.changes.add(childNode);
						this.changes.add(leftChildSibling);
						this.changes.add(node);
					} else if (rightChildSibling != null && rightChildSibling.size >= T) {
						childNode.keys[childNode.size] = node.keys[i];
						childNode.values[childNode.size] = node.values[i];
						if (!childNode.leaf) {
							childNode.nodes[childNode.size + 1] = rightChildSibling.nodes[0];
						}
						childNode.size++;
						node.keys[i] = rightChildSibling.keys[0];
						node.values[i] = rightChildSibling.values[0];
						rightChildSibling.remove(0, 0);
						this.changes.add(childNode);
						this.changes.add(rightChildSibling);
						this.changes.add(node);
					} else {
						if (leftChildSibling != null) {
							int medianKeyIndex = mergeNodes(childNode, leftChildSibling);
							moveKey(node, i - 1, 0, childNode, medianKeyIndex);
						} else if (rightChildSibling != null) {
							int medianKeyIndex = mergeNodes(childNode, rightChildSibling);
							moveKey(node, i, 1, childNode, medianKeyIndex);
						}
					}
				}
				remove(childNode, key);
			}
		}
	}

	private int mergeNodes(IOTreeNode dstNode, IOTreeNode srcNode) {
		int medianKeyIndex;
		if (srcNode.keys[0] < dstNode.keys[dstNode.size - 1]) {
			int i;
			if (!dstNode.leaf) {
				dstNode.nodes[srcNode.size + dstNode.size + 1] = dstNode.nodes[dstNode.size];
			}
			for (i = dstNode.size; i > 0; i--) {
				dstNode.keys[srcNode.size + i] = dstNode.keys[i - 1];
				dstNode.values[srcNode.size + i] = dstNode.values[i - 1];
				if (!dstNode.leaf) {
					dstNode.nodes[srcNode.size + i] = dstNode.nodes[i - 1];
				}
			}
			medianKeyIndex = srcNode.size;
			dstNode.keys[medianKeyIndex] = 0;
			dstNode.values[medianKeyIndex] = null;
			for (i = 0; i < srcNode.size; i++) {
				dstNode.keys[i] = srcNode.keys[i];
				dstNode.values[i] = srcNode.values[i];
				if (!srcNode.leaf) {
					dstNode.nodes[i] = srcNode.nodes[i];
				}
			}
			if (!srcNode.leaf) {
				dstNode.nodes[i] = srcNode.nodes[i];
			}

		} else {
			medianKeyIndex = dstNode.size;
			dstNode.keys[medianKeyIndex] = 0;
			dstNode.values[medianKeyIndex] = null;
			int offset = medianKeyIndex + 1;
			int i;
			for (i = 0; i < srcNode.size; i++) {
				dstNode.keys[offset + i] = srcNode.keys[i];
				dstNode.values[offset + i] = srcNode.values[i];
				if (!srcNode.leaf) {
					dstNode.nodes[offset + i] = srcNode.nodes[i];
				}
			}
			if (!srcNode.leaf) {
				dstNode.nodes[offset + i] = srcNode.nodes[i];
			}
		}
		dstNode.size += srcNode.size;
		this.changes.add(dstNode);
		return medianKeyIndex;
	}

	/**
	 * Move as chaves
	 * 
	 * @param srcNode
	 * @param srcKeyIndex
	 * @param childIndex
	 * @param dstNode
	 * @param medianKeyIndex
	 */
	private void moveKey(IOTreeNode srcNode, int srcKeyIndex, int childIndex, IOTreeNode dstNode, int medianKeyIndex) {
		dstNode.keys[medianKeyIndex] = srcNode.keys[srcKeyIndex];
		dstNode.values[medianKeyIndex] = srcNode.values[srcKeyIndex];
		dstNode.size++;
		srcNode.remove(srcKeyIndex, childIndex);
		if (srcNode == root && srcNode.size == 0) {
			this.root = dstNode;
		}
		this.changes.add(dstNode);
		this.changes.add(srcNode);
	}

	/**
	 * Busca por um elemento
	 * 
	 * @param key
	 * @return
	 * @throws IOException
	 */
	public Object get(int key) throws IOException {
		return get(root, key);
	}

	/**
	 * Busca por um elemento
	 * 
	 * @param node
	 * @param key
	 * @return elemento
	 * @throws IOException
	 */
	private Object get(IOTreeNode node, int key) throws IOException {
		while (node != null) {
			int i = 0;
			while (i < node.size && key > node.keys[i]) {
				i++;
			}
			if (i < node.size && key == node.keys[i]) {
				return node.values[i];
			}
			if (node.leaf) {
				return null;
			} else {
				node = node.nodes[i].get();
			}
		}
		return null;
	}

	/**
	 * Retorna o conjunto de mudanças de conteúdo
	 * 
	 * @return conjunto de mudanças
	 */
	public Set<IOTreeNode> getChanges() {
		return this.changes;
	}

}
