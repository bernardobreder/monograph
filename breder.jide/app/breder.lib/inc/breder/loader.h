#ifndef BC_LOADER_H_
#define BC_LOADE_H_

#include "breder.h"

struct b_loader_t {
	int structType ;
	__inst* buffer ;
	void** stack ;
	int bufferSize ;
} ;

#endif
