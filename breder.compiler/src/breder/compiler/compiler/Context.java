package breder.compiler.compiler;

import java.util.Stack;

import breder.compiler.node.INode;
import breder.compiler.node.command.Block;
import breder.compiler.node.standart.BMethod;
import breder.compiler.node.standart.BSource;
import breder.compiler.node.standart.BStruct;
import breder.compiler.node.standart.BValue;
import breder.compiler.node.standart.VariableDeclare;

public class Context {

	private final AbstractCompiler compiler;

	private Stack<String> classname = new Stack<String>();

	private final Stack<BSource> source = new Stack<BSource>();

	private final Stack<BStruct> struct = new Stack<BStruct>();

	private final Stack<BMethod> method = new Stack<BMethod>();

	private final Stack<Block> block = new Stack<Block>();

	private final Stack<BValue> value = new Stack<BValue>();

	private CompilerState state;

	private int memory;

	private int stacked;

	public Context(AbstractCompiler compiler) {
		super();
		this.compiler = compiler;
		this.memory = 1;
	}

	public VariableDeclare findDeclare(String name) {
		Block block = this.block.peek();
		while (block != null) {
			VariableDeclare vd = block.findDeclare(name);
			if (vd != null) {
				return vd;
			}
			INode node = block.getParent();
			while (node != null && node instanceof Block == false) {
				node = node.getParent();
			}
			block = (Block) node;
		}
		return null;
	}

	public AbstractCompiler getCompiler() {
		return compiler;
	}

	public void pushSource(BSource item) {
		this.source.push(item);
	}

	public void pushStruct(BStruct item) {
		this.struct.push(item);
	}

	public void pushMethod(BMethod item) {
		this.method.push(item);
	}

	public void popSource() {
		this.source.pop();
	}

	public void popStruct() {
		this.struct.pop();
	}

	public void popMethod() {
		this.method.pop();
	}

	public BSource getSource() {
		return this.source.peek();
	}

	public BStruct getStruct() {
		return this.struct.peek();
	}

	public BMethod getMethod() {
		return this.method.peek();
	}

	public int getMemory() {
		return memory;
	}

	public void setMemory(int memory) {
		this.memory = memory;
	}

	public void incMemory(int value) {
		this.memory += value;
	}

	public int getStacked() {
		return stacked;
	}

	public void setStacked(int stacked) {
		this.stacked = stacked;
	}

	public CompilerState getState() {
		return state;
	}

	public void setState(CompilerState state) {
		this.state = state;
	}

	public String getClassname() {
		return classname.peek();
	}

	public void pushClassname(String classname) {
		this.classname.push(classname);
	}

	public void popClassname() {
		this.classname.pop();
	}

	public void pushValue(BValue value) {
		this.value.push(value);
	}

	public void popValue() {
		this.value.pop();
	}

	public void pushBlock(Block value) {
		this.block.push(value);
	}

	public void popBlock() {
		this.block.pop();
	}
}
