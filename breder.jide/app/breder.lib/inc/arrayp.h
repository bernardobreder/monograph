#ifndef B_ARRAYP_H_
#define B_ARRAYP_H_

#include "breder.h"

struct b_array_t {
	void** array;
	int size;
	int used;
};

b_arrayp_t* b_arrayl_new(int size);
void b_array_free(b_arrayp_t* self);
void b_array_clean(b_arrayp_t* self);
void b_array_set_size(b_arrayp_t* self, int value);
void b_arrayp_free1(b_arrayp_t* self, void*);
void b_arrayp_free2(b_arrayp_t* self, void*, void*);
int b_arrayp_find(b_arrayp_t* self, void* value);
int b_array_add(b_arrayp_t* self, b_vm_t* vm, void* value);
void b_arrayp_close(b_arrayp_t*);
void b_array_remove(b_arrayp_t* self, int index);
void b_arrayp_iterator(b_arrayp_t*, void* func, void* object);

#define b_arrayl_addi(A,V) b_array_add(A,sizeof(int),V)
#define b_arrayl_add64(A,V) b_array_add(A,sizeof(__inst),V)
#define b_arrayp_add(A,V) b_array_add(A,NULL,V)
#define b_arrayp_new() b_arrayl_new(sizeof(void*))
#define b_arrayp_pop_typed(T,A) 						((T*)b_arrayp_pop(A))
#define b_arrayp_pop(A)				 						(A->used ? A->array [ --A->used ] : 0)
#define b_array_dec(A) (A)->used--;
#define b_arrayp_pops(A,N)				 					(A->used ? A->array [ A->used-=N ] : 0)
#define b_arrayp_get( A , I ) 							A->array[ I ]
#define b_arrayp_get_typed( T , A , I ) 		((T*)A->array[ I ])
#define b_arrayp_get_last( A )							b_array_size(A) ? A->array[ b_array_size_safely(A)-1 ] : 0
#define b_arrayp_get_last_safely( A )			A->array[ b_array_size_safely(A)-1 ]
#define b_arrayp_get_first( A )						A->array[ 0 ]
#define b_arrayp_get_last_typed( T , A )		((T*)b_arrayp_get_last( A ))
#define b_arrayp_get_first_typed( T , A )	((T*)b_arrayp_get_first( A ))
#define b_arrayp_set( A , I , V ) 					A->array[ I ] = V
#define b_array_size( A ) 								(A ? A->used : 0)
#define b_array_size_safely( A ) 								A->used

#endif
