#ifndef B_NET_ERROR_H_
#define B_NET_ERROR_H_

#define B_BNI_NET_SUCESS 0
#define B_BNI_NET_ERROR 1

#include "b_net.h"

/**
 * Método que indica se houve ou não error em algum processo da library net.
 */
b_bni_net_state_t b_net_error_has();

/**
 * Método para receber o texto que representa o error.
 */
char* b_net_error_get();

#endif
