#include <stdlib.h>

#include "b_pgsql.h"
#include "libpq-fe.h"

b_pgsql_connection_t*
b_pgsql_connection_new(const char* host, const char* port, const char* database, const char* username, const char* password) {
	PGconn *conn = PQsetdbLogin(host, port, "", "", database, username, password);
	if (PQstatus(conn) != CONNECTION_OK) {
		b_pgsql_error_set(PQerrorMessage(conn), 1);
		return 0;
	}
	b_pgsql_connection_t* c = (b_pgsql_connection_t*) calloc(1, sizeof(b_pgsql_connection_t));
	if (!c) {
		b_pgsql_error_set("out of memory", 1);
		return 0;
	}
	c->connection = conn;
	return c;
}

void b_pgsql_connection_close(b_pgsql_connection_t* c) {
	PQfinish(c->connection);
}

