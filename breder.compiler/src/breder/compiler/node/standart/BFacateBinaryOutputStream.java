
package breder.compiler.node.standart;

import breder.compiler.compiler.BinaryOutputStream;
import breder.compiler.compiler.Context;

public class BFacateBinaryOutputStream extends BinaryOutputStream {

	public BFacateBinaryOutputStream(Context context) {
		super(context, new BFacateOutputStream());
	}

	@Override
	public void printlnInt(int value, short cindex, short line) {
		this.counter++;
	}

}
