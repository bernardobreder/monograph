#ifndef B_BNI_IO_H_
#define B_BNI_IO_H_

#include <dirent.h>
#include "b_bni.h"

typedef struct b_bni_io_file_t b_bni_io_file_t;

typedef struct b_bni_io_dir_t b_bni_io_dir_t;

typedef struct b_bni_io_resource_t b_bni_io_resource_t;

struct b_bni_io_resource_t {
	int struct_type;
	const char* name;
};

struct b_bni_io_file_t {
	int struct_type;
	b_bni_io_resource_t resource;
	FILE* file;
};

struct b_bni_io_dir_t {
	int struct_type;
	b_bni_io_resource_t resource;
};

b_method_id_t b_bni_io_resource_getname_index(b_vm_t* vm);
b_method_id_t b_bni_io_folder_constructor_index(b_vm_t* vm);
b_method_id_t b_bni_io_file_constructor_index(b_vm_t* vm);
b_method_id_t b_bni_io_resource_constructor_index(b_vm_t* vm);
b_method_id_t b_bni_util_list_constructor_index(b_vm_t* vm);
b_method_id_t b_bni_util_list_add_index(b_vm_t* vm);
b_class_id_t b_bni_util_list_class_index(b_vm_t* vm);
b_class_id_t b_bni_io_dir_class_index(b_vm_t* vm);
b_class_id_t b_bni_io_res_class_index(b_vm_t* vm);
b_class_id_t b_bni_io_file_class_index(b_vm_t* vm);

b_bni_io_dir_t* b_bni_io_dir_new(const char* path);

#endif /* B_BNI_O_H_ */
