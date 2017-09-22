#ifndef B_SO_H_
#define B_SO_H_

#include "breder.h"

int b_file_read_int (void* file);

char* b_file_read_chars (void* file);

b_ints_t* b_file_read_aint (void* file);

void b_so_exit (int state);

void* b_file_open_read (void* data);

void b_file_set_size (void* file, unsigned int size);

void b_file_close (void* file);

int b_file_eof (void* file);

int b_file_error (void* file);

int b_file_read (void* file);

b_state_t b_file_scan0 (void* file, const char* format);

b_state_t b_file_scan1 (void* file, const char* format, void* data1);

b_state_t b_file_scan2 (void* file, const char* format, void* data1, void* data2);

b_state_t b_file_scan3 (void* file, const char* format, void* data1, void* data2, void* data3);

int b_file_read_buffer (void* file, void* data, int size);

char* b_file_read_string (void* file);

int* b_file_read_ints (void* file, int* size);

b_ints_t* b_file_read_aints (void* file);

void* b_so_dynamic_load (const char* dir, const char* pathname);

const char* b_so_dynamic_error ();

void b_so_dynamic_free (void* library);

void* b_so_dynamic_get (void* library, char* method);

const char* b_so_library_error ();

void b_so_library_free (void* func);

void* b_so_library_get (void* library, const char* name);

void* b_so_library_load (const char* path);

int b_so_dir_list (b_vm_t* vm, const char* name, b_array_t* dirs, b_array_t* files);

int b_so_file_exist (const char* filename);

void b_so_out_flush ();

#define b_call_function( Return , Function , ParamBetweenParentese ) \
	( ( Return (*) ParamBetweenParentese ) Function )

#endif
