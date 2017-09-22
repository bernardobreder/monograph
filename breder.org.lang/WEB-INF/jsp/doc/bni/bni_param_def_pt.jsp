<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p>
No tópico anterior, foi discutido como criar uma exemplo simples de operação nativa. 
A seguir, será abordado como criar a operação com parâmetros.
</p>

<p>
O nome do método native na Linguagem C ANSI foi modificado por um nome um pouco diferente.
O mesmo será usado quando se deseja colocar parâmetros nos métodos nativos.
</p>

<p>
Todos os métodos implementado nativamente, deverá ter em seu nome os parâmetros.
Abaixo terá diversos exemplos que demonstram como converter o nome da Linguagem Breder para Linguagem C Anci.
</p>

<pre>
package hello;
public class Hello {
  public native void hi();
  public native void hi(Number x);
  public native void hi(Number x, Number y);
  public native void hi(Integer x);
  public native void hi(Natural x);
  public native void hi(Index x);
  public native void hi(String x);
  public native void hi(Boolean x);
  public native void hi(IList&lt;String&gt; w);
  public native static void hi();
  public native static void hi(notnull Number x);
  public native static void hi(IList&lt;String&gt; w);
}
</pre>

<p>
O código fonte acima na Linguagem Breder será convertida para o código fonte abaixo na Linguagem C ANSI.
</p>

<pre>
#include "b_bni.h"

// public native void hi();
b_bni_state_t hello_Hello$hi
    (b_vm_t* vm, b_object_t* object) {
  // Realiza o processamento
  printf("Hello World!\n");
  // Retorna 0 valores com 0 Parâmetros
  b_bni_ret0(vm, 0);
}

// public native void hi(Number x);
b_bni_state_t hello_Hello$hi$breder_lang_Number
    (b_vm_t* vm, b_object_t* object) {
  // Pega o parâmetro 0 como número.
  b_bni_get_param_as_number(vm, oparam0, param0, 0);
  // Realiza o processamento
  printf("Hello World!\n");
  // Retorna 0 valores com 1 Parâmetros
  b_bni_ret0(vm, 1);
}

// public native void hi(Number x, Number y);
b_bni_state_t  
    hello_Hello$hi$breder_lang_Number$breder_lang_Number
    (b_vm_t* vm, b_object_t* object) {
  // Pega o parâmetro 0 como número.
  b_bni_get_param_as_number(vm, oparam0, param0, 0);
  // Pega o parâmetro 1 como número.
  b_bni_get_param_as_number(vm, oparam1, param1, 1);
  // Realiza o processamento
  printf("Hello World!\n");
  // Retorna 0 valores com 2 Parâmetros
  b_bni_ret0(vm, 2);
}

// public native void hi(Integer x);
b_bni_state_t hello_Hello$hi$breder_lang_Integer
    (b_vm_t* vm, b_object_t* object) {
  // Pega o parâmetro 0 como número inteiro.
  b_bni_get_param_as_integer(vm, oparam0, param0, 0);
  // Realiza o processamento
  printf("Hello World!\n");
  // Retorna 0 valores com 1 Parâmetros
  b_bni_ret0(vm, 1);
}

// public native void hi(Natural x);
b_bni_state_t hello_Hello$hi$breder_lang_Natural
    (b_vm_t* vm, b_object_t* object) {
  // Pega o parâmetro 0 como número natural.
  b_bni_get_param_as_natural(vm, oparam0, param0, 0);
  // Realiza o processamento
  printf("Hello World!\n");
  // Retorna 0 valores com 1 Parâmetros
  b_bni_ret0(vm, 1);
}

// public native void hi(Index x);
b_bni_state_t hello_Hello$hi$breder_lang_Index
    (b_vm_t* vm, b_object_t* object) {
  // Pega o parâmetro 0 como indice.
  b_bni_get_param_as_index(vm, oparam0, param0, 0);
  // Realiza o processamento
  printf("Hello World!\n");
  // Retorna 0 valores com 1 Parâmetros
  b_bni_ret0(vm, 1);
}

// public native void hi(String x);
b_bni_state_t hello_Hello$hi$breder_lang_String
    (b_vm_t* vm, b_object_t* object) {
  // Pega o parâmetro 0 como string.
  b_bni_get_param_as_string(vm, oparam0, param0, 0);
  // Realiza o processamento
  printf("Hello World!\n");
  // Retorna 0 valores com 1 Parâmetros
  b_bni_ret0(vm, 1);
}

// public native void hi(Boolean x);
b_bni_state_t hello_Hello$hi$breder_lang_Boolean
    (b_vm_t* vm, b_object_t* object) {
  // Pega o parâmetro 0 como boolean.
  b_bni_get_param_as_boolean(vm, oparam0, param0, 0);
  // Realiza o processamento
  printf("Hello World!\n");
  // Retorna 0 valores com 1 Parâmetros
  b_bni_ret0(vm, 1);
}

// public native void hi(IList&lt;String&gt; w);
b_bni_state_t hello_Hello$hi$breder_util_IList
    (b_vm_t* vm, b_object_t* object) {
  // Pega o parâmetro 0 protegido contra nulo.
  // Caso seja nulo, NullPointerRuntimeExcepiton
  b_bni_get_param_notnull(vm, oparam0, 0);
  // Pega o parâmetro 0 não protegido.
  b_bni_get_param_nullable(vm, oparam0_nullable, 0);
  // Realiza o processamento
  printf("Hello World!\n");
  // Retorna 0 valores com 1 Parâmetros
  b_bni_ret0(vm, 1); 
}

// public native static void hi();
b_bni_state_t hello_Hello$$hi
    (b_vm_t* vm) {
  // Realiza o processamento
  printf("Hello World!\n");
  // Estático, retorna 0 valores com 0 Parâmetros.
  b_bni_sret0(vm, 0);
}

// public native static void hi(notnull Number x);
b_bni_state_t hello_Hello$$hi$breder_lang_Number
    (b_vm_t* vm) {
  // Pega o parâmetro 0 como número.
  b_bni_get_param_as_number(vm, oparam0, param0, 0);
  // Realiza o processamento
  printf("Hello World!\n");
  // Estático, retorna 0 valores com 1 Parâmetros.
  b_bni_sret0(vm, 1);
}

// public native static void hi(IList&lt;String&gt; w);
b_bni_state_t hello_Hello$$hi$breder_util_IList
    (b_vm_t* vm) {
  // Pega o parâmetro 0 protegido contra nulo.
  // Caso seja nulo, NullPointerRuntimeExcepiton
  b_bni_get_param_notnull(vm, oparam0, 0);
  // Pega o parâmetro 0 não protegido.
  b_bni_get_param_nullable(vm, oparam0_nullable, 0);
  // Realiza o processamento
  printf("Hello World!\n");
  // Estático, retorna 0 valores com 1 Parâmetros.
  b_bni_sret0(vm, 1); 
}
</pre>

<p>
Repare que os método nativos não estático, possuem a referência do próprio objeto como parâmetro na função.
Isso porque esses métodos não nativos são referentes ao objeto e não a classe. 
</p>

<p>
Foi especificado que quando se deseja recuperar um parâmetro primitivo, sempre não poderá ser passado o valor nulo.
Para que consigo recuperar o valor nulo, terá que usar a função <code>'b_bni_get_param_nullable'</code> para receber o parâmetro, seguido pela conversão de objeto para primitivo número.
</p>