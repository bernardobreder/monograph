<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p>
Nesse t�pico iremos mostrar como foi criado a biblioteca do pacote <code>breder.net.standard</code>.
Primeiro iremos criar um projeto C Ansi para criar uma biblioteca :
</p>

<p>
Header : b_net.h
</p>
<dl><dt>
<pre>
#ifndef B_NET_H_
#define B_NET_H_

#include "b_tcp.h"

#endif
</pre></dt></dl>

<p>
Header : b_tcp.h
</p>
<dl><dt>
<pre>
#ifndef B_NET_TCP_H_
#define B_NET_TCP_H_

#define B_BNI_NET_SUCESS 0
#define B_BNI_NET_ERROR 1

typedef char b_bni_net_state_t;

typedef struct b_bni_net_tcp_server_t b_bni_net_tcp_server_t;

typedef struct b_bni_net_tcp_socket_t b_bni_net_tcp_socket_t;

struct b_bni_net_tcp_server_t {
    unsigned short port;
    int sd;
};

struct b_bni_net_tcp_socket_t {
    int sd;
};

/**
 * M�todo que indica se houve ou n�o error em algum processo da library net.
 */
b_bni_net_state_t b_bni_net_error_has();

/**
 * M�todo para receber o texto que representa o error.
 */
char* b_bni_net_error_get();

/**
 * M�todo utilizado para se criar uma conex�o de servidor.
 */
b_bni_net_tcp_server_t* b_bni_net_tcp_server_new(unsigned short port);

/**
 * M�todo que realiza a busca por um cliente.
 * O controle ir� dormir at� achar um cliente.
 */
b_bni_net_tcp_socket_t* b_bni_net_tcp_server_accept(b_bni_net_tcp_server_t*);

/**
 * M�todo usado para fechar a conex�o de servidor.
 */
void b_bni_net_tcp_server_close(b_bni_net_tcp_server_t*);

/**
 * M�todo utilizado para se criar uma conex�o com um servidor.
 */
b_bni_net_tcp_socket_t* b_bni_net_tcp_socket_new(const char* host, unsigned short port);

/**
 * M�todo usado para se receber alguma menssagem do cliente.
 * O controle ir� dormir at� a menssagem chegar.
 */
char* b_bni_net_tcp_socket_receive(b_bni_net_tcp_socket_t*, unsigned int size);

/**
 * M�todo usado para se enviar alguma menssagem para o cliente.
 */
b_bni_net_state_t b_bni_net_tcp_socket_send(b_bni_net_tcp_socket_t*, const char* content);

/**
 * M�todo usado para fechar a conex�o com o cliente.
 */
void b_bni_net_tcp_socket_close(b_bni_net_tcp_socket_t*);

#endif
</pre></dt></dl>