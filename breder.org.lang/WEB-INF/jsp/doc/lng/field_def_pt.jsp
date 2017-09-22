<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p>
Um campo representa uma forma na qual é possível armazenar alguma informação num objeto de uma classe.
Com isso, será possível associar um objeto a um campo de uma classe.
</p>

<p>
Em uma classe, é possível declarar diversos campo de acordo com a necessidade. 
Um campo é uma espaço de memória separada em cada o objeto da classe.
Com isso, todo o objeto que for criado dessa classe, existirá diversos campos para ser associado a objetos em tempo de execução.
</p>

<p>
No exemplo abaixo, será construído uma classe com 1 campo do tipo <code>'breder.lang.Number'</code> com o nome <code>'x'</code> :
</p>
<pre>
public class Test {
  private Number x;
}
</pre>

<p>
No exemplo abaixo, será construído uma classe com 2 campos :
</p>
<pre>
public class Test {
  private Number x;
  private Number y;
}
</pre>

<p>
No exemplo abaixo, será construído uma classe com 1 campo do tipo <code>'breder.lang.Number'</code> com o nome <code>'x'</code> de uma classe :
</p>
<pre>
public class Test {
  public static Number x;
}
</pre>

<p>
No exemplo abaixo, será construído uma classe com 2 campos de uma classe :
</p>
<pre>
public class Test {
  private static Number x;
  public static Number y;
}
</pre>

<p>
Uma campo de uma classe, normalmente é usado para ser acessado por outras classes. 
Isso porque, esses campos de classes são usados como constantes.
</p>

<p>
Além disso, os campos de classes são, normalmente, públicos para outras classes, usando assim a palavra reservado 'public'.
Para campos de objetos, são usados, normalmente, de forma privada ou restrita.
Isso porque somente a própria classe pode enxergar esse campo, para que possa garantir assim o encapsulamento. 
</p>

<p>
Então, como boa prática de programação, os campos de objetos serão sempre declarados como privado.
Além disso, os campos de classes devem ser sempre estático e públicos se forem considerados constantes.
</p>