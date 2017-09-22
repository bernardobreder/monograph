#ifndef B_BNI_H_
#define B_BNI_H_

#include "breder.h"

typedef struct b_bni_string_t b_bni_string_t;

struct b_bni_string_t {
	int length;
	int hashcode;
	char text[255];
};

#define b_bni_state char
#define B_BNI_SUCCESS 1
#define B_BNI_FAIL 0

#define b_bni_new_ostring_define(VM,NAME,VALUE) \
		b_object_t* NAME = b_bni_new_ostring(VM, VALUE); \
		if(!NAME) { \
			return 0; \
		}

#define b_bni_new_onumber_define(VM,NAME,VALUE) \
		b_object_t* NAME = b_bni_new_onumber(VM, VALUE); \
		if(!NAME) { \
			return 0; \
		}

#define b_bni_new_ofalse_define(VM,NAME) \
		b_object_t* NAME = b_bni_new_ofalse(VM); \
		if(!NAME) { \
			return 0; \
		}

#define b_bni_new_oboolean_define(VM,NAME,FLAG) \
		b_object_t* NAME = b_bni_new_oboolean(VM,FLAG); \
		if(!NAME) { \
			return 0; \
		}

#define b_bni_new_otrue_define(VM,NAME) \
		b_object_t* NAME = b_bni_new_ofalse(VM); \
		if(!NAME) { \
			return 0; \
		}

#define b_bni_get_param_nullable(VM,NAME,INDEX) \
		b_object_t* NAME = b_bni_get_param(VM, INDEX)

#define b_bni_get_param_notnull(VM,NAME,INDEX) \
		b_bni_get_param_nullable(VM,NAME,INDEX); \
		if(!NAME){ \
			return b_bni_throw_null_pointer_exception(VM); \
		}

#define b_bni_get_param_number(VM,ONAME,NAME,INDEX) \
		b_bni_get_param_notnull(VM,ONAME,INDEX); \
		double NAME = b_bni_onumber_to_primitive(vm, ONAME);

#define b_bni_get_param_int(VM,ONAME,NAME,INDEX) \
		b_bni_get_param_notnull(VM,ONAME,INDEX); \
		int NAME = b_bni_onumber_to_primitive(vm, ONAME);

#define b_bni_get_param_string(VM,ONAME,NAME,INDEX) \
		b_bni_get_param_notnull(VM,ONAME,INDEX); \
		const char* NAME = b_bni_ostring_to_text(vm, ONAME);

#define b_bni_call_1_return_0_param_nullable(VM,NAME,OBJECT,METHODNAME) \
		b_object_t* NAME = b_bni_call_1_return_0_param(VM,OBJECT,METHODNAME)

#define b_bni_call_1_return_0_param_notnull(VM,NAME,OBJECT,METHODNAME) \
		b_bni_call_1_return_0_param_nullable(VM,NAME,OBJECT,METHODNAME); \
		if(!NAME){ \
			return b_bni_throw_null_pointer_exception(VM); \
		}

#define b_bni_call_0_return_0_param_safe(VM,OBJECT,METHODNAME) \
		if(b_bni_call_0_return_0_param(VM,OBJECT,METHODNAME) == B_BNI_FAIL) { \
			return B_BNI_FAIL; \
		}

#define b_bni_call_0_return_1_param_safe(VM,OBJECT,METHODNAME,OPARAM1) \
		if(b_bni_call_0_return_1_param(VM,OBJECT,METHODNAME,OPARAM1) == B_BNI_FAIL) { \
			return B_BNI_FAIL; \
		}

#define b_bni_call_0_return_2_param_safe(VM,OBJECT,METHODNAME,OPARAM1,OPARAM2) \
		if(b_bni_call_0_return_2_param(VM,OBJECT,METHODNAME,OPARAM1,OPARAM2) == B_BNI_FAIL) { \
			return B_BNI_FAIL; \
		}

#define b_bni_call_0_return_3_param_safe(VM,OBJECT,METHODNAME,OPARAM1,OPARAM2,OPARAM3) \
		if(b_bni_call_0_return_3_param(VM,OBJECT,METHODNAME,OPARAM1,OPARAM2,OPARAM3) == B_BNI_FAIL) { \
			return B_BNI_FAIL; \
		}

#define b_bni_call_0_return_4_param_safe(VM,OBJECT,METHODNAME,OPARAM1,OPARAM2,OPARAM3,OPARAM4) \
		if(b_bni_call_0_return_1_param(VM,OBJECT,METHODNAME,OPARAM1,OPARAM2,OPARAM3,OPARAM4) == B_BNI_FAIL) { \
			return B_BNI_FAIL; \
		}

/**
 * Recupera o valor do parametro de indice 'n' um valor. O indice estï¿½ organizado de esquerda
 * para direita, portanto o indice zero corresponde o primeiro parametro.
 */
b_object_t* b_bni_get_param(b_vm_t*, int n);

/**
 * Recupera o valor do parametro de indice 'n' um valor. O indice estï¿½ organizado de esquerda
 * para direita, portanto o indice zero corresponde o primeiro parametro.
 */
b_object_t* b_bni_get_param(b_vm_t*, int n);

/**
 * Recupera o valor do parametro de indice 'n' um valor. O indice estï¿½ organizado de esquerda
 * para direita, portanto o indice zero corresponde o primeiro parametro.
 */
b_object_t* b_bni_get_static_param(b_vm_t*, int n);

/**
 * Atribui ao retorno de indice 'n' um valor. O indice estï¿½ organizado de esquerda
 * para direita, portanto o indice zero corresponde o primeiro retorno.
 */
b_bni_state b_bni_set_return(b_vm_t* vm, int n, b_object_t* value);

/**
 * Converte um objeto do tipo breder.lang.Number para um tipo primitivo double
 */
double b_bni_onumber_to_primitive(b_vm_t*, b_object_t* onumber);

/**
 * Converte um objeto  do tipo breder.lang.String para um tipo primitivo char*.
 * O seu conteï¿½do nï¿½o ï¿½ um copia do conteï¿½do do objeto e sim o prï¿½prio conteï¿½do do objeto.
 * Portanto, nï¿½o se pode mudar o conteï¿½do, pois estarï¿½ mudando a do objeto.
 */
public const char* b_bni_ostring_to_text(b_vm_t*, b_object_t* ostring);

b_object_t* b_bni_get_throw(b_vm_t* vm);

void b_bni_release_throw(b_vm_t* vm);

/**
 * Converte um objeto do tipo breder.lang.String para o comprimento do conteï¿½do do objeto.
 * Essa funï¿½ï¿½o ï¿½ mais rï¿½pida do que qualquer outra funï¿½ï¿½o, pois essa informaï¿½ï¿½o estï¿½ em cache.
 */
int b_bni_ostring_to_length(b_vm_t*, b_object_t* ostring);

/**
 * Converte um objeto do tipo breder.lang.String para o hashcode do conteï¿½do do objeto.
 * Essa funï¿½ï¿½o ï¿½ mais rï¿½pida do que qualquer outra funï¿½ï¿½o, pois essa informaï¿½ï¿½o estï¿½ em cache.
 */
int b_bni_ostring_to_hashcode(b_vm_t*, b_object_t* ostring);

/**
 * Converte um objeto do tipo breder.lang.Number para um tipo primitivo double
 */
int b_bni_oboolean_to_primitive(b_vm_t*, b_object_t* oboolean);

/**
 * Atribui um estrutura desconhecido para um objeto. Em quanto o objeto estiver vivo,
 * esse conteï¿½do estarï¿½ vivo. Caso o objeto for coletado, a funï¿½ï¿½o finalize serï¿½ chamada
 * para poder coletar essa estrutura desconhecida.
 */
void b_bni_set_data(b_vm_t*, b_object_t* object, void* data);

/**
 * Retorna uma estrutura desconhecida de um objeto.
 */
void* b_bni_get_data(b_vm_t*, b_object_t* object);

b_object_t* b_bni_new_oboolean(b_vm_t* vm, int flag);

/**
 * Cria um objeto do tipo breder.lang.Number
 */
public b_object_t* b_bni_new_onumber(b_vm_t*, double value);

/**
 * Cria um objeto do tipo breder.lang.String.
 * O conteï¿½do passado como parametro ï¿½ clonado
 * para o objeto a ser criado.
 */
b_object_t* b_bni_new_ostring(b_vm_t*, char* value);

/**
 * Cria um objeto do tipo breder.lang.Boolean com valor verdadeiro
 */
b_object_t* b_bni_new_otrue(b_vm_t*);

/**
 * Cria um objeto do tipo breder.lang.Boolean com valor falso
 */
b_object_t* b_bni_new_ofalse(b_vm_t*);

/**
 * Incrementa o contador de uso de objeto nativamente
 */
void b_bni_inc_used(b_vm_t*, b_object_t* ovalue);

/**
 * Decrementa o contador de uso de objeto nativamente
 */
void b_bni_dec_used(b_vm_t*, b_object_t* ovalue);

/**
 * Realiza a chamada de um método correspondente a um indice.
 */
int b_bni_call(b_vm_t* vm, const char* methodname, int params);

b_object_t* b_bni_call_1_return_0_param(b_vm_t* vm, b_object_t* object, const char* methodname);

int b_bni_call_0_return_0_param(b_vm_t* vm, b_object_t* object, const char* methodname);

int b_bni_call_0_return_1_param(b_vm_t* vm, b_object_t* object, const char* methodname, b_object_t* oparam);

int b_bni_call_0_return_2_param(b_vm_t* vm, b_object_t* object, const char* methodname, b_object_t* oparam1,
		b_object_t* oparam2);

int b_bni_call_0_return_3_param(b_vm_t* vm, b_object_t* object, const char* methodname, b_object_t* oparam1,
		b_object_t* oparam2, b_object_t* oparam3);

int b_bni_call_0_return_4_param(b_vm_t* vm, b_object_t* object, const char* methodname, b_object_t* oparam1,
		b_object_t* oparam2, b_object_t* oparam3, b_object_t* oparam4);

/**
 * Dispara um erro de NullPointerException
 */
int b_bni_throw_null_pointer_exception(b_vm_t* vm);

/**
 * Dispara um erro de OutOfMemoryRuntimeException
 */
int b_bni_throw_out_of_memory_runtime_exception(b_vm_t* vm);

/**
 * Dispara um erro para uma classe Generica
 */
int b_bni_throw_generic_exception(b_vm_t* vm, char* classname);

b_object_t* b_bni_new(b_vm_t* vm, int classindex);

void b_bni_push(b_vm_t* vm, b_object_t* object);

b_object_t* b_bni_pop(b_vm_t* vm);

b_object_t* b_bni_new1(b_vm_t* vm, char* classname);

void b_bni_inc(b_vm_t* vm, int count);

void b_bni_dec(b_vm_t* vm, int count);

#endif
