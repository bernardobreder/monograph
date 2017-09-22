#include "b_bni.h"

b_bni_state_t breder_lang_standard_Throw_finalize (b_vm_t* vm, b_object_t* object, b_array_t* data) {
	b_bni_super_object_finalize (vm, object);
	b_array_free (data);
	b_bni_ret0 (vm, 0);
}

b_bni_state_t breder_lang_standard_Throw_Throw (b_vm_t* vm, b_object_t* object) {
	b_bni_super_object (vm, object);
	b_array_t* data = b_array_new ();
	int n, size = b_array_size_safely (vm->stackTrace);
	for (n = 0; n < size; n ++ ) {
		char* stacktrace = b_array_get_typed(char, vm->stackTrace, n);
		b_array_add (data, stacktrace);
	}
	b_bni_set_data_current (vm, object, data);
	b_bni_cret (vm, 0);
}

b_bni_state_t breder_lang_standard_Throw_printStackTrace (b_vm_t* vm, b_object_t* object) {
	b_vm_throw_print_object (vm, object);
	b_bni_ret0 (vm, 0);
}
