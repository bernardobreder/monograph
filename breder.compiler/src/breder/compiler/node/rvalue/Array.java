
package breder.compiler.node.rvalue;

import java.io.IOException;

import breder.compiler.compiler.BinaryOutputStream;
import breder.compiler.compiler.Context;
import breder.compiler.exception.BrederJRuntimeException;
import breder.compiler.node.IRValue;
import breder.compiler.node.standart.BType;
import breder.compiler.parser.javacc.ParseException;
import breder.compiler.parser.javacc.Token;

public class Array extends RValue {

	private final IRValue left;

	private final RNumber index;

	private Integer number;

	public Array(IRValue left, RNumber index) {
		super();
		this.left = left;
		this.index = index;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void syntax(Context context) throws ParseException {
		this.left.setParent(this);
		this.index.setParent(this);
		this.left.syntax(context);
		this.index.syntax(context);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws ParseException
	 */
	@Override
	public void semantic(Context context) throws ParseException {
		this.left.semantic(context);
		this.index.semantic(context);
		if (this.left.getTypes().length < 2) {
			throw new BrederJRuntimeException(context, this.getIndex().getToken(),
					"operator array only for expression with more 2 returns");
		}
		number = new Integer(this.index.getToken().image);
		{
			if (number <= 0 || number > this.left.getTypes().length) {
				throw new BrederJRuntimeException(context, this.index.getToken(), "index must be between %d and %d", 1,
						this.left.getTypes().length);
			}
			BType type = this.getLeft().getTypes()[number - 1];
			this.setTypes(new BType[] { new BType(this.index.getToken(), type.getStruct(this.getLeft())) });
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws ParseException
	 */
	@Override
	public void check(Context context) throws ParseException {
		this.left.check(context);
		this.index.check(context);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void build(Context context, BinaryOutputStream output) throws IOException {
		this.left.build(context, output);
		{
			int returns = this.getLeft().getTypes().length;
			output.printArray(returns, number);
		}
	}

	@Override
	public Token getToken() {
		return left.getToken();
	}

	public IRValue getLeft() {
		return left;
	}

	public RValue getIndex() {
		return index;
	}

}
