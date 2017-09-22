package org.breder.lng.node.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.breder.lng.node.AbstractNode;
import org.breder.lng.node.value.ValueNode;
import org.breder.lng.util.AbstractDataInputStream;
import org.breder.lng.util.AbstractDataOutputStream;

public class ForNode extends CommandNode {

  private final List<CommandNode> initValues;

  private final ValueNode condValue;

  private final List<CommandNode> posValues;

  private final CommandNode command;

  public ForNode(List<CommandNode> initValues, ValueNode condValue,
    List<CommandNode> posValues, CommandNode command) {
    this.initValues = initValues;
    this.condValue = condValue;
    this.posValues = posValues;
    this.command = command;
  }

  @SuppressWarnings("unchecked")
  public static <E extends AbstractNode> E read(AbstractDataInputStream input)
    throws IOException {
    int initValueSize = input.readIndex();
    List<CommandNode> initValues = new ArrayList<CommandNode>();
    for (int n = 0; n < initValueSize; n++) {
      initValues.add(AbstractNode.<CommandNode> read(input));
    }
    ValueNode condValue = AbstractNode.<ValueNode> read(input);
    int posValueSize = input.readIndex();
    List<CommandNode> posValues = new ArrayList<CommandNode>();
    for (int n = 0; n < posValueSize; n++) {
      posValues.add(AbstractNode.<CommandNode> read(input));
    }
    CommandNode command = AbstractNode.<CommandNode> read(input);
    return (E) new ForNode(initValues, condValue, posValues, command);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void write(AbstractDataOutputStream output) throws IOException {
    output.writeIndex(FOR_CMD);
    output.writeIndex(this.initValues.size());
    for (int n = 0; n < this.initValues.size(); n++) {
      this.initValues.get(n).write(output);
    }
    this.condValue.write(output);
    output.writeIndex(this.posValues.size());
    for (int n = 0; n < this.posValues.size(); n++) {
      this.posValues.get(n).write(output);
    }
    this.command.write(output);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + command.hashCode();
    result = prime * result + condValue.hashCode();
    result = prime * result + initValues.hashCode();
    result = prime * result + posValues.hashCode();
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
    ForNode other = (ForNode) obj;
    if (!command.equals(other.command)) {
      return false;
    }
    if (!condValue.equals(other.condValue)) {
      return false;
    }
    if (!initValues.equals(other.initValues)) {
      return false;
    }
    if (!posValues.equals(other.posValues)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "for " + this.initValues + " ; " + this.condValue + " ; "
      + this.posValues + " " + this.command;
  }

}
