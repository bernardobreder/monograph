#include "b_bni_io.h"

typedef struct b_finput_t b_finput_t;

struct b_finput_t {
	FILE* file;
};

b_bni_state_t breder_io_local_standard_LocalFileInputStream_LocalFileInputStream_breder_io_local_standard_LocalFile (b_vm_t* vm, b_object_t* object) {
	b_bni_get_param_notnull (vm, ofile, 0);
	b_bni_super_object (vm, object);
	b_object_t* opath;
	if (b_bni_execute_1_return_0_param (vm, ofile, & opath, b_bni_method_resource_getabsolutename_id (vm)) == B_BNI_FAIL) {
		return B_BNI_FAIL;
	}
	b_bni_ostring_to_text_define (vm, path, opath);
	FILE* file = fopen (path, "rb");
	if ( ! file) {
		b_bni_throw_ioexception (vm);
	}
	b_finput_t* data = b_memory_alloc0 (sizeof(b_finput_t));
	if ( ! data) {
		fclose (file);
		b_bni_throw_out_of_memory_runtime_exception (vm);
	}
	data->file = file;
	b_bni_set_data_current (vm, object, data);
	b_bni_sret0 (vm, 1);
}

b_bni_state_t breder_io_local_standard_LocalFileInputStream_read_breder_lang_standard_Natural (b_vm_t* vm, b_object_t* object, b_finput_t* data) {
	b_bni_get_param_as_natural (vm, olength, length, 0);
	if ( ! data->file) {
		b_bni_throw_ioexception (vm);
	}
	if (length > 0) {
		char* chars = b_memory_alloc0 ( (length + 1) * sizeof(char));
		if ( ! chars) {
			b_bni_throw_out_of_memory_runtime_exception (vm);
		}
		char* aux = chars;
		while (length -- ) {
			int n = fgetc (data->file);
			if (n < 0) {
				* aux = 0;
				break;
			}
			char c = (char)n;
			* aux ++ = c;
		}
		b_object_t* oresult = b_bni_new_ostring (vm, chars);
		free (chars);
		if ( ! oresult) {
			b_bni_throw_out_of_memory_runtime_exception (vm);
		}
		b_bni_ret1 (vm, 1, oresult);
	} else {
		b_bni_new_ostring_define (vm, oresult, "");
		b_bni_ret1 (vm, 1, oresult);
	}
}

b_bni_state_t breder_io_local_standard_LocalFileInputStream_close (b_vm_t* vm, b_object_t* object, b_finput_t* data) {
	if (data->file) {
		fclose (data->file);
	}
	data->file = 0;
	b_bni_ret0 (vm, 0);
}

b_bni_state_t breder_io_local_standard_LocalFileInputStream_finalize (b_vm_t* vm, b_object_t* object, b_finput_t* data) {
	if (data->file) {
		fclose (data->file);
	}
	free (data);
	b_bni_ret0 (vm, 0);
}
