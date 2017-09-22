package org.breder.lng.node.value.primitive;

import java.io.IOException;

import org.breder.lng.node.AbstractNode;
import org.breder.lng.token.StringToken;
import org.breder.lng.util.AbstractDataInputStream;
import org.breder.lng.util.AbstractDataOutputStream;
import org.breder.lng.util.AbstractOpcodeOutputStream;

public class StringNode extends PrimitiveNode {

  private String value;

  public StringNode(StringToken token) {
    this.value = token.value;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void build(AbstractOpcodeOutputStream output) throws IOException {
    output.opStackString(value);
  }

  @SuppressWarnings("unchecked")
  public static <E extends AbstractNode> E read(AbstractDataInputStream input)
    throws IOException {
    return (E) new StringNode(new StringToken(input.readString()));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void write(AbstractDataOutputStream output) throws IOException {
    output.writeIndex(STRING_VALUE);
    output.writeString(this.value);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + value.hashCode();
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    StringNode other = (StringNode) obj;
    if (!value.equals(other.value)) {
      return false;
    }
    return true;
  }

}
