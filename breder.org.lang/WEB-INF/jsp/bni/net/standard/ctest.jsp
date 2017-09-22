<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p>
Source : b_test_server.c
</p>
<dl><dt>
<pre>
#include "b_net.h"

int main (int argc, char** argv) {
    b_bni_net_tcp_server_t* server = b_bni_net_tcp_server_new(5555);
    b_bni_net_tcp_socket_t* socket = b_bni_net_tcp_server_accept(server);
    char* msg1 = b_bni_net_tcp_socket_receive(socket, 1024);
    printf("%s\n", msg1);
    b_bni_net_tcp_socket_send(socket, "Hello from Server!");
    b_bni_net_tcp_socket_close(socket);
    b_bni_net_tcp_server_close(server);
    return EXIT_SUCCESS;
}
</pre></dt></dl>

<p>
Source : b_test_client.c
</p>
<dl><dt>
<pre>
#include "b_net.h"

int main (int argc, char** argv) {
    b_bni_net_tcp_socket_t* socket = b_bni_net_tcp_socket_new("localhost", 5555);
    b_bni_net_tcp_socket_send(socket, "Hello from Client!");
    char* msg = b_bni_net_tcp_socket_receive(socket, 1024);
    printf("%s\n", msg);
    b_bni_net_tcp_socket_close(socket);
    b_bni_net_tcp_socket_close(socket);
    return EXIT_SUCCESS;
}
</pre></dt></dl>