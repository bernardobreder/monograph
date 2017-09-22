#include "b_bni_net_native.h"

b_bni_state_t breder_net_standard_BClientSocket$BClientSocket$breder_lang_String$breder_lang_Natural(b_vm_t* vm, b_object_t* object) {
	b_bni_get_param_as_string(vm, ohost, host, 0);
	b_bni_get_param_as_natural(vm, oport, port, 1);
	b_bni_net_tcp_socket_t* data = b_bni_net_tcp_socket_new(host, port);
	b_bni_super_0_param(vm, object, b_bni_method_bsocket$init_id(vm));
	b_bni_set_data_class(vm, object, b_bni_class_bsocket_id(vm), data);
	b_bni_sret0(vm, 2);
}
