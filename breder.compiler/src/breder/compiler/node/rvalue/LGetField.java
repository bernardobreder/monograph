
package breder.compiler.node.rvalue;

import java.io.IOException;

import breder.compiler.compiler.BinaryOutputStream;
import breder.compiler.compiler.Context;
import breder.compiler.exception.BrederJRuntimeException;
import breder.compiler.node.ILValue;
import breder.compiler.node.lvalue.LIdentify;
import breder.compiler.node.lvalue.LValue;
import breder.compiler.node.standart.BAccess;
import breder.compiler.node.standart.BClass;
import breder.compiler.node.standart.BField;
import breder.compiler.node.standart.BType;
import breder.compiler.parser.javacc.ParseException;
import breder.compiler.parser.javacc.Token;

public class LGetField extends LValue {

	private final ILValue left;

	private final Token token;

	private Integer index;

	private boolean isStatic;

	public LGetField(ILValue left, Token name) {
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
			this.isStatic = left instanceof LIdentify && ((LIdentify) left).getClazz() != null;
			BClass clazz = (BClass) left.getType().getStruct();
			BField field = clazz.findField(context, token, isStatic);
			if (field == null) {
				throw new BrederJRuntimeException(context, token, "field '%s' not found", token.image);
			}
			if (field.getAccess() == BAccess.PRIVATE
					&& !field.getStruct().getSource().getClassname().equals(clazz.getSource().getClassname())) {
				throw new BrederJRuntimeException(context, token, "can not access the field");
			}
			if (field.getAccess() == BAccess.PROTECTED && field.isStatic() && !(clazz.canCastTo(field.getStruct()))) {
				throw new BrederJRuntimeException(context, token, "can not access the field");
			}
			if (context.getMethod().isStatic() && !field.isStatic()) {
				throw new BrederJRuntimeException(context, token, "can not use field in static method");
			}
			{
				field.syntax(context);
				this.index = field.getNameIndex();
			}
			{
				BType type = new BType(this.token, field.getType().getStruct(this.getLeft()));
				this.setTypes(new BType[] { type });
			}
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
		if (!this.isStatic) {
			this.left.semantic(context);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void build(Context context, BinaryOutputStream output) throws IOException {
		if (this.isStatic) {
			output.printGetStaticField(index, this.getToken());
		} else {
			this.left.build(context, output);
			output.printSetField(index, this.getToken());
		}
	}

	@Override
	public RValue buildRValue(Context context) throws ParseException {
		return new RGetField(this.left.buildRValue(context), this.token);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Token getToken() {
		return token;
	}

	public ILValue getLeft() {
		return left;
	}

}
