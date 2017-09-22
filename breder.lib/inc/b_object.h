#ifndef B_OBJECT_H_
#define B_OBJECT_H_

#include "breder.h"

struct b_object_t {
	int cindex;
	int fieldCount;
	int used;
	int gc;
	b_object_t** fields;
	void** datas;
};

/**
 * Cria um objeto da uma classe especifica.
 * Esse objeto não será coletado pelo Garbage Collector.
 * Caso ocorra algum erro, será retornado nulo.
 */
b_object_t* b_object_new_cache (b_vm_t* vm, b_class_id_t cindex);

/**
 * Cria um objeto de uma classe especifica.
 * Esse objeto poderá ser coletado pelo Garbage Collector.
 * Caso ocorra algum erro, será retornado nulo.
 */
b_object_t* b_object_new (b_vm_t* vm, b_class_id_t cindex);

/**
 * Libera o espaço associado ao objeto.
 */
void b_object_free (b_object_t* self);

/**
 * Cria um objeto da classe breder.lang.Number com um valor especifico.
 * Esse objeto não será coletado pelo Garbage Collector.
 * Caso ocorra algum erro, será retornado nulo.
 */
b_object_t* b_object_new_number_cached (b_vm_t* self, b_number_t number);

/**
 * Cria um objeto da classe breder.lang.Integer com um valor especifico.
 * Esse objeto não será coletado pelo Garbage Collector.
 * Caso ocorra algum erro, será retornado nulo.
 */
b_object_t* b_object_new_integer_cached (b_vm_t* self, b_integer_t number);

/**
 * Cria um objeto da classe breder.lang.Natural com um valor especifico.
 * Esse objeto não será coletado pelo Garbage Collector.
 * Caso ocorra algum erro, será retornado nulo.
 */
b_object_t* b_object_new_natural_cached (b_vm_t* self, b_integer_t number);

/**
 * Cria um objeto da classe breder.lang.Index com um valor especifico.
 * Esse objeto não será coletado pelo Garbage Collector.
 * Caso ocorra algum erro, será retornado nulo.
 */
b_object_t* b_object_new_index_cached (b_vm_t* self, b_integer_t number);

/**
 * Cria um objeto da classe breder.lang.Boolean com um valor especifico.
 * Esse objeto não será coletado pelo Garbage Collector.
 * Caso ocorra algum erro, será retornado nulo.
 */
b_object_t* b_object_new_boolean_cached (b_vm_t* vm, int flag);

/**
 * Cria um objeto da classe breder.lang.Number com um valor especifico.
 * Esse objeto poderá ser coletado pelo Garbage Collector.
 * Caso ocorra algum erro, será retornado nulo.
 */
b_object_t* b_object_new_number (b_vm_t* self, b_number_t number);

b_object_t* b_object_new_number_generic (b_vm_t* self, b_class_id_t cindex, b_number_t number, b_boolean_t isCache);

/**
 * Cria um objeto da classe breder.lang.Integer com um valor especifico.
 * Esse objeto poderá ser coletado pelo Garbage Collector.
 * Caso ocorra algum erro, será retornado nulo.
 */
b_object_t* b_object_new_integer (b_vm_t* self, b_integer_t number);

b_object_t* b_object_new_string_generic (b_vm_t* self, const char* text, unsigned int len, unsigned int hash, b_boolean_t isCache);

/**
 * Cria um objeto da classe breder.lang.Natural com um valor especifico.
 * Esse objeto poderá ser coletado pelo Garbage Collector.
 * Caso ocorra algum erro, será retornado nulo.
 */
b_object_t* b_object_new_natural (b_vm_t* vm, b_integer_t number);

/**
 * Cria um objeto da classe breder.lang.Index com um valor especifico.
 * Esse objeto poderá ser coletado pelo Garbage Collector.
 * Caso ocorra algum erro, será retornado nulo.
 */
b_object_t* b_object_new_index (b_vm_t* vm, b_integer_t number);

/**
 * Cria um objeto da classe breder.lang.Boolean com um valor especifico.
 * Esse objeto poderá ser coletado pelo Garbage Collector.
 * Caso ocorra algum erro, será retornado nulo.
 */
#define b_object_new_boolean(VM, FLAG) (VM)->obooleans[(FLAG)]

#define b_object_header(o) ((int*)o)
#define b_object_size() (b_object_size_noproxy() + sizeof(void*))
#define b_object_size_noproxy() (4 * sizeof(unsigned int) + 2 * sizeof(void*))
#define b_object_class(o) ((o)->cindex)
#define b_object_fields(o) (b_object_header(o)[1])
#define b_object_used(o) ((o)->used)
#define b_object_gc_used(o) ((o)->gc)
#define b_object_content(o) ((void**)(b_object_header(o)+4))
#define b_object_field(o,i) b_object_content(o)[(i)+1]
#define b_object_data(o) ((o)->datas[0])
#define b_object_set_data(o,v) b_object_data(o) = v
#define b_object_data_typed(t,o) ((t*)b_object_data(o))

#define b_object_onumber_to_primitive(o) (*((b_number_t*)(o)->datas[0]))
#define b_object_ostring_to_text(VM, OBJECT) ((char*)(((unsigned int*)(OBJECT)->datas[0])+2))

#endif
