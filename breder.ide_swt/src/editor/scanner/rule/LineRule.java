package editor.scanner.rule;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;

import editor.scanner.Rule;
import editor.scanner.RuleEnum;

public abstract class LineRule extends Rule {
	
	private int begin;
	
	public LineRule(Font font, Color color) {
		super(font, color);
	}
	
	public abstract String getBegin();
	
	@Override
	public RuleEnum accept(int index, int character) {
		String start = this.getBegin();
		if (index < start.length()) {
			if (start.charAt(begin) == character) {
				this.begin++;
			} else {
				this.reset();
				return RuleEnum.NO;
			}
		} else if (character == '\n' || character == -1) {
			this.reset();
			return RuleEnum.FINAL;
		}
		return RuleEnum.YES;
	}
	
	public void reset() {
		this.begin = 0;
	}
	
}
