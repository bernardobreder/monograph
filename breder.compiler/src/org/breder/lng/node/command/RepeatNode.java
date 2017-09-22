package org.breder.lng.node.command;

import java.io.IOException;

import org.breder.lng.node.AbstractNode;
import org.breder.lng.node.value.ValueNode;
import org.breder.lng.util.AbstractDataInputStream;
import org.breder.lng.util.AbstractDataOutputStream;

public class RepeatNode extends CommandNode {

  private final ValueNode condValue;

  private final CommandNode command;

  public RepeatNode(ValueNode condValue, CommandNode command) {
    this.condValue = condValue;
    this.command = command;
  }

  @SuppressWarnings("unchecked")
  public static <E extends AbstractNode> E read(AbstractDataInputStream input)
    throws IOException {
    return (E) new WhileNode(AbstractNode.<ValueNode> read(input), AbstractNode
      .<CommandNode> read(input));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void write(AbstractDataOutputStream output) throws IOException {
    output.writeIndex(REPEAT_CMD);
    this.condValue.write(output);
    this.command.write(output);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + command.hashCode();
    result = prime * result + condValue.hashCode();
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
    RepeatNode other = (RepeatNode) obj;
    if (!command.equals(other.command)) {
      return false;
    }
    if (!condValue.equals(other.condValue)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "repeat " + this.command + " " + this.condValue;
  }

}
