package breder.ide.lang.c;

import breder.ide.lang.ILanguageStyle;
import breder.ide.lang.generic.IStyle;
import breder.ide.lang.generic.WordStyle;

public class CStyle implements ILanguageStyle {
	
	private static final IStyle[] STYLES = new IStyle[] { new WordStyle("include"),
			new WordStyle("void"), new WordStyle("int"), new WordStyle("long"),
			new WordStyle("unsigned"), new WordStyle("char"), new WordStyle("return"),
			new WordStyle("self"), new WordStyle("="), new WordStyle("-"), new WordStyle(">"),
			new WordStyle("<"), new WordStyle(","), new WordStyle("{"), new WordStyle("}"),
			new WordStyle("("), new WordStyle(")"), new WordStyle("["), new WordStyle("]") };
	
	@Override
	public IStyle[] getAllStyles() {
		return STYLES;
	}
	
}
