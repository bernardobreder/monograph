<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p>
No t�pico anterior, foi discutido como criar m�todos nativos com par�metros. 
A seguir, ser� abordado como criar a opera��o que retornam valores.
</p>

<p>
O nome do m�todo nativo na Linguagem C ANSI n�o ser� modificado em fun��o no n�mero de retornos.
</p>

<p>
Abaixo ter� diversos exemplos que demonstram como converter o nome da Linguagem Breder para Linguagem C ANSI.
</p>

<pre>
package hello;
public class Hello {
  public native Number hi();
  public native Number, Natural hi(Number x);
  public native notnull Boolean hi(Boolean x);
  public native static Number hi();
}
</pre>

<p>
O c�digo fonte acima na Linguagem Breder ser� convertida para o c�digo fonte abaixo na Linguagem C ANSI.
</p>

<pre>
#include "b_bni.h"

// public native Number hi();
b_bni_state_t hello_Hello$hi
    (b_vm_t* vm, b_object_t* object) {
  // Realiza o processamento
  printf("Hello World!\n");
  // Criando um objeto do tipo Number
  b_bni_new_onumber_define(vm, oresult0, 1.2);
  // Retorna 1 valores com 0 Par�metros
  b_bni_ret1(vm, oresult0, 0);
}

// public native Number, Natural hi(Number x);
b_bni_state_t hello_Hello$hi$breder_lang_Number
    (b_vm_t* vm, b_object_t* object) {
  // Recupera o par�metro de indice 0 como n�mero.
  b_bni_get_param_as_number(vm, oparam0, param0, 0);
  // Criando um objeto do tipo Number
  b_object_t* oresult0 = b_bni_new_onumber(vm, 1.2);
  if (!oresult0) { return B_BNI_FAIL; }
  b_bni_new_onatural_define(vm, oresult1, 12);
  // Realiza o processamento
  printf("Hello World!\n");
  // Retorna 2 valores com 1 Par�metros
  b_bni_ret2(vm, oresult0, oresult1, 1);
}

// public native notnull Boolean hi(Boolean x);
b_bni_state_t hello_Hello$hi$breder_lang_Boolean
    (b_vm_t* vm, b_object_t* object) {
  // Recupera o par�metro de indice 0 como boolean.
  b_bni_get_param_as_boolean(vm, oparam0, param0, 0);
  // Criando um objeto do tipo Boolean
  b_bni_new_oboolean_define(vm, oresult0, B_BNI_TRUE);
  // Realiza o processamento
  printf("Hello World!\n");
  // Retorna 1 valores com 1 Par�metros
  b_bni_ret0(vm, oresult0, 1);
}

// public native static Number hi();
b_bni_state_t hello_Hello$$hi
    (b_vm_t* vm) {
  // Realiza o processamento
  printf("Hello World!\n");
  // Criando um objeto do tipo Number
  b_object_t* oresult0 = b_bni_new_onumber(vm, 1.2);
  if (!oresult0) { return B_BNI_FAIL; }
  // Est�tico, retorna 1 valores com 0 Par�metros.
  b_bni_sret1(vm, oresult0, 0);
}
</pre>
