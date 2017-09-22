#include "b_bni_net_native.h"

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
