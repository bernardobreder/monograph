package editors.java;

import org.eclipse.swt.graphics.Color;

import editor.scanner.Scanner;
import editor.scanner.rule.WordRule;
import frame.GenericShell;

public class SqlScanner extends Scanner {

  public SqlScanner() {
    Color aColor = new Color(GenericShell.getDisplay(), 255, 0, 0);
    super.add(new WordRule(aColor, "create"));
    super.add(new WordRule(aColor, "table"));
    super.add(new WordRule(aColor, "from"));
    super.add(new WordRule(aColor, "select"));
    super.add(new WordRule(aColor, "into"));
    super.add(new WordRule(aColor, "where"));
    super.add(new WordRule(aColor, "order"));
    super.add(new WordRule(aColor, "by"));
    super.add(new WordRule(aColor, "drop"));
    super.add(new WordRule(aColor, "insert"));
    super.add(new WordRule(aColor, "values"));
    super.add(new WordRule(aColor, "("));
    super.add(new WordRule(aColor, ")"));
  }
}
