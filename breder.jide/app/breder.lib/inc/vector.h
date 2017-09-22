#ifndef VECTOR_H_
#define VECTOR_H_

#include "breder.h"

#define b_aint_content(a) ((int*)a)
#define b_aint_size(a) b_aint_content(a)[0]
#define b_aint_get(a,i) b_aint_content(a)[(i)+1]

#define b_achar_content(a) ((int*)a)
#define b_achar_len(a) b_aint_content(a)[0]
#define b_achar_hash(a) b_aint_content(a)[1]
#define b_achar_text(a) ((char*)(b_aint_content(a)+2))

b_vector_t* b_vector_new();

b_vector_t* b_vector_new1(int counter, ...);

#define b_vector_header( Vector ) ((int*)(Vector))
#define b_vector_free( Vector ) free( Vector )
#define b_vector_begin( Vector ) ((void**)(b_vector_header(Vector)+2)+1)
#define b_vector_begin_typed( Type , Vector ) (( Type** )b_vector_begin(Vector))
#define b_vector_end( Vector ) (b_vector_begin(Vector)+b_vector_size(Vector)-1)
#define b_vector_size( Vector ) (b_vector_header(Vector)[1])
#define b_vector_get( Vector , Index ) (b_vector_begin(Vector)[Index])
#define b_vector_top( Vector ) (b_vector_begin(Vector)[b_vector_size(Vector)-1])
#define b_vector_get_typed( Type , Vector , Index ) ( Type* ) b_vector_get( Vector , Index )
#define b_vector_set( Vector , Index , Value ) (b_vector_get(Vector,Index) = Value)
#define b_vector_pop_typed( Type , Vector ) ( Type* ) Vector$Pop( Vector )
#define b_vector_dec_size( Vector ) (*(b_vector_header(Vector)+1)-=1)
#define b_vector_dec_sizes( Vector , Len ) (*(b_vector_header(Vector)+1)-=Len)

void* b_vector_pop(b_vector_t*);

void b_vector_add(b_vector_t**, void* value);

#endif /* VECTOR_H_ */
