#include "breder.h"

b_field_t* b_field_new () {
	b_field_t* self = calloc ( 1 , sizeof(b_field_t) ) ;
	return self ;
}

void b_field_free ( b_field_t* self ) {
	free ( self ) ;
}
