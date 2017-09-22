#include "breder.h"

#ifdef __MOBILE__

typedef struct b_mobile_file b_mobile_file;

struct b_mobile_file {
	char* progmem;
	int offset;
	int len;
};

void* b_so_dynamic_load(const char* dir, const char* pathname) {
	return (void*) pathname;
}

void b_so_dynamic_free(void* library) {
}

void* b_so_dynamic_get(void* library, char* method) {
	return b_so_func_get(method);
}

void* b_file_open_read(void* data) {
	b_mobile_file* self = b_memory_alloc_typed(b_mobile_file, 1);
	self->progmem = (char*) data;
	self->offset = 0;
	return self;
}

void b_file_close(void* file) {
}

void b_file_set_size(void* file, unsigned int buffersize) {
	b_mobile_file* self = (b_mobile_file*) file;
	self->len = buffersize;
}

int b_file_error(void* file) {
	return 0;
}

int b_file_eof(void* file) {
	b_mobile_file* data = (b_mobile_file*) file;
	return data->offset >= data->len;
}

int b_file_read(void* file) {
	if (b_file_eof(file)) {
		return -1;
	}
	b_mobile_file* data = (b_mobile_file*) file;
	char c = *data->progmem;
	data->progmem += 1;
	data->offset += 1;
	return c;
}

b_state_t b_file_scan0(void* file, const char* format) {
	b_mobile_file* data = (b_mobile_file*) file;
	int r = sscanf(data->progmem, format);
	int len = b_char_size(format);
	data->progmem += len;
	data->offset += len;
	return r;
}

b_state_t b_file_scan1(void* file, const char* format, void* data1) {
	b_mobile_file* data = (b_mobile_file*) file;
	char str[1024];
	int r = sscanf(data->progmem, format, data1);
	if (r == 1) {
		sprintf(str, format, *((int*)data1));
		int len = b_char_size(str);
		data->progmem += len;
		data->offset += len;
	}
	return r;
}

b_state_t b_file_scan2(void* file, const char* format, void* data1, void* data2) {
	b_mobile_file* data = (b_mobile_file*) file;
	char str[1024];
	int r = sscanf(data->progmem, format, data1, data2);
	if (r == 2) {
		sprintf(str, format, *((int*)data1), *((int*)data2));
		int len = b_char_size(str);
		data->progmem += len;
		data->offset += len;
	}
	return r;
}

b_state_t b_file_scan3(void* file, const char* format, void* data1, void* data2, void* data3) {
	b_mobile_file* data = (b_mobile_file*) file;
	char str[1024];
	int r = sscanf(data->progmem, format, data1, data2, data3);
	if (r == 3) {
		sprintf(str, format, *((int*)data1), *((int*)data2), *((int*)data3));
		int len = b_char_size(str);
		data->progmem += len;
		data->offset += len;
	}
	return r;
}

int b_file_read_buffer(void* file, void* d, int buffersize) {
	b_mobile_file* data = (b_mobile_file*) file;
	memcpy(d, data->progmem, buffersize);
	data->progmem += buffersize;
	data->offset += buffersize;
	return 0;
}

#endif
