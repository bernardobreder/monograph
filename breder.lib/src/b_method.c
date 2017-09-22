#include "breder.h"

void b_method_free (b_method_t* self) {
	if (self->name) {
		b_char_free (self->name);
	}
	if (self->aname) {
		b_char_free (self->aname);
	}
	b_memory_free (self);
}
