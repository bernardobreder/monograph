#include "breder.h"

void b_class_free (b_class_t* self) {
	if (self->name) {
		b_memory_free (self->name);
	}
	if (self->methods) {
		int nindex, size = b_array_size (self->methods);
		for (nindex = 0; nindex < size; nindex ++ ) {
			void* value = b_array_get(self->methods, nindex);
			if (value) {
				b_method_free ((b_method_t*)value);
			}
		}
		b_array_free (self->methods);
	}
	b_memory_free (self);
}
