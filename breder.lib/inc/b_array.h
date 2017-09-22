#ifndef B_ARRAYP_H_
#define B_ARRAYP_H_

#include "breder.h"

struct b_array_t {
	void** array;
	int size;
	int used;
};

struct b_array_t* b_array_new1 (int count);
void b_array_free (struct b_array_t* self);
void b_array_free1 (struct b_array_t* self, void* func);
void b_array_clean (struct b_array_t* self);
void b_array_set_size (struct b_array_t* self, int value);

int b_array_find (struct b_array_t* self, void* value);
void b_array_close (struct b_array_t*);
void b_array_remove (struct b_array_t* self, int index);
void b_array_iterator (struct b_array_t*, void* func, void* object);
int b_array_add (struct b_array_t* self, void* value);

#define b_array_add_safely(ARRAY, VALUE) (ARRAY)->array[(ARRAY)->used++] = (VALUE);
#define b_array_new() b_array_new1(15)
#define b_array_pop_typed(T,A) ((T*)b_array_pop(A))
#define b_array_pop(A) (A->used ? A->array [ --A->used ] : 0)
#define b_array_dec(A) ((A)->used--);
#define b_array_decs(A,C) ((A)->used -= C);
#define b_array_pops(A,N) (A->used ? A->array [ A->used-=N ] : 0)
#define b_array_get(A,I) (A)->array[(I)]
#define b_array_get_typed(T,A,I) ((T*)(A)->array[(I)])
#define b_array_get_last( A ) b_array_size(A) ? A->array[ b_array_size_safely(A)-1 ] : 0
#define b_array_get_last_safely( A ) A->array[ b_array_size_safely(A)-1 ]
#define b_array_get_first( A ) A->array[ 0 ]
#define b_array_get_last_typed( T , A )	((T*)b_array_get_last( A ))
#define b_array_get_first_typed( T , A ) ((T*)b_array_get_first( A ))
#define b_array_set( A , I , V ) A->array[ I ] = V
#define b_array_size( A ) (A ? A->used : 0)
#define b_array_size_safely( A ) A->used

#endif
