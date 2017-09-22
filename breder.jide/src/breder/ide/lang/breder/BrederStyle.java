package breder.ide.lang.breder;

import breder.ide.lang.ILanguageStyle;
import breder.ide.lang.generic.IStyle;
import breder.ide.lang.generic.WordStyle;

public class BrederStyle implements ILanguageStyle {
	
	private static final IStyle[] STYLES = new IStyle[] { new WordStyle("public"),
			new WordStyle("class"), new WordStyle("extends"), new WordStyle("implements"),
			new WordStyle("import"), new WordStyle("package"), new WordStyle("return"),
			new WordStyle("static"), new WordStyle("private"), new WordStyle("new"),
			new WordStyle("this"), new WordStyle("="), new WordStyle("-"), new WordStyle(">"),
			new WordStyle("<"), new WordStyle(","), new WordStyle("{"), new WordStyle("}"),
			new WordStyle("("), new WordStyle(")"), new WordStyle("["), new WordStyle("]") };
	
	@Override
	public IStyle[] getAllStyles() {
		return STYLES;
	}
	
}
