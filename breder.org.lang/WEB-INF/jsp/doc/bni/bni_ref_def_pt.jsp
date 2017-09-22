<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p>
Nos tópicos anteriores, foram discutidos como criar um construtor e suas implementações. 
A seguir, será abordado como fazer referência a métodos e classes.
</p>

<p>
Para se executar um método qualquer nativamente, é preciso ter a referência do método.
Normalmente, essas referências já existem, tornando-se fácil a captura da referência.
</p>

<p>
Por exemplo, para se recuperar a referência de métodos.
</p>

<pre>
#include "b_bni.h"

b_bni_state_t hello_Hello$Hello
    (b_vm_t* vm, b_object_t* object) {
  // Chamada do construtor do Object
  b_bni_super_object(vm, object);
  // Método Object.toString
  b_method_id_t id1 = 
      b_bni_method_object$tostring_id(vm);
  // Método Object.hashcode
  b_method_id_t id2 = 
      b_bni_method_object$hashcode_id(vm);
  // Construtor ArrayList
  b_method_id_t id3 = 
      b_bni_method_arraylist$init_id(vm);
  // Método IList.add
  b_method_id_t id4 = 
      b_bni_method_ilist$add_id(vm);
  // Método IList.size
  b_method_id_t id5 = 
      b_bni_method_ilist$size_id(vm);
  // Método Object.operatorEqual
  b_method_id_t id6 = 
      b_bni_method_object$operatorequal_id(vm);
  // Retornado o construtor com 0 parâmetros
  b_bni_cret(vm, 0);
}
</pre>

<p>
Como visto acima, foi recuperado a referência de alguns métodos e construtores, de forma simples.
Porém, não existirar uma chamada de método, para cada método existente.
Com isso, será necessário declarar métodos de forma a poder recuperar desta mesma maneira.
</p>

<p>
Para se declarar um método, será preciso ter a referência, primeiro, da classe.
</p>

<p>
Por exemplo, para se recuperar a referência de classes.
</p>

<pre>
#include "b_bni.h"

b_bni_state_t hello_Hello$Hello
    (b_vm_t* vm, b_object_t* object) {
  // Chamada do construtor do Object
  b_bni_super_object(vm, object);
  // Classe Object
  b_class_id_t id1 = b_bni_class_object_id(vm);
  // Classe String
  b_class_id_t id1 = b_bni_class_string_id(vm);
  // Classe IList
  b_class_id_t id1 = b_bni_class_ilist_id(vm);
  // Classe ArrayList
  b_class_id_t id1 = b_bni_class_arraylist_id(vm);
  // Retornado o construtor com 0 parâmetros
  b_bni_cret(vm, 0);
}
</pre>

<p>
Como visto acima, também é simples recuperar a referência de uma Classe.
Mas também não existirá um métodos para cada Classe existente.
Com isso, deverá ser preciso declarar novos métodos referênte a novas Classes.
</p>

<p>
No exemplo a seguir, será declarado métodos que referênciam Classes da Linguagem Breder.
</p>

<pre>
#include "b_bni.h"

b_bni_class_define(
    b_bni_class_hello_hello_id, 
    hello_hello_id, 
    "hello.Hello")

b_bni_state_t hello_Hello$Hello
    (b_vm_t* vm, b_object_t* object) {
  // Chamada do construtor do Object
  b_bni_super_object(vm, object);
  // Classe Hello
  b_class_id_t id = b_bni_class_hello_hello_id(vm);
  // Retornado o construtor com 0 parâmetros
  b_bni_cret(vm, 0);
}
</pre>

<p>
No exemplo a seguir, será declardo métodos que referênciam Métodos da Linguagem Breder.
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

b_bni_state_t hello_Hello$Hello
    (b_vm_t* vm, b_object_t* object) {
  // Chamada do construtor do Object
  b_bni_super_object(vm, object);
  // Classe Hello
  b_class_id_t class_id = 
      b_bni_class_hello_hello_id(vm);
  // Método Hello.hi
  b_method_id_t method_id = 
     b_bni_method_hello_hello$hi_id(vm);
  // Retornado o construtor com 0 parâmetros
  b_bni_cret(vm, 0);
}
</pre>