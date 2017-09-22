#ifndef B_PGSQL_H_
#define B_PGSQL_H_

typedef char b_pgsql_state_t;

#include "b_pgsql_error.h"
#include "b_pgsql_connection.h"

void b_pgsql_init();

void b_pgsql_close();

#endif
