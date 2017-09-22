package breder.compiler.compiler;

import java.io.IOException;

public abstract class AbstractCompilerOutputStream {

	protected final SkipByteArrayOutputStream output;

	private final Context context;

	protected int counter;

	protected int stack;

	public AbstractCompilerOutputStream(Context context,
			SkipByteArrayOutputStream output) {
		this.output = output;
		this.context = context;
	}

	public SkipByteArrayOutputStream getOutput() {
		return output;
	}

	public Context getContext() {
		return context;
	}

	public void print(char c) throws IOException {
		output.write(c);
	}

	public void print(int c) throws IOException {
		this.print("" + c);
	}

	public void print(String text) throws IOException {
		output.write(text.getBytes());
	}

	public void decStack(int value) {
		stack -= value;
	}

	public void incStack(int value) {
		stack += value;
	}

	public int getStack() {
		return this.stack;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public void setStack(int stack) {
		this.stack = stack;
	}

	public void close() throws IOException {
		output.close();
	}

}
