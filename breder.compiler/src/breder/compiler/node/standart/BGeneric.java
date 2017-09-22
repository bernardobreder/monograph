
package breder.compiler.node.standart;

import breder.compiler.parser.javacc.Token;

public class BGeneric {

	private Token token;

	private BType type;

	public BGeneric(Token token, BType type) {
		super();
		this.token = token;
		this.type = type;
	}

	public Token getToken() {
		return token;
	}

	public BType getType() {
		return type;
	}

	public void setToken(Token token) {
		this.token = token;
	}

	public void setType(BType type) {
		this.type = type;
	}

	public String getCompleteName() {
		return "<" + this.token.image + " " + this.type + ">";
	}

	public String toString() {
		return this.getCompleteName();
	}
}
