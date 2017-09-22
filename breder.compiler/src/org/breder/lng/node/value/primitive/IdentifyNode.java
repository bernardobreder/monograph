package org.breder.lng.node.value.primitive;

import java.io.IOException;

import org.breder.lng.node.AbstractNode;
import org.breder.lng.token.IdentifyToken;
import org.breder.lng.util.AbstractDataInputStream;
import org.breder.lng.util.AbstractDataOutputStream;

public class IdentifyNode extends PrimitiveNode {

  private IdentifyToken token;

  public IdentifyNode(IdentifyToken token) {
    this.token = token;
  }

  @SuppressWarnings("unchecked")
  public static <E extends AbstractNode> E read(AbstractDataInputStream input)
    throws IOException {
    return (E) new IdentifyNode(new IdentifyToken(input.readString()));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void write(AbstractDataOutputStream output) throws IOException {
    output.writeIndex(IDENTIFY_VALUE);
    output.writeString(this.token.lexeme);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((token == null) ? 0 : token.hashCode());
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
    IdentifyNode other = (IdentifyNode) obj;
    if (token == null) {
      if (other.token != null) {
        return false;
      }
    }
    else if (!token.equals(other.token)) {
      return false;
    }
    return true;
  }

}
