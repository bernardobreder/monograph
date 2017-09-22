package editor.scanner.rule;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;

import editor.scanner.Rule;
import editor.scanner.RuleEnum;

public class SymbolRule extends Rule {
	
	protected String word;
	
	public SymbolRule(Font font, Color color, String word) {
		super(font, color);
		this.word = word;
	}
	
	@Override
	public RuleEnum accept(int index, int character) {
		if (word.charAt(index) == character) {
			if (word.length() == index + 1) {
				return RuleEnum.FINAL;
			} else {
				return RuleEnum.YES;
			}
		} else {
			return RuleEnum.NO;
		}
	}
	
}
