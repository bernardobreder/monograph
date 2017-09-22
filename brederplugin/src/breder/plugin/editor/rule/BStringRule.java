package breder.plugin.editor.rule;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;

import breder.plugin.editor.ColorConstants;
import breder.plugin.editor.ColorManager;

public class BStringRule extends SingleLineRule {

	public BStringRule(ColorManager manager) {
		super("\"", "\"", new Token(new TextAttribute(manager
				.getColor(ColorConstants.STRING))));
	}

}
