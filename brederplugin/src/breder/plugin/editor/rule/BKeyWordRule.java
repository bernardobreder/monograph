package breder.plugin.editor.rule;

import java.io.File;

import org.eclipse.swt.graphics.RGB;

import breder.plugin.editor.ColorConstants;
import breder.plugin.editor.ColorManager;
import breder.plugin.util.BrederLanguageFile;
import breder.plugin.util.FileUtil;

public class BKeyWordRule extends BWordRule {

	private static final BKeyWordRule instance = new BKeyWordRule(
			new ColorManager());

	private BKeyWordRule(ColorManager manager) {
		super(new BWordDetector(), manager);
		this.addWord("import", ColorConstants.DARK_RED);
		this.addWord("package", ColorConstants.DARK_RED);
		this.addWord("public", ColorConstants.RED);
		this.addWord("protected", ColorConstants.RED);
		this.addWord("private", ColorConstants.RED);
		this.addWord("class", ColorConstants.RED);
		this.addWord("interface", ColorConstants.RED);
		this.addWord("extends", ColorConstants.RED);
		this.addWord("implements", ColorConstants.RED);
		this.addWord("abstract", ColorConstants.DARK_RED);
		this.addWord("final", ColorConstants.DARK_RED);
		this.addWord("static", ColorConstants.DARK_RED);
		this.addWord("native", ColorConstants.DARK_RED);
		this.addWord("return", ColorConstants.RED);
		this.addWord("new", ColorConstants.RED);
		this.addWord("for", ColorConstants.RED);
		this.addWord("or", ColorConstants.RED);
		this.addWord("and", ColorConstants.RED);
		this.addWord("throw", ColorConstants.RED);
		this.addWord("throws", ColorConstants.DARK_RED);
		this.addWord("function", ColorConstants.RED);
		this.addWord("super", ColorConstants.RED);
		this.addWord("this", ColorConstants.RED);
		this.addWord("true", ColorConstants.RED);
		this.addWord("false", ColorConstants.RED);
		this.addWord("null", ColorConstants.RED);
		this.addWord("to", ColorConstants.RED);
		this.addWord("void", ColorConstants.DARK_GREEN);
		this.addWord("try", ColorConstants.RED);
		this.addWord("catch", ColorConstants.RED);
		this.addWord("finally", ColorConstants.RED);
		this.addWord("while", ColorConstants.RED);
		this.addWord("repeat", ColorConstants.RED);
		this.addWord("if", ColorConstants.RED);
		this.addWord("elseif", ColorConstants.RED);
		this.addWord("else", ColorConstants.DARK_RED);
		this.addWord("until", ColorConstants.RED);
		this.addWord("switch", ColorConstants.RED);
		this.addWord("default", ColorConstants.RED);
		this.addWord("break", ColorConstants.RED);
		this.addWord("continue", ColorConstants.RED);
		this.addWord("case", ColorConstants.RED);
		this.addWord("notnull", ColorConstants.DARK_RED);
		this.addWord("property", ColorConstants.DARK_RED);
		{
			File brederFolder = new File(new BrederLanguageFile(), "src");
			File[] brederSources = FileUtil.list(brederFolder, "breder");
			for (File brederSource : brederSources) {
				String name = brederSource.getName();
				name = name.substring(0, name.length() - ".breder".length());
				if (name.startsWith("I") && name.length() > 1
						&& Character.isUpperCase(name.charAt(1))) {
					this.addWord(name, ColorConstants.DARK_BLUE);
				} else {
					this.addWord(name, ColorConstants.DARK_GREEN);
				}
			}
		}
	}

	public static BKeyWordRule getInstance() {
		return instance;
	}

}
