#ifndef B_THROW_H_
#define B_THROW_H_

#include "breder.h"

struct b_throw_t {
	char* message;
};

void b_vm_throw_print(b_vm_t* vm);

void b_vm_throw_print_object(b_vm_t* vm, b_object_t* throwObject);

#endif
