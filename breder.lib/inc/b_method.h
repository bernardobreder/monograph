#ifndef B_METHOD_H_
#define B_METHOD_H_

#include "breder.h"

struct b_method_t {
	char* name; // Name of the method
	char* aname; // Absolute Name of the method joined with the class
	__inst memIndex; // Index of the program memory
	b_method_id_t index; // index of the method's list
	void* isNative; // Function of the native method
	int isStatic; // Method is static
	int isConstructor; // Method is constructor
	int isAbstract; // Method is abstract
	b_class_id_t cindex; // Index of the owner class
	b_ints_t* params;
	int returns;
	// b_object_t* object;
};

void b_method_free(b_method_t* self);

b_method_id_t b_method_add_new(b_vm_t* vm, b_class_id_t cindex, b_ints_t* returns, const char* name, b_ints_t* params);

b_method_id_t b_constructor_add_new(b_vm_t* vm, b_class_id_t cindex, const char* name, b_ints_t* params);

#define b_method_get(VM, METHOD_ID) \
		b_array_get_typed(b_method_t, (VM)->methods, METHOD_ID)

#define b_method_get_byclass(VM, CLASS, INDEX) \
		b_array_get_typed(b_method_t, (CLASS)->methods, INDEX)

#endif
