package editor.scanner.rule;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;

import editor.scanner.Rule;
import editor.scanner.RuleEnum;

public abstract class LimitRule extends Rule {
	
	private int ending;
	
	public LimitRule(Font font, Color color) {
		super(font, color);
	}
	
	@Override
	public RuleEnum accept(int index, int character) {
		String begin = this.getBegin();
		String end = this.getEnd();
		if (index < begin.length()) {
			if (begin.charAt(index) == character) {
				return RuleEnum.YES;
			} else {
				this.reset();
				return RuleEnum.NO;
			}
		} else {
			if (this.ending == 0 && end.charAt(0) == character) {
				this.ending = 1;
			}
			if (this.ending == 0) {
				if (character == -1) {
					this.reset();
					return RuleEnum.NO;
				} else {
					return RuleEnum.YES;
				}
			} else {
				if (end.charAt(this.ending - 1) == character) {
					if (end.length() == this.ending) {
						this.reset();
						return RuleEnum.FINAL;
					} else {
						this.ending++;
						return RuleEnum.YES;
					}
				} else {
					this.reset();
					return RuleEnum.YES;
				}
			}
		}
	}
	
	private void reset() {
		this.ending = 0;
	}
	
	public abstract String getBegin();
	
	public abstract String getEnd();
	
}
