package editors.java.rule;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;

import editor.scanner.rule.LineRule;

public class LineJavaDocRule extends LineRule {
	
	public LineJavaDocRule(Font font, Color color) {
		super(font, color);
	}
	
	@Override
	public String getBegin() {
		return "//";
	}
	
}
