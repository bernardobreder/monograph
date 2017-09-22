<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p>
Na Linguagem Breder suporta primitivos do tipo String, Boolean, Number, Integer, Natural, Index.
Essas primitivas ser� melhor descrita um a um abaixo :
</p>
<p>
Nos t�picos abaixo ser� demonstrado um a um todas as poss�veis express�es que a Linguagem Breder suporta.
</p>

<p>
No exemplo abaixo ser� constru�da uma classe utilizando o primitivo <code>String</code> :
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
No exemplo abaixo ser� constru�da uma classe utilizando o primitivo <code>Boolean</code> :
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
A representa��o de n�mero na Linguagem Breder ser� vista sempre em alto n�vel.
Com isso, para n�meros cujo valor seja <code>value >= 1</code>, o objeto ser� da classe <code>breder.lang.Index</code>.
Al�m disso, para n�meros cujo valor seja <code>value >= 0</code>, o objeto ser� da classe <code>breder.lang.Natural</code>.
Tamb�m, para n�meros cujo valor seja inteiro, o objeto ser� da classe <code>breder.lang.Integer</code>.
Por final, os n�meros n�o inteiros ficam representados pela classe <code>breder.lang.Number</code>
</p>

<p>
No exemplo abaixo ser� constru�da uma classe utilizando o primitivo de n�mero :
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
