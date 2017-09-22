#ifndef B_METHOD_H_
#define B_METHOD_H_

#include "breder.h"

struct b_method_t {
	char* name ;
	unsigned int nameIndex ;
	unsigned int memIndex ;
	unsigned int index; // indice correspondente a lista de metodos do vm->methods
	b_chars_t* cname;
	b_chars_t* absoluteName;
	void* isNative;
	int isStatic;
	int isAbstract;
	aint* params;
	int returns;
} ;

b_method_t* b_method_new ( ) ;

void b_method_free ( b_method_t* self ) ;

#endif
