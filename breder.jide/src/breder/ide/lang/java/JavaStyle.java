package breder.ide.lang.java;

import breder.ide.lang.ILanguageStyle;
import breder.ide.lang.generic.IStyle;
import breder.ide.lang.generic.WordStyle;

public class JavaStyle implements ILanguageStyle {
	
	private static final IStyle[] STYLES = new IStyle[] { new WordStyle("package"),
			new WordStyle("import"), new WordStyle("private"), new WordStyle("protected"),
			new WordStyle("public"), new WordStyle("class"), new WordStyle("extends"),
			new WordStyle("implements"), new WordStyle("void"), new WordStyle("int"),
			new WordStyle("char"), new WordStyle("long"), new WordStyle("float"),
			new WordStyle("double"), new WordStyle("boolean"), new WordStyle("short"),
			new WordStyle("static"), new WordStyle("final"), new WordStyle("abstract"),
			new WordStyle("transient"), new WordStyle("finally"), new WordStyle("try"),
			new WordStyle("catch"), new WordStyle("native"), new WordStyle("for"),
			new WordStyle("if"), new WordStyle("while"), new WordStyle("do"), new WordStyle("new"),
			new WordStyle("return"), new WordStyle("true"), new WordStyle("false"),
			new WordStyle("this"), new WordStyle("super"), new WordStyle("null"),
			new WordStyle("break"), new WordStyle("continue"), new WordStyle("switch"),
			new WordStyle("default") };
	
	@Override
	public IStyle[] getAllStyles() {
		return STYLES;
	}
	
}
