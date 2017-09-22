#ifndef B_GC_H_
#define B_GC_H_

#include "breder.h"

void b_gc_start(b_vm_t*);

void b_gc_mark(b_vm_t*, b_object_t*);

void b_gc_mark_extend(b_vm_t* vm, b_object_t* object, b_class_t* clazz);

#endif
