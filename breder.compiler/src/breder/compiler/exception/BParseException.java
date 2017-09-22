package breder.compiler.exception;

import breder.compiler.compiler.Context;
import breder.compiler.parser.javacc.ParseException;
import breder.compiler.parser.javacc.Token;
import breder.compiler.parser.javacc.TokenMgrError;
import breder.compiler.parser.javacc.TokenUtil;

public class BParseException extends BrederJRuntimeException {

	public BParseException(ParseException e, Context context) {
		super(context, e.currentToken.next, build(e, context));
	}

	public BParseException(TokenMgrError e, Context context) {
		super(context, buildToken(e), buildMessage(e));
	}

	private static String buildMessage(TokenMgrError e) {
		return "token invalid";
	}

	private static Token buildToken(TokenMgrError e) {
		String msg = e.getMessage();
		int index1 = msg.indexOf("line");
		int index2 = msg.indexOf(',', index1);
		int index3 = msg.indexOf("column", index2);
		int index4 = msg.indexOf('.', index3);
		int line = new Integer(msg.substring(index1 + "line".length(), index2).trim());
		int column = new Integer(msg.substring(index3 + "column".length(), index4).trim());
		int index5 = msg.indexOf(':', index4);
		int index6 = msg.indexOf("after", index5);
		String tokenStr = msg.substring(index5 + 1, index6).trim();
		if (tokenStr.startsWith("<")) {
			tokenStr = "";
		} else {
			tokenStr = tokenStr.substring(1, tokenStr.indexOf("\"", 1));
		}
		return TokenUtil.newInstance(tokenStr, line, column);
	}

	private static String build(ParseException e, Context context) {
		StringBuilder expected = new StringBuilder();
		for (int n = 0; n < e.expectedTokenSequences.length; n++) {
			expected.append(e.tokenImage[e.expectedTokenSequences[n][0]]);
			if (n != e.expectedTokenSequences.length - 1) {
				expected.append(", ");
			}
		}
		String msg = expected.toString().toLowerCase();
		return String.format("Expected {%s}", msg.toString());
	}

}
