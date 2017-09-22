#ifndef B_BREDERC_H_
#define B_BREDERC_H_

#ifdef WIN32
#include <io.h>
#include <dir.h>
#define SO_EXTENSION ".dll"
#define SO_FILE_SEPARATOR "\\"
#else
#define SO_EXTENSION ".so"
#define SO_FILE_SEPARATOR "/"
#endif

#include <iso646.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <stdarg.h>
#include <assert.h>
#include <limits.h>
#include <pthread.h>
#define public
#define private static

#include "typedef.h"
#include "define.h"

#include "util.h"
#include "memory.h"
#include "arrayp.h"
#include "linker.h"
#include "char.h"
#include "error.h"
#include "so.h"
#include "hash.h"
#include "vector.h"
#include "opcode.h"
#include "main.h"
#include "bni.h"

#include "breder/vm.h"
#include "breder/thread.h"
#include "breder/gc.h"
#include "breder/loader.h"
#include "breder/class.h"
#include "breder/method.h"
#include "breder/field.h"
#include "breder/object.h"
#include "breder/throw.h"

#endif
