<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p>
Na Linguagem Breder suporta primitivos do tipo String, Boolean, Number, Integer, Natural, Index.
Essas primitivas será melhor descrita um a um abaixo :
</p>
<p>
Nos tópicos abaixo será demonstrado um a um todas as possíveis expressões que a Linguagem Breder suporta.
</p>

<p>
No exemplo abaixo será construída uma classe utilizando o primitivo <code>String</code> :
</p>
<pre>
public class Test {
  public static void main(IList&lt;String&gt; args) {
    String a = "a";
    String b = "ab";
    String c = "a" + "b";
    String d = a + b + c;
    String e = a + "b" + "c" + d;
  }
}
</pre>

<p>
No exemplo abaixo será construída uma classe utilizando o primitivo <code>Boolean</code> :
</p>
<pre>
public class Test {
  public static void main(IList&lt;String&gt; args) {
    Boolean a = true;
    Boolean b = false;
  }
}
</pre>

<p>
A representação de número na Linguagem Breder será vista sempre em alto nível.
Com isso, para números cujo valor seja <code>value >= 1</code>, o objeto será da classe <code>breder.lang.Index</code>.
Além disso, para números cujo valor seja <code>value >= 0</code>, o objeto será da classe <code>breder.lang.Natural</code>.
Também, para números cujo valor seja inteiro, o objeto será da classe <code>breder.lang.Integer</code>.
Por final, os números não inteiros ficam representados pela classe <code>breder.lang.Number</code>
</p>

<p>
No exemplo abaixo será construída uma classe utilizando o primitivo de número :
</p>
<pre>
public class Test {
  public static void main(IList&lt;String&gt; args) {
    Index a = 1;
    Index b = 2;
    Natural c = 0;
    Natural d = 3;
    Integer e = -1;
    Integer f = -5;
    Integer g = 5;
    Number h = -0.5;
    Number i = 1.5;
    Number j = 10;
    Number aa = a;
    Integer bb = b;
    Integer cc = c;
    Number ee = e;
  }
}
</pre>
