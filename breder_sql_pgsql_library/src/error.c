#include "b_pgsql.h"

static char* msg = 0;

b_pgsql_state_t b_pgsql_error_has() {
	if (msg) {
		return B_PGSQL_ERROR;
	} else {
		return B_PGSQL_SUCESS;
	}
}

char* b_pgsql_error_get() {
	return msg;
}

void b_pgsql_error_set(char* text, int copy) {
	if (msg) {
		free(msg);
	}
	if (copy) {
		msg = strdup(text);
	} else {
		msg = text;
	}
}
