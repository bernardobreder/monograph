#ifndef B_MEMORY_H_
#define B_MEMORY_H_

#include "breder.h"

void b_memory_init(b_vm_t* vm);

void* b_memory_alloc(b_vm_t* vm, int size);

void* b_memory_realloc(b_vm_t* vm, void* old, int size);

void b_memory_free(void* data);

#endif
