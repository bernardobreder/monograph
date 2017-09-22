#ifndef B_CLASS_H_
#define B_CLASS_H_

#include "breder.h"

struct b_class_t {
	unsigned int index;
	unsigned int nameindex;
	aint* extendindex;
	b_array_class_t* extends;
	char* name;
	int fieldCount;
	b_array_field_t* fields;
	b_array_field_t* cfields;
	b_array_method_t* methods;
	b_class_t* extend;
};

b_class_t* b_class_new();

void b_class_free(b_class_t* self);

void b_class_add_method(b_class_t*, b_method_t*);

#endif
