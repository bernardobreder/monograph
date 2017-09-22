package breder.ide;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;
import javax.swing.text.TabSet;
import javax.swing.text.TabStop;

import breder.ide.lang.Token;
import breder.ide.lang.generic.IStyle;
import breder.ide.projects.ProjectNode;
import breder.util.lookandfeel.LookAndFeel;
import breder.util.swing.BEditorPane;
import breder.util.swing.tree.GenericFileTreeNode;
import breder.util.util.InputStreamUtil;

public class IdeEditorPane extends BEditorPane {
	
	public IdeEditorPane(GenericFileTreeNode index, String text) {
		super(index, text);
	}
	
	protected void buildStyles(int begin, int length) {
		StyledDocument doc = this.getTextPane().getStyledDocument();
		if (false) {
			SimpleAttributeSet attributes = new SimpleAttributeSet();
			TabStop tabstop = new TabStop(10);
			TabSet tabset = new TabSet(new TabStop[] { tabstop, tabstop });
			StyleConstants.setTabSet(attributes, tabset);
			doc.setParagraphAttributes(begin, length, attributes, false);
		}
		doc.setCharacterAttributes(begin, length, doc.getStyle(StyleContext.DEFAULT_STYLE), true);
		List<Token> words = this.refreshStyle(begin, length);
		for (Token token : words) {
			for (IStyle style : this.getStyles()) {
				if (style.accept(token.getWord())) {
					doc.setCharacterAttributes(token.getBegin(), token.getWord().length(), doc
							.getStyle("bold"), true);
					break;
				}
			}
		}
	}
	
	protected IStyle[] getStyles() {
		if (this.getIndex() == null) {
			return new IStyle[0];
		} else {
			return this.getProjectNode().getProject().getLanguage().getStyle().getAllStyles();
		}
	}
	
	public ProjectNode getProjectNode() {
		return this.index.getParent(ProjectNode.class);
	}
	
	public List<Token> refreshStyle(int begin, int length) {
		String text = this.getText().substring(begin, begin + length).trim();
		length = text.length();
		List<Token> words = new ArrayList<Token>();
		char[] chars = text.toCharArray();
		for (int m = 0, n = 0; n < length;) {
			char c = chars[n];
			if (this.isSpace(c)) {
				n++;
				while (true) {
					c = chars[n];
					if (this.isSpace(c)) {
						n++;
					} else
						break;
				}
			}
			m = n++;
			while (true) {
				if (this.getConst(c) == SYMBOL || this.isSpace(c)) {
					if (n != m + 1) {
						n--;
					}
					break;
				}
				if (n == length) {
					break;
				}
				c = chars[n++];
			}
			words.add(new Token(begin + m, new String(chars, m, n - m)));
		}
		return words;
	}
	
	protected void addStylesToDocument() {
		StyledDocument doc = this.getTextPane().getStyledDocument();
		Style def = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);
		
		Style regular = doc.addStyle("regular", def);
		StyleConstants.setFontFamily(def, "SansSerif");
		
		Style s = doc.addStyle("italic", regular);
		StyleConstants.setItalic(s, true);
		
		s = doc.addStyle("bold", regular);
		StyleConstants.setBold(s, true);
		
		s = doc.addStyle("small", regular);
		StyleConstants.setFontSize(s, 10);
		
		s = doc.addStyle("large", regular);
		StyleConstants.setFontSize(s, 16);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				LookAndFeel.getInstance().installNimbus();
				JFrame frame = new JFrame();
				String text;
				try {
					text = new String(InputStreamUtil.getBytes(new FileInputStream(
							"src/breder/ide/IDEEditorPane.java")));
				} catch (IOException e) {
					e.printStackTrace();
					text = "";
				}
				final IdeEditorPane editor = new IdeEditorPane(null, text);
				frame.add(editor);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setSize(200, 300);
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
				SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						editor.selectLine(5);
					}
				});
				SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						editor.selectLine(7);
					}
				});
			}
			
		});
	}
}
