#include "breder.h"

b_vector_t* b_vector_new () {
	int size = 5 ;
	b_vector_t* self = ( b_vector_t* ) calloc ( 1 , ( ( size + 2 ) * sizeof(void*) ) + ( 2 * sizeof(int) ) ) ;
	int* header = ( int* ) self ;
	header [ 0 ] = size ;
	return self ;
}

void* b_vector_pop ( b_vector_t* self ) {
	void* data = b_vector_get( self , b_vector_size ( self ) - 1 ) ;
	b_vector_set(self , b_vector_dec_size( self ) , 0 ) ;
	return data ;
}

void b_vector_pops ( b_vector_t* self , int len ) {
	b_vector_set ( self , b_vector_dec_sizes( self , len ) , 0 ) ;
}

void b_vector_add ( b_vector_t** self , void* value ) {
	int* header = ( int* ) * self ;
	int size = * header ++ ;
	int used = * header ;
	* header += 1 ;
	void** pointer = ( void** ) ( header + 1 ) ;
	pointer ++ ;
	if ( used == size ) {
		void* aux ;
		size *= 2 ;
		aux = realloc ( * self , ( ( size + 2 ) * sizeof(void*) ) + ( 2 * sizeof(int) ) ) ;
		* self = ( b_vector_t* ) aux ;
		b_vector_header( aux ) [ 0 ] = size ;
		pointer = b_vector_begin( aux ) + ( size / 2 ) ;
	} else {
		pointer += used ;
	}
	* pointer = value ;
	pointer [ 1 ] = 0 ;
}

b_vector_t* b_vector_new1 ( int counter , ... ) {
	b_vector_t* self = b_vector_new () ;
	va_list vl ;
	va_start ( vl , counter ) ;
	int n ;
	for ( n = 0 ; n < counter ; n ++ ) {
		b_vector_add ( & self , va_arg(vl,void*) ) ;
	}
	va_end(vl) ;
	return self ;
}

void b_vector_free1 ( b_vector_t* self , void* func ) {
	if ( func ) {
		void** values = b_vector_begin( self ) ;
		while ( * values ) {
			void* value = * values ++ ;
			if ( func ) {
				b_call_function( void , func , (void*) ) ( value ) ;
			}
		}
	}
	b_vector_free( self ) ;
}

void b_vector_test () {
	b_vector_t* vec = b_vector_new () ;
	register int n ;
	for ( n = 0 ; n < 1000 ; n ++ ) {
		b_vector_add ( & vec , n + 1 ) ;
	}
	assert( b_vector_size( vec) == 1000) ;
	for ( n = 0 ; n < 1000 ; n ++ ) {
		assert( b_vector_get( vec , n ) == n+1 ) ;
	}
	for ( n = 0 ; n < 1000 ; n ++ ) {
		assert( b_vector_pop( vec ) == 1000-n ) ;
	}
	assert( b_vector_size( vec) == 0) ;
	b_vector_free( vec ) ;
}
