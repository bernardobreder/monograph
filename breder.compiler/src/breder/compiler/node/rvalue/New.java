
package breder.compiler.node.rvalue;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import breder.compiler.compiler.BinaryOutputStream;
import breder.compiler.compiler.Context;
import breder.compiler.exception.BrederJRuntimeException;
import breder.compiler.exception.GenericException;
import breder.compiler.node.IRValue;
import breder.compiler.node.standart.BBasicStruct;
import breder.compiler.node.standart.BMethod;
import breder.compiler.node.standart.BStruct;
import breder.compiler.node.standart.BType;
import breder.compiler.parser.javacc.ParseException;
import breder.compiler.parser.javacc.Token;
import breder.compiler.util.ConvertUtil;
import breder.compiler.util.LightArrayList;

public class New extends RValue {

	private final List<IRValue> params = new LightArrayList<IRValue>();

	private BType type;

	private Integer paramCount;

	private BMethod method;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void syntax(Context context) throws ParseException {
		for (IRValue param : params) {
			param.syntax(context);
		}
		type.syntax(context);
		if (!this.type.getStruct().isInstanceable()) {
			throw new BrederJRuntimeException(context, type.getName(), "can not create a instance of this type");
		}
		type.setNotNull(true);
		this.setTypes(new BType[] { type });
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws ParseException
	 */
	@Override
	public void semantic(Context context) throws ParseException {
		for (IRValue param : params) {
			param.semantic(context);
		}
		{
			List<BType> paramTypes = ConvertUtil.convertRValuesToTypes(context, params);
			List<BMethod> methods = type.getStruct().findMethod(context, null, type.getName(), paramTypes, true);
			this.method = type.getStruct().getMethodClosed(context, type.getName(), methods, paramTypes);
			if (this.method == null) {
				throw new BrederJRuntimeException(context, type.getName(), "not found the constructor");
			}
			this.checkAccess(context);
			paramCount = method.getParams().size();
		}
		{
			if (this.getType().getStruct() instanceof BBasicStruct) {
				BBasicStruct struct = (BBasicStruct) this.getType().getStruct();
				if (struct.getGenerics().size() != this.getType().getGenerics().size()) {
					throw new GenericException(context, this.getType().getName());
				}
			}
		}
	}

	private void checkAccess(Context context) {
		switch (this.method.getAccess()) {
			case PUBLIC: {
				break;
			}
			case PRIVATE: {
				BStruct struct = this.getMethod().getStruct();
				BStruct cstruct = context.getStruct();
				if (!struct.getSource().getClassname().equals(cstruct.getSource().getClassname())) {
					throw new BrederJRuntimeException(context, this.getToken(), "can not access a private method");
				}
				break;
			}
			case PROTECTED: {
				BStruct struct = this.getMethod().getStruct();
				BStruct cstruct = context.getStruct();
				if (!cstruct.canCastTo(struct)) {
					throw new BrederJRuntimeException(context, this.getToken(), "can not access a protected method");
				}
				break;
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
		Call.checkException(context, this, this.getMethod(), this.getToken());
		for (IRValue param : params) {
			param.check(context);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void build(Context context, BinaryOutputStream output) throws IOException {
		{
			int index = Arrays.asList(context.getCompiler().getSources()).indexOf(type.getStruct().getSource());
			output.printNew(index, this.getToken());
		}
		for (int n = this.params.size() - 1; n >= 0; n--) {
			this.params.get(n).build(context, output);
		}
		output.printOJump(paramCount, this.method.getIndex(), this.getToken());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Token getToken() {
		return this.type.getName();
	}

	public List<IRValue> getParams() {
		return params;
	}

	public BType getType() {
		return type;
	}

	public void setType(BType type) {
		this.type = type;
	}

	public BMethod getMethod() {
		return method;
	}

}
