#ifndef B_CONFIG_UNIX_H
#define B_CONFIG_UNIX_H

#include "breder.h"

#ifdef __WINDOWS__

#include <windows.h>
#include <stdlib.h>
#include <assert.h>
#include <sys/types.h>
#include <dirent.h>
#include <stdio.h>

#define MAXSTRING 1024

void* b_so_library_load(const char* path) {
	return LoadLibrary(path);
}

void* b_so_library_get(void* library,const char* name) {
	return GetProcAddress(library, name);
}

void b_so_library_free(void* library) {
	FreeLibrary(library);
}

const char* b_so_library_error() {
	LPVOID lpMsgBuf;
	FormatMessage(FORMAT_MESSAGE_ALLOCATE_BUFFER | FORMAT_MESSAGE_FROM_SYSTEM | FORMAT_MESSAGE_IGNORE_INSERTS, NULL, GetLastError(), 0, // Default language
			(LPTSTR) & lpMsgBuf, 0, NULL);
	return (const char*) lpMsgBuf;
}

void b_so_out_flush() {
	_flushall();
}

int b_so_dir_list(b_vm_t* vm, const char* name, b_array_t* dirs, b_array_t* files) {
	HANDLE hList;
	TCHAR szDir[MAXSTRING];
	WIN32_FIND_DATA FileData;
	snprintf(szDir, MAXSTRING, "%s/*", name);
	hList = FindFirstFile(szDir, &FileData);
	if (hList != INVALID_HANDLE_VALUE) {
		do {
			if (b_char_compare(FileData.cFileName, ".") && b_char_compare(FileData.cFileName, "..")) {
				b_bni_new_ostring_define(vm, opath, FileData.cFileName);
				if (FileData.dwFileAttributes & FILE_ATTRIBUTE_DIRECTORY) {
					b_array_add(dirs, b_char_dup(FileData.cFileName));
				} else {
					b_array_add(files, b_char_dup(FileData.cFileName));
				}
			}
		}while (FindNextFile(hList, &FileData));
		FindClose(hList);
	}
	return B_STATE_SUCCESS;
}

#endif

#endif
