package breder.plugin.editor.parser;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.WhitespaceRule;

import breder.plugin.editor.ColorManager;
import breder.plugin.editor.rule.BCommentBlockRule;
import breder.plugin.editor.rule.BCommentLineRule;
import breder.plugin.editor.rule.BKeyWordRule;
import breder.plugin.editor.rule.BStringRule;
import breder.plugin.editor.rule.BSymbolRule;

public class BrederScanner extends RuleBasedScanner {

	public BrederScanner(ColorManager manager) {
		List<IRule> rules = new ArrayList<IRule>();
		rules.add(new WhitespaceRule(new BrederWhitespaceDetector()));
		rules.add(new BCommentLineRule(manager));
		rules.add(new BCommentBlockRule(manager));
		rules.add(BKeyWordRule.getInstance());
		rules.add(new BSymbolRule(manager));
		rules.add(new BStringRule(manager));
		super.setRules(rules.toArray(new IRule[0]));
	}

}
