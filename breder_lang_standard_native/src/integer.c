#include "b_bni.h"

b_bni_state_t breder_lang_standard_Integer_toString (b_vm_t* vm, b_object_t* object) {
	char svalue[32];
	b_integer_t value = b_bni_onumber_to_integer (vm, object);
	sprintf (svalue, B_INTEGER_SCANF, value);
	b_object_t* oreturn = b_bni_new_ostring (vm, svalue);
	if ( ! oreturn) {
		b_bni_throw_out_of_memory_runtime_exception (vm);
	}
	b_bni_ret1 (vm, 0, oreturn);
}

b_bni_state_t breder_lang_standard_Integer__getMax (b_vm_t* vm) {
	b_object_t* oreturn = b_bni_new_onumber (vm, B_BNI_INTEGER_MAX);
	if ( ! oreturn) {
		b_bni_throw_out_of_memory_runtime_exception (vm);
	}
	b_bni_sret1 (vm, 0, oreturn);
}

b_bni_state_t breder_lang_standard_Integer__getMin (b_vm_t* vm) {
	b_object_t* oreturn = b_bni_new_onumber (vm, - B_BNI_INTEGER_MAX);
	if ( ! oreturn) {
		b_bni_throw_out_of_memory_runtime_exception (vm);
	}
	b_bni_sret1 (vm, 0, oreturn);
}
