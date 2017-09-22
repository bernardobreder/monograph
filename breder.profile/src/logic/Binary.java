
package logic;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import logic.instruction.Instruction;
import logic.struct.JPClass;
import logic.struct.JPMethod;
import logic.struct.JPVM;
import breder.util.util.InputStreamUtil;

public class Binary {

	private final InputStream input;

	private byte[] bytes;

	private int index;

	private JPVM vm;

	public Binary(InputStream input) {
		super();
		this.input = input;
		this.vm = new JPVM();
	}

	public void start() throws IOException {
		this.parse();
		this.link();
	}

	private void parse() throws IOException {
		this.cache();
		this.validate();
		this.classStarter();
		this.librarys();
		this.classes();
		this.fields();
		this.methods();
		this.nameNatives();
		this.constants();
		this.constants();
		this.instructions();
	}

	private void fields() throws IOException {
		this.rnInt();
		for (int n = 0; n < this.vm.getClasses().size(); n++) {
			JPClass c = this.vm.getClasses().get(n);
			int sizem = this.rnInt();
			for (int m = 0; m < sizem; m++) {
				int isStatic = this.rnInt();
				String name = this.rnString();
			}
		}
	}

	private void methods() throws IOException {
		int index = 0;
		rnInt();
		for (int n = 0; n < this.vm.getClasses().size(); n++) {
			JPClass c = this.vm.getClasses().get(index);
			int len = rnInt();
			for (int m = 0; m < len; m++) {
				JPMethod method = new JPMethod();
				method.setName(this.rnString());
				int[] values = this.rnInts();
				int[] params = this.rnInts();
				method.setReturns(values[0]);
				method.setIsStatic(values[1]);
				method.setAbstract(values[2] == 1);
				method.setConstructor(values[3] == 1);
				method.setMemIndex(values[4]);
				c.add(method);
				vm.getMethods().add(method);
			}
		}
	}

	private void classes() throws IOException {
		int len = rnInt();
		for (int n = 0; n < len; n++) {
			String classname = this.rnString();
			int[] extend = this.rnInts();
			int[] implement = this.rnInts();
			JPClass c = new JPClass();
			c.setName(classname);
			this.vm.getClasses().add(c);
		}
	}

	private int[] rnInts() throws IOException {
		int length = this.rnInt();
		int[] values = new int[length];
		for (int n = 0; n < length; n++) {
			values[n] = this.rnInt();
		}
		return values;
	}

	private String readString() throws IOException {
		this.check('(');
		int len = this.readInt();
		this.check(',');
		String text = this.readString(len);
		this.check(')');
		return text;
	}

	private void link() {
		this.linkMethods();
	}

	private void linkMethods() {
		int mem = 1;
		for (int n = 0; n < vm.getMethods().size(); n++) {
			JPMethod method = vm.getMethods().get(n);
			if (!method.getIsNative() && !method.isAbstract()) {
				int memindex, index = 1;
				do {
					if (vm.getMethods().size() == n + index) {
						memindex = vm.getInstructions().size();
					} else {
						memindex = vm.getMethods().get(n + index++).getMemIndex();
					}
				} while (memindex == 0);

				int size = memindex - method.getMemIndex();
				for (int m = 0; m < size && mem != vm.getInstructions().size(); m++) {
					method.getInsts().add(vm.getInstructions().get(mem++));
				}
			}
		}
	}

	private void cache() throws IOException {
		this.bytes = InputStreamUtil.getBytes(input);
	}

	private void validate() throws IOException {
		check('B');
		check('B');
	}

	private void classStarter() throws IOException {
		this.rnInt();
	}

	private int rnInt() throws IOException {
		int value = this.readInt();
		this.check('|');
		return value;
	}

	private void constants() throws IOException {
		int len = rnInt();
		for (int n = 0; n < len; n++) {
			String name = rnString();
			vm.getConstants().add(name);
		}
	}

	private void librarys() throws IOException {
		int len = rnInt();
		for (int n = 0; n < len; n++) {
			String library = rnString();
		}
	}

	private String rnString() throws IOException {
		int length = this.rnInt();
		String value = this.readString(length);
		this.check('|');
		return value;
	}

	private void nameNatives() throws IOException {
		int len = rnInt();
		for (int n = 0; n < len; n++) {
			int[] values = this.rnInts();
			this.rnString();
			int mindex = values[0];
			this.vm.getMethods().get(mindex).setIsNative(true);
		}
	}

	private void instructions() throws IOException {
		while (!eof()) {
			{
				int h1, h2, h3, h4;
				int inst = (h1 = read()) << 24;
				inst += (h2 = read()) << 16;
				inst += (h3 = read()) << 8;
				inst += (h4 = read());
				this.vm.getInstructions().add(Instruction.newInstance(inst));
			}
			if (false) {
				int h1, h2, h3, h4, h5, h6, h7, h8;
				long inst = (h1 = read()) << 56;
				inst += (h2 = read()) << 48;
				inst += (h3 = read()) << 40;
				inst += (h4 = read()) << 32;
				inst += (h5 = read()) << 24;
				inst += (h6 = read()) << 16;
				inst += (h7 = read()) << 8;
				inst += (h8 = read());
			}
		}
	}

	private boolean eof() {
		return index == bytes.length;
	}

	private boolean can(char c) throws IOException {
		int v = (char) read();
		if (v == c) {
			return true;
		} else {
			index--;
			return false;
		}
	}

	private boolean is(char c) throws IOException {
		int v = (char) read();
		index--;
		if (v == c) {
			return true;
		} else {
			return false;
		}
	}

	private boolean canInt() throws IOException {
		int c = read();
		if (c >= '0' && c <= '9') {
			return true;
		} else {
			index--;
			return false;
		}
	}

	private boolean hasInt() throws IOException {
		int c = read();
		index--;
		if (c >= '0' && c <= '9') {
			return true;
		} else {
			return false;
		}
	}

	private int read() throws IOException {
		return (256 + bytes[index++]) & 0xFF;
	}

	private Integer[] readInts() throws IOException {
		this.check('(');
		int c = this.read();
		int size = c - '0';
		c = this.read();
		if (c == ')') {
			return new Integer[0];
		}
		if (size == '0') {
			return new Integer[0];
		}
		List<Integer> list = new ArrayList<Integer>();
		for (int n = 0; n < size; n++) {
			list.add(this.readInt());
			c = this.read();
		}
		this.check(')');
		return list.toArray(new Integer[0]);
	}

	private Integer[] readInts2() throws IOException {
		this.check('(');
		int c = this.read();
		if (c == ')') {
			return new Integer[0];
		}
		this.backRead();
		List<Integer> list = new ArrayList<Integer>();
		while (true) {
			list.add(this.readInt());
			c = this.read();
			if (c == ')') {
				break;
			} else if (c != ',') {
				throw new IOException("parse exception");
			}
		}
		return list.toArray(new Integer[0]);
	}

	private int readInt() throws IOException {
		int[] cs = new int[10];
		int index = 0;
		int c = read();
		do {
			int value = 1;
			if (c == '-') {
				value = -1;
				c = read();
			}
			cs[index++] = ((char) c - '0') * value;
			c = read();
		} while (c >= '0' && c <= '9');
		this.index--;
		int value = 0;
		for (int n = 0; n < index; n++) {
			value += cs[n] * Math.pow(10, index - n + -1);
		}
		return value;
	}

	public String readString(int len) {
		String value = new String(bytes, index, len);
		index += len;
		return value;
	}

	private void check(int c) throws IOException {
		int v = read();
		if (v != c) {
			throw new IOException();
		}
	}

	public void backRead() {
		this.index--;
	}

	public JPVM getVm() {
		return vm;
	}

}
