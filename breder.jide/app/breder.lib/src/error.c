#include "breder.h"

static char* msg = 0;
static int copy = 0;

int b_error_has() {
	return msg != 0;
}

int b_error_put(char* m) {
	b_error_put1(m, 1);
	return 0;
}

void b_error_put1(char* m, int cpy) {
	msg = cpy ? b_char_dup(m) : m;
	copy = cpy;
}

char* b_error_get() {
	char* r = msg;
	msg = 0;
	return r;
}

int b_error_show() {
	char* msg = b_error_get();
	printf("%s\n", msg);
	b_char_free(msg);
	return 1;
}
