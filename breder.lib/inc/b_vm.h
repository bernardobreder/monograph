#ifndef B_VM_H_
#define B_VM_H_

#include "breder.h"

struct b_vm_t {
	/** Tamanho do progmem */
	unsigned int bufferSize;
	unsigned int bufferAlloced;
	/** Apontador da Proxima Instrução */
	__inst* progmem;
	__inst* cprogmem;
	/** Apontador da Proxima Instrução dizendo qual a classe que o pertence */
	b_class_id_t* pc_cindex;
	b_class_id_t* cpc_cindex;
	/** Apontador da Proxima Instrução dizendo qual a linha que o pertence */
	short* pc_line;
	short* cpc_line;
	/** Pilha de Objetos */
	b_object_t** objstack;
	b_object_t** cobjstack;
	/**
	 * Quando é efetuado uma chamada a um método, primeiro é armazenado nessa pilha
	 * a proxima instrução, para que quando for finalizado a operação, a VM saiba
	 * aonde estava anteriormente.
	 */
	int* memstack;
	int* cmemstack;
	/** Pilha de estado do progmem */
	int* cprogthrowstack;
	int* progthrowstack;
	/** Pilha de estado do objstack */
	int* cobjthrowstack;
	int* objthrowstack;
	/** Pilha de estado do memstack */
	int* cmemthrowstack;
	int* memthrowstack;
	/** Objeto de Erro que não foi tratado */
	b_object_t* othrow;
	/**
	 * Campo utilizado para indicar quantos parametro uma funï¿½ï¿½o native irï¿½ ter.
	 * Ele esta sendo necessario temporariamente para poder manter a API do BNI
	 * simples na atribuiï¿½ï¿½o do retornos da funï¿½ï¿½o.
	 */
	b_method_t* method;
	b_array_char_t* stackTrace;

	/* Cache do Cast */
	b_state_t** cast;

	unsigned int onumbersLen;
	b_object_t** onumbers;
	unsigned int ostringsLen;
	b_object_t** ostrings;
	b_object_t** obooleans;
	b_object_t** ointegers;
	b_object_t** ofields;

	b_array_void_t* libraryFuncs;
	b_array_char_t* libraryPaths;
	b_array_void_t* nativeMethods;

	b_method_id_t** mvirtual;
	b_field_id_t** fvirtual;

	int** dataIndexs;
	int* dataIndexCount;

	b_linker_object_t* objects;

	unsigned int classSize;
	b_class_t** classes;
	b_array_method_t* methods;
	b_array_field_t* fields;

	b_class_id_t index_id;
	b_class_id_t natural_id;
	b_class_id_t integer_id;
	b_class_id_t number_id;
	b_class_id_t boolean_id;
	b_class_id_t string_id;

	unsigned int gc;
#ifdef DEBUG_APP
	    b_debug_t* debug;
#endif
    };

b_vm_t* b_vm_new ();

void b_vm_free (b_vm_t* vm);

int b_vm_exec (register b_vm_t* vm);

b_state_t b_vm_opcode_cast_aux (b_vm_t* vm, unsigned int index, b_class_t* clazz);

void b_vm_init_find_finalize (b_vm_t* vm);

b_state_t b_vm_throw (b_vm_t* vm, b_object_t* othrow);
b_state_t b_vm_throw_generic_exception (b_vm_t* vm, char* classname);
#define b_vm_throw_null_pointer_runtime_exception(VM) \
		b_vm_throw_generic_exception (VM, "breder.lang.standard.NullPointerRuntimeException")
#define b_vm_throw_arithmetic_runtime_exception(VM) \
		b_vm_throw_generic_exception (VM, "breder.lang.standard.ArithmeticRuntimeException")
#define b_vm_throw_cast_runtime_exception(VM) \
		b_vm_throw_generic_exception (VM, "breder.lang.standard.CastRuntimeException")
#define b_vm_throw_out_of_memory_runtime_exception(VM) \
		b_vm_throw_generic_exception (VM, "breder.lang.standard.OutOfMemoryRuntimeException")

b_state_t b_vm_init_cache (b_vm_t* vm);

void b_vm_init_cache_linearing (b_vm_t* vm, int* index, int* fields, b_class_t* bclass, b_class_t* clazz);

int b_vm_add_const (b_vm_t* vm, const char* constant);

int b_vm_add_library (b_vm_t* vm, const char* library);

void b_vm_call_native (b_vm_t* vm, char* constant);

void b_vm_set_class_extend (b_vm_t* vm, int classnameIndex, b_ints_t* extendindex);

b_class_t* b_vm_find_class (b_vm_t* vm, const char* name);

b_method_t
* b_vm_find_method (b_vm_t* vm, b_class_id_t cindex, const char* name);

void b_vm_set_output (b_vm_t* vm, const char* output);

void b_vm_add_linkerpath (b_vm_t* vm, const char* linkerpath);

b_state_t b_vm_execute (b_vm_t* vm, const char* classname, const char* methodname);

b_state_t b_bni_opcode_sjump (b_vm_t* vm, b_method_id_t method_index);

b_method_id_t b_vm_load (b_vm_t* self, const char* filename);

b_method_id_t b_vm_load1 (b_vm_t* self, const char* filename, unsigned int fsize);

b_ints_t* b_vm_read_ints (void* f);

void b_vm_init_cache_method_aux (b_vm_t* vm, b_class_t* bclazz, b_class_t* clazz);

void b_vm_init_cache_field_aux1 (b_vm_t* vm, b_class_t* bclass, b_class_t* clazz, int* fields);

#endif
