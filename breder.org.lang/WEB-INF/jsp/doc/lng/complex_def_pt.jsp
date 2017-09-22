<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p>
Nessa se��o iremos combinar todas as defini��es da Linguagem Breder vista at� agora. 
Com isso, iremos criar uma interface com uma funcionalidade, implementando uma classe padr�o de teste.
</p>
<pre>
package test.math ;
public interface IMath {
  public Number sin (Number angle);
  public Number cos (Number angle);
}
</pre>

<p>
Foi criado uma interface matem�tica com as fun��es de seno e coseno cuja a implementa��o pode ser de qualquer maneira.
No exemplo abaixo, iremos implementar a interface com uma classe teste, precisando implementar as 2 opera��es descrita na interface.
</p>

<pre>
package test.math.impl;
import test.math.*;
public class Math implements IMap {
  private Number sin;
  private Number cos;
  public Math () {
    this.sin = 1.2;
    this.cos = 0.6;
  }
  public Number sin (Number angle) {
    return this.sin;
  }
  public Number cos (Number angle) {
    return this.cos;
  }
}
</pre>

<p>
Cabe ressaltar que a interface <code>IMath</code> ficar� numa pasta chamado <code>test/math</code> e a classe Math ficar� na pasta chamado <code>test/math/impl</code> devido os seus pacotes respectivos.
</p>

<p>
Com auxilio de alguns campos atribu�do no seu construtor, os m�todos implementados retornaram os campos, como uma simula��o de uma calculo de seno e coseno.
Portanto, foi criado na Linguagem Breder, uma interface e uma classe que possui 2 funcionalidades de seno e coseno. 
Esse � um simples exemplo que mostra como � poss�vel de forma simples estruturar os dados e suas opera��es.
</p>
