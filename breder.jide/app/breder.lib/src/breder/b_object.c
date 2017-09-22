#include "breder.h"

static b_class_t* b_class_super(b_class_t* original, b_class_t* current) {
	if (!original->extend) {
		return null;
	} else if (original->extend->index == current->index) {
		return original;
	} else {
		return b_class_super(original->extend, current);
	}
}

static void b_object_load_methods_aux_aux(b_class_t* class, b_hashnew_t* hash,
		b_method_t* method) {
	int n, size = b_array_size(class->methods);
	for (n = 0; n < size; n++) {
		b_method_t* othermethod =
				b_arrayp_get_typed(b_method_t, class->methods, n);
		if (!b_char_compare(method->name, othermethod->name)) {
			b_hashnew_put2(hash, othermethod->cname, method);
			break;
		}
	}
	size = b_array_size(class->extends);
	for (n = 0; n < size; n++) {
		b_class_t* extend = b_arrayp_get_typed(b_class_t, class->extends, n);
		b_object_load_methods_aux_aux(extend, hash, method);
	}
}

static void b_object_load_methods_aux(b_class_t* class, b_hashnew_t* hash) {
	int n, size = b_array_size(class->extends);
	for (n = 0; n < size; n++) {
		b_class_t* extend = b_arrayp_get_typed(b_class_t, class->extends, n);
		b_object_load_methods_aux(extend, hash);
	}
	size = b_array_size(class->methods);
	for (n = 0; n < size; n++) {
		b_method_t* method = b_arrayp_get_typed(b_method_t, class->methods, n);
		b_object_load_methods_aux_aux(class, hash, method);
		b_hashnew_put1(hash, method->name, method);
		b_hashnew_put2(hash, method->absoluteName, method);
	}
	size = b_array_size(class->fields);
	for (n = 0; n < size; n++) {
		b_field_t* field = b_arrayp_get_typed(b_field_t, class->fields, n);
		b_hashnew_put2(hash, field->cname, null);
	}
}

static void b_object_load_fields_aux(b_class_t* class, b_hashnew_t* hash) {
	int n, size = b_array_size(class->extends);
	for (n = 0; n < size; n++) {
		b_class_t* extend = b_arrayp_get_typed(b_class_t, class->extends, n);
		b_object_load_fields_aux(extend, hash);
	}
	size = b_array_size(class->fields);
	for (n = 0; n < size; n++) {
		b_field_t* field = b_arrayp_get_typed(b_field_t, class->fields, n);
		b_hashnew_put2(hash, field->cname, null);
	}
}

void* b_object_cache_methods(b_class_t* class) {
	b_hashnew_t* hash = b_hashp_new1(null, false);
	b_object_load_methods_aux(class, hash);
	void* result = b_hashnew_close(hash);
	b_hashnew_free(hash);
	return result;
}

void* b_object_cache_fields(b_class_t* class) {
	b_hashnew_t* hash = b_hashp_new1(null, false);
	b_object_load_fields_aux(class, hash);
	void* result = b_hashnew_close(hash);
	b_hashnew_free(hash);
	return result;
}

b_object_t* b_object_new(b_vm_t* vm, b_class_t* class) {
	b_object_t* self = b_memory_alloc(vm, b_object_size());
	if (self == NULL) {
		return B_BNI_FAIL;
	}
	int* header = (int*) self;
	*header++ = class->index; // class
	*header++ = 0; // fields TODO : Campo pode ser retirado pq os campos foram colocado no hash
	*header++ = 0; // used
	*header++ = 0; // gc_used
	void** data = (void**) header;
	*data++ = 0;
	*data++ = b_object_cache_fields(class);
	*data++ = b_object_cache_methods(class);
	return self;
}

void b_object_free(b_object_t* self) {
	b_hashclose_free(b_object_hash_field(self));
	b_memory_free(self);
}

b_object_t* b_object_new_object(b_vm_t* self, int classindex) {
	b_object_t* cache =
			b_arrayp_get_typed( b_object_t , self->caches , classindex );
	b_object_t* object = b_memory_alloc(self, b_object_size());
	if (object == NULL) {
		return B_BNI_FAIL;
	}
	memcpy (object, cache, b_object_size_noproxy());
	{
		int size = b_hashclose_sizeof(b_object_hash_field(object));
		void * fields = b_memory_alloc(self, size);
		memcpy (fields, b_object_hash_field(object), size);
		b_object_hash_field(object) = fields;
	}
	b_linker_add_last(self->objects, self, object);
	return object;
}

b_object_t* b_object_new_number(b_vm_t* self, double number) {
	b_object_t* object = b_object_new_object(self, self->numberClass->index);
	double* data = b_memory_alloc(self, sizeof(double));
	if (data == NULL) {
		return B_BNI_FAIL;
	}
	*data = number;
	b_object_set_data(object, data);
	return object;
}

b_object_t* b_object_new_boolean(b_vm_t* self, int flag) {
	b_object_t* object = b_object_new_object(self, self->booleanClass->index);
	if (flag) {
		b_object_set_data( object , object );
	}
	return object;
}

b_object_t* b_object_new_string(b_vm_t* self, const char* text) {
	return b_object_new_string0(self, text, strlen(text), 0);
}

b_object_t* b_object_new_string0(b_vm_t* self, const char* text, int len,
		int hash) {
	b_object_t* object = b_object_new_object(self, self->stringClass->index);
	int* data =
			b_memory_alloc(self, 2 * sizeof(int) + (len + 1) * sizeof(char));
	if (data == NULL) {
		return B_BNI_FAIL;
	}
	data[0] = len;
	data[1] = hash;
	char* chars = (char*) (data + 2);
	strcpy ( chars , text );
	b_object_set_data( object , data );
	return object;
}
