
package breder.compiler.node.lvalue;

import java.io.IOException;
import java.util.List;

import breder.compiler.compiler.BinaryOutputStream;
import breder.compiler.compiler.Context;
import breder.compiler.exception.BrederJRuntimeException;
import breder.compiler.node.rvalue.RIdentify;
import breder.compiler.node.rvalue.RValue;
import breder.compiler.node.standart.BClass;
import breder.compiler.node.standart.BParam;
import breder.compiler.node.standart.BSource;
import breder.compiler.node.standart.BType;
import breder.compiler.node.standart.VariableDeclare;
import breder.compiler.parser.javacc.ParseException;
import breder.compiler.parser.javacc.Token;

public class LIdentify extends LValue {

	protected final Token name;

	protected Integer index;

	protected VariableDeclare declare;

	protected BParam param;

	protected BClass clazz;

	protected boolean isStatic;

	public LIdentify(Token name) {
		super();
		this.name = name;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws ParseException
	 */
	@Override
	public void syntax(Context context) throws ParseException {
		List<BParam> params = context.getMethod().getParams();
		List<VariableDeclare> declares = context.getMethod().getDeclares();
		{
			VariableDeclare declare = context.findDeclare(this.getToken().image);
			if (declare != null) {
				if (!declare.isDeclared()) {
					throw new BrederJRuntimeException(context, this.getToken(), "variable not declared yet");
				}
				int n = context.getMethod().getDeclares().indexOf(declare);
				this.index = declares.size() - n - 1;
				this.setTypes(new BType[] { declare.getType() });
				return;
			}
		}
		{
			for (int n = 0; n < params.size(); n++) {
				BParam param = params.get(n);
				if (param.getName().image.equals(this.name.image)) {
					this.param = param;
					this.index = declares.size() + n;
					this.setTypes(new BType[] { this.param.getType() });
					return;
				}
			}
		}
		{
			BSource source = context.getSource().findClass(context, this.name);
			if (source != null) {
				clazz = (BClass) source.getStruct();
				this.setTypes(new BType[] { new BType(name, clazz) });
				return;
			}
		}
		throw new BrederJRuntimeException(context, this.getToken(), "not found reference for this token");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void semantic(Context context) {
		if (this.declare != null) {
			this.declare.semantic(context);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void check(Context context) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void build(Context context, BinaryOutputStream output) throws IOException {
		if (this.index != null) {
			int index = context.getMethod().getDeclares().size() - this.index - 1;
			output.printLoad(index, this.getName());
		} else if (this.param != null) {
			throw new RuntimeException();
		} else {
		}
	}

	@Override
	public RValue buildRValue(Context context) throws ParseException {
		return new RIdentify(this.getName());
	}

	public Token getName() {
		return name;
	}

	public BParam getDeclare() {
		return declare;
	}

	public BClass getClazz() {
		return clazz;
	}

	public boolean isStatic() {
		return this.clazz != null;
	}

	@Override
	public Token getToken() {
		return this.name;
	}

	public Integer getIndex() {
		return this.index;
	}

}
