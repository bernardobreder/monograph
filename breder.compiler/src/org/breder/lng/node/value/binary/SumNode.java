package org.breder.lng.node.value.binary;

import java.io.IOException;

import org.breder.lng.node.AbstractNode;
import org.breder.lng.node.value.ValueNode;
import org.breder.lng.util.AbstractDataInputStream;
import org.breder.lng.util.AbstractDataOutputStream;
import org.breder.lng.util.AbstractOpcodeOutputStream;

public class SumNode extends BinaryNode {

  /**
   * Construtor
   * 
   * @param token
   * @param left
   * @param right
   */
  public SumNode(ValueNode left, ValueNode right) {
    super(left, right);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void build(AbstractOpcodeOutputStream output) throws IOException {
    this.left.build(output);
    this.right.build(output);
    output.opDoubleSum();
  }

  @SuppressWarnings("unchecked")
  public static <E extends AbstractNode> E read(AbstractDataInputStream input)
    throws IOException {
    return (E) new SumNode(AbstractNode.<ValueNode> read(input), AbstractNode
      .<ValueNode> read(input));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void write(AbstractDataOutputStream output) throws IOException {
    output.writeIndex(SUM_VALUE);
    super.write(output);
  }

}
