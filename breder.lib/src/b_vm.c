#include "breder.h"

b_vm_t* b_vm_new () {
	b_vm_t* vm;
	b_assert_end(vm = b_memory_alloc_struct(b_vm_t));
	vm->bufferSize = 0;
	vm->bufferAlloced = 1024;
	b_assert_end(vm->cobjstack = vm->objstack = b_memory_alloc_typed(b_object_t*, B_VM_STACK_MAX));
	b_assert_end(vm->progmem = b_memory_alloc_typed(__inst, vm->bufferAlloced));
	b_assert_end(vm->pc_cindex = b_memory_alloc_typed(b_class_id_t, vm->bufferAlloced));
	b_assert_end(vm->pc_line = b_memory_alloc_typed(short, vm->bufferAlloced));
	b_assert_end(vm->cmemstack = vm->memstack = b_memory_alloc_typed(int, B_VM_STACKTRACE_MAX));
	b_assert_end(vm->cprogthrowstack = vm->progthrowstack = b_memory_alloc_typed(int, B_VM_THROWTRACE_MAX));
	b_assert_end(vm->cobjthrowstack = vm->objthrowstack = b_memory_alloc_typed (int, B_VM_THROWTRACE_MAX));
	b_assert_end(vm->cmemthrowstack = vm->memthrowstack = b_memory_alloc_typed (int, B_VM_THROWTRACE_MAX));
	b_assert_end(vm->stackTrace = b_array_new1 (B_VM_STACK_MAX));
	b_assert_end(vm->libraryPaths = b_array_new());
	b_assert_end(vm->nativeMethods = b_array_new());
	b_assert_end(vm->libraryFuncs = b_array_new());
	b_assert_end(vm->objects = b_linker_new ());
	b_assert_end(vm->methods = b_array_new());
	b_assert_end(vm->fields = b_array_new());
	b_assert_end(vm->nativeMethods = b_array_new());
	b_assert_end(b_linker_add_last (vm->objects, vm, 0));
	return vm;
	end : b_vm_free (vm);
	return B_STATE_FAIL;
}

void b_vm_free (b_vm_t* vm) {
	if ( ! vm) {
		return;
	}
	b_memory_free (vm->objstack);
	b_memory_free (vm->progmem);
	b_memory_free (vm->pc_cindex);
	b_memory_free (vm->pc_line);
	b_memory_free (vm->memstack);
	b_memory_free (vm->progthrowstack);
	b_memory_free (vm->objthrowstack);
	b_memory_free (vm->memthrowstack);
	{
		int nindex, size = vm->classSize;
		for (nindex = 0; nindex < size; nindex ++ ) {
			b_class_t* value = b_class_get(vm, nindex);
			if (value) {
				b_class_free ((b_class_t*)value);
			}
		}
		b_memory_free (vm->classes);
	}
	b_linker_free1(vm->objects, b_object_t*, b_object_free);
	b_memory_free (vm);
}

b_class_t* b_vm_find_class (b_vm_t* vm, const char* name) {
	int n, sizen = vm->classSize;
	for (n = 0; n < sizen; n ++ ) {
		b_class_t* clazz = b_class_get(vm, n);
		if ( ! b_char_compare(clazz->name, name)) return clazz;
	}
	printf ("[bvm] : not found the class '%s'", name);
	exit (0);
	return 0;
}

b_method_t* b_vm_find_method (b_vm_t* vm, b_class_id_t cindex, const char* name) {
	b_class_t* clazz = b_class_get(vm, cindex);
	register int n, size = b_array_size_safely(clazz->methods);
	for (n = 0; n < size; n ++ ) {
		b_method_t* method = b_array_get_typed(b_method_t, clazz->methods, n);
		if ( ! b_char_compare(method->name, name)) return method;
	}
	printf ("[bvm] : not found the method '%s' in the class '%s'", name, clazz->name);
	exit (0);
	return 0;
}
