#ifndef B_CONFIG_UNIX_H
#define B_CONFIG_UNIX_H

#include "breder.h"

#if defined(__UNIX__)

#include <dlfcn.h>
#include <stdlib.h>
#include <assert.h>
#include <sys/types.h>
#include <dirent.h>
#include <stdio.h>

void* b_so_library_load (const char* path) {
	return dlopen (path, RTLD_NOW);
}

const char* b_so_library_error () {
	return dlerror ();
}

void b_so_library_free (void* func) {
	dlclose (func);
}

void* b_so_library_get (void* library, const char* name) {
	return dlsym (library, name);
}

void b_so_out_flush () {
	fflush (stdout);
	fflush (stderr);
	fflush (stdin);
}

int b_so_dir_list (b_vm_t* vm, const char* name, b_array_t* dirs, b_array_t* files) {
	DIR* dir = opendir (name);
	if ( ! dir) {
		struct dirent *dp;
		while ( (dp = readdir (dir))) {
			if (b_char_compare(dp->d_name, ".") && b_char_compare(dp->d_name, "..")) {
				if (dp->d_type == DT_DIR) {
					b_array_add (dirs, b_char_dup (dp->d_name));
				} else if (dp->d_type == DT_REG) {
					b_array_add (files, b_char_dup (dp->d_name));
				}
			}
			closedir (dir);
		}
	}
	return B_STATE_SUCCESS;
}

#endif

#endif
