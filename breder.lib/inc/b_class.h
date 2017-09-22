#ifndef B_CLASS_H_
#define B_CLASS_H_

#include "breder.h"

struct b_class_t {
	b_class_id_t index;
	char* name;
	b_ints_t* castindex;
	b_ints_t* extendindex;
	b_ints_t* implementindex;
	int fieldCount;
	b_array_field_t* fields;
	b_array_method_t* methods;
// b_object_t* object;
};

void b_class_free (b_class_t* self);

void b_class_add_method (b_vm_t* self, b_class_t*, b_method_t*);

void b_class_add_field (b_vm_t* vm, b_class_t* clazz, b_field_t* field);

#define b_class_get(VM, CLASS_ID) \
		(VM)->classes[CLASS_ID]

#define b_class_get_extend(VM, CLASS, INDEX) \
		b_class_get(VM, b_aint_get((CLASS)->castindex, INDEX))

#endif
