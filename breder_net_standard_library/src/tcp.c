#include "b_net.h"

#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h>

b_bni_net_tcp_server_t* b_bni_net_tcp_server_new(unsigned short port) {
	int sd;
	if ((sd = socket(AF_INET, SOCK_STREAM, 0)) == -1) {
		b_bni_net_error_set("can not create a server.", 0);
		return 0;
	}
	struct sockaddr_in sin;
	memset(&sin, 0, sizeof(sin));
	sin.sin_family = AF_INET;
	sin.sin_addr.s_addr = INADDR_ANY;
	sin.sin_port = htons(port);
	if (bind(sd, (struct sockaddr *) &sin, sizeof(sin)) == -1) {
		close(sd);
		b_bni_net_error_set("can not bind the server.", 0);
		return 0;
	}
	if (listen(sd, 5) == -1) {
		close(sd);
		b_bni_net_error_set("can not listen the server.", 0);
		return 0;
	}
	b_bni_net_tcp_server_t* data = (b_bni_net_tcp_server_t*) calloc(1, sizeof(b_bni_net_tcp_server_t));
	if (!data) {
		close(sd);
		b_bni_net_error_set("can not alloc a struct.", 0);
		return 0;
	}
	data->port = port;
	data->sd = sd;
	return data;
}

b_bni_net_tcp_socket_t* b_bni_net_tcp_server_accept(b_bni_net_tcp_server_t* server) {
	int sd_current;
	struct sockaddr_in pin;
	socklen_t addrlen = sizeof(pin);
	if ((sd_current = accept(server->sd, (struct sockaddr *) &pin, &addrlen)) == -1) {
		b_bni_net_error_set("can not accept a socket.", 0);
		return 0;
	}
	b_bni_net_tcp_socket_t* data = (b_bni_net_tcp_socket_t*) calloc(1, sizeof(b_bni_net_tcp_socket_t));
	if (!data) {
		close(sd_current);
		b_bni_net_error_set("can not alloc a struct.", 0);
		return 0;
	}
	data->sd = sd_current;
	return data;
}

void b_bni_net_tcp_server_close(b_bni_net_tcp_server_t* server) {
	close(server->sd);
}

b_bni_net_tcp_socket_t* b_bni_net_tcp_socket_new(const char* host, unsigned short port) {
	int sd;
	struct sockaddr_in pin;
	struct hostent *hp;
	if ((hp = gethostbyname(host)) == 0) {
		b_bni_net_error_set("can not create a client with this host.", 0);
		return 0;
	}
	memset(&pin, 0, sizeof(pin));
	pin.sin_family = AF_INET;
	pin.sin_addr.s_addr = ((struct in_addr *) (hp->h_addr))->s_addr;
	pin.sin_port = htons(port);
	if ((sd = socket(AF_INET, SOCK_STREAM, 0)) == -1) {
		b_bni_net_error_set("can not create a client with this host or port.", 0);
		return 0;
	}
	if (connect(sd, (struct sockaddr *) &pin, sizeof(pin)) == -1) {
		close(sd);
		b_bni_net_error_set("can not connect a client with the server.", 0);
		return 0;
	}
	b_bni_net_tcp_socket_t* data = (b_bni_net_tcp_socket_t*) calloc(1, sizeof(b_bni_net_tcp_socket_t));
	if (!data) {
		close(sd);
		b_bni_net_error_set("can not alloc a struct.", 0);
		return 0;
	}
	data->sd = sd;
	return data;
}

char* b_bni_net_tcp_socket_receive(b_bni_net_tcp_socket_t* socket, unsigned int size) {
	char* content = (char*) calloc(size + 1, sizeof(char));
	if (recv(socket->sd, content, size, 0) == -1) {
		free(content);
		return 0;
	}
	return content;
}

b_bni_net_state_t b_bni_net_tcp_socket_send(b_bni_net_tcp_socket_t* socket, const char* content) {
	if (send(socket->sd, content, strlen(content), 0) == -1) {
		return B_BNI_NET_ERROR;
	}
	return B_BNI_NET_SUCESS;
}

void b_bni_net_tcp_socket_close(b_bni_net_tcp_socket_t* socket) {
	close(socket->sd);
}
