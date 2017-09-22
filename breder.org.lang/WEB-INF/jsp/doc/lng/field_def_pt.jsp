<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p>
Um campo representa uma forma na qual � poss�vel armazenar alguma informa��o num objeto de uma classe.
Com isso, ser� poss�vel associar um objeto a um campo de uma classe.
</p>

<p>
Em uma classe, � poss�vel declarar diversos campo de acordo com a necessidade. 
Um campo � uma espa�o de mem�ria separada em cada o objeto da classe.
Com isso, todo o objeto que for criado dessa classe, existir� diversos campos para ser associado a objetos em tempo de execu��o.
</p>

<p>
No exemplo abaixo, ser� constru�do uma classe com 1 campo do tipo <code>'breder.lang.Number'</code> com o nome <code>'x'</code> :
</p>
<pre>
public class Test {
  private Number x;
}
</pre>

<p>
No exemplo abaixo, ser� constru�do uma classe com 2 campos :
</p>
<pre>
public class Test {
  private Number x;
  private Number y;
}
</pre>

<p>
No exemplo abaixo, ser� constru�do uma classe com 1 campo do tipo <code>'breder.lang.Number'</code> com o nome <code>'x'</code> de uma classe :
</p>
<pre>
public class Test {
  public static Number x;
}
</pre>

<p>
No exemplo abaixo, ser� constru�do uma classe com 2 campos de uma classe :
</p>
<pre>
public class Test {
  private static Number x;
  public static Number y;
}
</pre>

<p>
Uma campo de uma classe, normalmente � usado para ser acessado por outras classes. 
Isso porque, esses campos de classes s�o usados como constantes.
</p>

<p>
Al�m disso, os campos de classes s�o, normalmente, p�blicos para outras classes, usando assim a palavra reservado 'public'.
Para campos de objetos, s�o usados, normalmente, de forma privada ou restrita.
Isso porque somente a pr�pria classe pode enxergar esse campo, para que possa garantir assim o encapsulamento. 
</p>

<p>
Ent�o, como boa pr�tica de programa��o, os campos de objetos ser�o sempre declarados como privado.
Al�m disso, os campos de classes devem ser sempre est�tico e p�blicos se forem considerados constantes.
</p>