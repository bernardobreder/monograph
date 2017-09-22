#include "b_bni.h"

b_bni_state_t breder_lang_standard_Object_toString (b_vm_t* vm, b_object_t* object) {
	char text[10];
	sprintf (text, "%d", (unsigned int) ((unsigned int*)object - (unsigned int*)vm));
	b_object_t* oreturn = b_bni_new_ostring (vm, text);
	if ( ! oreturn) {
		b_bni_throw_out_of_memory_runtime_exception (vm);
	}
	b_bni_ret1 (vm, 0, oreturn);
}

b_bni_state_t breder_lang_standard_Object_hashcode (b_vm_t* vm, b_object_t* object) {
	b_number_t hash = (int*)object - (int*)vm;
	b_object_t* oreturn = b_bni_new_onumber (vm, hash);
	b_bni_ret1 (vm, 0, oreturn);
}

b_bni_state_t breder_lang_standard_Object_operatorEqual_breder_lang_standard_Object (b_vm_t* vm, b_object_t* object) {
	b_bni_get_param_nullable (vm, onext, 0);
	if (onext == null) {
		b_bni_new_ofalse_define (vm, oreturn);
		b_bni_ret1 (vm, 1, oreturn);
	} else {
		b_object_t* oreturn = b_bni_new_oboolean (vm, object == onext);
		b_bni_ret1 (vm, 1, oreturn);
	}
}

b_bni_state_t breder_lang_standard_Object_operatorEqualDeep_breder_lang_standard_Object (b_vm_t* vm, b_object_t* object) {
	return breder_lang_standard_Object_operatorEqual_breder_lang_standard_Object (vm, object);
}

b_bni_state_t breder_lang_standard_Object_operatorNotEqual_breder_lang_standard_Object (b_vm_t* vm, b_object_t* object) {
	b_bni_get_param_nullable (vm, onext, 0);
	if (onext == null) {
		b_bni_new_ofalse_define (vm, oreturn);
		b_bni_ret1 (vm, 1, oreturn);
	} else {
		b_object_t* oreturn = b_bni_new_oboolean (vm, object != onext);
		b_bni_ret1 (vm, 1, oreturn);
	}
}
