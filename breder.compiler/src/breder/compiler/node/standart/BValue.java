
package breder.compiler.node.standart;

import java.io.IOException;

import breder.compiler.compiler.BinaryOutputStream;
import breder.compiler.compiler.Context;
import breder.compiler.node.IValue;
import breder.compiler.node.Node;

public abstract class BValue extends Node implements IValue {

	private BType[] types;

	public int instCount(Context context) throws IOException {
		BinaryOutputStream output = new BFacateBinaryOutputStream(context);
		this.build(context, output);
		return output.getCounter();
	}

	public BType[] getTypes() {
		if (types == null) {
			throw new IllegalStateException();
		}
		return types;
	}

	public void setTypes(BType[] types) {
		this.types = types;
	}

	public BType getType() {
		if (types == null) {
			throw new IllegalStateException();
		}
		return types[0];
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " [token=" + this.getToken().image + "]";
	}

}
