#ifndef B_UTIL_H_
#define B_UTIL_H_

#include "breder.h"

b_ints_t* b_aint_new(int len);
#define b_aint_content(a) ((int*)a)
#define b_aint_size(a) (*b_aint_content(a))
#define b_aint_get(a,i) b_aint_content(a)[(i)+1]
#define b_aint_set(a,i,v) b_aint_content(a)[(i)+1] = v

#endif
