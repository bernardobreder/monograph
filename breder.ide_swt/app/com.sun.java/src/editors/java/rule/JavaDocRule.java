package editors.java.rule;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;

import editor.scanner.rule.LimitRule;

public class JavaDocRule extends LimitRule {
	
	public JavaDocRule(Font font, Color color) {
		super(font, color);
	}
	
	@Override
	public String getBegin() {
		return "/**";
	}
	
	@Override
	public String getEnd() {
		return "*/";
	}
}
