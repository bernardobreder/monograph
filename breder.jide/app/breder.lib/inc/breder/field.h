#ifndef B_FIELD_H_
#define B_FIELD_H_

#include "breder.h"

struct b_field_t {
	//	char* name ;
	b_chars_t* cname;
	unsigned int index; // indice correspondente a lista de metodos do vm->methods
};

b_field_t* b_field_new();

void b_field_free(b_field_t* self);

#endif
