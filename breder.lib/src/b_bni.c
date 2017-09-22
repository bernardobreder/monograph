#include "b_bni.h"

b_object_t* b_bni_get_throw (b_vm_t* vm) {
	return vm->othrow;
}

void b_bni_release_throw (b_vm_t* vm) {
	vm->othrow = 0;
}

unsigned int b_bni_ostring_to_hashcode (b_vm_t* vm, b_object_t* object) {
	unsigned int hashcode = * ( ((unsigned int*)object->datas[0]) + 1);
	if ( ! hashcode) {
		* ( ((unsigned int*)object->datas[0]) + 1) = hashcode = b_char_hash_build (b_bni_ostring_to_text(vm, object));
	}
	return hashcode;
}

void b_bni_set_data_current (b_vm_t* vm, b_object_t* object, void* data) {
	int index = vm->dataIndexs[b_object_class(object)][vm->method->cindex];
	b_bni_set_data_index (vm, object, index, data);
}

void b_bni_set_data_index (b_vm_t* vm, b_object_t* object, unsigned int index, void* data) {
	object->datas[index] = data;
}

void b_bni_set_data_class (b_vm_t* vm, b_object_t* object, unsigned int cindex, void* data) {
	int index = vm->dataIndexs[b_object_class(object)][cindex];
	object->datas[index] = data;
}

b_state_t b_bni_opcode_sjump (b_vm_t* self, b_method_id_t method_index) {
	b_method_t* method = b_method_get(self, method_index);
	b_array_add (self->stackTrace, method->name);
	self->cprogmem = self->progmem + method->memIndex;
	self->cmemstack = self->memstack;
	return b_vm_exec (self);
}

b_bni_state_t b_bni_opcode_ojump_generic (b_vm_t* self, b_object_t* object, b_class_id_t cindex, b_method_t* method) {
	b_bni_state_t state;
	b_method_id_t mindex = self->mvirtual[cindex][method->index];
	method = b_method_get(self, mindex);
	int stackTrackIndex = b_array_size_safely(self->stackTrace);
	b_array_add (self->stackTrace, method->name);
	{
		int* memstack = self->memstack;
		self->memstack = self->memstack;
		if (method->isNative) {
			b_method_t* oldMethod = self->method;
			self->method = method;
			int index = self->dataIndexs[object->cindex][method->cindex];
			void* data = object->datas[index];
			state = b_call_function(int, method->isNative, (b_vm_t*,b_object_t*, void*)) (self, object, data);
			self->method = oldMethod;
		} else {
			int cpc = self->cprogmem - self->progmem;
			self->cprogmem = self->progmem + method->memIndex;
			state = b_vm_exec (self);
			self->cprogmem = self->progmem + cpc;
		}
		self->memstack = memstack;
	}
	if (state == B_STATE_SUCCESS) {
		b_array_dec(self->stackTrace);
	} else {
		b_array_set_size (self->stackTrace, stackTrackIndex);
	}
	return state;
}

b_bni_state_t b_bni_opcode_ojump_specific (b_vm_t* vm, b_class_id_t cindex, b_method_id_t mindex) {
	b_method_t* method = b_method_get(vm, mindex);
	b_object_t* object = vm->cobjstack[ - b_aint_size(method->params)];
	if ( ! object) {
		b_bni_throw_null_pointer_exception(vm);
	}
	return b_bni_opcode_ojump_generic (vm, object, cindex, method);
}

b_bni_state_t b_bni_opcode_ojump (b_vm_t* vm, b_method_id_t mindex) {
	b_method_t* method = b_method_get(vm, mindex);
	b_object_t* object = vm->cobjstack[ - b_aint_size(method->params)];
	if ( ! object) {
		b_bni_throw_null_pointer_exception(vm);
	}
	return b_bni_opcode_ojump_generic (vm, object, object->cindex, method);
}

b_bni_state_t b_bni_opcode_new (b_vm_t* vm, b_class_id_t cindex) {
	b_object_t* object = b_object_new (vm, cindex);
	if (object == null) {
		return B_BNI_FAIL;
	}
	vm->cobjstack ++ ;
	* vm->cobjstack = object;
	return B_BNI_SUCCESS;
}

b_bni_state_t b_bni_execute_0_return_0_param (b_vm_t* vm, b_object_t* object, b_method_id_t mindex) {
	b_bni_opcode_push(vm, object);
	return b_bni_opcode_ojump (vm, mindex);
}

b_bni_state_t b_bni_execute_1_return_0_param (b_vm_t* vm, b_object_t* object, b_object_t** oreturn1, b_method_id_t mindex) {
	b_bni_opcode_push(vm, object);
	b_bni_opcode_ojump_define(vm, mindex);
	* oreturn1 = b_bni_opcode_pop(vm);
	return B_BNI_SUCCESS;
}

b_bni_state_t b_bni_execute_1_return_1_param (b_vm_t* vm, b_object_t* object, b_object_t** oreturn1, b_method_id_t mindex, b_object_t* oparam1) {
	b_bni_opcode_push(vm, object);
	b_bni_opcode_push(vm, oparam1);
	if (b_bni_opcode_ojump (vm, mindex) == B_BNI_FAIL) {
		return B_BNI_FAIL;
	}
	* oreturn1 = b_bni_opcode_pop(vm);
	return B_BNI_SUCCESS;
}
