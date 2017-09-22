
package breder.compiler.node;

import java.io.IOException;

import breder.compiler.compiler.BinaryOutputStream;
import breder.compiler.compiler.Context;
import breder.compiler.parser.javacc.ParseException;

public interface INode {

	public void syntax(Context context) throws ParseException;

	public void semantic(Context context) throws ParseException;

	public void check(Context context) throws ParseException;

	public int instCount(Context context) throws IOException;

	public void build(Context context, BinaryOutputStream output) throws IOException;

	public INode getParent();

	public void setParent(INode parent);

}
