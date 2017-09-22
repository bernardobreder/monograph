
package breder.compiler.node.standart;

import breder.compiler.compiler.Context;
import breder.compiler.exception.BrederJRuntimeException;
import breder.compiler.node.IAccess;
import breder.compiler.parser.javacc.ParseException;
import breder.compiler.parser.javacc.Token;

public class BField implements IAccess {

	private BAccess access;

	private BStruct struct;

	private Token name;

	private BType type;

	private boolean isStatic;

	private int globalIndex;

	private Integer nameIndex;

	private Integer index;

	private static int cindex;

	public BField() {
		this.index = cindex++;
	}

	public void preCheck(Context context) {
		if (this.getAccess() == BAccess.PUBLIC && !this.isStatic()) {
			throw new BrederJRuntimeException(context, this.name, "a public field only with static declaration");
		}
		if (this.getAccess() == BAccess.PROTECTED && !this.isStatic()) {
			throw new BrederJRuntimeException(context, this.name, "a protected field only with static declaration");
		}
	}

	public void syntax(Context context) throws ParseException {
		this.preCheck(context);
		type.syntax(context);
		type.setNotNull(true);
		this.nameIndex = context.getCompiler().addConstant(this.getCompleteName());
	}

	public void semantic(Context context) {
	}

	public void check(Context context) {
	}

	public int getNameIndex() {
		return nameIndex;
	}

	public Token getName() {
		return name;
	}

	public void setName(Token name) {
		this.name = name;
	}

	public BType getType() {
		return type;
	}

	public void setType(BType type) {
		this.type = type;
	}

	public boolean isStatic() {
		return isStatic;
	}

	public void setStatic(boolean isStatic) {
		this.isStatic = isStatic;
	}

	public Integer getGlobalIndex() {
		return globalIndex;
	}

	public void setGlobalIndex(int index) {
		this.globalIndex = index;
	}

	public BAccess getAccess() {
		return access;
	}

	public void setAccess(BAccess access) {
		this.access = access;
	}

	public BStruct getStruct() {
		return struct;
	}

	public void setStruct(BStruct struct) {
		this.struct = struct;
	}

	@Override
	public String toString() {
		return this.getCompleteName();
	}

	public String getCompleteName() {
		if (this.isStatic()) {
			return type.toString() + "." + name.image;
		} else {
			return type.toString() + " " + name.image;
		}
	}

	public Integer getIndex() {
		return this.index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

}
