#include "b_bni_io.h"

typedef struct b_foutput_t b_foutput_t;

struct b_foutput_t {
	FILE* file;
};

b_bni_state_t breder_io_local_standard_LocalFileOutputStream_LocalFileOutputStream_breder_io_local_standard_LocalFile (b_vm_t* vm, b_object_t* object) {
	b_bni_get_param_notnull (vm, ofile, 0);
	b_bni_super_object (vm, object);
	b_object_t* opath;
	if (b_bni_execute_1_return_0_param (vm, ofile, & opath, b_bni_method_resource_getabsolutename_id (vm)) == B_BNI_FAIL) {
		return B_BNI_FAIL;
	}
	b_bni_ostring_to_text_define (vm, path, opath);
	FILE* file = fopen (path, "wb");
	if ( ! file) {
		b_bni_throw_ioexception (vm);
	}
	b_foutput_t* data = b_memory_alloc0 (sizeof(b_foutput_t));
	if ( ! data) {
		fclose (file);
		b_bni_throw_out_of_memory_runtime_exception (vm);
	}
	data->file = file;
	b_bni_set_data_current (vm, object, data);
	b_bni_sret0 (vm, 1);
}

b_bni_state_t breder_io_local_standard_LocalFileOutputStream_write_breder_lang_standard_String (b_vm_t* vm, b_object_t* object, b_foutput_t* data) {
	b_bni_get_param_as_string (vm, ocontent, content, 0);
	if ( ! data->file) {
		b_bni_throw_ioexception (vm);
	}
	int length = strlen (content);
	if (length > 0) {
		char* aux = content;
		while (length -- ) {
			if (fputc ( * aux, data->file) != * aux) {
				b_bni_throw_ioexception (vm);
			}
			aux ++ ;
		}
	}
	b_bni_ret0 (vm, 1);
}

b_bni_state_t breder_io_local_standard_LocalFileOutputStream_flush (b_vm_t* vm, b_object_t* object, b_foutput_t* data) {
	if (data->file) {
		fflush (data->file);
	}
	b_bni_ret0 (vm, 0);
}

b_bni_state_t breder_io_local_standard_LocalFileOutputStream_close (b_vm_t* vm, b_object_t* object, b_foutput_t* data) {
	if (data->file) {
		fclose (data->file);
	}
	free (data);
	b_bni_ret0 (vm, 0);
}
