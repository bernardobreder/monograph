#include "breder.h"

int b_file_read_int (void* file) {
	int number;
	if (b_file_scan1 (file, "%d|", & number)) {
		return number;
	} else {
		return 0;
	}
}

char* b_file_read_chars (void* file) {
	int length;
	if ( ! b_file_scan1 (file, "%d|", & length)) {
		return 0;
	}
	char* chars = b_memory_alloc_typed(char, length + 1);
	if ( ! chars) {
		return 0;
	}
	if ( ! b_file_read_buffer (file, chars, length + 1)) {
		b_memory_free (chars);
		return 0;
	}
	chars[length] = 0;
	return chars;
}

b_ints_t* b_file_read_aint (void* file) {
	int n, length, number;
	if ( ! b_file_scan1 (file, "%d|", & length)) {
		return 0;
	}
	b_ints_t* array = b_aint_new (length);
	for (n = 0; n < length; n ++ ) {
		if ( ! b_file_scan1 (file, "%d|", & number)) {
			b_memory_free (array);
			return 0;
		}
		b_aint_set(array, n, number);
	}
	return array;
}

void b_so_exit (int state) {
	exit (state);
}

char* b_file_read_string (void* file) {
	int len;
	b_file_scan1 (file, "(%d,", & len);
	char* chars = b_memory_alloc_typed (char, len + 1);
	if ( ! chars) {
		return 0;
	}
	b_file_read_buffer (file, chars, len);
	b_file_read (file);
	return chars;
}

int* b_file_read_ints (void* file, int* size) {
	int len;
	b_file_scan1 (file, "(%d,", & len);
	if (size) {
		* size = len;
	}
	if (len == 0) {
		b_file_read (file);
		return b_memory_alloc_typed(int, 0);
	}
	int* result = b_memory_alloc_typed(int, len);
	int n, value;
	for (n = 0; n < len; n ++ ) {
		b_file_scan1 (file, "%d,", & value);
		result[n] = value;
	}
	b_file_read (file);
	return result;
}

b_ints_t* b_file_read_aints (void* file) {
	int len;
	b_file_scan1 (file, "(%d,", & len);
	if (len == 0) {
		b_file_read (file);
		return b_aint_new (0);
	}
	b_ints_t* result = b_aint_new (len);
	int n, value;
	for (n = 0; n < len; n ++ ) {
		b_file_scan1 (file, "%d,", & value);
		b_aint_set(result, n, value);
	}
	b_file_read (file);
	return result;
}
