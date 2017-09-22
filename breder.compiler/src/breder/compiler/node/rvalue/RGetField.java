
package breder.compiler.node.rvalue;

import java.io.IOException;

import breder.compiler.compiler.BinaryOutputStream;
import breder.compiler.compiler.Context;
import breder.compiler.exception.BrederJRuntimeException;
import breder.compiler.node.IValue;
import breder.compiler.node.standart.BAccess;
import breder.compiler.node.standart.BClass;
import breder.compiler.node.standart.BField;
import breder.compiler.node.standart.BType;
import breder.compiler.parser.javacc.ParseException;
import breder.compiler.parser.javacc.Token;

public class RGetField extends RValue {

	private final IValue left;

	private final Token token;

	private int index;

	private boolean isStatic;

	private BField field;

	public RGetField(IValue left, Token name) {
		super();
		this.left = left;
		this.token = name;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void syntax(Context context) throws ParseException {
		left.syntax(context);
		{
			isStatic = this.left instanceof RIdentify && ((RIdentify) left).isStatic();
			if (!isStatic && !(this.left instanceof RThis)) {
				throw new BrederJRuntimeException(context, token, "can not access the field");
			}
			BClass clazz = (BClass) left.getType().getStruct();
			this.field = clazz.findField(context, token, isStatic);
			if (field == null) {
				throw new BrederJRuntimeException(context, token, "field '%s' not found", token.image);
			}
			if (field.getAccess() == BAccess.PRIVATE
					&& !field.getStruct().getSource().getClassname().equals(context.getSource().getClassname())) {
				throw new BrederJRuntimeException(context, token, "can not access the field");
			}
			if (field.getAccess() == BAccess.PROTECTED && field.isStatic() && !(clazz.canCastTo(field.getStruct()))) {
				throw new BrederJRuntimeException(context, token, "can not access the field");
			}
			this.setTypes(new BType[] { field.getType() });
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws ParseException
	 */
	@Override
	public void semantic(Context context) throws ParseException {
		left.semantic(context);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws ParseException
	 */
	@Override
	public void check(Context context) throws ParseException {
		this.left.check(context);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void build(Context context, BinaryOutputStream output) throws IOException {
		if (this.isStatic) {
			output.printGetStaticField(this.getField().getGlobalIndex(), this.getToken());
		} else {
			this.left.build(context, output);
			output.printGetField(this.getField().getIndex(), this.getToken());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Token getToken() {
		return token;
	}

	public Integer getIndex() {
		return index;
	}

	public boolean isStatic() {
		return isStatic;
	}

	public BField getField() {
		return field;
	}

	public IValue getLeft() {
		return left;
	}

}
