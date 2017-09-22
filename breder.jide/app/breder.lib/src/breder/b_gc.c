#include "breder.h"

void b_gc_start(b_vm_t* vm) {
	int n, objectCount = b_linker_size(vm->objects) - 1;
	b_object_t** index = vm->stack + 1;
	if (vm->othrow) {
		b_gc_mark(vm, vm->othrow);
	}
	{
		int size = vm->cstack - vm->stack;
		for (n = 0; n < size; n++) {
			if (*index) {
				if (!b_object_gc_used( * index )) {
					b_gc_mark(vm, *index);
				}
			}
			index++;
		}
	}
	b_linker_begin(vm->objects);
	b_linker_next(vm->objects);
	while (b_linker_has_current(vm->objects)) {
		b_object_t* object = b_linker_current_typed(b_object_t, vm->objects);
		if (!b_object_gc_used( object ) && !b_object_used( object )) {
			int index = b_object_class(object);
			if (index == vm->booleanClass->index || index
					== vm->numberClass->index || index
					== vm->stringClass->index) {
				if (index == vm->numberClass->index) {
					double value = b_bni_onumber_to_primitive(vm, object);
					b_memory_free(b_object_data(object));
				} else if (index == vm->stringClass->index) {
					b_memory_free(b_object_data(object));
				}
				b_object_free(object);
				b_linker_remove_current(vm->objects);
				if (!b_linker_has_current(vm->objects)) {
					break;
				}
				b_linker_back(vm->objects);
			}
		}
		b_linker_next(vm->objects);
	}
	b_linker_begin(vm->objects);
	b_linker_next(vm->objects);
	while (b_linker_has_current(vm->objects)) {
		b_object_t* object = b_linker_current_typed(b_object_t, vm->objects);
		if (!b_object_gc_used(object) && !b_object_used(object)) {
			b_object_used(object) += 1;
			b_bni_call_0_return_0_param(vm, object, "() finalize ()");
			b_object_free(object);
			b_linker_remove_current(vm->objects);
			if (!b_linker_has_current(vm->objects)) {
				break;
			}
			b_linker_back(vm->objects);
		} else {
			b_object_gc_used( object ) = 0;
		}
		b_linker_next(vm->objects);
	}
	objectCount = b_linker_size(vm->objects) - 1;
}

void b_gc_mark(b_vm_t* vm, b_object_t* object) {
	b_object_gc_used(object) = 1;
	int classindex = b_object_class(object);
	b_class_t* clazz = b_arrayp_get_typed(b_class_t, vm->classs, classindex);
	b_gc_mark_extend(vm, object, clazz);
}

void b_gc_mark_extend(b_vm_t* vm, b_object_t* object, b_class_t* clazz) {
	int n, size = b_array_size(clazz->fields);
	for (n = 0; n < size; n++) {
		b_field_t* field = b_arrayp_get_typed(b_field_t, clazz->fields, n);
		b_object_t* ofield = b_hashclose_get1(b_object_hash_field(object),
				field->cname);
		if (ofield) {
			if (!b_object_gc_used(ofield)) {
				b_gc_mark(vm, ofield);
			}
		}
	}
	size = b_array_size(clazz->extends);
	for (n = 0; n < size; n++) {
		b_class_t* extend = b_arrayp_get_typed(b_class_t, clazz->extends, n);
		b_gc_mark_extend(vm, object, extend);
	}
}
