#ifndef B_BREDERC_H_
#define B_BREDERC_H_

#include "b_typedef.h"
#include "b_define.h"
#include "b_platform.h"
#include "b_config.h"

#if defined(__WIN32__)
#include <io.h>
#include <dir.h>
#endif

#include <iso646.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <stdarg.h>
#include <assert.h>
#include <limits.h>
#include <math.h>

#define public
#define private static

#include "b_so.h"
#include "b_main.h"
#include "b_util.h"
#include "b_memory.h"
#include "b_array.h"
#include "b_linker.h"
#include "b_char.h"
#include "b_error.h"
#include "b_opcode.h"
#include "b_log.h"
#include "b_math.h"
#include "b_bni.h"
#include "b_index.h"

#ifdef DEBUG_APP
#include "b_debug.h"
#endif
#include "b_vm.h"
#include "b_thread.h"
#include "b_gc.h"
#include "b_class.h"
#include "b_method.h"
#include "b_field.h"
#include "b_object.h"
#include "b_throw.h"

#endif
