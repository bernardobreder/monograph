<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p>
Nesse tópico iremos mostrar como foi criado a biblioteca do pacote <code>breder.net.standard</code>.
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
 * Método que indica se houve ou não error em algum processo da library net.
 */
b_bni_net_state_t b_bni_net_error_has();

/**
 * Método para receber o texto que representa o error.
 */
char* b_bni_net_error_get();

/**
 * Método utilizado para se criar uma conexão de servidor.
 */
b_bni_net_tcp_server_t* b_bni_net_tcp_server_new(unsigned short port);

/**
 * Método que realiza a busca por um cliente.
 * O controle irá dormir até achar um cliente.
 */
b_bni_net_tcp_socket_t* b_bni_net_tcp_server_accept(b_bni_net_tcp_server_t*);

/**
 * Método usado para fechar a conexão de servidor.
 */
void b_bni_net_tcp_server_close(b_bni_net_tcp_server_t*);

/**
 * Método utilizado para se criar uma conexão com um servidor.
 */
b_bni_net_tcp_socket_t* b_bni_net_tcp_socket_new(const char* host, unsigned short port);

/**
 * Método usado para se receber alguma menssagem do cliente.
 * O controle irá dormir até a menssagem chegar.
 */
char* b_bni_net_tcp_socket_receive(b_bni_net_tcp_socket_t*, unsigned int size);

/**
 * Método usado para se enviar alguma menssagem para o cliente.
 */
b_bni_net_state_t b_bni_net_tcp_socket_send(b_bni_net_tcp_socket_t*, const char* content);

/**
 * Método usado para fechar a conexão com o cliente.
 */
void b_bni_net_tcp_socket_close(b_bni_net_tcp_socket_t*);

#endif
</pre></dt></dl>