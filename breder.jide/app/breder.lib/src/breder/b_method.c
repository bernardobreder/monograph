#include "breder.h"

b_method_t* b_method_new () {
	b_method_t* self = calloc ( 1 , sizeof(b_method_t) ) ;
	return self ;
}

void b_method_free ( b_method_t* self ) {
	if ( self->name ) b_char_free ( self->name ) ;
	free ( self ) ;
}
