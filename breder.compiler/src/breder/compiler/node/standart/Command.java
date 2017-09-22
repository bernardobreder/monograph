
package breder.compiler.node.standart;

import java.io.IOException;

import breder.compiler.compiler.BinaryOutputStream;
import breder.compiler.compiler.Context;
import breder.compiler.node.ICommand;
import breder.compiler.node.Node;

public abstract class Command extends Node implements ICommand {

	public int instCount(Context context) throws IOException {
		BinaryOutputStream output = new BFacateBinaryOutputStream(context);
		this.build(context, output);
		return output.getCounter();
	}

	@Override
	public boolean isReturned() {
		return false;
	}

}
