#include "b_net.h"

static char* msg = 0;

b_bni_net_state_t b_net_error_has() {
	if (msg) {
		return B_BNI_NET_ERROR;
	} else {
		return B_BNI_NET_SUCESS;
	}
}

char* b_net_error_get() {
	return msg;
}

void b_bni_net_error_set(char* text, int copy) {
	if (msg) {
		free(msg);
	}
	if (copy) {
		msg = strdup(text);
	} else {
		msg = text;
	}
}
