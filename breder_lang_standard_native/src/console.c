#include "b_bni.h"

b_bni_state_t breder_lang_standard_Console__println (b_vm_t* vm) {
	printf ("\n");
	b_bni_sret0 (vm, 1);
}

b_bni_state_t breder_lang_standard_Console__println_breder_lang_standard_Number (b_vm_t* vm) {
	b_bni_get_param_nullable (vm, oparam, 0);
	if (oparam) {
		b_number_t data = b_bni_onumber_to_primitive (vm, oparam);
		if (data == (b_integer_t)data) {
			printf (B_INTEGER_SCANF, (b_integer_t)data);
		} else {
			printf (B_NUMBER_SCANF, data);
		}
		printf ("\n");
	} else {
		printf ("null\n");
	}
	b_bni_sret0 (vm, 1);
}

b_bni_state_t breder_lang_standard_Console__println_breder_lang_standard_String (b_vm_t* vm) {
	b_bni_get_param_nullable (vm, oparam, 0);
	if (oparam) {
		const char* data = b_bni_ostring_to_text (vm, oparam);
		printf ("%s\n", data);
	} else {
		printf ("null\n");
	}
	b_bni_sret0 (vm, 1);
}

b_bni_state_t breder_lang_standard_Console__println_breder_lang_standard_Boolean (b_vm_t* vm) {
	b_bni_get_param_nullable (vm, oparam, 0);
	if (oparam) {
		if (b_bni_oboolean_to_primitive (vm, oparam)) {
			printf ("true\n");
		} else {
			printf ("false\n");
		}
	} else {
		printf ("null\n");
	}
	b_bni_sret0 (vm, 1);
}

b_bni_state_t breder_lang_standard_Console__println_breder_lang_IObject (b_vm_t* vm) {
	b_bni_get_param_nullable (vm, oparam, 0);
	if (oparam) {
		b_object_t* ovalue;
		if (b_bni_execute_1_return_0_param (vm, oparam, & ovalue, b_bni_method_object_tostring_id (vm)) == B_BNI_FAIL) {
			return B_BNI_FAIL;
		}
		if (ovalue == null) {
			printf ("null\n");
		} else {
			const char* data = b_bni_ostring_to_text (vm, ovalue);
			printf ("%s\n", data);
		}
	} else {
		printf ("null\n");
	}
	b_bni_sret0 (vm, 1);
}

b_bni_state_t breder_lang_standard_Console__print_breder_lang_standard_Number (b_vm_t* vm) {
	b_bni_get_param_nullable (vm, oparam, 0);
	if (oparam) {
		b_number_t data = b_bni_onumber_to_primitive (vm, oparam);
		if (data == (int)data) {
			printf (B_INTEGER_SCANF, (int)data);
		} else {
			printf (B_NUMBER_SCANF, data);
		}
	} else {
		printf ("null");
	}
	b_bni_sret0 (vm, 1);
}

b_bni_state_t breder_lang_standard_Console__print_breder_lang_standard_String (b_vm_t* vm) {
	b_bni_get_param_nullable (vm, oparam, 0);
	if (oparam) {
		const char* data = b_bni_ostring_to_text (vm, oparam);
		printf ("%s", data);
	} else {
		printf ("null");
	}
	b_bni_sret0 (vm, 1);
}

b_bni_state_t breder_lang_standard_Console__print_breder_lang_standard_Boolean (b_vm_t* vm) {
	b_bni_get_param_nullable (vm, oparam, 0);
	if (oparam) {
		if (b_bni_oboolean_to_primitive (vm, oparam)) {
			printf ("true");
		} else {
			printf ("false");
		}
	} else {
		printf ("null");
	}
	b_bni_sret0 (vm, 1);
}

b_bni_state_t breder_lang_standard_Console__print_breder_lang_IObject (b_vm_t* vm) {
	b_bni_get_param_nullable (vm, oparam, 0);
	if (oparam) {
		b_object_t* ovalue;
		if (b_bni_execute_1_return_0_param (vm, oparam, & ovalue, b_bni_method_object_tostring_id (vm)) == B_BNI_FAIL) {
			return B_BNI_FAIL;
		}
		if (ovalue == null) {
			printf ("null");
		} else {
			const char* data = b_bni_ostring_to_text (vm, ovalue);
			printf ("%s", data);
		}
	} else {
		printf ("null");
	}
	b_bni_sret0 (vm, 1);
}
