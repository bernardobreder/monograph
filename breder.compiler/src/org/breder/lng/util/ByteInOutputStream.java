//package org.breder.lng.util;
//
//import java.io.DataInput;
//import java.io.DataOutput;
//import java.io.IOException;
//
//public class ByteInOutputStream implements DataOutput, DataInput {
//
//	private transient byte[] elements;
//
//	private transient int head;
//
//	private transient int tail;
//
//	@Override
//	public void readFully(byte[] b) throws IOException {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void readFully(byte[] b, int off, int len) throws IOException {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public int skipBytes(int n) throws IOException {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	public boolean readBoolean() throws IOException {
//		int read = this.read();
//		if (read != 0) {
//			return true;
//		} else {
//			return false;
//		}
//	}
//
//	public int read() throws IOException {
//		if (head == tail) {
//			return -1;
//		}
//		int h = head;
//		byte result = elements[h];
//		head = (h + 1) & (elements.length - 1);
//		return result;
//	}
//
//	@Override
//	public byte readByte() throws IOException {
//		return (byte) this.read();
//	}
//
//	@Override
//	public int readUnsignedByte() throws IOException {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public short readShort() throws IOException {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public int readUnsignedShort() throws IOException {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public char readChar() throws IOException {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public int readInt() throws IOException {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public long readLong() throws IOException {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public float readFloat() throws IOException {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public double readDouble() throws IOException {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public String readLine() throws IOException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public String readUTF() throws IOException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void write(int b) throws IOException {
//		elements[tail] = b;
//		if ((tail = (tail + 1) & (elements.length - 1)) == head) {
//			doubleCapacity();
//		}
//	}
//
//	@Override
//	public void write(byte[] b) throws IOException {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void write(byte[] b, int off, int len) throws IOException {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void writeBoolean(boolean v) throws IOException {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void writeByte(int v) throws IOException {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void writeShort(int v) throws IOException {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void writeChar(int v) throws IOException {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void writeInt(int v) throws IOException {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void writeLong(long v) throws IOException {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void writeFloat(float v) throws IOException {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void writeDouble(double v) throws IOException {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void writeBytes(String s) throws IOException {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void writeChars(String s) throws IOException {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void writeUTF(String s) throws IOException {
//		// TODO Auto-generated method stub
//
//	}
//
//	/**
//	 * Double the capacity of this deque. Call only when full, i.e., when head
//	 * and tail have wrapped around to become equal.
//	 */
//	private void doubleCapacity() {
//		assert head == tail;
//		int p = head;
//		int n = elements.length;
//		int r = n - p; // number of elements to the right of p
//		int newCapacity = n << 1;
//		if (newCapacity < 0)
//			throw new IllegalStateException("Sorry, deque too big");
//		byte[] a = new byte[newCapacity];
//		System.arraycopy(elements, p, a, 0, r);
//		System.arraycopy(elements, 0, a, r, p);
//		elements = (byte[]) a;
//		head = 0;
//		tail = n;
//	}
//
//}
