#include "b_bni_net_native.h"

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

