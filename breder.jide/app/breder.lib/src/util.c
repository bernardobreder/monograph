#include "breder.h"

int* b_pint(int value) {
	int* v = malloc(sizeof(int));
	*v = value;
	return v;
}
