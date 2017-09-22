#ifdef DEBUG_APP

#ifndef B_DEBUG_H_
#define B_DEBUG_H_

#include "breder.h"

struct b_debug_t {
	b_boolean_t inited;
	b_boolean_t running;
};

b_debug_t* b_debug_new();

b_boolean_t b_debug_not_running(b_vm_t* vm);

void b_debug_ask(b_vm_t* vm);

void b_debug_print(b_vm_t* vm);

#endif

#endif
