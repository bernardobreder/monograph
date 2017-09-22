#include "breder.h"

void b_memory_init(b_vm_t* vm) {
}

void* b_memory_alloc(b_vm_t* vm, int size) {
	void* data = calloc(1, size);
	if (data == NULL) {
		//		b_gc_start(vm);
		//		data = calloc(1, size);
		//		if (data == NULL) {
		b_bni_throw_out_of_memory_runtime_exception(vm);
		return NULL;
		//		}
	}
	return data;
}

void* b_memory_realloc(b_vm_t* vm, void* old, int size) {
	void* data = realloc(old, size);
	if (data == NULL && vm) {
		//		b_gc_start(vm);
		//		data = realloc(old, size);
		//		if (data == NULL) {
		b_bni_throw_out_of_memory_runtime_exception(vm);
		return NULL;
		//		}
	}
	return data;
}

void b_memory_free(void* data) {
	free(data);
}
