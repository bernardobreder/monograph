#ifndef B_VM_H_
#define B_VM_H_

#include "breder.h"

struct b_vm_t {
	int structType;
	int *buffer;
	int *memstack, *cmemstack;
	int *memory;
	int* cmemory;
	b_object_t **stack, **cstack;
	b_object_t* othrow;
	/**
	 * Campo utilizado para indicar quantos parametro uma funï¿½ï¿½o native irï¿½ ter.
	 * Ele esta sendo necessario temporariamente para poder manter a API do BNI
	 * simples na atribuiï¿½ï¿½o do retornos da funï¿½ï¿½o.
	 */
	char params;
	__inst *returns;
	b_method_t* method;
	int bufferSize;
	b_char_t* output;
	b_array_chars_t* constants;
	b_array_char_t* stackTrace;
	b_array_achar_t* stringConstants;
	b_array_achar_t* doubleConstants;
	b_array_char_t* librarys;
	b_array_void_t* libraryFuncs;
	b_array_char_t* libraryPaths;
	b_array_void_t* nativeMethods;
	b_array_class_t* classs;
	b_hash_class_t* classhash;
	b_array_int_t* memIndexs; // Indices dos mï¿½todos dinamicos
	b_array_object_t* caches;
	// Tabelas de Hash para cada Classe Carregada. Com isso, com um indice, é possível recuperar a tabela de hash
	// dos métodos da classe.
	b_array_hash_t* hashes;
	b_object_t* ostring;
	b_object_t* onumber;
	b_object_t* oboolean;
	unsigned int ostringl; // comprimento do objeto ostring
	unsigned int onumberl; // comprimento do objeto onumber
	unsigned int obooleanl; // comprimento do objeto oboolean
	b_linker_object_t* objects;
	b_array_object_t* static_objects;
	b_array_method_t* methods;
	b_hashnew_t* sfields;
	b_class_t* objectClass;
	b_class_t* numberClass;
	b_class_t* stringClass;
	b_class_t* booleanClass;
	int finalizeIndex;
	b_method_t*** virtualAddress;
};

b_vm_t* b_vm_new();

void b_vm_free(b_vm_t* self);

int b_vm_init(b_vm_t* self, const char* classname, const char* methodname);

int b_vm_init_native_method(b_vm_t* self);

void b_vm_init_load_class(b_vm_t* self);

void b_vm_init_find_finalize(b_vm_t* self);

int b_vm_throw_generic_exception(b_vm_t* self, char* classname);

int b_vm_throw_null_pointer_runtime_exception(b_vm_t* self);

int b_vm_throw_cast_runtime_exception(b_vm_t* self);

void b_vm_init_cache(b_vm_t* self);

int b_vm_add_class(b_vm_t* self, int classnameIndex);

int b_vm_add_const_string(b_vm_t* self, const char* constant);

int b_vm_add_const_number(b_vm_t* self, const char* constant);

int b_vm_add_const(b_vm_t* self, const char* constant);

int b_vm_add_library(b_vm_t* self, const char* library);

void* b_vm_add_native_method(b_vm_t* self, char* nativeMethod);

b_chars_t* b_vm_get_const(b_vm_t* self, unsigned int index);

void b_vm_call_native(b_vm_t* self, char* constant);

b_method_t* b_vm_add_method(b_vm_t* self, int classIndex, int methodnameIndex);

int b_vm_set_class_fields(b_vm_t* self, int classIndex, aint* fields);

void b_vm_set_class_extend(b_vm_t* self, int classnameIndex, aint* extendindex);

void b_vm_link_method(b_vm_t* self, int classindex, int methodIndex);

__inst *b_vm_add_opcode(b_vm_t* self, __inst opcode);

int b_vm_get_opcode_index(b_vm_t* self);

b_class_t* b_vm_get_class(b_vm_t* self, char* name);

void b_vm_set_static_fields(b_vm_t* self, int count);

void b_vm_set_output(b_vm_t* self, b_char_t* output);

void b_vm_add_linkerpath(b_vm_t* self, b_char_t* linkerpath);

int b_vm_execute(b_vm_t* self, const char* classname, const char* methodname);

int b_vm_opcode_sjump(b_vm_t* self, int method_index);

b_object_t* b_vm_opcode_object_pop(b_vm_t* self);

int b_vm_opcode_inc(b_vm_t* self, int);

int b_vm_opcode_dec(b_vm_t* self, int);

int
		b_vm_opcode_ojump(register b_vm_t* self, const char* methodname,
				int params);

int b_vm_save(b_vm_t* self, const char* filename);

char* b_vm_load(b_vm_t* self, const char* filename);

aint* b_vm_read_ints(FILE* f);

#endif
