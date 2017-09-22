package editor.scanner;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.custom.StyleRange;

import editor.Editor;

/**
 * Scanner � uma estrutura respons�vel por realizar uma leitura no c�digo fonte
 * do editor e gerar, em tempo real, as colora��es de todas as palavras
 * significantes.
 * 
 * 
 * @author Tecgraf
 */
public class Scanner {
	
	/** Editor do scanner */
	protected Editor editor;
	
	/** Regra de todas as colocara��es */
	protected List<Rule> rules = new ArrayList<Rule>();
	
	/**
	 * Adiciona uma regra ao scanner, para que ele possa levar em considera��o,
	 * na hora que for verificado as colora��es das palavras
	 * 
	 * @param e
	 *            regra
	 * @return indica se a regra foi adicionada
	 */
	public boolean add(Rule e) {
		return rules.add(e);
	}
	
	/**
	 * Realiza a visita��o em todos os caracteres especificado, verificando se
	 * houve alguma altera��o na colora��o.
	 * 
	 * @param start
	 *            caracter inicial
	 * @param length
	 *            quantidades de caracterer
	 */
	public void build(int start, int length) {
		String text;
		if (length == 0) {
			text = "";
		} else {
			text = this.getEditor().getUi().getText().getText(start, length - start - 1);
		}
		{
			StyleRange range = new StyleRange();
			range.start = start;
			range.length = length;
			this.getEditor().getUi().getText().setStyleRange(range);
		}
		for (Rule rule : rules) {
			for (int m = 0, n = start; n < length + 1; n++) {
				int c = -1;
				if (n < text.length()) {
					c = text.charAt(n);
				}
				RuleEnum flag = rule.accept(m, c);
				if (flag == RuleEnum.YES) {
					m++;
				} else if (flag == RuleEnum.FINAL) {
					StyleRange range = new StyleRange();
					range.start = n - m;
					range.length = m;
					if (c != -1) {
						range.length++;
					}
					range.foreground = rule.getColor();
					range.font = rule.getFont();
					m = 0;
					this.getEditor().getUi().getText().setStyleRange(range);
				} else if (flag == RuleEnum.NO) {
					m = 0;
				}
			}
		}
		
	}
	
	public Editor getEditor() {
		return editor;
	}
	
	public void setEditor(Editor editor) {
		this.editor = editor;
	}
	
}
