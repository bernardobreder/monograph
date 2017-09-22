#include "breder.h"

void b_vm_throw_print_object (b_vm_t* vm, b_object_t* throwObject) {
	const char* clazzChars;
	if (throwObject) {
		int classindex = b_object_class(throwObject);
		b_class_t* clazz = b_class_get(vm, classindex);
		clazzChars = clazz->name;
	} else {
		clazzChars = "null";
	}
	printf ("throw %s : %s\n", clazzChars, "");
	if (throwObject) {
		b_bni_data_index_class_define(vm, b_array_t, data, b_bni_class_throw_id(vm), throwObject);
		int n, size = b_array_size(data);
		for (n = size - 1; n >= 0; n -- ) {
			char* methodname = b_array_get_typed( char , data , n );
			printf ("\tmethod %s\n", methodname);
		}
	}
}

void b_vm_throw_print (b_vm_t* vm) {
	b_vm_throw_print_object (vm, vm->othrow);
}

b_state_t b_vm_throw (b_vm_t* self, b_object_t* othrow) {
	self->othrow = othrow;
	b_array_t* data = b_array_new();
	int n, size = b_array_size(self->stackTrace);
	for (n = 0; n < size; n ++ ) {
		char* stacktrace = b_array_get_typed(char, self->stackTrace, n);
		b_array_add (data, stacktrace);
	}
	int index = b_bni_data_index_class(self, othrow, b_bni_class_throw_id(self));
	b_bni_set_data_index (self, othrow, index, data);
	return B_STATE_FAIL;
}

b_state_t b_vm_throw_generic_exception (b_vm_t* self, char* classname) {
	b_class_t* clazz = b_vm_find_class (self, classname);
	b_object_t* othrow = b_object_new (self, clazz->index);
	return b_vm_throw (self, othrow);
}
