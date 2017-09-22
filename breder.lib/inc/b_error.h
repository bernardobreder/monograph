#ifndef B_ERRO_H_
#define B_ERRO_H_

#include "breder.h"

void b_error_put1(char*, int copy);
int b_error_put(char *fmt, ...);
int b_error_has();
/*
 * Retorna como parametro de saída o código do erro e a menssagem.
 * O erro é limpado automaticamente.
 * Exemplo :
 * 		int code;
 * 		const char* msg = Error$get( &code );
 */
char* b_error_get();

int b_error_show();

#endif /* ERRO_H_ */
