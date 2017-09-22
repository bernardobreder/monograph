#include "b_bni_io.h"

b_bni_io_dir_t* b_bni_io_dir_new (const char* path) {
	b_bni_io_dir_t* data = b_memory_alloc_typed (b_bni_io_dir_t, 1);
	return data;
}

b_bni_state_t breder_io_local_standard_LocalFolder_LocalFolder_breder_lang_standard_String (b_vm_t* vm, b_object_t* object) {
	b_bni_get_param_as_string (vm, opath, path, 0);
	b_bni_super_1_param (vm, object, b_bni_method_resource_init_id (vm), opath);
	b_bni_alloc_typed (vm, b_bni_io_dir_t, data);
	b_bni_set_data_current (vm, object, data);
	b_bni_sret0 (vm, 1);
}

b_bni_state_t breder_io_local_standard_LocalFolder_list (b_vm_t* vm, b_object_t* object, b_bni_io_dir_t* data) {
	b_bni_new_0_param (vm, olist, b_bni_method_arraylist_init_id (vm));
	b_object_t* oname;
	if (b_bni_execute_1_return_0_param (vm, object, & oname, b_bni_method_iresource_getabsolutename_id (vm)) == B_BNI_FAIL) {
		return B_BNI_FAIL;
	}
	const char* name = b_bni_ostring_to_text (vm, oname);
	b_array_t* dirs = b_array_new ();
	b_array_t* files = b_array_new ();
	b_so_dir_list (vm, name, dirs, files);
	int n, size = b_array_size (dirs);
	for (n = 0; n < size; n ++ ) {
		b_bni_new_ostring_define(vm, opath, b_array_get_typed(char, dirs, n));
		b_bni_new_1_param (vm, ofolder, b_bni_method_folder_init_id (vm), opath);
		b_bni_execute_0_return_1_param (vm, olist, b_bni_method_ilist_add_id (vm), ofolder);
	}
	size = b_array_size (files);
	for (n = 0; n < size; n ++ ) {
		b_bni_new_ostring_define(vm, opath, b_array_get_typed(char, files, n));
		b_bni_new_1_param (vm, ofile, b_bni_method_file_init_id (vm), opath);
		b_bni_execute_0_return_1_param (vm, olist, b_bni_method_ilist_add_id (vm), ofile);
	}
	b_array_free1 (dirs, free);
	b_array_free1 (files, free);
	b_bni_ret1 (vm, 0, olist);
}
