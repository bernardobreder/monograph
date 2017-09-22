package editor.scanner;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;

public abstract class Rule {
	
	private final Color color;
	
	private final Font font;
	
	public Rule(Font font, Color color) {
		super();
		this.color = color;
		this.font = font;
	}
	
	public abstract RuleEnum accept(int index, int character);
	
	public Color getColor() {
		return color;
	}
	
	public Font getFont() {
		return font;
	}
	
}
