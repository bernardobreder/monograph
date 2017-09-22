#include "breder.h"

const char* b_so_dynamic_error () {
	return b_so_library_error ();
}

int b_so_file_exist (const char* filename) {
	FILE* file = fopen (filename, "rb");
	int result = file != NULL;
	if (file) {
		fclose (file);
	}
	return result;
}

void* b_so_dynamic_load (const char* dir, const char* pathname) {
	char* path = b_char_new4 (dir, SO_FILE_SEPARATOR, pathname, SO_EXTENSION);
	void* library = 0;
	if (b_so_file_exist (path)) {
		library = b_so_library_load (path);
		if ( ! library) {
			printf ("Cannot open the library '%s' : \n\t%s\n", path, b_so_library_error ());
			exit (0);
		}
	}
	b_memory_free (path);
	return library;
}

void b_so_dynamic_free (void* library) {
	b_so_library_free (library);
}

void* b_so_dynamic_get (void* library, char* method) {
	return b_so_library_get (library, method);
}

void* b_file_open_read (void* data) {
	return fopen (data, "rb");
}

void b_file_set_size (void* file, unsigned int size) {
}

void b_file_close (void* file) {
	fclose (file);
}

int b_file_eof (void* file) {
	return feof ((FILE*)file);
}

int b_file_error (void* file) {
	return ferror ((FILE*)file);
}

int b_file_read (void* file) {
	int n = fgetc (file);
	char c = (char)n;
	return n;
}

b_state_t b_file_scan0 (void* file, const char* format) {
	int n, len = b_char_size (format);
	for (n = 0; n < len; n ++ ) {
		if (format[n] != fgetc (file)) {
			return 0;
		}
	}
	return 1;
}

b_state_t b_file_scan1 (void* file, const char* format, void* data1) {
	return fscanf (file, format, data1) == 1;
}

b_state_t b_file_scan2 (void* file, const char* format, void* data1, void* data2) {
	return fscanf (file, format, data1, data2) == 2;
}

b_state_t b_file_scan3 (void* file, const char* format, void* data1, void* data2, void* data3) {
	return fscanf (file, format, data1, data2, data3) == 3;
}

int b_file_read_buffer (void* file, void* data, int size) {
	return fread (data, 1, size, file) == size;
}
