#include "b_bni.h"

b_bni_state_t breder_lang_standard_Boolean_toString (b_vm_t* vm, b_object_t* object) {
	int value = b_bni_oboolean_to_primitive (vm, object);
	if (value) {
		b_object_t* oreturn = b_bni_new_ostring (vm, "true");
		if ( ! oreturn) {
			b_bni_throw_out_of_memory_runtime_exception (vm);
		}
		b_bni_ret1 (vm, 0, oreturn);
	} else {
		b_object_t* oreturn = b_bni_new_ostring (vm, "false");
		if ( ! oreturn) {
			b_bni_throw_out_of_memory_runtime_exception (vm);
		}
		b_bni_ret1 (vm, 0, oreturn);
	}
}
