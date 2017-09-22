#include "b_bni.h"

b_bni_state_t breder_lang_standard_String_length(b_vm_t* vm, b_object_t* object) {
	unsigned int length = b_bni_ostring_to_length(vm, object);
	b_object_t* oreturn = b_bni_new_number(vm, length);
	if (!oreturn) {
		b_bni_throw_out_of_memory_runtime_exception(vm);
	}
	b_bni_ret1(vm, 0, oreturn);
}

b_bni_state_t breder_lang_standard_String_hashcode(b_vm_t* vm,
		b_object_t* object) {
	unsigned int hashcode = b_bni_ostring_to_hashcode(vm, object);
	b_object_t* oreturn = b_bni_new_number(vm, hashcode);
	if (!oreturn) {
		b_bni_throw_out_of_memory_runtime_exception(vm);
	}
	b_bni_ret1(vm, 0, oreturn);
}

b_bni_state_t breder_lang_standard_String_operatorEqual_breder_lang_standard_Object(
		b_vm_t* vm, b_object_t* object) {
	b_bni_get_param_nullable(vm, onext, 0);
	if (onext == NULL || b_bni_class_index(vm, object) != b_bni_class_index(vm,
			onext)) {
		b_bni_new_ofalse_define(vm, oreturn);
		b_bni_ret1(vm, 1, oreturn);
	} else {
		unsigned int hashcode1 = b_bni_ostring_to_hashcode(vm, object);
		unsigned int hashcode2 = b_bni_ostring_to_hashcode(vm, onext);
		if (hashcode1 != hashcode2) {
			b_bni_new_ofalse_define(vm, oreturn);
			b_bni_ret1(vm, 1, oreturn);
		}
		const char* value1 = b_bni_ostring_to_text(vm, object);
		const char* value2 = b_bni_ostring_to_text(vm, onext);
		b_bni_new_oboolean_define(vm, oreturn, !strcmp(value1, value2));
		b_bni_ret1(vm, 1, oreturn);
	}
}

b_bni_state_t breder_lang_standard_String_startWith_breder_lang_standard_String(
		b_vm_t* vm, b_object_t* object) {
	b_bni_get_param_as_string(vm, otext, text2, 0);
	if (b_bni_ostring_to_length(vm, otext)
			> b_bni_ostring_to_length(vm, object)) {
		b_bni_new_ofalse_define(vm, oreturn);
		b_bni_ret1(vm, 1, oreturn);
	} else {
		const char* text1 = b_bni_ostring_to_text(vm, object);
		unsigned int n, length = b_bni_ostring_to_length(vm, otext);
		for (n = 0; n < length; n++) {
			if (text1[n] != text2[n]) {
				b_bni_new_ofalse_define(vm, oreturn);
				b_bni_ret1(vm, 1, oreturn);
			}
		}
		b_bni_new_otrue_define(vm, oreturn);
		b_bni_ret1(vm, 1, oreturn);
	}
}

b_bni_state_t breder_lang_standard_String_endWith_breder_lang_standard_String(
		b_vm_t* vm, b_object_t* object) {
	b_bni_get_param_as_string(vm, otext, text2, 0);
	if (b_bni_ostring_to_length(vm, otext)
			> b_bni_ostring_to_length(vm, object)) {
		b_bni_new_ofalse_define(vm, oreturn);
		b_bni_ret1(vm, 1, oreturn);
	} else {
		const char* text1 = b_bni_ostring_to_text(vm, object);
		unsigned int length1 = b_bni_ostring_to_length(vm, object);
		unsigned int length2 = b_bni_ostring_to_length(vm, otext);
		unsigned int n, m = length1 - length2;
		for (n = length2 - 1; n >= 0; n--) {
			if (text1[n + m] != text2[n]) {
				b_bni_new_ofalse_define(vm, oreturn);
				b_bni_ret1(vm, 1, oreturn);
			}
		}
		b_bni_new_otrue_define(vm, oreturn);
		b_bni_ret1(vm, 1, oreturn);
	}
}

b_bni_state_t breder_lang_standard_String_trim(b_vm_t* vm, b_object_t* object) {
	const char* text = b_bni_ostring_to_text(vm, object);
	int len = b_bni_ostring_to_length(vm, object);
	if (!len) {
		b_bni_ret1(vm, 0, object);
	}
	char* cnew = strdup(text);
	char* chars = cnew;
	while (chars[len - 1] == ' ') {
		chars[--len] = 0;
	}
	while (*chars == ' ') {
		chars++;
	}
	b_bni_new_ostring_define(vm, oreturn, chars);
	free(cnew);
	b_bni_ret1(vm, 0, oreturn);
}

b_bni_state_t breder_lang_standard_String_toString(b_vm_t* vm,
		b_object_t* object) {
	b_bni_ret1(vm, 0, object);
}

/*
 static b_bni_state_t b_bni_div(b_vm_t* vm, b_object_t* object, int offset) {
 const char* text = b_bni_ostring_to_text(vm, object);
 int len = b_bni_ostring_to_length(vm, object);
 if (!len) {
 b_bni_return_with_2_values(vm, object, object);
 }
 b_bni_get_param_as_string_declared(vm, oseparator, separator, 0);
 int separatorlen = b_bni_ostring_to_length(vm, oseparator);
 if (!separatorlen) {
 b_bni_new_ostring_define(vm, oreturn2, "");
 b_bni_return_with_2_values(vm, object, oreturn2);
 }
 const char* data = text;
 int n, m;
 for (n = offset, m = 0; n < len && m != separatorlen; n++) {
 if (text[n] == separator[m]) {
 m++;
 } else if (!m) {
 m = 0;
 }
 }
 if (n == len) {
 b_bni_return_with_2_values(vm, object, b_bni_new_ostring(vm, ""));
 } else {
 char* aux = strdup(data);
 aux[n - separatorlen - 1] = 0;
 b_bni_new_ostring_define(vm, oresult1, aux + offset);
 b_bni_new_ostring_define(vm, oresult2, aux + n);
 free(aux);
 b_bni_return_with_2_values(vm, oresult1, oresult2);
 }
 }

 b_bni_state_t breder_lang_standard_String_div_breder_lang_standard_String(b_vm_t* vm, b_object_t* object) {
 return b_bni_div(vm, object, 0);
 }

 b_bni_state_t breder_lang_standard_String_div_breder_lang_standard_String_breder_lang_standard_Index(b_vm_t* vm, b_object_t* object) {
 b_bni_get_param_as_number(vm, ooffset, offset, 1);
 return b_bni_div(vm, object, offset);
 }
 */
