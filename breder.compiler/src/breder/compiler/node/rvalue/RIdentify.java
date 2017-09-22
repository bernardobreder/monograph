
package breder.compiler.node.rvalue;

import java.io.IOException;
import java.util.List;

import breder.compiler.compiler.BinaryOutputStream;
import breder.compiler.compiler.Context;
import breder.compiler.exception.BrederJRuntimeException;
import breder.compiler.node.standart.BParam;
import breder.compiler.node.standart.BSource;
import breder.compiler.node.standart.BType;
import breder.compiler.node.standart.VariableDeclare;
import breder.compiler.parser.javacc.ParseException;
import breder.compiler.parser.javacc.Token;
import breder.compiler.util.SourceUtil;

public class RIdentify extends RValue {

	private final Token token;

	private Integer index;

	private BSource source;

	public RIdentify(Token name) {
		super();
		this.token = name;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void syntax(Context context) throws ParseException {
		{
			VariableDeclare declare = context.findDeclare(this.getToken().image);
			if (declare != null) {
				if (!declare.isDeclared()) {
					throw new BrederJRuntimeException(context, this.getToken(), "variable not declared yet");
				}
				int n = context.getMethod().getDeclares().indexOf(declare);
				this.index = context.getMethod().getDeclares().size() - n - 1;
				this.setTypes(new BType[] { declare.getType() });
				return;
			}
		}
		{
			List<BParam> params = context.getMethod().getParams();
			for (int n = 0; n < params.size(); n++) {
				BParam declare = params.get(n);
				if (declare.getName().image.equals(this.token.image)) {
					this.index = n;
					this.index += context.getMethod().getDeclares().size();
					this.setTypes(new BType[] { declare.getType() });
					return;
				}
			}
		}
		{
			source = SourceUtil.findType(context, token);
			if (source != null) {
				this.setTypes(new BType[] { new BType(token, source.getStruct(), false, true) });
				return;
			}
		}
		throw new BrederJRuntimeException(context, token, "not found reference for this token");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void semantic(Context context) {
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
		if (index != null) {
			output.printLoad(index, this.getToken());
		} else {
			throw new BrederJRuntimeException(context, this.getToken(), "not found the variable with this name");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Token getToken() {
		return token;
	}

	public boolean isStatic() {
		return source != null;
	}

	public Integer getIndex() {
		return this.index;
	}

}
