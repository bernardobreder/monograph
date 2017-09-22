
package breder.compiler.node.rvalue;

import java.io.IOException;

import breder.compiler.compiler.BinaryOutputStream;
import breder.compiler.compiler.CompilerConstant;
import breder.compiler.compiler.Context;
import breder.compiler.exception.BrederJRuntimeException;
import breder.compiler.node.standart.BSource;
import breder.compiler.node.standart.BType;
import breder.compiler.parser.javacc.ParseException;
import breder.compiler.parser.javacc.Token;

public class RNumber extends RPrimitive {

	private int index;

	private double value;

	public RNumber(Token token) {
		super(token);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void syntax(Context context) throws ParseException {
		BSource source;
		double value = Double.valueOf(this.getToken().image);
		if (value == (int) value) {
			if (value < 0) {
				source = context.getCompiler().findSource(CompilerConstant.INTEGER_CLASS);
			} else if (value == 0) {
				source = context.getCompiler().findSource(CompilerConstant.NATURAL_CLASS);
			} else {
				source = context.getCompiler().findSource(CompilerConstant.INDEX_CLASS);
			}
		} else {
			source = context.getCompiler().findSource(CompilerConstant.NUMBER_CLASS);
		}
		this.setTypes(new BType[] { new BType(source.getStruct().getName(), source.getStruct(), false, true) });
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void semantic(Context context) {
		try {
			this.value = new Double(this.getToken().image).doubleValue();
			index = context.getCompiler().addNumberConstant(value);
		} catch (NumberFormatException e) {
			throw new BrederJRuntimeException(context, this.getToken(), "number '%s' with diferent especification",
					this.getToken().image);
		}
	}

	@Override
	public void check(Context context) {
		super.check(context);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void build(Context context, BinaryOutputStream output) throws IOException {
		output.printConstNumber(index, this.getToken());
	}

	public boolean isIndex() {
		double value = Double.valueOf(this.getToken().image);
		return value == (int) value && value > 0;
	}

	public boolean isNatural() {
		double value = Double.valueOf(this.getToken().image);
		return value == (int) value && value >= 0;
	}

	public boolean isInteger() {
		double value = Double.valueOf(this.getToken().image);
		return value == (int) value;
	}

	public int getIndex() {
		return index;
	}

	public double getValue() {
		return value;
	}

}
