#include "breder.h"

static void b_gc_mark (b_vm_t* vm, b_object_t* object) {
	b_object_gc_used(object) = 1;
	if (object->fields) {
		b_object_t** objects = object->fields;
		register int n, size = b_class_get(vm, object->cindex)->fieldCount;
		for (n = 0; n < size; n ++ ) {
			if ( * objects) {
				b_object_t* ofield = * objects;
				if ( ! b_object_gc_used(ofield)) {
					b_gc_mark (vm, ofield);
				}
			}
			objects ++ ;
		}
	}
}

void b_gc_start (b_vm_t* vm) {
	int n, objectCount = b_linker_size(vm->objects) - 1;
	b_object_t** index = vm->objstack + 1;
	if (vm->othrow) {
		b_gc_mark (vm, vm->othrow);
	}
	{
		int size = vm->cobjstack - vm->objstack;
		for (n = 0; n < size; n ++ ) {
			if ( * index) {
				if ( ! b_object_gc_used(*index)) {
					b_gc_mark (vm, * index);
				}
			}
			index ++ ;
		}
	}
	int stringClassId = b_bni_class_string_id (vm);
	int booleanClassId = b_bni_class_boolean_id (vm);
	int numberClassId = b_bni_class_number_id (vm);
	int integerClassId = b_bni_class_integer_id (vm);
	int naturalClassId = b_bni_class_natural_id (vm);
	int indexClassId = b_bni_class_index_id (vm);
	b_linker_begin(vm->objects);
	b_linker_next(vm->objects);
	while (b_linker_has_current(vm->objects)) {
		b_object_t* object = b_linker_current_typed(b_object_t, vm->objects);
		if ( ! b_object_gc_used(object) && ! b_object_used(object)) {
			b_linker_remove_current(vm->objects);
			int cindex = b_object_class(object);
			if (cindex == booleanClassId || cindex == stringClassId || cindex == numberClassId || cindex == integerClassId || cindex == naturalClassId
			    || cindex == indexClassId) {
				if (object->datas[0]) {
					b_memory_free (object->datas[0]);
				}
			} else {
				b_bni_opcode_push(vm, object);
				b_bni_opcode_ojump (vm, b_bni_method_object_finalize_id (vm));
			}
			b_object_free (object);
			if ( ! b_linker_has_current(vm->objects)) {
				break;
			} else {
				b_linker_back(vm->objects);
			}
		} else {
			b_object_gc_used(object) = 0;
		}
		b_linker_next(vm->objects);
	}
	objectCount = b_linker_size(vm->objects) - 1;
	//b_log_public_print("Objects :%d\n", objectCount);
}
