#include "bni.h"

public b_object_t* b_bni_get_param(b_vm_t* vm, int n) {
	return vm->cstack[-(vm->params - n - 1)];
}

public b_object_t* b_bni_get_static_param(b_vm_t* vm, int n) {
	return vm->cstack[-(vm->params - n)];
}

b_bni_state b_bni_set_return(b_vm_t* vm, int n, b_object_t* value) {
	int params = b_aint_size(vm->method->params);
	if (!vm->method->isStatic) {
		params++;
	}
	int max = vm->method->returns > params ? vm->method->returns : params;
	vm->cstack[-(max - n - 1)] = value;
	return B_BNI_SUCCESS;
}

double b_bni_onumber_to_primitive(b_vm_t* vm, b_object_t* object) {
	return *b_object_data_typed(double, object);
}

const char* b_bni_ostring_to_text(b_vm_t* vm, b_object_t* object) {
	return (char*) (b_object_data_typed(int, object) + 2);
}

b_object_t* b_bni_get_throw(b_vm_t* vm) {
	return vm->othrow;
}

void b_bni_release_throw(b_vm_t* vm) {
	vm->othrow = 0;
}

int b_bni_ostring_to_length(b_vm_t* vm, b_object_t* onumber) {
	return *b_object_data_typed( int , onumber );
}

int b_bni_ostring_to_hashcode(b_vm_t* vm, b_object_t* onumber) {
	int hashcode = *(b_object_data_typed( int , onumber ) + 1);
	if (!hashcode) {
		int n, len = *b_object_data_typed( int , onumber );
		char* text = (char*) (b_object_data_typed( int , onumber ) + 2);
		for (n = 0; n < len; n++) {
			hashcode = 3 * hashcode + text[n];
		}
		*(b_object_data_typed( int , onumber ) + 1) = hashcode;
	}
	return hashcode;
}

int b_bni_oboolean_to_primitive(b_vm_t* vm, b_object_t* onumber) {
	return b_object_data_typed(void, onumber) != null;
}

void b_bni_set_data(b_vm_t* vm, b_object_t* object, void* data) {
	b_object_set_data( object , data );
}

void* b_bni_get_data(b_vm_t* vm, b_object_t* object) {
	return b_object_data( object );
}

public b_object_t* b_bni_new_onumber(b_vm_t* vm, double value) {
	return b_object_new_number(vm, value);
}

b_object_t* b_bni_new_oboolean(b_vm_t* vm, int flag) {
	return b_object_new_boolean(vm, flag);
}

b_object_t* b_bni_new_ostring(b_vm_t* vm, char* value) {
	return b_object_new_string(vm, value);
}

b_object_t* b_bni_new_otrue(b_vm_t* vm) {
	return b_object_new_boolean(vm, 1);
}

b_object_t* b_bni_new_ofalse(b_vm_t* vm) {
	return b_object_new_boolean(vm, 0);
}

void b_bni_inc_used(b_vm_t* vm, b_object_t* ovalue) {
	if (ovalue) {
		b_object_used( ovalue ) += 1;
	}
}

void b_bni_dec_used(b_vm_t* vm, b_object_t* ovalue) {
	if (ovalue) {
		b_object_used( ovalue ) -= 1;
	}
}

b_object_t* b_bni_call_1_return_0_param(b_vm_t* vm, b_object_t* object, const char* methodname) {
	b_bni_push(vm, object);
	b_bni_call(vm, methodname, 0);
	b_object_t* oresult = b_vm_opcode_object_pop(vm);
	return oresult;
}

int b_bni_call_0_return_0_param(b_vm_t* vm, b_object_t* object, const char* methodname) {
	b_bni_push(vm, object);
	int state = b_bni_call(vm, methodname, 0);
	b_bni_dec(vm, 1);
	return state;
}

int b_bni_call_0_return_1_param(b_vm_t* vm, b_object_t* object, const char* methodname, b_object_t* oparam) {
	b_bni_push(vm, object);
	b_bni_push(vm, oparam);
	int state = b_bni_call(vm, methodname, 1);
	b_bni_dec(vm, 2);
	return state;
}

int b_bni_call_0_return_2_param(b_vm_t* vm, b_object_t* object, const char* methodname, b_object_t* oparam1,
		b_object_t* oparam2) {
	b_bni_push(vm, object);
	b_bni_push(vm, oparam1);
	b_bni_push(vm, oparam2);
	int state = b_bni_call(vm, methodname, 2);
	b_bni_dec(vm, 3);
	return state;
}

int b_bni_call_0_return_3_param(b_vm_t* vm, b_object_t* object, const char* methodname, b_object_t* oparam1,
		b_object_t* oparam2, b_object_t* oparam3) {
	b_bni_push(vm, object);
	b_bni_push(vm, oparam1);
	b_bni_push(vm, oparam2);
	b_bni_push(vm, oparam3);
	int state = b_bni_call(vm, methodname, 3);
	b_bni_dec(vm, 4);
	return state;
}

int b_bni_call_0_return_4_param(b_vm_t* vm, b_object_t* object, const char* methodname, b_object_t* oparam1,
		b_object_t* oparam2, b_object_t* oparam3, b_object_t* oparam4) {
	b_bni_push(vm, object);
	b_bni_push(vm, oparam1);
	b_bni_push(vm, oparam2);
	b_bni_push(vm, oparam3);
	b_bni_push(vm, oparam4);
	int state = b_bni_call(vm, methodname, 4);
	b_bni_dec(vm, 5);
	return state;
}

int b_bni_call(b_vm_t* vm, const char* methodname, int params) {
	return b_vm_opcode_ojump(vm, methodname, params);
}

int b_bni_throw_null_pointer_exception(b_vm_t* vm) {
	return b_vm_throw_null_pointer_runtime_exception(vm);
}

int b_bni_throw_out_of_memory_runtime_exception(b_vm_t* vm) {
	return b_bni_throw_generic_exception(vm, "breder.lang.OutOfMemoryRuntimeException");
}

int b_bni_throw_generic_exception(b_vm_t* vm, char* classname) {
	return b_vm_throw_generic_exception(vm, classname);
}

b_object_t* b_bni_new1(b_vm_t* vm, char* classname) {
	b_class_t* clazz = b_hashnew_get1(vm->classhash, classname);
	int classindex = clazz->index;
	return b_bni_new(vm, classindex);
}

b_object_t* b_bni_new(b_vm_t* vm, int classindex) {
	b_object_t* cache = vm->caches->array[classindex];
	b_object_t* object = calloc(1, b_object_size());
	memcpy (object, cache, b_object_size_noproxy());
	b_linker_add_last(vm->objects, vm, object);
	return object;
}

void b_bni_push(b_vm_t* vm, b_object_t* object) {
	vm->cstack++;
	*vm->cstack = object;
}

b_object_t* b_bni_pop(b_vm_t* vm) {
	b_object_t* object = *vm->stack;
	vm->cstack--;
	return object;
}

void b_bni_inc(b_vm_t* vm, int count) {
	vm->cstack += count;
}

void b_bni_dec(b_vm_t* vm, int count) {
	vm->cstack -= count;
}
