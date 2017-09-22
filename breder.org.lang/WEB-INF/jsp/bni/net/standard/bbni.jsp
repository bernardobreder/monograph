<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p>
Header : b_bni_native.h
</p>
<dl><dt>
<pre>
#ifndef B_BNI_NATIVE_H_
#define B_BNI_NATIVE_H_

#include "breder.h"
#include "b_net.h"

b_class_id_t b_bni_class_bsocket_id(b_vm_t*);
b_method_id_t b_bni_method_bsocket$init_id(b_vm_t*);

#endif
</pre></dt></dl>

<p>
Source : b_bni_server.c
</p>
<dl><dt>
<pre>
#include "b_bni_native.h"

b_bni_state_t breder_net_standard_BServerSocket$BServerSocket$breder_lang_Natural(b_vm_t* vm, b_object_t* object) {
    b_bni_get_param_as_natural(vm, oport, port, 0);
    b_bni_super_object(vm, object);
    b_bni_net_tcp_server_t* data = b_bni_net_tcp_server_new(port);
    if (data == null) {
        b_bni_throw_ioexception(vm);
    }
    b_bni_set_data_current(vm, object, data);
    b_bni_sret0(vm, 1);
}

b_bni_state_t breder_net_standard_BServerSocket$accept(b_vm_t* vm, b_object_t* object, b_bni_net_tcp_server_t* data) {
    b_bni_net_tcp_socket_t* sdata = b_bni_net_tcp_server_accept(data);
    if (sdata == null) {
        b_bni_throw_ioexception(vm);
    }
    b_bni_new_0_param(vm, osocket, b_bni_method_bsocket$init_id(vm));
    b_bni_set_data_class(vm, osocket, b_bni_class_bsocket_id(vm), sdata);
    b_bni_ret1(vm, 0, osocket);
}
</pre></dt></dl>

<p>
Source : b_bni_socket.c
</p>
<dl><dt>
<pre>
#include "b_bni_native.h"

b_bni_class_define(b_bni_class_bsocket_id, bsocket_id, "breder.net.standard.BSocket")
b_bni_method_define(b_bni_method_bsocket$init_id, bsocket$init_id, b_bni_class_bsocket_id, "() BSocket ()")

b_bni_state_t breder_net_standard_BSocket$read$breder_lang_Natural(b_vm_t* vm, b_object_t* object, b_bni_net_tcp_socket_t* data) {
    b_bni_get_param_as_natural(vm, olength, length, 0);
    char* result = b_bni_net_tcp_socket_receive(data, length);
    b_object_t* oresult = b_bni_new_ostring(vm, result);
    free(result);
    if (oresult == null) {
        b_bni_throw_ioexception(vm);
    }
    b_bni_ret1(vm, 1, oresult);
}

b_bni_state_t breder_net_standard_BSocket$write$breder_lang_String(b_vm_t* vm, b_object_t* object, b_bni_net_tcp_socket_t* data) {
    b_bni_get_param_as_string(vm, ocontent, content, 0);
    b_object_t* oresult = b_bni_new_onatural(vm, strlen(content));
    if (oresult == null) {
        b_bni_throw_ioexception(vm);
    }
    if (b_bni_net_tcp_socket_send(data, content) == B_BNI_NET_ERROR) {
        b_bni_throw_ioexception(vm);
    }
    b_bni_ret1(vm, 1, oresult);
}

b_bni_state_t breder_net_standard_BSocket$close(b_vm_t* vm, b_object_t* object, b_bni_net_tcp_socket_t* data) {
    b_bni_net_tcp_socket_close(data);
    b_bni_ret0(vm, 0);
}

</pre></dt></dl>

<p>
Source : b_bni_client.c
</p>
<dl><dt>
<pre>
#include "b_bni_native.h"
#include "native.h"

b_bni_state_t breder_net_standard_BClientSocket$BClientSocket$breder_lang_String$breder_lang_Natural(b_vm_t* vm, b_object_t* object) {
    b_bni_get_param_as_string(vm, ohost, host, 0);
    b_bni_get_param_as_natural(vm, oport, port, 1);
    b_bni_net_tcp_socket_t* data = b_bni_net_tcp_socket_new(host, port);
    b_bni_super_0_param(vm, object, b_bni_method_bsocket$init_id(vm));
    b_bni_set_data_class(vm, object, b_bni_class_bsocket_id(vm), data);
    b_bni_sret0(vm, 2);
}

</pre></dt></dl>