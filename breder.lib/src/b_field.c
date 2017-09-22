#include "breder.h"

void b_field_free (b_field_t* self) {
	b_memory_free (self);
}
