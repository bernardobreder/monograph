package editors.java;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Display;

import editor.scanner.Scanner;
import editor.scanner.rule.SymbolRule;
import editor.scanner.rule.WordRule;
import editors.java.rule.JavaDocRule;
import editors.java.rule.LineJavaDocRule;
import editors.java.rule.SimpleJavaDocRule;
import frame.GenericShell;

public class JavaScanner extends Scanner {
	
	private static final String[] WORDS = new String[] { "public", "protected", "private",
			"package", "import", "class", "extends", "implements", "new", "this", "super",
			"static", "final", "abstract", "native", "transient", "volatile", "void", "boolean",
			"short", "int", "long", "float", "double", "if", "for", "while", "do", "case",
			"default", "switch", "finally", "catch", "try", "throw", "throws" };
	
	private static final String[] SYMBOLS = new String[] { "(", ")", "[", "]", ".", ",", "/", "*",
			"+", "-", "!", "&", "=", ">", "<", ";", "?", ":", "|" };
	
	public JavaScanner(JavaEditor editor) {
		this.buildWord();
		this.buildSymbol();
		this.buildJavaDoc();
		this.buildSimpleJavaDoc();
		this.buildLineJavaDoc();
	}
	
	private void buildWord() {
		Font font = new Font(Display.getCurrent(), java.awt.Font.SANS_SERIF, 12,
				java.awt.Font.PLAIN);
		Color color = new Color(GenericShell.getDisplay(), 255, 0, 0);
		for (String word : WORDS) {
			this.add(new WordRule(font, color, word));
		}
	}
	
	private void buildSymbol() {
		Font font = new Font(Display.getCurrent(), java.awt.Font.SANS_SERIF, 12, java.awt.Font.BOLD);
		Color color = new Color(GenericShell.getDisplay(), 0, 0, 0);
		for (String symbol : SYMBOLS) {
			this.add(new SymbolRule(font, color, symbol));
		}
	}
	
	private void buildJavaDoc() {
		Font font = new Font(Display.getCurrent(), java.awt.Font.SANS_SERIF, 12,
				java.awt.Font.PLAIN);
		Color color = new Color(GenericShell.getDisplay(), 0, 0, 255);
		this.add(new JavaDocRule(font, color));
	}
	
	private void buildSimpleJavaDoc() {
		Font font = new Font(Display.getCurrent(), java.awt.Font.SANS_SERIF, 12,
				java.awt.Font.PLAIN);
		Color color = new Color(GenericShell.getDisplay(), 0, 255, 0);
		this.add(new SimpleJavaDocRule(font, color));
	}
	
	private void buildLineJavaDoc() {
		Font font = new Font(Display.getCurrent(), java.awt.Font.SANS_SERIF, 12,
				java.awt.Font.PLAIN);
		Color color = new Color(GenericShell.getDisplay(), 0, 255, 0);
		this.add(new LineJavaDocRule(font, color));
	}
	
}
