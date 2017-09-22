#include "b_bni.h"
#include "b_hash.h"

typedef struct b_hashoentity_t b_hashoentity_t;
struct b_hashoentity_t {
	unsigned int hash;
	unsigned int size;
	b_object_t* key;
	void* value;
};

b_hashnew_t* b_hash_new2 () {
	return b_hash_new (sizeof(void*), null);
}

b_hashnew_t* b_hash_new (int size, void* freeFunc) {
	return b_hash_new1 (size, freeFunc, true);
}

b_hashnew_t* b_hash_new1 (int size, void* freeFunc, int isDupKey) {
	b_hashnew_t* self = b_memory_alloc_typed (b_hashnew_t, 1);
	self->isDupKey = isDupKey;
	self->entitys = b_array_new ();
	int n, m;
	for (n = 0; n < B_HASH_SIZE; n ++ ) {
		if ( ! b_array_add (self->entitys, 0)) {
			for (m = 0; m < n; m ++ ) {
				b_array_free (b_array_get_typed (b_array_t, self->entitys, m));
			}
			b_array_free (self->entitys);
			b_memory_free (self);
			return 0;
		}
	}
	return self;
}

int b_hashnew_size (b_hashnew_t* self) {
	return self->size;
}

b_bni_state_t breder_util_standard_HashMap_HashMap (b_vm_t* vm, b_object_t* object) {
	b_bni_super_object (vm, object);
	b_bni_set_data_current (vm, object, b_hash_new2 ());
	b_bni_cret (vm, 0);
}

static b_hashoentity_t* b_hashnew_entity_object_new (b_object_t* chars, int hash, b_object_t* value) {
	b_hashoentity_t* self = (b_hashoentity_t*)b_memory_alloc_typed (b_hashoentity_t, 1);
	if (self == null) {
		return 0;
	}
	self->key = chars;
	self->hash = hash;
	self->value = value;
	return self;
}

b_bni_state_t breder_util_standard_HashMap_set_breder_lang_standard_Object_breder_lang_standard_Object (b_vm_t* vm, b_object_t* object, b_hashnew_t* data) {
	b_bni_get_param_notnull (vm, okey, 0);
	b_bni_get_param_notnull (vm, ovalue, 1);
	b_object_t* ohash;
	if (b_bni_execute_1_return_0_param (vm, okey, & ohash, b_bni_method_object_hashcode_id (vm)) == B_BNI_FAIL) {
		return B_BNI_FAIL;
	}
	unsigned int hash = b_bni_onumber_to_integer (vm, ohash);
	unsigned int select = hash % b_array_size_safely (data->entitys);
	b_array_t* array = b_array_get_typed (b_array_t, data->entitys, select);
	if ( ! array) {
		array = b_array_new ();
		if (array == null) {
			b_bni_throw_out_of_memory_runtime_exception (vm);
		}
		b_array_set (data->entitys, select, array);
		b_hashoentity_t* entity = b_hashnew_entity_object_new (okey, hash, ovalue);
		if (entity == null) {
			b_array_free (array);
			b_bni_throw_out_of_memory_runtime_exception (vm);
		}
		if ( ! b_array_add (array, entity)) {
			b_array_free (array);
			b_bni_throw_out_of_memory_runtime_exception (vm);
		}
		b_bni_inc_used (vm, okey);
		b_bni_inc_used (vm, ovalue);
		data->size ++ ;
		b_bni_ret1 (vm, 2, object);
	} else {
		b_hashoentity_t** entitys = (b_hashoentity_t**)array->array;
		int n;
		int size = b_array_size_safely (array);
		for (n = 0; n < size; n ++ ) {
			b_hashoentity_t* entity = * entitys ++ ;
			if (entity->hash == hash) {
				b_object_t* oequal;
				if (b_bni_execute_1_return_1_param (vm, okey, & oequal, b_bni_method_object_operatorequal_id (vm), entity->key) == B_BNI_FAIL) {
					return B_BNI_FAIL;
				}
				if (b_bni_oboolean_to_primitive (vm, oequal)) {
					b_bni_dec_used (vm, ((b_object_t*)entity->value));
					b_bni_inc_used (vm, ovalue);
					entity->value = ovalue;
					b_bni_ret1 (vm, 2, object);
				}
			}
		}
		b_hashoentity_t* entity = b_hashnew_entity_object_new (okey, hash, ovalue);
		if (entity == null) {
			b_bni_throw_out_of_memory_runtime_exception (vm);
		}
		if ( ! b_array_add (array, entity)) {
			b_memory_free (entity);
			b_bni_throw_out_of_memory_runtime_exception (vm);
		}
		b_bni_inc_used (vm, okey);
		b_bni_inc_used (vm, ovalue);
		data->size ++ ;
		b_bni_ret1 (vm, 2, object);
	}
}

b_bni_state_t breder_util_standard_HashMap_get_breder_lang_standard_Object (b_vm_t* vm, b_object_t* object, b_hashnew_t* data) {
	b_bni_get_param_notnull (vm, okey, 0);
	b_object_t* ohash;
	if (b_bni_execute_1_return_0_param (vm, okey, & ohash, b_bni_method_object_hashcode_id (vm)) == B_BNI_FAIL) {
		return B_BNI_FAIL;
	}
	unsigned int hash = b_bni_onumber_to_integer (vm, ohash);
	unsigned int select = hash % b_array_size_safely (data->entitys);
	b_array_t* array = b_array_get_typed (b_array_t, data->entitys, select);
	if (array) {
		b_hashoentity_t** entitys = (b_hashoentity_t**)array->array;
		int n;
		int size = b_array_size_safely (array);
		for (n = 0; n < size; n ++ ) {
			b_hashoentity_t* entity = * entitys ++ ;
			if (entity->hash == hash) {
				b_object_t* oequal;
				if (b_bni_execute_1_return_1_param (vm, okey, & oequal, b_bni_method_object_operatorequal_id (vm), entity->key) == B_BNI_FAIL) {
					return B_BNI_FAIL;
				}
				if (b_bni_oboolean_to_primitive (vm, oequal)) {
					b_bni_ret1 (vm, 1, (b_object_t*)entity->value);
				}
			}
		}
	}
	b_bni_ret1 (vm, 1, null);
}

b_bni_state_t breder_util_standard_HashMap_remove_breder_lang_standard_Object (b_vm_t* vm, b_object_t* object, b_hashnew_t* data) {
	b_bni_get_param_notnull (vm, okey, 0);
	b_object_t* ohash;
	if (b_bni_execute_1_return_0_param (vm, okey, & ohash, b_bni_method_object_hashcode_id (vm)) == B_BNI_FAIL) {
		return B_BNI_FAIL;
	}
	unsigned int hash = b_bni_onumber_to_integer (vm, ohash);
	unsigned int select = hash % b_array_size_safely (data->entitys);
	b_array_t* array = b_array_get_typed (b_array_t, data->entitys, select);
	if (array) {
		b_hashoentity_t** entitys = (b_hashoentity_t**)array->array;
		int n;
		int size = b_array_size_safely (array);
		for (n = 0; n < size; n ++ ) {
			b_hashoentity_t* entity = * entitys ++ ;
			if (entity->hash == hash) {
				b_object_t* oequal;
				if (b_bni_execute_1_return_1_param (vm, okey, & oequal, b_bni_method_object_operatorequal_id (vm), entity->key) == B_BNI_FAIL) {
					return B_BNI_FAIL;
				}
				if (b_bni_oboolean_to_primitive (vm, oequal)) {
					b_array_remove (array, n);
					b_bni_dec_used (vm, ((b_object_t*)entity->value));
					b_bni_dec_used (vm, ((b_object_t*)entity->key));
					b_memory_free (entity);
					data->size -- ;
					b_bni_ret1 (vm, 1, object);
				}
			}
		}
	}
	b_bni_ret1 (vm, 1, object);
}

b_bni_state_t breder_util_standard_HashMap_size (b_vm_t* vm, b_object_t* object, b_hashnew_t* data) {
	b_object_t* oreturn = b_bni_new_onumber (vm, b_hashnew_size (data));
	if (oreturn == null) {
		b_bni_throw_out_of_memory_runtime_exception (vm);
	}
	b_bni_ret1 (vm, 0, oreturn);
}

b_bni_state_t breder_util_standard_HashMap_finalize (b_vm_t* vm, b_object_t* object, b_hashnew_t* data) {
	b_bni_super_object_finalize (vm, object);
	int n, m;
	int sizen = b_array_size_safely (data->entitys);
	for (n = 0; n < sizen; n ++ ) {
		b_array_t* array = b_array_get_typed (b_array_t, data->entitys, n);
		if (array) {
			b_hashoentity_t** entitys = (b_hashoentity_t**)array->array;
			int sizem = b_array_size_safely (array);
			for (m = 0; m < sizem; m ++ ) {
				b_hashoentity_t* entity = * entitys ++ ;
				b_bni_dec_used (vm, entity->key);
				b_bni_dec_used (vm, ((b_object_t*)entity->value));
				b_memory_free (entity);
			}
			b_array_free (array);
		}
	}
	b_array_free (data->entitys);
	b_memory_free (data);
	b_bni_ret0 (vm, 0);
}
