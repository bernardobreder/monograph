#include "breder.h"

b_array_t* b_array_new1 (int count) {
	b_array_t* self = b_memory_alloc_struct(b_array_t);
	self->size = count;
	self->used = 0;
	self->array = b_memory_alloc_typed(void*, count);
	return self;
}

void b_array_free (b_array_t* self) {
	if (self->array) {
		b_memory_free (self->array);
		self->array = 0;
	}
	b_memory_free (self);
}

void b_array_free1 (b_array_t* self, void* func) {
	int n, size = b_array_size ( self );
	for (n = 0; n < size; n ++ ) {
		void* value = b_array_get( self , n );
		if (value) {
			b_call_function( void , func , (void*) ) (value);
		}
	}
	b_array_free (self);
}

void b_array_set_size (b_array_t* self, int value) {
	if (value > self->size) {
		self->array = (void**)b_memory_realloc_typed(void*, self->array, self->size
		);
	}
	self->size = value;
	self->used = value;
}

void b_array_clean (b_array_t* self) {
	self->used = 0;
}

int b_array_find (b_array_t* self, void* value) {
	int n;
	for (n = 0; n < self->used && self->array[n] != value; n ++ )
		;
	if (n == self->used) return - 1;
	else return n;
}

void b_array_put (b_array_t* self, int index, void* value) {
	if (self->used <= index) {
		int n;
		for (n = 0; n <= index - self->used; n ++ ) {
			b_array_add (self, 0);
		}
	}
	b_array_set ( self , index , value );
}

void b_array_remove (b_array_t* self, int index) {
	if (index > self->used - 1 || index < 0) return;
	int n;
	for (n = index + 1; n < self->used; n ++ ) {
		self->array[n - 1] = self->array[n];
	}
	self->used -= 1;
}

void b_array_close (b_array_t* self) {
	int size = self->used;
	if ( ! size) {
		size = 1;
	}
	self->array = b_memory_realloc_typed(void*, self->array, size);
	self->size = size;
}

void b_array_iterator (b_array_t* self, void* func, void* object) {
	int n;
	for (n = 0; n < b_array_size(self); n ++ ) {
		void* item = b_array_get( self , n );
		b_call_function( void , func , (void*,void*) ) (object, item);
	}
}

int b_array_add (b_array_t* self, void* value) {
	if (self->size == self->used) {
		self->size *= 2;
		void** array = b_memory_realloc_typed(void*, self->array, self->size);
		if (array == NULL) {
			return 0;
		}
		self->array = array;
	}
	b_array_add_safely(self, value);
	return 1;
}
