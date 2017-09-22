
package breder.compiler.node.standart;

import java.util.List;

import breder.compiler.compiler.Context;
import breder.compiler.exception.BrederJRuntimeException;
import breder.compiler.node.IValue;
import breder.compiler.parser.javacc.ParseException;
import breder.compiler.parser.javacc.Token;
import breder.compiler.util.BStringBuilder;
import breder.compiler.util.LightArrayList;
import breder.compiler.util.SourceUtil;

public class BType implements Cloneable {

	private final Token name;

	private BStruct struct;

	private List<BType> generics = new LightArrayList<BType>();

	private boolean isGeneric;

	private boolean notNull;

	protected String toString;

	private BBasicStruct isGenericClass;

	public BType(Token token) {
		this(token, null);
	}

	public BType(BStruct struct) {
		this(null, struct);
	}

	public BType(Token token, BStruct struct) {
		this(token, struct, false, false);
	}

	public BType(Token token, BStruct struct, boolean isGeneric, boolean notnull) {
		super();
		this.name = token;
		this.struct = struct;
		this.isGeneric = isGeneric;
		this.notNull = notnull;
	}

	public void syntax(Context context) throws ParseException {
		BStruct struct = context.getStruct();
		for (BType generic : this.getGenerics()) {
			generic.syntax(context);
		}
		if (this.struct == null) {
			if (struct instanceof BBasicStruct) {
				BBasicStruct bstruct = (BBasicStruct) struct;
				for (BGeneric generic : bstruct.getGenerics()) {
					if (generic.getToken().image.equals(this.name.image)) {
						this.struct = generic.getType().getStruct();
						this.isGeneric = true;
						this.isGenericClass = bstruct;
					}
				}
			}
		}
		if (this.struct == null) {
			this.struct = SourceUtil.findType(context, name).getStruct();
			if (!this.isGeneric() && this.struct.getGenerics().size() != this.getGenerics().size()) {
				throw new BrederJRuntimeException(context, this.getName(), "number different of generic");
			}
		}
		if (this.struct == null) {
			throw new BrederJRuntimeException(context, name, "can not found the type with name '%s'", name.image);
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof BType)) {
			return false;
		}
		BType type = (BType) obj;
		return this.getStruct().getSource().getClassname().equals(type.getStruct().getSource().getClassname());
	}

	public boolean canCastTo(Context context, IValue value, BType type) {
		BStruct lstruct = this.getStruct(value);
		BStruct rstruct = type.getStruct(value);
		boolean flag = lstruct.canCastTo(rstruct);
		if (!flag) {
			return false;
		}
		if (lstruct instanceof BBasicStruct && rstruct instanceof BBasicStruct) {
			BBasicStruct lbstruct = (BBasicStruct) lstruct;
			BBasicStruct rbstruct = (BBasicStruct) rstruct;
			if (lbstruct.getGenerics().size() > 0 && rbstruct.getGenerics().size() > 0) {
				if (lbstruct.getGenerics().size() != rbstruct.getGenerics().size() && !type.isGeneric()
						&& !this.isGeneric()) {
					return false;
				}
				type = type.getTypeGenericed(value);
				for (int n = 0; n < this.getGenerics().size(); n++) {
					BType generic1 = this.getGenerics().get(n);
					BType generic2 = type.getGenerics().get(n);
					if (!generic1.equals(generic2)) {
						return false;
					}
				}
			}
		}
		return true;
	}

	public Token getName() {
		return name;
	}

	public BStruct getStruct() {
		return this.getStruct((IValue) null);
	}

	public BStruct getStruct(IValue value) {
		if (value != null && this.isGeneric()) {
			return this.getGenericStruct(value.getType());
		}
		return this.struct;
	}

	public BStruct getGenericStruct(BType ltype) {
		BBasicStruct lstruct = (BBasicStruct) ltype.getStruct();
		if (ltype.getStruct().equals(this.getIsGenericClass())) {
			for (int n = 0; n < lstruct.getGenerics().size(); n++) {
				BGeneric generic = lstruct.getGenerics().get(n);
				if (generic.getToken().image.equals(this.getName().image)) {
					return ltype.getGenerics().get(n).getStruct();
				}
			}
		} else {
			for (BType etype : lstruct.getExtends()) {
				BStruct struct = this.getStruct(etype);
				if (struct != null) {
					return struct;
				}
			}
			for (BType itype : lstruct.getImplements()) {
				BStruct struct = this.getStruct(itype);
				if (struct != null) {
					return struct;
				}
			}
		}
		return this.getStruct();
	}

	public BType getTypeGenericed(IValue value) {
		if (value != null && this.isGeneric()) {
			BType ltype = value.getType();
			BBasicStruct lstruct = (BBasicStruct) ltype.getStruct();
			for (int n = 0; n < lstruct.getGenerics().size(); n++) {
				BGeneric generic = lstruct.getGenerics().get(n);
				if (generic.getToken().image.equals(this.getName().image)) {
					try {
						BType rtype = (BType) ltype.getGenerics().get(n).clone();
						rtype.setNotNull(this.isNotNull());
						return rtype;
					} catch (CloneNotSupportedException e) {
						throw new RuntimeException();
					}
				}
			}
			throw new BrederJRuntimeException("bug");
		} else {
			return this;
		}
	}

	public boolean isGeneric() {
		return isGeneric;
	}

	public List<BType> getGenerics() {
		return generics;
	}

	public void addGeneric(BType type) {
		this.generics.add(type);
	}

	@Override
	public String toString() {
		if (this.toString == null) {
			if (this.isGeneric()) {
				String value = "<" + this.getName().image + " " + this.getStruct().toString() + ">";
				return value;
			} else {
				BStringBuilder sb = new BStringBuilder();
				sb.append(this.getStruct().toString());
				if (this.getGenerics().size() > 0) {
					sb.append("<");
					for (int n = 0; n < this.getGenerics().size(); n++) {
						sb.append(this.getGenerics().get(n).toString());
						if (n != this.getGenerics().size() - 1) {
							sb.append(",");
						}
					}
					sb.append(">");
				}
				this.toString = sb.toString();
			}
		}
		return this.toString;
	}

	public BStruct getStruct(BType type) {
		if (this.isGeneric() && type.getStruct() instanceof BBasicStruct) {
			String genericName = this.getName().image;
			BBasicStruct bstruct = (BBasicStruct) type.getStruct();
			for (int n = 0; n < bstruct.getGenerics().size(); n++) {
				BGeneric generic = bstruct.getGenerics().get(n);
				if (generic.getToken().image.equals(genericName)) {
					return type.getGenerics().get(n).getStruct();
				}
			}

		}
		return this.getStruct();
	}

	public boolean isNotNull() {
		return notNull;
	}

	public void setNotNull(boolean notNull) {
		this.notNull = notNull;
	}

	public BBasicStruct getIsGenericClass() {
		return isGenericClass;
	}

	public void setStruct(BStruct struct) {
		this.struct = struct;
	}

}
