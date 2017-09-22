<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p>
Nos t�picos anteriores, foram discutidos como criar um construtor e suas implementa��es. 
A seguir, ser� abordado como fazer refer�ncia a m�todos e classes.
</p>

<p>
Para se executar um m�todo qualquer nativamente, � preciso ter a refer�ncia do m�todo.
Normalmente, essas refer�ncias j� existem, tornando-se f�cil a captura da refer�ncia.
</p>

<p>
Por exemplo, para se recuperar a refer�ncia de m�todos.
</p>

<pre>
#include "b_bni.h"

b_bni_state_t hello_Hello$Hello
    (b_vm_t* vm, b_object_t* object) {
  // Chamada do construtor do Object
  b_bni_super_object(vm, object);
  // M�todo Object.toString
  b_method_id_t id1 = 
      b_bni_method_object$tostring_id(vm);
  // M�todo Object.hashcode
  b_method_id_t id2 = 
      b_bni_method_object$hashcode_id(vm);
  // Construtor ArrayList
  b_method_id_t id3 = 
      b_bni_method_arraylist$init_id(vm);
  // M�todo IList.add
  b_method_id_t id4 = 
      b_bni_method_ilist$add_id(vm);
  // M�todo IList.size
  b_method_id_t id5 = 
      b_bni_method_ilist$size_id(vm);
  // M�todo Object.operatorEqual
  b_method_id_t id6 = 
      b_bni_method_object$operatorequal_id(vm);
  // Retornado o construtor com 0 par�metros
  b_bni_cret(vm, 0);
}
</pre>

<p>
Como visto acima, foi recuperado a refer�ncia de alguns m�todos e construtores, de forma simples.
Por�m, n�o existirar uma chamada de m�todo, para cada m�todo existente.
Com isso, ser� necess�rio declarar m�todos de forma a poder recuperar desta mesma maneira.
</p>

<p>
Para se declarar um m�todo, ser� preciso ter a refer�ncia, primeiro, da classe.
</p>

<p>
Por exemplo, para se recuperar a refer�ncia de classes.
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
  // Retornado o construtor com 0 par�metros
  b_bni_cret(vm, 0);
}
</pre>

<p>
Como visto acima, tamb�m � simples recuperar a refer�ncia de uma Classe.
Mas tamb�m n�o existir� um m�todos para cada Classe existente.
Com isso, dever� ser preciso declarar novos m�todos refer�nte a novas Classes.
</p>

<p>
No exemplo a seguir, ser� declarado m�todos que refer�nciam Classes da Linguagem Breder.
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
  // Retornado o construtor com 0 par�metros
  b_bni_cret(vm, 0);
}
</pre>

<p>
No exemplo a seguir, ser� declardo m�todos que refer�nciam M�todos da Linguagem Breder.
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
  // M�todo Hello.hi
  b_method_id_t method_id = 
     b_bni_method_hello_hello$hi_id(vm);
  // Retornado o construtor com 0 par�metros
  b_bni_cret(vm, 0);
}
</pre>