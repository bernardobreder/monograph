package org.breder.lng.node.value.binary;

import java.io.IOException;

import org.breder.lng.node.AbstractNode;
import org.breder.lng.node.value.ValueNode;
import org.breder.lng.util.AbstractDataOutputStream;
import org.breder.lng.util.StreamOpcodeInputStream;

public class AssignNode extends BinaryNode {

  /**
   * Construtor
   * 
   * @param token
   * @param left
   * @param right
   */
  public AssignNode(ValueNode left, ValueNode right) {
    super(left, right);
  }

  @SuppressWarnings("unchecked")
  public static <E extends AbstractNode> E read(StreamOpcodeInputStream input)
    throws IOException {
    return (E) new AssignNode(AbstractNode.<ValueNode> read(input),
      AbstractNode.<ValueNode> read(input));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void write(AbstractDataOutputStream output) throws IOException {
    output.writeIndex(ASSIGN_VALUE);
    super.write(output);
  }

}
