#include "b_bni.h"

b_bni_state_t breder_lang_standard_Natural__getMin (b_vm_t* vm) {
	b_object_t* oreturn = b_bni_new_onumber (vm, 0);
	if ( ! oreturn) {
		b_bni_throw_out_of_memory_runtime_exception (vm);
	}
	b_bni_sret1 (vm, 0, oreturn);
}
