#ifndef B_INDEX_H_
#define B_INDEX_H_

#include "breder.h"

b_class_id_t b_bni_class_boolean_id (b_vm_t* vm);
b_class_id_t b_bni_class_throw_id (b_vm_t* vm);
b_class_id_t b_bni_class_string_id (b_vm_t* vm);
b_class_id_t b_bni_class_number_id (b_vm_t* vm);
b_class_id_t b_bni_class_integer_id (b_vm_t* vm);
b_class_id_t b_bni_class_natural_id (b_vm_t* vm);
b_class_id_t b_bni_class_index_id (b_vm_t* vm);
b_class_id_t b_bni_class_class_id (b_vm_t* vm);
b_class_id_t b_bni_class_method_id (b_vm_t* vm);
b_class_id_t b_bni_class_ilist_id (b_vm_t* vm);
b_class_id_t b_bni_class_iframe_id (b_vm_t* vm);
b_class_id_t b_bni_class_iresource_id (b_vm_t* vm);
b_class_id_t b_bni_class_ifolder_id (b_vm_t* vm);
b_class_id_t b_bni_class_ifile_id (b_vm_t* vm);
b_class_id_t b_bni_class_resource_id (b_vm_t* vm);
b_class_id_t b_bni_class_folder_id (b_vm_t* vm);
b_class_id_t b_bni_class_file_id (b_vm_t* vm);
b_class_id_t b_bni_class_arraylist_id (b_vm_t* vm);
b_class_id_t b_bni_class_vector2d_id (b_vm_t* vm);
b_class_id_t b_bni_class_vector3d_id (b_vm_t* vm);
b_class_id_t b_bni_class_titleable_id (b_vm_t* vm);
b_class_id_t b_bni_class_sizeable_id (b_vm_t* vm);
b_class_id_t b_bni_class_opengl_id (b_vm_t* vm);
b_class_id_t b_bni_class_glframe_id (b_vm_t* vm);
b_class_id_t b_bni_class_object_id (b_vm_t* vm);
b_class_id_t b_bni_class_indexoutofboundsexception_id (b_vm_t* vm);
b_class_id_t b_bni_class_outofmemoryexception_id (b_vm_t* vm);
b_class_id_t b_bni_class_classnotfoundexception_id (b_vm_t* vm);
b_class_id_t b_bni_class_ioexception_id (b_vm_t* vm);
b_class_id_t b_bni_class_parseexception_id (b_vm_t* vm);

b_method_id_t b_bni_method_object_tostring_id (b_vm_t* vm);
b_method_id_t b_bni_method_object_hashcode_id (b_vm_t* vm);
b_method_id_t b_bni_method_object_finalize_id (b_vm_t* vm);
b_method_id_t b_bni_method_object_operatorequal_id (b_vm_t* vm);
b_method_id_t b_bni_method_indexoutofboundsruntimeexception_init_id (b_vm_t* vm);
b_method_id_t b_bni_method_outofmemoryruntimeexception_init_id (b_vm_t* vm);
b_method_id_t b_bni_method_classnotfoundexception_init_id (b_vm_t* vm);
b_method_id_t b_bni_method_parseexception_init_id (b_vm_t* vm);
b_method_id_t b_bni_method_ilist_size_id (b_vm_t* vm);
b_method_id_t b_bni_method_ilistadd_id (b_vm_t* vm);
b_method_id_t b_bni_method_glframe_reshape_id (b_vm_t* vm);
b_method_id_t b_bni_method_glframe_init_id (b_vm_t* vm);
b_method_id_t b_bni_method_glframe_keypress_id (b_vm_t* vm);
b_method_id_t b_bni_method_glframe_keyrelease_id (b_vm_t* vm);
b_method_id_t b_bni_method_glframe_paint_id (b_vm_t* vm);
b_method_id_t b_bni_method_glframe_fps_id (b_vm_t* vm);
b_method_id_t b_bni_method_sizeable_setsize_id (b_vm_t* vm);
b_method_id_t b_bni_method_sizeable_getwidth_id (b_vm_t* vm);
b_method_id_t b_bni_method_sizeable_getheight_id (b_vm_t* vm);
b_method_id_t b_bni_method_titleable_gettitle_id (b_vm_t* vm);
b_method_id_t b_bni_method_vector2d_init_id (b_vm_t* vm);
b_method_id_t b_bni_method_vector3d_init_id (b_vm_t* vm);
b_method_id_t b_bni_method_vector2d_getx_id (b_vm_t* vm);
b_method_id_t b_bni_method_vector2d_gety_id (b_vm_t* vm);
b_method_id_t b_bni_method_vector3d_getz_id (b_vm_t* vm);
b_method_id_t b_bni_method_vector3d_length_id (b_vm_t* vm);
b_method_id_t b_bni_method_iframe_paint_id (b_vm_t* vm);
b_method_id_t b_bni_method_ioexception_init_id (b_vm_t* vm);
b_method_id_t b_bni_method_arraylist_init_id (b_vm_t* vm);
b_method_id_t b_bni_method_iresource_getname_id (b_vm_t* vm);
b_method_id_t b_bni_method_resource_getname_id (b_vm_t* vm);
b_method_id_t b_bni_method_resource_getabsolutename_id (b_vm_t* vm);
b_method_id_t b_bni_method_ilist_add_id (b_vm_t* vm);

#endif
