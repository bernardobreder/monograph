#include "b_bni.h"

b_bni_state_t breder_lang_standard_Number_toString (b_vm_t* vm, b_object_t* object) {
	b_number_t value = b_bni_onumber_to_primitive (vm, object);
	char svalue[32];
	if (value == (int)value) {
		sprintf (svalue, B_INTEGER_SCANF, (int)value);
	} else {
		sprintf (svalue, B_NUMBER_SCANF, value);
	}
	b_object_t* oreturn = b_bni_new_ostring (vm, svalue);
	if ( ! oreturn) {
		b_bni_throw_out_of_memory_runtime_exception (vm);
	}
	b_bni_ret1 (vm, 0, oreturn);
}

b_bni_state_t breder_lang_standard_Number_hashcode (b_vm_t* vm, b_object_t* object) {
	b_bni_ret1 (vm, 0, object);
}

b_bni_state_t breder_lang_standard_Number_operatorEqual_breder_lang_standard_Object (b_vm_t* vm, b_object_t* object) {
	b_bni_get_param_nullable (vm, onext, 0);
	if (onext == NULL) {
		b_bni_new_ofalse_define (vm, oreturn);
		b_bni_ret1 (vm, 1, oreturn);
	} else {
		b_number_t value1 = b_bni_onumber_to_primitive (vm, object);
		b_number_t value2 = b_bni_onumber_to_primitive (vm, onext);
		b_object_t* oreturn = b_bni_new_oboolean (vm, value1 == value2);
		if ( ! oreturn) {
			b_bni_throw_out_of_memory_runtime_exception (vm);
		}
		b_bni_ret1 (vm, 1, oreturn);
	}
}

b_bni_state_t breder_lang_standard_Number__getMax (b_vm_t* vm) {
	b_object_t* oreturn = b_bni_new_onumber (vm, B_BNI_NUMBER_MAX);
	if ( ! oreturn) {
		b_bni_throw_out_of_memory_runtime_exception (vm);
	}
	b_bni_sret1 (vm, 0, oreturn);
}

b_bni_state_t breder_lang_standard_Number__getMin (b_vm_t* vm) {
	b_object_t* oreturn = b_bni_new_onumber (vm, - B_BNI_NUMBER_MAX);
	if ( ! oreturn) {
		b_bni_throw_out_of_memory_runtime_exception (vm);
	}
	b_bni_sret1 (vm, 0, oreturn);
}
