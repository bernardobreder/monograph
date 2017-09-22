
package breder.compiler.node.standart;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import breder.compiler.compiler.AbstractCompiler;
import breder.compiler.compiler.BinaryOutputStream;
import breder.compiler.compiler.Context;
import breder.compiler.exception.BrederJRuntimeException;
import breder.compiler.parser.javacc.ParseException;
import breder.compiler.parser.javacc.Token;
import breder.compiler.parser.javacc.TokenUtil;
import breder.compiler.util.LightArrayList;
import breder.compiler.util.SourceUtil;

public class BSource {

	private final AbstractCompiler compiler;

	private final String classname;

	private int classNameIndex;

	private Token packageToken;

	private final List<Token> imports;

	private BStruct struct;

	private Map<String, BSource> sources = new HashMap<String, BSource>();

	public BSource(AbstractCompiler compiler, String className) {
		super();
		this.compiler = compiler;
		this.classname = className;
		this.imports = new LightArrayList<Token>();
	}

	public void syntax(Context context) throws ParseException {
		context.pushSource(this);
		this.classNameIndex = context.getCompiler().addConstant(classname);
		if (this.packageToken == null) {
			this.packageToken = TokenUtil.newInstance("");
		}
		{
			int index = this.classname.lastIndexOf('.');
			if (index == -1) {
				if (!this.packageToken.image.equals("")) {
					throw new BrederJRuntimeException(context, struct.getName(), "package must be empty name");
				}
			} else {
				String name = this.classname.substring(0, index);
				if (!this.packageToken.image.equals(name)) {
					throw new BrederJRuntimeException(context, struct.getName(), "package must be the name '%s'", name);
				}
			}
		}
		{
			int index = classname.lastIndexOf('.');
			if (index == -1) {
			} else {
			}
		}
		if (this.packageToken.image.length() > 0) {
			this.addImport(TokenUtil.newInstance(this.packageToken.image + ".*"));
		}
		String[] defaults = new String[] { "breder.lang", "breder.util", "breder.math", "breder.io", "breder.net",
				"breder.gui", "breder.sql", "breder.lang.standard", "breder.util.standard", "breder.math.standard",
				"breder.io.standard", "breder.net.standard", "breder.gui.standard", "breder.sql.standard" };
		for (String sdefault : defaults)
			if (!this.packageToken.image.equals(sdefault)) {
				this.addImport(TokenUtil.newInstance(sdefault + ".*"));
			}
		this.struct.syntax(context);
		context.popSource();
	}

	public void addImport(Token token) {
		for (Token importToken : this.imports) {
			if (importToken.image.equals(token.image)) {
				return;
			}
		}
		this.imports.add(token);
	}

	public void semanticHeader(Context context) throws ParseException {
		context.pushSource(this);
		context.pushClassname(classname);
		this.struct.semanticHeader(context);
		context.popClassname();
		context.popSource();
	}

	public void semanticBody(Context context) throws ParseException {
		context.pushSource(this);
		context.pushClassname(classname);
		this.struct.semanticBody(context);
		context.popClassname();
		context.popSource();
	}

	public void check(Context context) throws ParseException {
		context.pushSource(this);
		context.pushClassname(classname);
		this.struct.check(context);
		context.popClassname();
		context.popSource();
	}

	public void memory(Context context) throws IOException {
		context.pushSource(this);
		context.pushClassname(classname);
		struct.memory(context);
		context.popClassname();
		context.popSource();
	}

	public void build(Context context, BinaryOutputStream output) throws IOException {
		context.pushSource(this);
		context.pushClassname(classname);
		struct.build(context, output);
		context.popClassname();
		context.popSource();
	}

	public Token getPackageToken() {
		return packageToken;
	}

	public void setPackageToken(Token packageToken) {
		this.packageToken = packageToken;
	}

	public BStruct getStruct() {
		return struct;
	}

	public void setStruct(BStruct struct) {
		this.struct = struct;
	}

	public List<Token> getImports() {
		return imports;
	}

	public AbstractCompiler getCompiler() {
		return compiler;
	}

	public String getClassname() {
		return classname;
	}

	public int getClassNameIndex() {
		return classNameIndex;
	}

	public void setClassNameIndex(int classNameIndex) {
		this.classNameIndex = classNameIndex;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((classname == null) ? 0 : classname.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BSource other = (BSource) obj;
		if (classname == null) {
			if (other.classname != null)
				return false;
		} else if (!classname.equals(other.classname))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.getClassname();
	}

	public BSource findClass(Context context, Token simplename) throws ParseException {
		return SourceUtil.findType(context, simplename);
	}

	public void putImport(String image, BSource source) {
		this.sources.put(image, source);
	}

	public BSource getImport(String image) {
		return this.sources.get(image);
	}

	public void body(Context context) throws ParseException {
		context.pushSource(this);
		this.getStruct().body(context);
		context.popSource();
	}

}
