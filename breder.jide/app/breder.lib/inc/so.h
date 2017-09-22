#ifndef B_SO_H
#define B_SO_H

#include "breder.h"

void b_so_exit(int state);

void* b_file_open_read(void* data);

void b_file_close(void* file);

int b_file_eof(void* file);

int b_file_error(void* file);

int b_file_read(void* file);

int b_file_scan0(void* file, const char* format);

int b_file_scan1(void* file, const char* format, void* data1);

int b_file_scan2(void* file, const char* format, void* data1, void* data2);

int b_file_read_buffer(void* file, void* data, int size);

void* b_so_dynamic_load(char* pathname);

void b_so_dynamic_free(void* library);

void* b_so_dynamic_get(void* library, char* method);

b_array_char_t* b_so_dir(const char* directory);

void b_so_out_flush();

#define b_call_function( Return , Function , ParamBetweenParentese ) \
	( ( Return (*) ParamBetweenParentese ) Function )

#endif
