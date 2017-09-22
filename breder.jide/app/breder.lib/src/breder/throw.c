#include "breder.h"

void b_throw_print(b_vm_t* vm, b_object_t* object) {
	char* clazzChars;
	if (vm->othrow) {
		int classindex = b_object_class(vm->othrow);
		b_class_t* clazz = b_arrayp_get_typed( b_class_t , vm->classs , classindex );
		clazzChars = clazz->name;
	} else {
		clazzChars = "null";
	}
	printf("throw %s : %s\n", clazzChars, "");
	if (vm->othrow) {
		b_arrayp_t* data = b_bni_get_data(vm, vm->othrow);
		int n, size = b_array_size( data );
		for (n = size - 1; n >= 0; n--) {
			char* methodname = b_arrayp_get_typed( char , data , n );
			printf("\tmethod %s\n", methodname);
		}
	}
}
