<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p>
Nos tópicos anteriores, foram discutidos como criar referências de classes e métodos nativamente. 
A seguir, será abordado como fazer chamada de uma operações.
</p>

<p>
Para se executar um método qualquer nativamente, é preciso ter a referência do método.
Para isso, precisará ser feito a declaração do método para que possamos usar.
</p>

<p>
No exemplo a seguir, será declardo métodos e executando.
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
  // Método Hello.hi
  b_method_id_t method_id = b_bni_method_hello_hello$hi_id(vm);
  // Chamada o método Hello.hi retornando o objeto oresult
  b_bni_execute_1_return_0_param(vm, oresult, object, method_id);
  // Transformando o objeto Number em primitivo C Ansi
  b_bni_onumber_to_number_define(vm, result, oresult);
  // Imprimindo o valor de result
  printf("%d\n", result);
  // Retornado o construtor com 0 parâmetros
  b_bni_cret(vm, 0);
}
</pre>

<p>
No exemplo acima, foi executado o método <code>b_bni_execute_1_return_0_param</code> na qual irá chamar a função passada como parâmetro, contendo nenhum argumento e um retorno.
O retorno será declarado no bloco com o nome <code>oresult</code>, sendo utilizado para a impressão na tela.
Desconsiderando a implementação apresentada em tópicos anteriores do método <code>hi</code>, o resultado da chamada do construtor, fará com que chame o método <code>hi</code>.
</p>

<p>
Existem diversos métodos que fazem chamadas a Método da Linguagem como : <code>b_bni_execute_0_return_0_param</code>, <code>b_bni_execute_0_return_1_param</code>, <code>b_bni_execute_0_return_2_param</code>, <code>b_bni_execute_1_return_0_param</code>, <code>b_bni_execute_1_return_1_param</code>, <code>b_bni_execute_2_return_0_param</code>, <code>b_bni_execute_2_return_1_param</code>, <code>b_bni_execute_2_return_2_param</code>, de forma a manter uma combinação de todos os possíveis chamadas de métodos.
</p>