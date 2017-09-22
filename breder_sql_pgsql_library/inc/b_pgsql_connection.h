#ifndef B_PGSQL_CONNECTION_H_
#define B_PGSQL_CONNECTION_H_

#include "libpq-fe.h"

typedef struct b_pgsql_connection_t b_pgsql_connection_t;

struct b_pgsql_connection_t {
	PGconn * connection;
};

b_pgsql_connection_t
		*
		b_pgsql_connection_new(const char* host, const char* port, const char* database, const char* username, const char* password);

void b_pgsql_connection_close(b_pgsql_connection_t*);

#endif
