
package breder.compiler.node.rvalue;

import java.io.IOException;

import breder.compiler.compiler.BinaryOutputStream;
import breder.compiler.compiler.CompilerConstant;
import breder.compiler.compiler.Context;
import breder.compiler.exception.BrederJRuntimeException;
import breder.compiler.node.IRValue;
import breder.compiler.node.standart.BType;
import breder.compiler.parser.javacc.ParseException;
import breder.compiler.parser.javacc.Token;

public class Cast extends RValue {

	private final BType type;

	private IRValue value;

	private Short classIndex;

	private Short notnullClassIndex;

	public Cast(BType type, IRValue value) {
		super();
		this.type = type;
		this.value = value;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void syntax(Context context) throws ParseException {
		this.value.setParent(this);
		type.syntax(context);
		value.syntax(context);
		this.type.setNotNull(this.type.isNotNull() || value.getType().isNotNull());
		this.setTypes(new BType[] { type });
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws ParseException
	 */
	@Override
	public void semantic(Context context) throws ParseException {
		this.getValue().semantic(context);
		if (this.value.getTypes().length == 0) {
			throw new BrederJRuntimeException(context, this.value.getToken(), "value not return a value");
		}
		BType valueType = this.getValue().getType();
		if (this.getType().isNotNull() && !valueType.isNotNull()) {
			this.notnullClassIndex = type.getStruct().getIndex();
		}
		if (this.getValue().getType().isNotNull() && !this.getType().isNotNull()) {
			this.getType().setNotNull(true);
		}
		if (!(value instanceof RNull)) {
			if (this.getType().isGeneric()
					|| (!valueType.canCastTo(context, null, type) && type.canCastTo(context, null, valueType))) {
				this.classIndex = type.getStruct().getIndex();
			} else if (!valueType.canCastTo(context, null, type)) {
				throw new BrederJRuntimeException(context, type.getName(), "can not cast '%s' to '%s'",
						valueType.getStruct(), type.getStruct());
			}
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws ParseException
	 */
	@Override
	public void check(Context context) throws ParseException {
		value.check(context);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void build(Context context, BinaryOutputStream output) throws IOException {
		value.build(context, output);
		if (this.value.getType().getStruct().getSource().getClassname().equals(CompilerConstant.NUMBER_CLASS)) {
			output.printCastNumberToInteger(value.getToken());
		}
		if (this.classIndex != null) {
			output.printCast(classIndex, this.getValue().getToken());
		}
		if (this.notnullClassIndex != null) {
			output.printNNCast(notnullClassIndex, this.getValue().getToken());
		}
	}

	@Override
	public Token getToken() {
		return type.getName();
	}

	public BType getType() {
		return type;
	}

	public IRValue getValue() {
		return value;
	}

	public void setValue(RValue value) {
		this.value = value;
	}

}
