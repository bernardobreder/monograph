#include "breder.h"

b_arrayp_t* b_arrayl_new(int size) {
	b_arrayp_t* self = calloc(1, sizeof(b_arrayp_t));
	self->size = 15;
	self->used = 0;
	self->array = calloc(self->size, size);
	return self;
}

void b_array_free(b_arrayp_t* self) {
	if (self->array) {
		free(self->array);
		self->array = 0;
	}
	free(self);
}

void b_array_set_size(b_arrayp_t* self, int value) {
	if (value > self->size) {
		self->array = realloc(self->array, self->size * sizeof(void*));
	}
	self->size = value;
	self->used = value;
}

void b_array_clean(b_arrayp_t* self) {
	self->used = 0;
}

void b_arrayp_free1(b_arrayp_t* self, void* func) {
	register int n;
	register int size = b_array_size ( self );
	for (n = 0; n < size; n++) {
		void* value = b_arrayp_get( self , n );
		if (value)
			b_call_function( void , func , (void*) )(value);
	}
	b_array_free(self);
}

void b_arrayp_free2(b_arrayp_t* self, void* func1, void* func2) {
	int n;
	register int size = b_array_size ( self );
	for (n = 0; n < size; n++) {
		void* value = b_arrayp_get( self , n );
		b_arrayp_free1(value, func2);
	}
	b_call_function( void , func1 , (void*) )(self);
}

int b_arrayp_find(b_arrayp_t* self, void* value) {
	int n;
	for (n = 0; n < self->used && self->array[n] != value; n++)
		;
	if (n == self->used)
		return -1;
	else
		return n;
}

void b_array_put(b_arrayp_t* self, int index, void* value) {
	if (self->used <= index) {
		int n;
		for (n = 0; n <= index - self->used; n++) {
			b_arrayp_add ( self , 0 );
		}
	}
	b_arrayp_set ( self , index , value );
}

void b_array_remove(b_arrayp_t* self, int index) {
	if (index > self->used - 1 || index < 0)
		return;
	int n;
	for (n = index + 1; n < self->used; n++) {
		self->array[n - 1] = self->array[n];
	}
	self->used -= 1;
}

void b_arrayp_close(b_arrayp_t* self) {
	int size = self->used;
	if (!size)
		size = 1;
	self->array = realloc(self->array, size * sizeof(void*));
	self->size = size;
}

void b_arrayp_iterator(b_arrayp_t* self, void* func, void* object) {
	int n;
	for (n = 0; n < b_array_size(self); n++) {
		void* item = b_arrayp_get( self , n );
		b_call_function( void , func , (void*,void*) )(object, item);
	}
}

int b_array_add(b_arrayp_t* self, b_vm_t* vm, void* value) {
	if (self->size == self->used) {
		self->size *= 2;
		void* array = b_memory_realloc(vm, self->array, sizeof(void*) * self->size);
		if (array == NULL) {
			return B_BNI_FAIL;
		}
		self->array = array;
	}
	self->array[self->used++] = value;
	return 1;
}
