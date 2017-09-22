#ifndef B_MEMORY_H_
#define B_MEMORY_H_

#include "breder.h"

void b_memory_init ();

void b_memory_close ();

void* b_memory_alloc0 (int size);

#define b_memory_alloc_typed(TYPE, SIZE) \
		((TYPE*)b_memory_alloc0((SIZE) * sizeof(TYPE)))

#define b_memory_alloc_struct(TYPE) \
		b_memory_alloc_typed(TYPE, 1)

void* b_memory_alloc (b_vm_t* vm, int size);

#define b_memory_alloc_vm_typed(VM, TYPE, SIZE) \
		((TYPE*)b_memory_alloc(VM, SIZE * sizeof(TYPE)))

#define b_memory_alloc_vm_struct(VM, TYPE) \
		b_memory_alloc_vm_typed(VM, TYPE, 1)

void* b_memory_realloc (b_vm_t* vm, void* old, int size);

void* b_memory_realloc0 (void* old, int size);

#define b_memory_realloc_typed(TYPE, OLD, SIZE) \
		((TYPE*)b_memory_realloc0(OLD, (SIZE) * sizeof(TYPE)))

void b_memory_free (void* data);

#endif
