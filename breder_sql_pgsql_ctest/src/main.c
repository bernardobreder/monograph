#include "b_pgsql.h"

int main(int argc, char **argv) {
	b_pgsql_init();
	b_pgsql_connection_t* conn = b_pgsql_connection_new("localhost","5432", "breder","postgres","24813612");
	b_pgsql_connection_close(conn);
	b_pgsql_close();
	return 0;
}
