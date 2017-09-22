#include "breder.h"

b_object_t* b_object_new_cache (b_vm_t* vm, b_class_id_t cindex) {
	b_class_t* clazz = b_class_get(vm, cindex);
	b_object_t* self = b_memory_alloc_vm_struct(vm, b_object_t);
	if (self == null) {
		return B_BNI_FAIL;
	}
	self->datas = b_memory_alloc_vm_typed(vm, void*, vm->dataIndexCount[cindex]);
	if (self->datas == null) {
		b_object_free (self);
		return B_BNI_FAIL;
	}
	self->cindex = cindex;
	if (clazz->fieldCount) {
		self->fields = b_memory_alloc_vm_typed(vm, b_object_t*, clazz->fieldCount);
		if (self->fields == null) {
			b_object_free (self);
			return B_BNI_FAIL;
		}
	}
	return self;
}

b_object_t* b_object_new (b_vm_t* vm, b_class_id_t cindex) {
	b_object_t* object = b_object_new_cache (vm, cindex);
	if ( ! object) {
		return null;
	}
	if ( ! b_linker_add_last (vm->objects, vm, object)) {
		b_object_free (object);
		return null;
	}
	return object;
}

b_object_t* b_object_new_number (b_vm_t* vm, b_number_t value) {
	b_boolean_t isCache = value == (b_integer_t)value && value >= - B_NUMBER_POOL_MAX && value <= B_NUMBER_POOL_MAX;
	b_object_t* ovalue;
	if (value != (b_integer_t)value) {
		ovalue = b_object_new_number_generic (vm, b_bni_class_number_id (vm), value, 0);
	} else if (value > 0) {
		ovalue = b_object_new_number_generic (vm, b_bni_class_index_id (vm), value, isCache);
	} else if (value == 0) {
		ovalue = b_object_new_number_generic (vm, b_bni_class_natural_id (vm), value, isCache);
	} else {
		ovalue = b_object_new_number_generic (vm, b_bni_class_integer_id (vm), value, isCache);
	}
	if (isCache) {
		int index = (b_integer_t)value + B_NUMBER_POOL_MAX;
		if ( ! vm->ointegers[index]) {
			vm->ointegers[index] = ovalue;
		}
	}
	return ovalue;
}

b_object_t* b_object_new_number_generic (b_vm_t* vm, b_class_id_t cindex, b_number_t number, b_boolean_t isCache) {
	b_object_t* object;
	if (isCache) {
		object = b_object_new_cache (vm, cindex);
	} else {
		object = b_object_new (vm, cindex);
	}
	if (object == null) {
		return null;
	}
	b_number_t* data = b_memory_alloc_vm_struct(vm, b_number_t);
	if (data == null) {
		b_memory_free (object);
		return null;
	}
	* data = number;
	object->datas[0] = data;
	return object;
}

b_object_t* b_object_new_boolean_cached (b_vm_t* vm, int flag) {
	b_object_t* object = b_object_new_cache (vm, b_bni_class_boolean_id (vm));
	if (object == null) {
		return null;
	}
	if (flag) {
		b_boolean_t* data = b_memory_alloc_vm_struct(vm, b_boolean_t);
		if (data == null) {
			b_memory_free (object);
			return null;
		}
		* data = 1;
		object->datas[0] = data;
	}
	return object;
}

b_object_t* b_object_new_string_generic (b_vm_t* vm, const char* text, unsigned int len, unsigned int hash, b_boolean_t isCache) {
	b_object_t* object;
	if (isCache) {
		object = b_object_new_cache (vm, b_bni_class_string_id (vm));
	} else {
		object = b_object_new (vm, b_bni_class_string_id (vm));
	}
	if (object == null) {
		return null;
	}
	unsigned int* data = (unsigned int*)b_memory_alloc (vm, 2 * sizeof(unsigned int) + (len + 1) * sizeof(char));
	if (data == NULL) {
		b_object_free (object);
		return B_BNI_FAIL;
	}
	data[0] = len;
	data[1] = hash;
	char* chars = (char*) (data + 2);
	strcpy(chars, text);
	chars[len] = 0;
	object->datas[0] = data;
	return object;
}
void b_object_free (b_object_t* object) {
	if ( ! object) {
		return;
	}
	object->cindex = 0;
	if (object->fields) {
		b_memory_free (object->fields);
	}
	b_memory_free (object->datas);
	b_memory_free (object);
}

