#include "breder.h"

b_class_t* b_class_new() {
	b_class_t* self = calloc(1, sizeof(b_class_t));
	self->cfields = b_arrayp_new ( );
	self->fields = b_arrayp_new ( );
	self->methods = b_arrayp_new ( );
	self->extends = b_arrayp_new ( );
	return self;
}

void b_class_free(b_class_t* self) {
	if (self->name)
		b_char_free(self->name);
	if (self->methods)
		b_arrayp_free1(self->methods, b_method_free);
	free(self);
}

void b_class_add_method(b_class_t* self, b_method_t* method) {
	b_arrayp_add( self->methods , method );
}

