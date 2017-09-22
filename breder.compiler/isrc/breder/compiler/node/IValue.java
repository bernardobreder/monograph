
package breder.compiler.node;

import breder.compiler.node.standart.BType;
import breder.compiler.parser.javacc.Token;

public interface IValue extends INode {

	public Token getToken();

	public BType[] getTypes();

	public BType getType();

	public void setTypes(BType[] types);

}
