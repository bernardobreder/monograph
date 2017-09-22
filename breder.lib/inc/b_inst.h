#ifndef B_VM_H_
#define B_VM_H_

#include "breder.h"

struct b_inst_t {
	b_byte_t opcode;
	__inst data1;
	__inst data2;
	b_class_id_t cindex;
	short line;
};

b_inst_t* b_inst_new();

void b_inst_free(b_inst_t* self);

#endif
