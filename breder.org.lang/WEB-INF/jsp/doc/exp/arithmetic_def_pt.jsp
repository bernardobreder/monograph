<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p>
Na Linguagem Breder, todos os objetos podem possuir operações, desde que ela seja implementada pela classe.
Com isso, a classe <code>breder.lang.String</code> pode ser somada com outra classe <code>breder.lang.String</code>.
Isso, mesmo acontece com a classe <code>breder.lang.Number</code>, <code>breder.lang.Integer</code>, <code>breder.lang.Natural</code>, <code>breder.lang.Integer</code>.
Caso queira que uma classe anônima possua uma operação aritmética, a Linguagem Breder suporta.
</p>

<p>
No exemplo abaixo será construída uma classe utilizando o primitivo para fazer operações matemáticas :
</p>
<pre>
public class Test {
  public static void main(IList&lt;String&gt; args) {
    Index a = 1 + 2;
    Index b = a + 3;
    Natural c = b + 0;
    Number d = c + 1.2;
    Integer e = c - 5;
    Number f = 1.1;
    Number g = -0.1;
    Integer h = 1 - 1;
    Natural i = (Natural) (1 - 1);
    Natural h = 1 - 1.1;
    Natural j = (Number) (1 - 1.1);
    Integer l = c - 1;
    Integer m = (Integer) (c - 1);
    Number n = c - 1.1 + 2.1;
    Index o = (Index) (c - 1.1 + 2.1);
    Number p = d * 2 / 3;
    Natural q = 1 * 0;
    Index r = 1 * 1;
    Number s = 1 * 0.1;
    Natural t = 1 * 0;
    Number u = 1 / 1;
    Index v = (Index) (1 / 1);
    Integer x = (Integer) (1 / -1);
    String aa = "a" + "b";
    String bb = a + "c" + "d";
  }
}
</pre>

