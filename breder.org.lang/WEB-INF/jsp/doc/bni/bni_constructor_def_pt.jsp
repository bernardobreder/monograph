<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p>
Nos tópicos anteriores, foram discutidos como criar métodos nativos com parâmetros e retornos. 
A seguir, será abordado como criar a um método construtor.
</p>

<p>
O nome do método do construtor, deve sempre seguir a teoria da criação de um método nativo normal.
Porém, algumas coisas mudança, no qual será discutido.
</p>

<p>
No exemplo abaixo, será construído a classe ArrayList na Linguagem Breder.
</p>

<pre>
package hello;
public class Hello {
  public native Hello () ;
}
</pre>

<p>
O código fonte acima na Linguagem Breder será convertida para o código fonte abaixo na Linguagem C ANSI.
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
No código fonte acima, foi construído um método nativo construtor.
Na primeira linha, foi executado a chamada do construtor da classe Object, que por default, é sempre chamado, caso não seja escrito explicitamente na Linguagem Breder.
Mas como o método nativo é escrito na Linguagem C ANSI, o compilador não irá ajudar, fazendo com que seja necessário a chamada explicita do construtor da classe Object.
Logo depois, é feito um retorno de modo construtor, com 0 parâmetros
</p>

<p>
Agora, será adicionado um método nativo na qual usará de recursos nativos na sua implementação.
Além disso, iremos colocar um argumento no construtor para incrementar o exemplo.
Os exemplos abaixo irá descrever as mudanças na class Hello e sua Implementação.
Repare que agora o construtor possui um argumento, modificando assim o parâmetro do método <code>b_bni_cret</code> de zero para um.
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
  // Recuperando o parâmetro 0 como Number
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
  // Criando um objeto da classe Number com o conteúdo especifico.
  b_bni_new_onumber_define(vm, oindex, *data);
  // Retornando o objeto, informando 0 parâmetros.
  b_bni_ret1(vm, 0, oreturn);
}
</pre>

<p>
No exemplo acima, foi criado um construtor com um argumento.
Esse argumento é recuperado através do método <code>b_bni_get_param_as_number</code>, na qual irá declarar duas variáveis.
Com o valor do parâmetro, podemos guardar esse valor numa estrutura de dados anônima para Linguagem, mas reconhecida pelo programador da classe.
Um objeto pode guardar uma estrutura de dados anônima para cada classe que o objeto possui como hierarquia. 
No caso, será guardado a estrutura no objeto referente a classe <code>hello.Hello</code>.
Para guardar a estrutura de dados no objeto, será necessário executar o método <code>b_bni_get_param_as_number</code>, informando o objeto e a estrutura em que deseja guardar.
</p>

<p>
Com a estrutura guardada, em qualquer método nativo da mesma classe, poderá existir um atributo novo, do tipo <code>void*</code> em que a Máquina Virtual irá associar.
Com isso, foi criado o método <code>hi</code> com 3 argumento, ao invés de 2, como é normalmente feito para operações nativas não estáticas.
O terceiro argumento, representa a estrutura de dados guardada no construtor ou em qualquer outra operação anterior.
</p>

<p>
Através dessa estrutura armazenada, o método nativo <code>hi</code> implementa simplesmente o retorna do objeto que representa o valor do índice.
</p>

<p>
Um exemplo simular pode ser construído abaixo.
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
  // Recuperando o parâmetro 0 como Number
  b_bni_get_param_as_number(vm, oindex, index, 0);
  // Diz ao Coletor de Lixo que o objeto está em uso
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
  // Retornando o objeto, informando 0 parâmetros.
  b_bni_ret1(vm, 0, data->oindex);
}

b_bni_state_t hello_Hello$finalize
    (b_vm_t* vm, b_object_t* object, b_bni_hello_t* data) {
  // Diz ao Coletor de Lixo que o objeto não está em uso
  b_bni_dec_used(vm, data->oindex);
  // Desalocando memória
  b_memory_free(data);
  // Retornando, informando 0 parâmetros.
  b_bni_ret0(vm, 0);
}
</pre>

<p>
Nesse exemplo, foi construído uma estrutura de dados mais complexa, na qual foi armazenado não somente o inteiro de índice, mas também o objeto do índice.
Sempre que for guardado um objeto, deverá sempre chamar o método <code>b_bni_inc_used</code> para informar para o Coletor de Lixo que esse objeto está usando a referência.
Isso precisa ser feito, porque o Coletor de Lixo não sabe das operações que estão acontecendo nativamente, precisando assim, uma ajuda do programador informar que certos objetos estão sobre controle de alguns objetos.
Caso essa operação não for realizado, o Coletor de Lixo irá coletar o objeto sem o programador perceber, gerando assim um possível erro de execução.
</p>

<p>
Porém, como foi informado para o Coletor de Lixo que um objeto está usando, em sua estrutura interna, a referência de um outro objeto, será necessário também informar quando se deve desreferenciar os objetos utilizados nativamente.
Isso acontece quando o Coletor de Lixo verificar que esse objeto Hello não esta sendo mais utilizado por ninguém, fazendo com que seja chamado o método <code>finalize</code>.
Esse método é sempre chamado, quando o Coletor de Lixo perceber que esse objeto não precisa mais existir, necessitando assim, ser coletado.
Com isso, o Coletor de Lixo irá chamar esse método para que possa desalocar todos os recursos nativos, inclusive os objetos referênciados.    
</p>

<p>
A partir daí, foi implementado o método <code>finalize</code> de forma a liberar da memória a estrutura de dados e desrefenciar o objetos índice guardado no construtor.
Para se liberar a referência de um objeto, basta chamar o método <code>b_bni_dec_used</code>
</p>