
package breder.compiler.node.standart;

import java.util.Arrays;
import java.util.List;

import breder.compiler.parser.javacc.Token;

public class BTokenUtil {

	public static Token join(List<Token> tokens, String separator) {
		Token token = new Token();
		token.beginLine = tokens.get(0).beginLine;
		token.beginColumn = tokens.get(0).beginColumn;
		token.endLine = tokens.get(tokens.size() - 1).endLine;
		token.endColumn = tokens.get(tokens.size() - 1).endColumn;
		StringBuilder sb = new StringBuilder();
		for (Token t : tokens) {
			sb.append(t.image + separator);
		}
		sb.delete(sb.length() - separator.length(), sb.length() + separator.length());
		token.image = sb.toString();
		token.kind = -1;
		return token;
	}

	public static Token join(List<Token> tokens) {
		return join(tokens, "");
	}

	public static Token join(String separator, Token... tokens) {
		return join(Arrays.asList(tokens), separator);
	}

	public static Token build() {
		return null;
	}

}
