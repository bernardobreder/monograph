#ifndef B_PGSQL_ERROR_H_
#define B_PGSQL_ERROR_H_

#include "b_pgsql.h"

#define B_PGSQL_SUCESS 0
#define B_PGSQL_ERROR 1

/**
 * Método que indica se houve ou não error em algum processo da library net.
 */
b_pgsql_state_t b_pgsql_error_has();

/**
 * Método para receber o texto que representa o error.
 */
char* b_pgsql_error_get();

#endif
