#include "breder.h"

b_ints_t* b_aint_new (int len) {
	int* self = (int*)b_memory_alloc_typed(int, len + 1);
	* self = len;
	return self;
}

b_number_t b_number_scanf (const char* chars) {
	b_number_t value = 0;
	int n, dotindex = strchr (chars, '.') - chars;
	if (dotindex == - 1) {
		dotindex = strlen (chars);
	}
	char* aux = (char*)chars + (dotindex - 1);
	for (n = 0; n < dotindex; n ++ ) {
		value += ( * aux - '0') * pow (10, n);
		aux -- ;
	}
	aux = (char*)chars + (dotindex + 1);
	n = - 1;
	while ( * aux) {
		b_number_t delta = pow (10, - 1);
		value += ( ( * aux - '0') * pow (10, n));
		n -- ;
		aux ++ ;
	}
	return value;
}
