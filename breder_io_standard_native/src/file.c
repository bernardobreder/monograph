#include "b_bni_io.h"

b_bni_state_t breder_io_local_standard_LocalFile_LocalFile_breder_lang_standard_String(b_vm_t* vm, b_object_t* object) {
	b_bni_get_param_as_string(vm, opath, path, 0);
	b_bni_super_1_param(vm, object, b_bni_method_resource_init_id(vm), opath);
	b_bni_alloc_typed(vm, b_bni_io_file_t, data);
	b_bni_set_data_current(vm, object, data);
	b_bni_sret0(vm, 1);
}
