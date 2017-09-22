package breder.compiler.parser.javacc;

public class TokenUtil {

	public static Token newInstance(String name, int line, int column) {
		Token token = new Token();
		token.image = name;
		token.beginLine = line;
		token.endLine = line;
		token.beginColumn = column;
		token.endColumn = column + name.length();
		return token;
	}

	public static Token newInstance(String name) {
		return newInstance(name, 1, 1);
	}

}
