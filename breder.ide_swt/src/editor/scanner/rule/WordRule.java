package editor.scanner.rule;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;

import editor.scanner.Rule;
import editor.scanner.RuleEnum;

public class WordRule extends Rule {
	
	protected String word;
	
	public WordRule(Font font, Color color, String word) {
		super(font, color);
		this.word = word;
	}
	
	@Override
	public RuleEnum accept(int index, int character) {
		if (word.length() == index - 1) {
			return RuleEnum.NO;
		} else if (word.length() == index) {
			if (isSeparator(character)) {
				return RuleEnum.FINAL;
			} else {
				return RuleEnum.NO;
			}
		} else if (word.charAt(index) == character) {
			return RuleEnum.YES;
		} else {
			return RuleEnum.NO;
		}
	}
	
	public boolean isSeparator(int c) {
		return c == ' ' || c == '\t' || c == '\r' || c == '\n' || c == -1;
	}
	
}
