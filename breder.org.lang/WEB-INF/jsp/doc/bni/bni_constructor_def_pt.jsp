<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p>
Nos t�picos anteriores, foram discutidos como criar m�todos nativos com par�metros e retornos. 
A seguir, ser� abordado como criar a um m�todo construtor.
</p>

<p>
O nome do m�todo do construtor, deve sempre seguir a teoria da cria��o de um m�todo nativo normal.
Por�m, algumas coisas mudan�a, no qual ser� discutido.
</p>

<p>
No exemplo abaixo, ser� constru�do a classe ArrayList na Linguagem Breder.
</p>

<pre>
package hello;
public class Hello {
  public native Hello () ;
}
</pre>

<p>
O c�digo fonte acima na Linguagem Breder ser� convertida para o c�digo fonte abaixo na Linguagem C ANSI.
</p>

<pre>
#include "b_bni.h"

b_bni_state_t hello_Hello$Hello
    (b_vm_t* vm, b_object_t* object) {
  // Chamada do construtor do Object
  b_bni_super_object(vm, object);
  // Retorno do construtor
  b_bni_cret(vm, 0);
}
</pre>

<p>
No c�digo fonte acima, foi constru�do um m�todo nativo construtor.
Na primeira linha, foi executado a chamada do construtor da classe Object, que por default, � sempre chamado, caso n�o seja escrito explicitamente na Linguagem Breder.
Mas como o m�todo nativo � escrito na Linguagem C ANSI, o compilador n�o ir� ajudar, fazendo com que seja necess�rio a chamada explicita do construtor da classe Object.
Logo depois, � feito um retorno de modo construtor, com 0 par�metros
</p>

<p>
Agora, ser� adicionado um m�todo nativo na qual usar� de recursos nativos na sua implementa��o.
Al�m disso, iremos colocar um argumento no construtor para incrementar o exemplo.
Os exemplos abaixo ir� descrever as mudan�as na class Hello e sua Implementa��o.
Repare que agora o construtor possui um argumento, modificando assim o par�metro do m�todo <code>b_bni_cret</code> de zero para um.
</p>

<pre>
package hello;
public class Hello {
  public native Hello (Number i) ;
  public native notnull Number hi () ;
}
</pre>

<pre>
#include "b_bni.h"

b_bni_state_t hello_Hello$Hello$breder_lang_Number
    (b_vm_t* vm, b_object_t* object) {
  // Chamada do construtor do Object
  b_bni_super_object(vm, object);
  // Recuperando o par�metro 0 como Number
  b_bni_get_param_as_number(vm, oindex, index, 0);
  // Criando uma estrutura de dados
  int* data = (int*) b_memory_alloc0(sizeof(int));
  // Preenchendo a estrutura de dados
  *data = index;
  // Associando a estrutura ao Objeto
  b_bni_set_data_current(vm, object, data);
  // Retorno do construtor
  b_bni_cret(vm, 1);
}

b_bni_state_t hello_Hello$hi
    (b_vm_t* vm, b_object_t* object, int* data) {
  // Criando um objeto da classe Number com o conte�do especifico.
  b_bni_new_onumber_define(vm, oindex, *data);
  // Retornando o objeto, informando 0 par�metros.
  b_bni_ret1(vm, 0, oreturn);
}
</pre>

<p>
No exemplo acima, foi criado um construtor com um argumento.
Esse argumento � recuperado atrav�s do m�todo <code>b_bni_get_param_as_number</code>, na qual ir� declarar duas vari�veis.
Com o valor do par�metro, podemos guardar esse valor numa estrutura de dados an�nima para Linguagem, mas reconhecida pelo programador da classe.
Um objeto pode guardar uma estrutura de dados an�nima para cada classe que o objeto possui como hierarquia. 
No caso, ser� guardado a estrutura no objeto referente a classe <code>hello.Hello</code>.
Para guardar a estrutura de dados no objeto, ser� necess�rio executar o m�todo <code>b_bni_get_param_as_number</code>, informando o objeto e a estrutura em que deseja guardar.
</p>

<p>
Com a estrutura guardada, em qualquer m�todo nativo da mesma classe, poder� existir um atributo novo, do tipo <code>void*</code> em que a M�quina Virtual ir� associar.
Com isso, foi criado o m�todo <code>hi</code> com 3 argumento, ao inv�s de 2, como � normalmente feito para opera��es nativas n�o est�ticas.
O terceiro argumento, representa a estrutura de dados guardada no construtor ou em qualquer outra opera��o anterior.
</p>

<p>
Atrav�s dessa estrutura armazenada, o m�todo nativo <code>hi</code> implementa simplesmente o retorna do objeto que representa o valor do �ndice.
</p>

<p>
Um exemplo simular pode ser constru�do abaixo.
</p>

<pre>
package hello;
public class Hello {
  public native Hello (Number i) ;
  public native notnull Number hi () ;
  public native void finalize () ;
}
</pre>

<pre>
#include "b_bni.h"

typedef struct b_bni_hello_t b_bni_hello_t;
struct b_bni_hello_t {
  b_object_t* oindex;
  int index;
};

b_bni_state_t hello_Hello$Hello$breder_lang_Number
    (b_vm_t* vm, b_object_t* object) {
  // Chamada do construtor do Object
  b_bni_super_object(vm, object);
  // Recuperando o par�metro 0 como Number
  b_bni_get_param_as_number(vm, oindex, index, 0);
  // Diz ao Coletor de Lixo que o objeto est� em uso
  b_bni_inc_used(vm, oindex);
  // Criando uma estrutura de dados
  b_bni_hello_t* data = (b_bni_hello_t*) 
      b_memory_alloc0(sizeof(b_bni_hello_t));
  // Preenchendo a estrutura de dados
  data->index = index;
  // Preenchendo a estrutura de dados
  data->oindex = oindex;
  // Associando a estrutura ao Objeto
  b_bni_set_data_current(vm, object, data);
  // Retorno do construtor
  b_bni_cret(vm, 1);
}

b_bni_state_t hello_Hello$hi
    (b_vm_t* vm, b_object_t* object, b_bni_hello_t* data) {
  // Retornando o objeto, informando 0 par�metros.
  b_bni_ret1(vm, 0, data->oindex);
}

b_bni_state_t hello_Hello$finalize
    (b_vm_t* vm, b_object_t* object, b_bni_hello_t* data) {
  // Diz ao Coletor de Lixo que o objeto n�o est� em uso
  b_bni_dec_used(vm, data->oindex);
  // Desalocando mem�ria
  b_memory_free(data);
  // Retornando, informando 0 par�metros.
  b_bni_ret0(vm, 0);
}
</pre>

<p>
Nesse exemplo, foi constru�do uma estrutura de dados mais complexa, na qual foi armazenado n�o somente o inteiro de �ndice, mas tamb�m o objeto do �ndice.
Sempre que for guardado um objeto, dever� sempre chamar o m�todo <code>b_bni_inc_used</code> para informar para o Coletor de Lixo que esse objeto est� usando a refer�ncia.
Isso precisa ser feito, porque o Coletor de Lixo n�o sabe das opera��es que est�o acontecendo nativamente, precisando assim, uma ajuda do programador informar que certos objetos est�o sobre controle de alguns objetos.
Caso essa opera��o n�o for realizado, o Coletor de Lixo ir� coletar o objeto sem o programador perceber, gerando assim um poss�vel erro de execu��o.
</p>

<p>
Por�m, como foi informado para o Coletor de Lixo que um objeto est� usando, em sua estrutura interna, a refer�ncia de um outro objeto, ser� necess�rio tamb�m informar quando se deve desreferenciar os objetos utilizados nativamente.
Isso acontece quando o Coletor de Lixo verificar que esse objeto Hello n�o esta sendo mais utilizado por ningu�m, fazendo com que seja chamado o m�todo <code>finalize</code>.
Esse m�todo � sempre chamado, quando o Coletor de Lixo perceber que esse objeto n�o precisa mais existir, necessitando assim, ser coletado.
Com isso, o Coletor de Lixo ir� chamar esse m�todo para que possa desalocar todos os recursos nativos, inclusive os objetos refer�nciados.    
</p>

<p>
A partir da�, foi implementado o m�todo <code>finalize</code> de forma a liberar da mem�ria a estrutura de dados e desrefenciar o objetos �ndice guardado no construtor.
Para se liberar a refer�ncia de um objeto, basta chamar o m�todo <code>b_bni_dec_used</code>
</p>