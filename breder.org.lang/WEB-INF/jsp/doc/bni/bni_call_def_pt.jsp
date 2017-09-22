<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p>
Nos t�picos anteriores, foram discutidos como criar refer�ncias de classes e m�todos nativamente. 
A seguir, ser� abordado como fazer chamada de uma opera��es.
</p>

<p>
Para se executar um m�todo qualquer nativamente, � preciso ter a refer�ncia do m�todo.
Para isso, precisar� ser feito a declara��o do m�todo para que possamos usar.
</p>

<p>
No exemplo a seguir, ser� declardo m�todos e executando.
</p>

<pre>
#include "b_bni.h"

b_bni_class_define(
    b_bni_class_hello_hello_id, 
    hello_hello_id, 
    "hello.Hello")
    
b_bni_method_define(
    b_bni_method_hello_hello$hi_id, 
    hello_hello$hi_id, 
    b_bni_class_hello_hello_id, 
    "(breder.lang.Number) hi ()")

b_bni_state_t hello_Hello$Hello(b_vm_t* vm, b_object_t* object) {
  // Chamada do construtor do Object
  b_bni_super_object(vm, object);
  // M�todo Hello.hi
  b_method_id_t method_id = b_bni_method_hello_hello$hi_id(vm);
  // Chamada o m�todo Hello.hi retornando o objeto oresult
  b_bni_execute_1_return_0_param(vm, oresult, object, method_id);
  // Transformando o objeto Number em primitivo C Ansi
  b_bni_onumber_to_number_define(vm, result, oresult);
  // Imprimindo o valor de result
  printf("%d\n", result);
  // Retornado o construtor com 0 par�metros
  b_bni_cret(vm, 0);
}
</pre>

<p>
No exemplo acima, foi executado o m�todo <code>b_bni_execute_1_return_0_param</code> na qual ir� chamar a fun��o passada como par�metro, contendo nenhum argumento e um retorno.
O retorno ser� declarado no bloco com o nome <code>oresult</code>, sendo utilizado para a impress�o na tela.
Desconsiderando a implementa��o apresentada em t�picos anteriores do m�todo <code>hi</code>, o resultado da chamada do construtor, far� com que chame o m�todo <code>hi</code>.
</p>

<p>
Existem diversos m�todos que fazem chamadas a M�todo da Linguagem como : <code>b_bni_execute_0_return_0_param</code>, <code>b_bni_execute_0_return_1_param</code>, <code>b_bni_execute_0_return_2_param</code>, <code>b_bni_execute_1_return_0_param</code>, <code>b_bni_execute_1_return_1_param</code>, <code>b_bni_execute_2_return_0_param</code>, <code>b_bni_execute_2_return_1_param</code>, <code>b_bni_execute_2_return_2_param</code>, de forma a manter uma combina��o de todos os poss�veis chamadas de m�todos.
</p>