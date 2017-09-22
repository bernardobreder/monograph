#include "b_bni_io.h"

b_bni_state_t breder_io_local_standard_LocalResource_LocalResource_breder_lang_standard_String(
		b_vm_t* vm, b_object_t* object) {
	b_bni_super_object(vm, object);
	b_bni_get_param_as_string(vm, opath, path, 0);
	b_bni_alloc_typed(vm, b_bni_io_resource_t, data);
	data->name = b_char_dup(path);
	b_bni_set_data_current(vm, object, data);
	b_bni_sret0(vm, 1);
}

b_bni_state_t breder_io_local_standard_LocalResource_getName(b_vm_t* vm,
		b_object_t* object, b_bni_io_resource_t* data) {
	char* aux = strrchr(data->name, '/');
	if (!aux) {
		b_bni_new_ostring_define(vm, oreturn, data->name);
		b_bni_ret1(vm, 0, oreturn);
	} else {
		char* name = aux + 1;
		b_bni_new_ostring_define(vm, oreturn, name);
		b_bni_ret1(vm, 0, oreturn);
	}
}

b_bni_state_t breder_io_local_standard_LocalResource_getAbsoluteName(b_vm_t* vm,
		b_object_t* object, b_bni_io_resource_t* data) {
	b_bni_new_ostring_define(vm, oreturn, data->name);
	b_bni_ret1(vm, 0, oreturn);
}

b_bni_state_t breder_io_local_standard_LocalResource_exist(b_vm_t* vm,
		b_object_t* object, b_bni_io_resource_t* data) {
	FILE* file = fopen(data->name, "r");
	b_bni_new_oboolean_define(vm, oreturn, file != NULL);
	fclose(file);
	b_bni_ret1(vm, 0, oreturn);
}
