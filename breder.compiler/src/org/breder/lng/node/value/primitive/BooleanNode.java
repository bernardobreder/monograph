package org.breder.lng.node.value.primitive;

import java.io.IOException;

import org.breder.lng.util.AbstractDataOutputStream;
import org.breder.lng.util.AbstractOpcodeOutputStream;

public class BooleanNode extends PrimitiveNode {

	private static final BooleanNode TRUE = new BooleanNode();

	private static final BooleanNode FALSE = new BooleanNode();

	private BooleanNode() {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void build(AbstractOpcodeOutputStream output) throws IOException {
		if (this == TRUE) {
			output.opStackTrue();
		} else {
			output.opStackFalse();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void write(AbstractDataOutputStream output) throws IOException {
		if (this == TRUE) {
			output.writeIndex(TRUE_VALUE);
		} else {
			output.writeIndex(FALSE_VALUE);
		}
	}

	/**
	 * Constroi um objeto
	 * 
	 * @param flag
	 * @return objeto boolean
	 */
	public static BooleanNode build(boolean flag) {
		if (flag) {
			return TRUE;
		} else {
			return FALSE;
		}
	}

}
