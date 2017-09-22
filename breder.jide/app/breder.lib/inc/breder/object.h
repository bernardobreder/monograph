#ifndef B_OBJECT_H_
#define B_OBJECT_H_

#include "breder.h"

b_object_t* b_object_new(b_vm_t* vm, b_class_t*);
void* b_object_cache_methods(b_class_t* class) ;
void* b_object_cache_fields(b_class_t* class) ;
void b_object_free(b_object_t* self);

b_object_t* b_object_new_object(b_vm_t* self, int classindex);
b_object_t* b_object_new_number(b_vm_t* self, double number);
b_object_t* b_object_new_boolean(b_vm_t* self, int flag);
b_object_t* b_object_new_string(b_vm_t* self, const char* text);
b_object_t* b_object_new_string0(b_vm_t* self, const char* text, int len, int hash);

#define b_object_hash_field(o) ((b_hashclose_t*)(b_object_content(o)[1]))
#define b_object_hash_method(o) ((b_hashclose_t*)(b_object_content(o)[2]))
#define b_object_header(o) ((int*)o)
#define b_object_size() (b_object_size_noproxy() + sizeof(void*))
#define b_object_size_noproxy() (4 * sizeof(int) + 2 * sizeof(void*))
#define b_object_class(o) (b_object_header(o)[0])
#define b_object_fields(o) (b_object_header(o)[1])
#define b_object_used(o) (b_object_header(o)[2])
#define b_object_gc_used(o) (b_object_header(o)[3])
#define b_object_content(o) ((void**)(b_object_header(o)+4))
#define b_object_field(o,i) b_object_content(o)[(i)+1]
#define b_object_data(o) b_object_content(o)[0]
#define b_object_set_data(o,v) b_object_data(o) = v
#define b_object_data_typed(t,o) ((t*)b_object_data(o))

#endif
