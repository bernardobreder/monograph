<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p>
No t�pico anterior, foi discutido como criar uma exemplo simples de opera��o nativa. 
A seguir, ser� abordado como criar a opera��o com par�metros.
</p>

<p>
O nome do m�todo native na Linguagem C ANSI foi modificado por um nome um pouco diferente.
O mesmo ser� usado quando se deseja colocar par�metros nos m�todos nativos.
</p>

<p>
Todos os m�todos implementado nativamente, dever� ter em seu nome os par�metros.
Abaixo ter� diversos exemplos que demonstram como converter o nome da Linguagem Breder para Linguagem C Anci.
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
O c�digo fonte acima na Linguagem Breder ser� convertida para o c�digo fonte abaixo na Linguagem C ANSI.
</p>

<pre>
#include "b_bni.h"

// public native void hi();
b_bni_state_t hello_Hello$hi
    (b_vm_t* vm, b_object_t* object) {
  // Realiza o processamento
  printf("Hello World!\n");
  // Retorna 0 valores com 0 Par�metros
  b_bni_ret0(vm, 0);
}

// public native void hi(Number x);
b_bni_state_t hello_Hello$hi$breder_lang_Number
    (b_vm_t* vm, b_object_t* object) {
  // Pega o par�metro 0 como n�mero.
  b_bni_get_param_as_number(vm, oparam0, param0, 0);
  // Realiza o processamento
  printf("Hello World!\n");
  // Retorna 0 valores com 1 Par�metros
  b_bni_ret0(vm, 1);
}

// public native void hi(Number x, Number y);
b_bni_state_t  
    hello_Hello$hi$breder_lang_Number$breder_lang_Number
    (b_vm_t* vm, b_object_t* object) {
  // Pega o par�metro 0 como n�mero.
  b_bni_get_param_as_number(vm, oparam0, param0, 0);
  // Pega o par�metro 1 como n�mero.
  b_bni_get_param_as_number(vm, oparam1, param1, 1);
  // Realiza o processamento
  printf("Hello World!\n");
  // Retorna 0 valores com 2 Par�metros
  b_bni_ret0(vm, 2);
}

// public native void hi(Integer x);
b_bni_state_t hello_Hello$hi$breder_lang_Integer
    (b_vm_t* vm, b_object_t* object) {
  // Pega o par�metro 0 como n�mero inteiro.
  b_bni_get_param_as_integer(vm, oparam0, param0, 0);
  // Realiza o processamento
  printf("Hello World!\n");
  // Retorna 0 valores com 1 Par�metros
  b_bni_ret0(vm, 1);
}

// public native void hi(Natural x);
b_bni_state_t hello_Hello$hi$breder_lang_Natural
    (b_vm_t* vm, b_object_t* object) {
  // Pega o par�metro 0 como n�mero natural.
  b_bni_get_param_as_natural(vm, oparam0, param0, 0);
  // Realiza o processamento
  printf("Hello World!\n");
  // Retorna 0 valores com 1 Par�metros
  b_bni_ret0(vm, 1);
}

// public native void hi(Index x);
b_bni_state_t hello_Hello$hi$breder_lang_Index
    (b_vm_t* vm, b_object_t* object) {
  // Pega o par�metro 0 como indice.
  b_bni_get_param_as_index(vm, oparam0, param0, 0);
  // Realiza o processamento
  printf("Hello World!\n");
  // Retorna 0 valores com 1 Par�metros
  b_bni_ret0(vm, 1);
}

// public native void hi(String x);
b_bni_state_t hello_Hello$hi$breder_lang_String
    (b_vm_t* vm, b_object_t* object) {
  // Pega o par�metro 0 como string.
  b_bni_get_param_as_string(vm, oparam0, param0, 0);
  // Realiza o processamento
  printf("Hello World!\n");
  // Retorna 0 valores com 1 Par�metros
  b_bni_ret0(vm, 1);
}

// public native void hi(Boolean x);
b_bni_state_t hello_Hello$hi$breder_lang_Boolean
    (b_vm_t* vm, b_object_t* object) {
  // Pega o par�metro 0 como boolean.
  b_bni_get_param_as_boolean(vm, oparam0, param0, 0);
  // Realiza o processamento
  printf("Hello World!\n");
  // Retorna 0 valores com 1 Par�metros
  b_bni_ret0(vm, 1);
}

// public native void hi(IList&lt;String&gt; w);
b_bni_state_t hello_Hello$hi$breder_util_IList
    (b_vm_t* vm, b_object_t* object) {
  // Pega o par�metro 0 protegido contra nulo.
  // Caso seja nulo, NullPointerRuntimeExcepiton
  b_bni_get_param_notnull(vm, oparam0, 0);
  // Pega o par�metro 0 n�o protegido.
  b_bni_get_param_nullable(vm, oparam0_nullable, 0);
  // Realiza o processamento
  printf("Hello World!\n");
  // Retorna 0 valores com 1 Par�metros
  b_bni_ret0(vm, 1); 
}

// public native static void hi();
b_bni_state_t hello_Hello$$hi
    (b_vm_t* vm) {
  // Realiza o processamento
  printf("Hello World!\n");
  // Est�tico, retorna 0 valores com 0 Par�metros.
  b_bni_sret0(vm, 0);
}

// public native static void hi(notnull Number x);
b_bni_state_t hello_Hello$$hi$breder_lang_Number
    (b_vm_t* vm) {
  // Pega o par�metro 0 como n�mero.
  b_bni_get_param_as_number(vm, oparam0, param0, 0);
  // Realiza o processamento
  printf("Hello World!\n");
  // Est�tico, retorna 0 valores com 1 Par�metros.
  b_bni_sret0(vm, 1);
}

// public native static void hi(IList&lt;String&gt; w);
b_bni_state_t hello_Hello$$hi$breder_util_IList
    (b_vm_t* vm) {
  // Pega o par�metro 0 protegido contra nulo.
  // Caso seja nulo, NullPointerRuntimeExcepiton
  b_bni_get_param_notnull(vm, oparam0, 0);
  // Pega o par�metro 0 n�o protegido.
  b_bni_get_param_nullable(vm, oparam0_nullable, 0);
  // Realiza o processamento
  printf("Hello World!\n");
  // Est�tico, retorna 0 valores com 1 Par�metros.
  b_bni_sret0(vm, 1); 
}
</pre>

<p>
Repare que os m�todo nativos n�o est�tico, possuem a refer�ncia do pr�prio objeto como par�metro na fun��o.
Isso porque esses m�todos n�o nativos s�o referentes ao objeto e n�o a classe. 
</p>

<p>
Foi especificado que quando se deseja recuperar um par�metro primitivo, sempre n�o poder� ser passado o valor nulo.
Para que consigo recuperar o valor nulo, ter� que usar a fun��o <code>'b_bni_get_param_nullable'</code> para receber o par�metro, seguido pela convers�o de objeto para primitivo n�mero.
</p>