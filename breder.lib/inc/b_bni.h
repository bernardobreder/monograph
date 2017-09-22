#ifndef B_BNI_H_
#define B_BNI_H_

#include "breder.h"

typedef struct b_bni_string_t b_bni_string_t;

struct b_bni_string_t {
	int length;
	int hashcode;
	char text[255];
};

#define b_bni_state_t b_state_t
#define B_BNI_SUCCESS B_STATE_SUCCESS
#define B_BNI_FAIL B_STATE_FAIL
#define B_BNI_TRUE 1
#define B_BNI_FALSE 0
#define B_BNI_NUMBER_MAX 999999999.999999
#define B_BNI_INTEGER_MAX 999999999

#define b_bni_test_fail_ioexception(VM, EXP, NOTEXPECTED) \
		if((EXP) == (NOTEXPECTED)) { \
			b_bni_new_0_param(vm, othrow, b_bni_method_ioexception_init_id(vm)); \
			b_bni_throw(vm, othrow); \
		}

#define b_bni_test_fail_classcastexception(VM, OBJECT, CAST_TO_CLASS_INDEX) \
		b_vm_exec_test(!(VM)->cast[b_object_class(OBJECT)][CAST_TO_CLASS_INDEX],b_vm_throw_cast_runtime_exception);

#define b_bni_test(VM, DATA) \
		if (DATA == null) { \
			b_bni_new_0_param(VM, othrow, b_bni_method_outofmemoryruntimeexception_init_id(VM)); \
			b_bni_throw(VM, othrow); \
		}

#define b_bni_test_index(VM, COND) \
		if (COND) { \
			b_bni_new_0_param(VM, othrow, b_bni_method_indexoutofboundsruntimeexception_init_id(VM)); \
			b_bni_throw(VM, othrow); \
		}

#define b_bni_throw(VM, OBJECT) \
		return b_vm_throw(VM, OBJECT);

#define b_bni_alloc_typed(VM, TYPE, NAME) \
		b_bni_alloc_sized(VM, TYPE, NAME, sizeof(TYPE))

#define b_bni_alloc_sized(VM, TYPE, NAME, SIZE) \
		TYPE* NAME = (TYPE*)b_memory_alloc(VM, SIZE); \
		b_bni_test(VM, NAME);

#define b_bni_alloc(VM, NAME, SIZE) \
		NAME = b_memory_alloc(VM, SIZE); \
		b_bni_test(VM, NAME);

#define b_bni_class(VM, NAME, CLASSNAME) \
		{ \
			b_class_t* c = b_vm_find_class(VM, CLASSNAME); \
			if(c == null) { \
				b_bni_new_0_param(VM, othrow, b_bni_method_classnotfoundexception_init_id(VM)); \
				b_bni_throw(VM, othrow); \
			} \
			NAME = c->index; \
		}

#define b_bni_method_define(BNI_METHOD_NAME, NAME, CLASSID, METHODNAME) \
		static b_method_id_t NAME = null ; \
		b_method_id_t BNI_METHOD_NAME(b_vm_t* vm) { \
			if (NAME == null) { \
				NAME = b_vm_find_method(vm, CLASSID(vm), METHODNAME)->index; \
			} \
			return NAME; \
		}

#define b_bni_class_define(METHODNAME, NAME, CLASSNAME) \
		static b_class_id_t NAME = null ; \
		b_class_id_t METHODNAME(b_vm_t* vm) { \
			if (NAME == null) { \
				NAME = b_vm_find_class(vm, CLASSNAME)->index; \
			} \
			return NAME; \
		}

#define b_bni_ret_set(VM, INDEX, OBJECT) \
		(VM)->cobjstack[-(INDEX)] = OBJECT;

#define b_bni_cret(VM, PARAMS) b_bni_sret0(VM, PARAMS);

#define b_bni_sret0(VM, PARAMS) \
		if (PARAMS) { \
			b_bni_opcode_dec(VM, PARAMS); \
		} \
		return B_BNI_SUCCESS;

#define b_bni_ret0(VM, PARAMS) \
		b_bni_sret0(VM, (PARAMS)+1)

#define b_bni_sret1(VM, PARAMS, OBJECT1) \
		b_bni_ret_set(VM, (PARAMS) <= 1 ? 0 : (PARAMS)-1, OBJECT1); \
		b_bni_sret0(VM, (PARAMS) <= 1 ? 0 : (PARAMS)-1);

#define b_bni_ret1(VM, PARAMS, OBJECT1) \
		b_bni_ret_set(VM, (PARAMS), (OBJECT1)); \
		b_bni_sret0(VM, (PARAMS));

#define b_bni_sret2(VM, PARAMS, OBJECT1) \
		b_bni_ret_set(VM, (PARAMS) <= 2 ? 1 : (PARAMS)-2, OBJECT1); \
		b_bni_ret_set(VM, (PARAMS) <= 2 ? 0 : (PARAMS)-2, OBJECT2); \
		b_bni_sret0(VM, (PARAMS) <= 2 ? 0 : (PARAMS)-1);

#define b_bni_ret2(VM, PARAMS, OBJECT1, OBJECT2) \
		b_bni_ret_set(VM, (PARAMS) <= 1 : 1 ? (PARAMS), OBJECT1); \
		b_bni_ret_set(VM, (PARAMS) <= 1 : 0 ? , OBJECT2); \
		b_bni_sret0(VM, (PARAMS) <= 1 : 0 ? (PARAMS)-1);

#define b_bni_alloc_array(VM, TYPE, NAME, LENGTH) \
		b_bni_alloc_sized(VM, TYPE, NAME, sizeof(TYPE) * (LENGTH));

#define b_bni_execute_super_0_return_0_param(VM, OBJECT, CLASS_ID, METHOD_ID) \
		b_bni_execute_specific_0_return_0_param(VM, OBJECT, CLASS_ID, METHOD_ID)

#define b_bni_execute_specific_0_return_0_param(VM, OBJECT, CLASS_ID, METHOD_ID) \
		b_bni_opcode_push(VM, OBJECT); \
		b_bni_opcode_ojump_specific_define(VM, CLASS_ID, METHOD_ID);

#define b_bni_super_0_param(VM, OBJECT, METHOD_ID) \
		b_bni_opcode_push(VM, OBJECT); \
		b_bni_opcode_ojump_define(VM, METHOD_ID); \
		b_bni_opcode_dec(VM, 1);

#define b_bni_super_1_param(VM, OBJECT, METHOD_ID, OPARAM1) \
		b_bni_opcode_push(VM, OBJECT); \
		b_bni_opcode_push(VM, OPARAM1); \
		b_bni_opcode_ojump_define(VM, METHOD_ID); \
		b_bni_opcode_dec(VM, 1);

#define b_bni_super_2_param(VM, OBJECT, METHOD_ID, OPARAM1, OPARAM2) \
		b_bni_opcode_push(VM, OBJECT); \
		b_bni_opcode_push(VM, OPARAM2); \
		b_bni_opcode_push(VM, OPARAM1); \
		b_bni_opcode_ojump_define(VM, METHOD_ID); \
		b_bni_opcode_dec(VM, 1);

#define b_bni_super_3_param(VM, OBJECT, METHOD_ID, OPARAM1, OPARAM2, OPARAM3) \
		b_bni_opcode_push(VM, OBJECT); \
		b_bni_opcode_push(VM, OPARAM3); \
		b_bni_opcode_push(VM, OPARAM2); \
		b_bni_opcode_push(VM, OPARAM1); \
		b_bni_opcode_ojump_define(VM, METHOD_ID); \
		b_bni_opcode_dec(VM, 1);

#define b_bni_super_4_param(VM, OBJECT, METHOD_ID, OPARAM1, OPARAM2, OPARAM3, OPARAM4) \
		b_bni_opcode_push(VM, OBJECT); \
		b_bni_opcode_push(VM, OPARAM4); \
		b_bni_opcode_push(VM, OPARAM3); \
		b_bni_opcode_push(VM, OPARAM2); \
		b_bni_opcode_push(VM, OPARAM1); \
		b_bni_opcode_ojump_define(VM, METHOD_ID); \
		b_bni_opcode_dec(VM, 1);

b_bni_state_t b_bni_execute_0_return_0_param (b_vm_t* vm, b_object_t* object, b_method_id_t mindex);
b_bni_state_t b_bni_execute_1_return_0_param (b_vm_t* vm, b_object_t* object, b_object_t** oreturn1, b_method_id_t mindex);
b_bni_state_t b_bni_execute_1_return_1_param (b_vm_t* vm, b_object_t* object, b_object_t** oreturn1, b_method_id_t mindex, b_object_t* oparam1);

#define b_bni_execute_1_return_2_param(VM, ONAME, OBJECT, METHOD_ID, OPARAM1, OPARAM2) \
		b_bni_opcode_push(VM, OBJECT); \
		b_bni_opcode_push(VM, OPARAM2); \
		b_bni_opcode_push(VM, OPARAM1); \
		b_bni_opcode_ojump_define(VM, METHOD_ID); \
		b_object_t* ONAME = b_bni_opcode_pop(VM);

#define b_bni_execute_0_return_1_param(VM, OBJECT, METHOD_ID, OPARAM1) \
		b_bni_opcode_push(VM, OBJECT); \
		b_bni_opcode_push(VM, OPARAM1); \
		b_bni_opcode_ojump_define(VM, METHOD_ID);

#define b_bni_execute_0_return_2_param(VM, OBJECT, METHOD_ID, OPARAM1, OPARAM2) \
		b_bni_opcode_push(VM, OBJECT); \
		b_bni_opcode_push(VM, OPARAM2); \
		b_bni_opcode_push(VM, OPARAM1); \
		b_bni_opcode_ojump_define(VM, METHOD_ID);

#define b_bni_execute_0_return_3_param(VM, OBJECT, METHOD_ID, OPARAM1, OPARAM2, OPARAM3) \
		b_bni_opcode_push(VM, OBJECT); \
		b_bni_opcode_push(VM, OPARAM3); \
		b_bni_opcode_push(VM, OPARAM2); \
		b_bni_opcode_push(VM, OPARAM1); \
		b_bni_opcode_ojump_define(VM, METHOD_ID);

#define b_bni_new_0_param(VM, ONAME, METHOD_ID) \
		b_bni_opcode_new_define(VM, b_bni_classid_by_methodid(VM, METHOD_ID)); \
		b_bni_opcode_ojump_define(VM, METHOD_ID); \
		b_object_t* ONAME = b_bni_opcode_pop(VM); \
		if(ONAME == null) { \
			return B_BNI_FAIL; \
		}

#define b_bni_new_1_param(VM, ONAME, METHOD_ID, OPARAM1) \
		b_bni_opcode_new_define(VM, b_bni_classid_by_methodid(VM, METHOD_ID)); \
		b_bni_opcode_push(vm, OPARAM1); \
		b_bni_opcode_ojump_define(VM, METHOD_ID); \
		b_object_t* ONAME = b_bni_opcode_pop(VM); \
		if(ONAME == null) { \
			return B_BNI_FAIL; \
		}

#define b_bni_new_2_param(VM, ONAME, METHOD_ID, OPARAM1, OPARAM2) \
		b_bni_opcode_new_define(VM, b_bni_classid_by_methodid(VM, METHOD_ID)); \
		b_bni_opcode_push(vm, OPARAM2); \
		b_bni_opcode_push(vm, OPARAM1); \
		b_bni_opcode_ojump_define(VM, METHOD_ID); \
		b_object_t* ONAME = b_bni_opcode_pop(VM); \
		if(ONAME == null) { \
			return B_BNI_FAIL; \
		}

#define b_bni_new_3_param(VM, ONAME, METHOD_ID, OPARAM1, OPARAM2, OPARAM3) \
		b_bni_opcode_new_define(VM, b_bni_classid_by_methodid(VM, METHOD_ID)); \
		b_bni_opcode_push(vm, OPARAM3); \
		b_bni_opcode_push(vm, OPARAM2); \
		b_bni_opcode_push(vm, OPARAM1); \
		b_bni_opcode_ojump_define(VM, METHOD_ID); \
		b_object_t* ONAME = b_bni_opcode_pop(VM); \
		if(ONAME == null) { \
			return B_BNI_FAIL; \
		}

#define b_bni_new_4_param(VM, ONAME, METHOD_ID, OPARAM1, OPARAM2, OPARAM3, OPARAM4) \
		b_bni_opcode_new_define(VM, b_bni_classid_by_methodid(VM, METHOD_ID)); \
		b_bni_opcode_push(vm, OPARAM4); \
		b_bni_opcode_push(vm, OPARAM3); \
		b_bni_opcode_push(vm, OPARAM2); \
		b_bni_opcode_push(vm, OPARAM1); \
		b_bni_opcode_ojump_define(VM, METHOD_ID); \
		b_object_t* ONAME = b_bni_opcode_pop(VM); \
		if(ONAME == null) { \
			return B_BNI_FAIL; \
		}

#define b_bni_new_5_param(VM, ONAME, METHOD_ID, OPARAM1, OPARAM2, OPARAM3, OPARAM4, OPARAM5) \
		b_bni_opcode_new_define(VM, b_bni_classid_by_methodid(VM, METHOD_ID)); \
		b_bni_opcode_push(vm, OPARAM5); \
		b_bni_opcode_push(vm, OPARAM4); \
		b_bni_opcode_push(vm, OPARAM3); \
		b_bni_opcode_push(vm, OPARAM2); \
		b_bni_opcode_push(vm, OPARAM1); \
		b_bni_opcode_ojump_define(VM, METHOD_ID); \
		b_object_t* ONAME = b_bni_opcode_pop(VM); \
		if(ONAME == null) { \
			return B_BNI_FAIL; \
		}

#define b_bni_opcode_new_define(VM, CLASS_ID) \
		if(b_bni_opcode_new(VM, CLASS_ID) == B_BNI_FAIL) { \
			return B_BNI_FAIL; \
		}

#define b_bni_super_object(vm, object) {}
#define b_bni_super_object_finalize(vm, object) {}

#define b_bni_opcode_ojump_define(VM, METHOD_ID) \
		if(b_bni_opcode_ojump(VM, METHOD_ID) == B_BNI_FAIL) { \
			return B_BNI_FAIL; \
		}

#define b_bni_opcode_ojump_specific_define(VM, CLASS_ID, METHOD_ID) \
		if(b_bni_opcode_ojump_specific(VM, CLASS_ID, METHOD_ID) == B_BNI_FAIL) { \
			return B_BNI_FAIL; \
		}

#define b_bni_new_ostring_define(VM,NAME,VALUE) \
		b_object_t* NAME = b_bni_new_ostring(VM, VALUE); \
		if(!NAME) { \
			return B_BNI_FAIL; \
		}

#define b_bni_new_ofalse_define(VM, NAME) \
		b_object_t* NAME = b_bni_new_ofalse(VM);

#define b_bni_new_otrue_define(VM,NAME) \
		b_object_t* NAME = b_bni_new_ofalse(VM);

#define b_bni_get_param_nullable(VM,NAME,INDEX) \
		b_object_t* NAME = b_bni_get_param(VM, INDEX)

#define b_bni_get_param_notnull(VM, NAME, INDEX) \
		b_bni_get_param_nullable(VM, NAME, INDEX); \
		if(!NAME){ \
			b_bni_throw_null_pointer_exception(VM); \
		}

#define b_bni_get_param_as_boolean(VM,ONAME,NAME,INDEX) \
		b_bni_get_param_notnull(VM,ONAME,INDEX); \
		b_state_t NAME = b_bni_oboolean_to_primitive(vm, ONAME);

#define b_bni_get_param_as_number(VM,ONAME,NAME,INDEX) \
		b_bni_get_param_notnull(VM,ONAME,INDEX); \
		b_number_t NAME = b_bni_onumber_to_primitive(vm, ONAME);

#define b_bni_get_param_as_integer(VM,ONAME,NAME,INDEX) \
		b_bni_get_param_notnull(VM,ONAME,INDEX); \
		int NAME = b_bni_onumber_to_primitive(vm, ONAME);

#define b_bni_get_param_as_natural(VM, ONAME, NAME, INDEX) \
		b_bni_get_param_as_integer(VM, ONAME, NAME, INDEX)

#define b_bni_get_param_as_index(VM, ONAME, NAME, INDEX) \
		b_bni_get_param_as_integer(VM, ONAME, NAME, INDEX)

#define b_bni_get_param_as_string(VM, ONAME, NAME, INDEX) \
		b_bni_get_param_notnull(VM, ONAME, INDEX); \
		const char* NAME = b_bni_ostring_to_text(vm, ONAME);

/**
 * Recupera o valor do parametro de indice 'n' um valor. O indice est� organizado de esquerda
 * para direita, portanto o indice zero corresponde o primeiro parametro.
 */
#define b_bni_get_param(VM, INDEX) ((VM)->cobjstack[-(INDEX)])

/**
 * Atribui ao retorno de indice 'n' um valor. O indice est� organizado de esquerda
 * para direita, portanto o indice zero corresponde o primeiro retorno.
 */
#define b_bni_set_return(VM, INDEX, OBJECT) ((VM)->cobjstack[-(INDEX)] = OBJECT)

#define b_bni_onumber_to_number_define(VM, NAME, ONUMBER) \
		b_bni_onumber_to_primitive_define(VM, NAME, ONUMBER)

#define b_bni_onumber_to_primitive_define(VM, NAME, ONUMBER) \
		b_number_t NAME = b_bni_onumber_to_primitive(VM, ONUMBER)

#define b_bni_onumber_to_integer_define(VM, NAME, ONUMBER) \
		b_integer_t NAME = b_bni_onumber_to_integer(VM, ONUMBER)

#define b_bni_onumber_to_natural_define(VM, NAME, ONUMBER) \
		b_bni_onumber_to_integer_define(VM, NAME, ONUMBER)

#define b_bni_onumber_to_index_define(VM, NAME, ONUMBER) \
		b_bni_onumber_to_integer_define(VM, NAME, ONUMBER)

#define b_bni_data_index_class(VM, OBJECT, CINDEX) (VM)->dataIndexs[(OBJECT)->cindex][CINDEX]
#define b_bni_data_index(VM, OBJECT) b_bni_data_index_class(VM, OBJECT, (VM)->method->cindex)
#define b_bni_data_index_define(VM, TYPE, NAME, OBJECT) TYPE* NAME = (TYPE*) (OBJECT)->datas[b_bni_data_index(VM, OBJECT)]
#define b_bni_data_index_class_define(VM, TYPE, NAME, CINDEX, OBJECT) TYPE* NAME = (TYPE*) (OBJECT)->datas[b_bni_data_index_class(VM, OBJECT, CINDEX)]

/**
 * Converte um objeto do tipo breder.lang.Number para um tipo primitivo b_number_t
 */
#define b_bni_onumber_to_primitive(VM, ONUMBER) \
		(*(b_number_t*)(ONUMBER)->datas[0])

#define b_bni_ointeger_to_primitive(VM, ONUMBER) \
		(*(b_integer_t*)(ONUMBER)->datas[0])

#define b_bni_onatural_to_primitive(VM, ONUMBER) \
		b_bni_ointeger_to_primitive(VM, ONUMBER)

#define b_bni_oindex_to_primitive(VM, ONUMBER) \
		b_bni_ointeger_to_primitive(VM, ONUMBER)

#define b_bni_onumber_to_integer(VM, ONUMBER) \
		((b_integer_t)b_bni_onumber_to_primitive(VM, ONUMBER))

#define b_bni_ostring_to_text_define(VM, NAME, OBJECT) \
		const char* NAME = b_bni_ostring_to_text(VM, OBJECT)

/**
 * Converte um objeto  do tipo breder.lang.String para um tipo primitivo char*.
 * O seu conte�do n�o � um copia do conte�do do objeto e sim o pr�prio conte�do do objeto.
 * Portanto, n�o se pode mudar o conte�do, pois estar� mudando a do objeto.
 */
#define b_bni_ostring_to_text(VM, OBJECT) b_object_ostring_to_text(VM, OBJECT)

b_object_t* b_bni_get_throw (b_vm_t* vm);

void b_bni_release_throw (b_vm_t* vm);

/**
 * Converte um objeto do tipo breder.lang.String para o comprimento do conte�do do objeto.
 * Essa fun��o � mais r�pida do que qualquer outra fun��o, pois essa informa��o est� em cache.
 */
#define b_bni_ostring_to_length(VM, OSTRING) \
		(*((unsigned int*)(OSTRING)->datas[0]))

/**
 * Converte um objeto do tipo breder.lang.String para o hashcode do conte�do do objeto.
 * Essa fun��o � mais r�pida do que qualquer outra fun��o, pois essa informa��o est� em cache.
 */
unsigned int b_bni_ostring_to_hashcode (b_vm_t*, b_object_t* ostring);

/**
 * Converte um objeto do tipo breder.lang.Number para um tipo primitivo b_number_t
 */
#define b_bni_oboolean_to_primitive(VM, OBJECT) ((OBJECT)->datas[0] != null)

#define b_bni_oboolean_to_primitive_define(VM, OBJECT, NAME) b_state_t NAME = b_bni_oboolean_to_primitive(VM, OBJECT)

/**
 * Atribui um estrutura desconhecido para um objeto. Em quanto o objeto estiver vivo,
 * esse conte�do estar� vivo. Caso o objeto for coletado, a fun��o finalize ser� chamada
 * para poder coletar essa estrutura desconhecida.
 */

void b_bni_set_data_current (b_vm_t* vm, b_object_t* object, void* data);

void b_bni_set_data_index (b_vm_t* vm, b_object_t* object, unsigned int index, void* data);

/**
 * Retorna uma estrutura desconhecida de um objeto.
 */
#define b_bni_get_data(VM, OBJECT) ((OBJECT)->datas[0])

#define b_bni_set_data(VM, OBJECT, DATA) (b_bni_get_data(VM, OBJECT) = DATA)

#define b_bni_new_oboolean(VM, FLAG) b_object_new_boolean(VM, FLAG)

#define b_bni_new_oboolean_define(VM, NAME, VALUE) \
		b_object_t* NAME = b_bni_new_oboolean(VM, VALUE);

/**
 * Cria um objeto do tipo breder.lang.Number
 */
#define b_bni_new_onumber(VM, VALUE) b_object_new_number(VM, VALUE)

#define b_bni_new_number(VM, VALUE) \
		b_object_new_number(VM, VALUE)

#define b_bni_new_onumber_define(VM, NAME, VALUE) \
		b_object_t* NAME = b_bni_new_onumber(VM, VALUE); \
		if(!NAME) { \
			return B_BNI_FAIL; \
		}

/**
 * Cria um objeto do tipo breder.lang.Integer
 */
#define b_bni_new_ointeger(VM, VALUE) b_bni_new_onumber(VM, VALUE)

#define b_bni_new_ointeger_define(VM, NAME, VALUE) \
		b_object_t* NAME = b_bni_new_ointeger(VM, VALUE); \
		if(!NAME) { \
			return B_BNI_FAIL; \
		}

/**
 * Cria um objeto do tipo breder.lang.Natural
 */
#define b_bni_new_onatural(VM, VALUE) b_bni_new_ointeger(VM, VALUE)

#define b_bni_new_onatural_define(VM, NAME, VALUE) \
		b_object_t* NAME = b_bni_new_onatural(VM, VALUE); \
		if(!NAME) { \
			return B_BNI_FAIL; \
		}

/**
 * Cria um objeto do tipo breder.lang.Index
 */
#define b_bni_new_oindex(VM, VALUE) b_bni_new_onatural(VM, VALUE)

#define b_bni_new_oindex_define(VM, NAME, VALUE) \
		b_object_t* NAME = b_bni_new_oindex(VM, VALUE); \
		if(!NAME) { \
			return B_BNI_FAIL; \
		}

/**
 * Cria um objeto do tipo breder.lang.String.
 * O conte�do passado como parametro � clonado
 * para o objeto a ser criado.
 */
#define b_bni_new_ostring(VM, VALUE) b_object_new_string_generic(VM, VALUE, b_char_size(VALUE), 0, 0);

/**
 * Cria um objeto do tipo breder.lang.Boolean com valor verdadeiro
 */
#define b_bni_new_otrue(VM) b_object_new_boolean(VM, 1)

/**
 * Cria um objeto do tipo breder.lang.Boolean com valor falso
 */
#define b_bni_new_ofalse(VM) b_object_new_boolean(VM, 0)

#define b_bni_null(VM) null

/**
 * Incrementa o contador de uso de objeto nativamente
 */
#define b_bni_inc_used(VM, OBJECT) \
		if (OBJECT) { \
			b_object_used(OBJECT) += 1; \
		}

/**
 * Decrementa o contador de uso de objeto nativamente
 */
#define b_bni_dec_used(VM, OBJECT) \
		if (OBJECT) { \
			b_object_used(OBJECT) -= 1; \
		}

#define b_bni_throw_ioexception(VM) \
		return b_vm_throw_generic_exception(VM, "breder.io.IOException");

/**
 * Dispara um erro de NullPointerException
 */
#define b_bni_throw_null_pointer_exception(VM) \
		return b_vm_throw_null_pointer_runtime_exception(VM);

/**
 * Dispara um erro de OutOfMemoryRuntimeException
 */
#define b_bni_throw_out_of_memory_runtime_exception(VM) \
		return b_vm_throw_out_of_memory_runtime_exception(VM);

/**
 * Dispara um erro de ArithmeticRuntimeException
 */
#define b_bni_throw_arithmetic_runtime_exception(VM) \
		return b_vm_throw_arithmetic_runtime_exception(VM);

/**
 * Dispara um erro de ParserException
 */
#define b_bni_throw_parser_exception(VM) \
		return b_vm_throw_generic_exception(VM, "breder.compiler.ParseException");

/**
 * Dispara um erro de IndexOutOfBoundsRuntimeException
 */
#define b_bni_throw_index_out_of_bounds_runtime_exception(VM) \
		return b_vm_throw_generic_exception(VM, "breder.util.standard.IndexOutOfBoundsRuntimeException");

/**
 * Dispara um erro de IndexOutOfBoundsRuntimeException
 */
#define b_bni_throw_runtime_exception(VM) \
		return b_vm_throw_generic_exception(VM, "breder.lang.standard.RuntimeException");

/**
 * Dispara um erro para uma classe Generica
 */
#define b_bni_throw_generic_exception(VM, CLASSNAME) \
		return b_vm_throw_generic_exception (VM, CLASSNAME);

b_object_t* b_bni_new (b_vm_t* vm, b_class_id_t classindex);

#define b_bni_opcode_push(VM, OBJECT) \
		(VM)->cobjstack++; \
		*(VM)->cobjstack = ((b_object_t*)OBJECT);

#define b_bni_opcode_pop(VM) \
		*(VM)->cobjstack--;

b_object_t* b_bni_new1 (b_vm_t* vm, char* classname);

#define b_bni_opcode_inc(VM, COUNT) \
		(VM)->cobjstack += (COUNT)

#define b_bni_opcode_dec(VM, COUNT) \
		(VM)->cobjstack -= (COUNT)

#define b_bni_class_index(VM, OBJECT) \
		(OBJECT)->cindex

void b_bni_set_binary (b_vm_t* vm, const char* filepath);

#define b_bni_classid_by_methodid(VM, MINDEX) (b_method_get(VM, MINDEX)->cindex)

b_bni_state_t b_bni_opcode_new (b_vm_t* vm, b_class_id_t cindex);
b_bni_state_t b_bni_opcode_ojump (b_vm_t* self, b_method_id_t mindex);
b_bni_state_t b_bni_opcode_ojump_specific (b_vm_t* vm, b_class_id_t cindex, b_method_id_t mindex);
b_bni_state_t b_bni_opcode_ojump_generic (b_vm_t* vm, b_object_t* object, b_class_id_t cindex, b_method_t* method);

#endif
