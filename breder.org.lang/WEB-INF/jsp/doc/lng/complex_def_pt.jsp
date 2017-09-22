<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p>
Nessa seção iremos combinar todas as definições da Linguagem Breder vista até agora. 
Com isso, iremos criar uma interface com uma funcionalidade, implementando uma classe padrão de teste.
</p>
<pre>
package test.math ;
public interface IMath {
  public Number sin (Number angle);
  public Number cos (Number angle);
}
</pre>

<p>
Foi criado uma interface matemática com as funções de seno e coseno cuja a implementação pode ser de qualquer maneira.
No exemplo abaixo, iremos implementar a interface com uma classe teste, precisando implementar as 2 operações descrita na interface.
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
Cabe ressaltar que a interface <code>IMath</code> ficará numa pasta chamado <code>test/math</code> e a classe Math ficará na pasta chamado <code>test/math/impl</code> devido os seus pacotes respectivos.
</p>

<p>
Com auxilio de alguns campos atribuído no seu construtor, os métodos implementados retornaram os campos, como uma simulação de uma calculo de seno e coseno.
Portanto, foi criado na Linguagem Breder, uma interface e uma classe que possui 2 funcionalidades de seno e coseno. 
Esse é um simples exemplo que mostra como é possível de forma simples estruturar os dados e suas operações.
</p>
