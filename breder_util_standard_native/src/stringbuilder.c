#include "b_bni.h"

typedef struct b_stringbuilder_entity_t b_stringbuilder_entity_t;
typedef struct b_stringbuilder_data_t b_stringbuilder_data_t;

struct b_stringbuilder_data_t {
	b_array_t* array;
	int length;
};

struct b_stringbuilder_entity_t {
	b_object_t* ostring;
	int offset;
	int length;
};

b_bni_state_t breder_util_standard_StringBuilder_StringBuilder (b_vm_t* vm, b_object_t* object) {
	b_bni_super_object (vm, object);
	b_stringbuilder_data_t* data = b_memory_alloc_vm_typed (vm, b_stringbuilder_data_t, 1);
	if (data == null) {
		b_bni_throw_out_of_memory_runtime_exception (vm);
	}
	data->array = b_array_new ();
	if (data->array == null) {
		b_memory_free (data);
		b_bni_throw_out_of_memory_runtime_exception (vm);
	}
	b_bni_test (vm, data->array);
	b_bni_set_data_current (vm, object, data);
	b_bni_cret (vm, 0);
}

b_stringbuilder_entity_t* b_stringbuilder_entity_build (b_vm_t* vm, b_object_t* object, int offset, int length) {
	b_stringbuilder_entity_t* entity = b_memory_alloc_vm_typed (vm, b_stringbuilder_entity_t, 1);
	if (entity == null) {
		return 0;
	}
	entity->ostring = object;
	entity->offset = offset - 1;
	entity->length = length;
	b_bni_inc_used (vm, object);
	return entity;
}

b_bni_state_t breder_util_standard_StringBuilder_append_breder_lang_standard_String (b_vm_t* vm, b_object_t* object, b_stringbuilder_data_t* data) {
	b_bni_get_param_as_string (vm, ovalue, value, 0);
	int length = b_bni_ostring_to_length (vm, ovalue);
	b_stringbuilder_entity_t* entity = b_stringbuilder_entity_build (vm, ovalue, 1, length);
	if (entity == null) {
		b_bni_throw_out_of_memory_runtime_exception (vm);
	}
	b_bni_test (vm, entity);
	if ( ! b_array_add (data->array, entity)) {
		b_memory_free (entity);
		b_bni_throw_out_of_memory_runtime_exception (vm);
	}
	data->length += length;
	b_bni_ret0 (vm, 1);
}

b_bni_state_t breder_util_standard_StringBuilder_append_breder_lang_standard_String_breder_lang_standard_Number (b_vm_t* vm, b_object_t* object, b_stringbuilder_data_t* data) {
	b_bni_get_param_as_string (vm, ovalue, value, 0);
	b_bni_get_param_as_integer (vm, ooffset, offset, 1);
	int length = b_bni_ostring_to_length (vm, ovalue);
	b_bni_test_index (vm, offset <= 0 || offset > length);
	b_stringbuilder_entity_t* entity = b_stringbuilder_entity_build (vm, ovalue, offset, length - offset + 1);
	if (entity == null) {
		b_bni_throw_out_of_memory_runtime_exception (vm);
	}
	b_bni_test (vm, entity);
	if ( ! b_array_add (data->array, entity)) {
		b_memory_free (entity);
		b_bni_throw_out_of_memory_runtime_exception (vm);
	}
	data->length += length - offset;
	b_bni_ret0 (vm, 2);
}

b_bni_state_t breder_util_standard_StringBuilder_append_breder_lang_standard_String_breder_lang_standard_Number_breder_lang_standard_Number (b_vm_t* vm, b_object_t* object, b_stringbuilder_data_t* data) {
	b_bni_get_param_as_string (vm, ovalue, value, 0);
	b_bni_get_param_as_integer (vm, ooffset, offset, 1);
	b_bni_get_param_as_integer (vm, olength, length, 2);
	int textlength = b_bni_ostring_to_length (vm, ovalue);
	b_bni_test_index (vm, offset <= 0 || offset > textlength);
	b_bni_test_index (vm, length < 0 || length > textlength - (offset - 1));
	b_stringbuilder_entity_t* entity = b_stringbuilder_entity_build (vm, ovalue, offset, length);
	if (entity == null) {
		b_bni_throw_out_of_memory_runtime_exception (vm);
	}
	b_bni_test (vm, entity);
	if ( ! b_array_add (data->array, entity)) {
		b_memory_free (entity);
		b_bni_throw_out_of_memory_runtime_exception (vm);
	}
	data->length += length;
	b_bni_ret0 (vm, 3);
}

b_bni_state_t breder_util_standard_StringBuilder_length (b_vm_t* vm, b_object_t* object, b_stringbuilder_data_t* data) {
	b_object_t* oreturn = b_bni_new_onumber (vm, data->length);
	if (oreturn == null) {
		b_bni_throw_out_of_memory_runtime_exception (vm);
	}
	b_bni_ret1 (vm, 0, oreturn);
}

b_bni_state_t breder_util_standard_StringBuilder_clear (b_vm_t* vm, b_object_t* object, b_stringbuilder_data_t* data) {
	int n, size = b_array_size_safely (data->array);
	for (n = 0; n < size; n ++ ) {
		b_stringbuilder_entity_t* entity = b_array_get_typed (b_stringbuilder_entity_t, data->array, n);
		b_bni_dec_used (vm, entity->ostring);
		b_memory_free (entity);
	}
	b_array_clean (data->array);
	data->length = 0;
	b_bni_ret0 (vm, 0);
}

b_bni_state_t breder_util_standard_StringBuilder_toString (b_vm_t* vm, b_object_t* object, b_stringbuilder_data_t* data) {
	char text[data->length + 1];
	text[data->length] = 0;
	int n, offset = 0, size = b_array_size_safely (data->array);
	for (n = 0; n < size; n ++ ) {
		b_stringbuilder_entity_t* entity = b_array_get_typed (b_stringbuilder_entity_t, data->array, n);
		const char* tentity = b_bni_ostring_to_text (vm, entity->ostring);
		strncpy (text + offset, tentity + entity->offset, entity->length);
		offset += entity->length;
	}
	b_object_t* oreturn = b_bni_new_ostring (vm, text);
	if (oreturn == null) {
		b_bni_throw_out_of_memory_runtime_exception (vm);
	}
	b_bni_ret1 (vm, 0, oreturn);
}

b_bni_state_t breder_util_standard_StringBuilder_finalize (b_vm_t* vm, b_object_t* object, b_stringbuilder_data_t* data) {
	b_bni_super_object_finalize (vm, object);
	int n, size = b_array_size_safely (data->array);
	for (n = 0; n < size; n ++ ) {
		b_stringbuilder_entity_t* entity = b_array_get_typed (b_stringbuilder_entity_t, data->array, n);
		b_bni_dec_used (vm, entity->ostring);
		b_memory_free (entity);
	}
	b_array_free (data->array);
	b_memory_free (data);
	b_bni_ret0 (vm, 0);
}
