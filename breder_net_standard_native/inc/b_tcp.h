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
