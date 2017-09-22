
package breder.compiler.node.lvalue;

import java.io.IOException;

import breder.compiler.compiler.BinaryOutputStream;
import breder.compiler.compiler.Context;
import breder.compiler.exception.BrederJRuntimeException;
import breder.compiler.node.ILValue;
import breder.compiler.node.rvalue.RGetField;
import breder.compiler.node.rvalue.RValue;
import breder.compiler.node.standart.BClass;
import breder.compiler.node.standart.BField;
import breder.compiler.node.standart.BType;
import breder.compiler.parser.javacc.ParseException;
import breder.compiler.parser.javacc.Token;

public class LSetField extends LValue {

	private final ILValue left;

	private final Token name;

	private boolean isStatic;

	private BField field;

	public LSetField(ILValue left, Token name) {
		super();
		this.left = left;
		this.name = name;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RValue buildRValue(Context context) throws ParseException {
		return new RGetField(this.left.buildRValue(context), name);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void syntax(Context context) throws ParseException {
		left.syntax(context);
		BClass clazz = (BClass) left.getType().getStruct();
		this.field = clazz.findField(context, name, this.isStatic());
		if (this.field == null) {
			throw new BrederJRuntimeException(context, name, "field not found");
		}
		this.field.syntax(context);
		this.setTypes(new BType[] { field.getType() });
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws ParseException
	 */
	@Override
	public void semantic(Context context) throws ParseException {
		left.semantic(context);
		if (left.getTypes().length == 0) {
			throw new BrederJRuntimeException(context, left.getToken(), "lvalue not return a value");
		}
		this.field.semantic(context);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws ParseException
	 */
	@Override
	public void check(Context context) throws ParseException {
		left.check(context);
		if (!this.isStatic() && this.getLeft().getClass() != LThis.class) {
			throw new BrederJRuntimeException(context, this.getLeft().getToken(),
					"field can only be access by the keywork 'this'");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void build(Context context, BinaryOutputStream output) throws IOException {
		boolean isStatic = this.isStatic();
		if (isStatic) {
			output.printSetStaticField(this.getField().getGlobalIndex(), this.getToken());
		} else {
			left.build(context, output);
			output.printSetField(this.getField().getIndex(), this.getLeft().getToken());
		}

	}

	public boolean isStatic() {
		return this.left instanceof LIdentify && ((LIdentify) left).isStatic();
	}

	public ILValue getLeft() {
		return left;
	}

	public Token getName() {
		return name;
	}

	public BField getField() {
		return field;
	}

	@Override
	public Token getToken() {
		return this.name;
	}

}
