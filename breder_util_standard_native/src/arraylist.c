#include "b_bni.h"

b_bni_state_t breder_util_standard_ArrayList_ArrayList (b_vm_t* vm, b_object_t* object) {
	b_bni_super_object (vm, object);
	b_bni_set_data_current (vm, object, b_array_new ());
	b_bni_cret (vm, 0);
}

b_bni_state_t breder_util_standard_ArrayList_add_breder_lang_standard_Object (b_vm_t* vm, b_object_t* object, b_array_t* data) {
	b_bni_get_param_nullable (vm, ovalue, 0);
	if (ovalue) {
		b_bni_inc_used (vm, ovalue);
	}
	if ( ! b_array_add (data, ovalue)) {
		b_bni_throw_out_of_memory_runtime_exception (vm);
	}
	b_bni_ret1 (vm, 1, object);
}

b_bni_state_t breder_util_standard_ArrayList_get_breder_lang_standard_Index (b_vm_t* vm, b_object_t* object, b_array_t* data) {
	b_bni_get_param_as_index (vm, oindex, index, 0);
	int size = b_array_size_safely (data);
	b_bni_test_index (vm, index <= 0 || index > size);
	b_object_t* oreturn = b_array_get_typed (b_object_t, data, index - 1);
	b_bni_ret1 (vm, 1, oreturn);
}

b_bni_state_t breder_util_standard_ArrayList_set_breder_lang_standard_Index_breder_lang_standard_Object (b_vm_t* vm, b_object_t* object, b_array_t* data) {
	b_bni_get_param_as_index (vm, oindex, index, 0);
	b_bni_get_param_nullable (vm, ovalue, 1);
	int size = b_array_size_safely (data);
	b_bni_test_index (vm, index <= 0 || index > size);
	b_object_t* ooldvalue = b_array_get_typed (b_object_t, data, index - 1);
	b_bni_dec_used (vm, ooldvalue);
	b_array_set (data, (int)index - 1, ovalue);
	b_bni_inc_used (vm, ovalue);
	b_bni_ret1 (vm, 2, object);
}

b_bni_state_t breder_util_standard_ArrayList_size (b_vm_t* vm, b_object_t* object, b_array_t* data) {
	b_object_t* oreturn = b_bni_new_onumber (vm, b_array_size (data));
	if (oreturn == null) {
		b_bni_throw_out_of_memory_runtime_exception (vm);
	}
	b_bni_ret1 (vm, 0, oreturn);
}

b_bni_state_t breder_util_standard_ArrayList_isEmpty (b_vm_t* vm, b_object_t* object, b_array_t* data) {
	int size = b_array_size_safely (data);
	b_object_t* oreturn = b_bni_new_oboolean (vm, size == 0);
	if (oreturn == null) {
		b_bni_throw_out_of_memory_runtime_exception (vm);
	}
	b_bni_ret1 (vm, 0, oreturn);
}

b_bni_state_t breder_util_standard_ArrayList_contain_breder_lang_standard_Object (b_vm_t* vm, b_object_t* object, b_array_t* data) {
	b_bni_get_param_notnull (vm, oparam, 0);
	int n, size = b_array_size_safely (data);
	for (n = 0; n < size; n ++ ) {
		b_object_t* oitem = b_array_get_typed (b_object_t, data, n);
		b_object_t* oequal;
		if (b_bni_execute_1_return_1_param (vm, oparam, & oequal, b_bni_method_object_operatorequal_id (vm), oitem) == B_BNI_FAIL) {
			return B_BNI_FAIL;
		}
		if (b_bni_oboolean_to_primitive (vm, oequal)) {
			b_object_t* oreturn = b_bni_new_otrue (vm);
			if (oreturn == null) {
				b_bni_throw_out_of_memory_runtime_exception (vm);
			}
			b_bni_ret1 (vm, 1, oreturn);
		}
	}
	b_object_t* oreturn = b_bni_new_ofalse (vm);
	if (oreturn == null) {
		b_bni_throw_out_of_memory_runtime_exception (vm);
	}
	b_bni_ret1 (vm, 1, oreturn);
}

b_bni_state_t breder_util_standard_ArrayList_indexOf_breder_lang_standard_Object (b_vm_t* vm, b_object_t* object, b_array_t* data) {
	b_bni_get_param_notnull (vm, oparam, 0);
	int n, size = b_array_size_safely (data);
	for (n = 0; n < size; n ++ ) {
		b_object_t* oitem = b_array_get_typed (b_object_t, data, n);
		b_object_t* oequal;
		if (b_bni_execute_1_return_1_param (vm, oparam, & oequal, b_bni_method_object_operatorequal_id (vm), oitem) == B_BNI_FAIL) {
			return B_BNI_FAIL;
		}
		if (b_bni_oboolean_to_primitive (vm, oequal)) {
			b_object_t* oreturn = b_bni_new_onumber (vm, n + 1);
			if (oreturn == null) {
				b_bni_throw_out_of_memory_runtime_exception (vm);
			}
			b_bni_ret1 (vm, 1, oreturn);
		}
	}
	b_object_t* oreturn = b_bni_null (vm);
	b_bni_ret1 (vm, 1, oreturn);
}

b_bni_state_t breder_util_standard_ArrayList_indexOf_breder_lang_standard_Object_breder_lang_standard_Index (b_vm_t* vm, b_object_t* object, b_array_t* data) {
	b_bni_get_param_notnull (vm, oparam, 0);
	b_bni_get_param_as_index (vm, oindex, index, 1);
	int n, size = b_array_size_safely (data);
	for (n = index - 1; n < size; n ++ ) {
		b_object_t* oitem = b_array_get_typed (b_object_t, data, n);
		b_object_t* oequal;
		if (b_bni_execute_1_return_1_param (vm, oparam, & oequal, b_bni_method_object_operatorequal_id (vm), oitem) == B_BNI_FAIL) {
			return B_BNI_FAIL;
		}
		if (b_bni_oboolean_to_primitive (vm, oequal)) {
			b_object_t* oreturn = b_bni_new_onumber (vm, n + 1);
			if (oreturn == null) {
				b_bni_throw_out_of_memory_runtime_exception (vm);
			}
			b_bni_ret1 (vm, 2, oreturn);
		}
	}
	b_object_t* oreturn = b_bni_null (vm);
	b_bni_ret1 (vm, 2, oreturn);
}

b_bni_state_t breder_util_standard_ArrayList_remove_breder_lang_standard_Object (b_vm_t* vm, b_object_t* object, b_array_t* data) {
	b_bni_get_param_notnull (vm, oparam, 0);
	int n, size = b_array_size_safely (data);
	for (n = 0; n < size; n ++ ) {
		b_object_t* oitem = b_array_get_typed (b_object_t, data, n);
		b_object_t* oequal;
		if (b_bni_execute_1_return_1_param (vm, oparam, & oequal, b_bni_method_object_operatorequal_id (vm), oitem) == B_BNI_FAIL) {
			return B_BNI_FAIL;
		}
		if (b_bni_oboolean_to_primitive (vm, oequal)) {
			b_array_remove (data, n);
			b_bni_dec_used (vm, oitem);
			b_bni_ret1 (vm, 1, object);
		}
	}
	b_bni_ret1 (vm, 1, object);
}

b_bni_state_t breder_util_standard_ArrayList_remove_breder_lang_standard_Index (b_vm_t* vm, b_object_t* object, b_array_t* data) {
	b_bni_get_param_as_index (vm, oindex, index, 0);
	int size = b_array_size_safely (data);
	b_bni_test_index (vm, index <= 0 || index > size);
	b_object_t* oreturn = b_array_get_typed (b_object_t, data, index - 1);
	b_array_remove (data, index - 1);
	b_bni_dec_used (vm, oreturn);
	b_bni_ret1 (vm, 1, object);
}

b_bni_state_t breder_util_standard_ArrayList_clear (b_vm_t* vm, b_object_t* object, b_array_t* data) {
	b_array_clean (data);
	b_bni_ret1 (vm, 0, object);
}

b_bni_state_t breder_util_standard_ArrayList_finalize (b_vm_t* vm, b_object_t* object, b_array_t* data) {
	b_bni_super_object_finalize (vm, object);
	int n, size = b_array_size (data);
	for (n = 0; n < size; n ++ ) {
		b_object_t* object = b_array_get_typed (b_object_t, data, n);
		b_bni_dec_used (vm, object);
	}
	b_array_free (data);
	b_bni_ret0 (vm, 0);
}

