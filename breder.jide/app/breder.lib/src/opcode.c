#include "breder.h"

__inst* b_opcode_new ( __inst inst , const char* msg ) {
	__inst* i = calloc( 1 , sizeof(__inst) ) ;
	* i = inst ;
	return i ;
}

int b_opcode_add ( b_arrayp_t* array , __inst inst , const char* msg ) {
	if ( b_array_size(array) >= MAX_INDEX_A ) return 0 ;
	b_arrayp_add( array, b_opcode_new(inst,msg) ) ;
	return 1 ;
}
