#ifndef B_NET_TCP_H_
#define B_NET_TCP_H_

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
