#include "breder.h"

long long B_VM_MALLOC_SIZE = 1024 * 1024 * 1024;

#ifdef DEBUG_MEMORY
static FILE* file = 0;
#endif

void b_memory_close () {
#ifdef DEBUG_MEMORY
	fflush(file);
	fclose(file);
#endif
}

void b_memory_sized (long long size) {
	B_VM_MALLOC_SIZE = size;
}

void b_memory_init () {
#ifdef DEBUG_MEMORY
	remove("mem.txt");
	file = fopen("mem.txt", "w+b");
#endif
}

void b_memory_free (void* data) {
#ifdef DEBUG_MEMORY
	if (!file) {
		b_memory_init();
	}
	fprintf(file, "free %d\n", (int) data);
	fflush(file);
#endif
	free (data);
}
void* b_memory_alloc0 (int size) {
#ifdef DEBUG_MEMORY
	if (!file) {
		b_memory_init();
	}
	void* data = calloc(1, buffersize);
	fprintf(file, "alloc %d %d\n", (int) data, buffersize);
	fflush(file);
	return data;
#else
	if (B_VM_MALLOC_SIZE > size) {
		B_VM_MALLOC_SIZE -= size;
		return calloc (1, size);
	} else {
		return 0;
	}
#endif
}

void* b_memory_realloc0 (void* old, int size) {
#ifdef DEBUG_MEMORY
	if (!file) {
		b_memory_init();
	}
	void* data = realloc(old, buffersize);
	fprintf(file, "free %d\n", (int) old);
	fprintf(file, "alloc %d %d\n", (int) data, buffersize);
	fflush(file);
	return data;
#else
	return realloc (old, size);
#endif
}

void* b_memory_alloc (b_vm_t* vm, int size) {
	void* data = b_memory_alloc0 (size);
	vm->gc ++ ;
	if (data == NULL) {
		//		b_gc_start(vm);
		//		data = calloc(1, size);
		//		if (data == NULL) {
		//b_bni_throw_out_of_memory_runtime_exception(vm);
		return NULL;
		//		}
	}
	return data;
}

void* b_memory_realloc (b_vm_t* vm, void* old, int size) {
	void* data = b_memory_realloc0 (old, size);
	vm->gc ++ ;
	if (data == NULL && vm) {
		//		b_gc_start(vm);
		//		data = realloc(old, size);
		//		if (data == NULL) {
		//b_bni_throw_out_of_memory_runtime_exception(vm);
		return NULL;
		//		}
	}
	return data;
}
