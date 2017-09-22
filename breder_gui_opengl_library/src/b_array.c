#include "b_array.h"

struct b_array_t* b_array_new1(int count) {
	b_array_t* self = (b_array_t*) calloc(1, sizeof(b_array_t));
	self->size = count;
	self->used = 0;
	self->array = (void**) calloc(self->size, sizeof(void*));
	return self;
}

void b_array_free(struct b_array_t* self) {
	if (self->array) {
		free(self->array);
		self->array = 0;
	}
	free(self);
}

void b_array_free1(struct b_array_t* self, void* func) {
	int n, size = b_array_size ( self );
	for (n = 0; n < size; n++) {
		void* value = b_array_get( self , n );
		if (value) {
			b_call_function( void , func , (void*) )(value);
		}
	}
	b_array_free(self);
}

void b_array_free2(struct b_array_t* self, void* func1, void* func2) {
	int n;
	register int size = b_array_size ( self );
	for (n = 0; n < size; n++) {
		struct b_array_t* value = b_array_get_typed(struct b_array_t, self, n);
		b_array_free1(value, func2);
	}
	b_call_function( void , func1 , (void*) )(self);
}

void b_array_set_size(struct b_array_t* self, int value) {
	if (value > self->size) {
		self->array = (void**) realloc(self->array, self->size * sizeof(void*));
	}
	self->size = value;
	self->used = value;
}

void b_array_clean(struct b_array_t* self) {
	self->used = 0;
}

int b_array_find(struct b_array_t* self, void* value) {
	int n;
	for (n = 0; n < self->used && self->array[n] != value; n++)
		;
	if (n == self->used)
		return -1;
	else
		return n;
}

void b_array_put(struct b_array_t* self, int index, void* value) {
	if (self->used <= index) {
		int n;
		for (n = 0; n <= index - self->used; n++) {
			b_array_add(self, 0);
		}
	}
	b_array_set ( self , index , value );
}

void b_array_remove(struct b_array_t* self, int index) {
	if (index > self->used - 1 || index < 0)
		return;
	int n;
	for (n = index + 1; n < self->used; n++) {
		self->array[n - 1] = self->array[n];
	}
	self->used -= 1;
}

void b_array_close(struct b_array_t* self) {
	int size = self->used;
	if (!size) {
		size = 1;
	}
	self->array = (void**) realloc(self->array, size * sizeof(void*));
	self->size = size;
}

void b_array_iterator(struct b_array_t* self, void* func, void* object) {
	int n;
	for (n = 0; n < b_array_size(self); n++) {
		void* item = b_array_get( self , n );
		b_call_function( void , func , (void*,void*) )(object, item);
	}
}

int b_array_add(struct b_array_t* self, void* value) {
	if (self->size == self->used) {
		self->size *= 2;
		void** array = (void**) realloc(self->array, sizeof(void*) * self->size);
		if (array == 0) {
			return 0;
		}
		self->array = array;
	}
	self->array[self->used++] = value;
	return 1;
}
