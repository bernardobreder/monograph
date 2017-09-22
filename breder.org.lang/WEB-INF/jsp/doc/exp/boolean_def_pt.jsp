<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p>
Na Linguagem Breder, existem vários tipos de expressões booleanas.
São expressões booleanas aquelas que utilizam os operadores <code>or</code>, <code>and</code>, <code>==</code>, <code>!=</code>, <code>&lt;</code>, <code>&lt;=</code>, <code>&gt;</code>, <code>&gt;=</code>.
</p>

<p>
No exemplo abaixo será construída uma classe utilizando algumas expressões booleanas :
</p>
<pre>
public class Test {
  public static void main(IList&lt;String&gt; args) {
    Boolean a = true;
    Boolean b = false;
    Boolean c = a or b;
    Boolean d = a and b;
    Boolean e = true or false;
    Boolean f = false and true;
    Boolean g = a and b or c and true;
    Boolean h = ((a and b) or (c and true));
  }
}
</pre>

<p>
No exemplo abaixo será construída uma classe utilizando algumas expressões booleanas :
</p>
<pre>
public class Test {
  public static void main(IList&lt;String&gt; args) {
    Boolean a = 1 >= 1;
    Boolean b = 1 + 1 < 3;
    Boolean c = 1 > 0 and 0 < 1;
    Boolean d = 1 > 0 or 0 < 1;
    if (a) {}
    while (!b) {}
    repeat {} while (false and c); 
  }
}
</pre>
