package org.breder.lng.util.btree;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Implementação da storage na forma de arquivo
 * 
 * @author bernardobreder
 * 
 */
public class FileIOTreeService implements IOTreeService {

	/** Diretório da tabela */
	private final File dir;

	private int sequence;

	/**
	 * Construtor
	 * 
	 * @param dir
	 * @throws IOException
	 */
	public FileIOTreeService(File dir) throws IOException {
		this.dir = dir;
		dir.mkdirs();
		for (File file : dir.listFiles()) {
			if (!file.isHidden() && file.isFile()) {
				String name = file.getName();
				if (name.startsWith("node_")) {
					int id = Integer.valueOf(name.substring(5, name.length() - 5));
					this.sequence = Math.max(this.sequence, id);
				}
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OutputStream getTreeOutputStream() throws IOException {
		return new FileOutputStream(new File(dir, "table.data"));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public InputStream getTreeInputStream() throws IOException {
		return new FileInputStream(new File(dir, "table.data"));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public InputStream getTreeNodeInputStream(int nodeId) throws IOException {
		return new FileInputStream(new File(dir, "node_" + nodeId + ".data"));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OutputStream getTreeNodeOutputStream(int nodeId) throws IOException {
		return new FileOutputStream(new File(dir, "node_" + nodeId + ".data"));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void writeValue(DataOutputStream output, Object value) throws IOException {
		if (value == null) {
			output.writeByte(0);
		} else if (value instanceof String) {
			output.writeByte(1);
			output.writeUTF((String) value);
		} else if (value instanceof Integer) {
			output.writeByte(2);
			output.writeInt((Integer) value);
		} else if (value instanceof Long) {
			output.writeByte(3);
			output.writeLong((Long) value);
		} else if (value instanceof Float) {
			output.writeByte(4);
			output.writeFloat((Float) value);
		} else if (value instanceof Double) {
			output.writeByte(5);
			output.writeDouble((Double) value);
		} else if (value instanceof Boolean) {
			output.writeByte(6);
			output.writeBoolean((Boolean) value);
		} else if (value instanceof int[]) {
			output.writeByte(7);
			int[] is = (int[]) value;
			output.writeInt(is.length);
			for (int n = 0; n < is.length; n++) {
				output.writeInt(is[n]);
			}
		} else if (value instanceof int[][]) {
			output.writeByte(8);
			int[][] iss = (int[][]) value;
			output.writeInt(iss.length);
			for (int n = 0; n < iss.length; n++) {
				int[] is = iss[n];
				output.writeInt(is.length);
				for (int m = 0; m < is.length; m++) {
					output.writeInt(is[m]);
				}
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object readValue(DataInputStream input) throws IOException {
		short type = input.readByte();
		switch (type) {
		case 0: {
			return null;
		}
		case 1: {
			return input.readUTF();
		}
		case 2: {
			return input.readInt();
		}
		case 3: {
			return input.readLong();
		}
		case 4: {
			return input.readFloat();
		}
		case 5: {
			return input.readDouble();
		}
		case 6: {
			return input.readBoolean();
		}
		case 7: {
			int size = input.readInt();
			int[] is = new int[size];
			for (int n = 0; n < size; n++) {
				is[n] = input.readInt();
			}
			return is;
		}
		case 8: {
			int sizes = input.readInt();
			int[] iss = new int[sizes];
			for (int n = 0; n < sizes; n++) {
				int size = input.readInt();
				int[] is = new int[size];
				for (int m = 0; m < size; m++) {
					is[m] = input.readInt();
				}
			}
			return iss;
		}
		default: {
			throw new IllegalStateException();
		}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void closeTreeNodeStream(InputStream input) throws IOException {
		input.close();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void closeTreeNodeStream(OutputStream output) throws IOException {
		output.close();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void closeTreeStream(InputStream input) throws IOException {
		input.close();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void closeTreeStream(OutputStream output) throws IOException {
		output.close();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean exists(int id) throws IOException {
		return new File(dir, "node_" + id + ".data").exists();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public synchronized int buildSequence() {
		return ++sequence;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean exists() throws IOException {
		return new File(dir, "table.data").exists();
	}

}
