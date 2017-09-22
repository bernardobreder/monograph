#ifndef THROW_H_
#define THROW_H_

#include "breder.h"

struct b_throw_t {
	char* message ;
} ;

void b_throw_print ( b_vm_t* vm , b_object_t* object ) ;

#endif
