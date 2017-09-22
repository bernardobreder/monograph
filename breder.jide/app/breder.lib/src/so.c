#include "breder.h"

#ifdef WIN32

#include <io.h>
#include <dirent.h>
#include <windows.h>

#define MethodLibrary(Func,Name) GetProcAddress(Func,Name)

const char* ErrorLibrary () {
	LPVOID lpMsgBuf;
	FormatMessage ( FORMAT_MESSAGE_ALLOCATE_BUFFER | FORMAT_MESSAGE_FROM_SYSTEM | FORMAT_MESSAGE_IGNORE_INSERTS , NULL , GetLastError () , 0 , // Default language
			( LPTSTR ) & lpMsgBuf , 0 , NULL );
	return ( const char* ) lpMsgBuf;
}

void b_so_out_flush () {
	_flushall ();
}

#else
#include <dlfcn.h>
#define LoadLibrary(Path) dlopen(Path,RTLD_NOW)
#define ErrorLibrary() dlerror()
#define FreeLibrary(Func) dlclose(Func)
#define MethodLibrary(Func,Name) dlsym(Func,Name)

#include <stdlib.h>
#include <assert.h>
#include <sys/types.h>
#include <dirent.h>
#include <stdio.h>

void b_so_out_flush() {
	fflush(stdout);
	fflush(stderr);
	fflush(stdin);
}

#endif

b_array_char_t* b_so_dir(const char* directory) {
	b_array_char_t* array = 0;
	DIR* dir = opendir(directory);
	if (dir) {
		array = b_arrayp_new ( );
		struct dirent* dp;
		while ((dp = readdir(dir))) {
			if (strcmp(dp->d_name, ".") && strcmp(dp->d_name, "..")) {
				b_arrayp_add( array , b_char_dup( dp->d_name ) );
			}
		}
	}
	closedir(dir);
	return array;
}

void* b_so_dynamic_load(char* pathname) {
	char* path = calloc(strlen(pathname) + strlen(SO_EXTENSION) + 1, sizeof(char));
	strcat ( path , pathname );
	strcat ( path , SO_EXTENSION );
	void* library = LoadLibrary ( path );
	free(path);
	return library;
}

void b_so_exit(int state) {
	exit(state);
}

void b_so_dynamic_free(void* library) {
	FreeLibrary ( library );
}

void* b_so_dynamic_get(void* library, char* method) {
	return MethodLibrary( library, method );
}

#ifdef MOBILE

void* b_file_open_read(void* data) {
	void** self = malloc(sizeof(void*));
	*self = data;
	return self;
}

void b_file_close(void* file) {
	free(file);
}

int b_file_error(void* file) {
	return 0;
}

int b_file_eof(void* file) {
	char** chars = file;
	return (*chars)[1] == 0;
}

int b_file_read(void* file) {
	char** chars = file;
	char c = **chars;
	*chars += 1;
	return c;
}

int b_file_scan0(void* file, const char* format) {
	char** chars = file;
	return scanf(*chars, format);
}

int b_file_scan1(void* file, const char* format, void* data1) {
	char** chars = file;
	return scanf(*chars, format, data1);
}

int b_file_scan2(void* file, const char* format, void* data1, void* data2) {
	char** chars = file;
	return scanf(*chars, format, data1, data2);
}

int b_file_read_buffer(void* file, void* data, int size) {
	char** chars = file;
	memcpy(data,*chars,size);
	return 0;
}

#else

void* b_file_open_read(void* data) {
	return fopen(data, "rb");
}

void b_file_close(void* file) {
	fclose(file);
}

int b_file_eof(void* file) {
	return feof((FILE*)file);
}

int b_file_error(void* file) {
	return ferror((FILE*)file);
}

int b_file_read(void* file) {
	return fgetc(file);
}

int b_file_scan0(void* file, const char* format) {
	return fscanf(file, format);
}

int b_file_scan1(void* file, const char* format, void* data1) {
	return fscanf(file, format, data1);
}

int b_file_scan2(void* file, const char* format, void* data1, void* data2) {
	return fscanf(file, format, data1, data2);
}

int b_file_read_buffer(void* file, void* data, int size) {
	return fread(data, 1, size, file);
}

#endif
