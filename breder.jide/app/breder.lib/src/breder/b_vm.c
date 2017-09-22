#include "breder.h"
#ifdef _DEBUG_
#include <time.h>
#endif

int b_vm_exec(register b_vm_t* self) {
	register int index, index2, ir;
	register b_object_t* object = null;
	for (;;) {
#ifdef _DEBUG_
		int sp = self->cstack - self->stack;
		int pc = self->cmemory - self->buffer;
#endif
		if (self->cstack - self->stack >= 1000) {
			printf("Stack Overflow\n");
			exit(0);
		}
		ir = *self->cmemory;
		switch (b_opcode(ir)) {
		case V_OPCODE_PUSHNIL: {
			self->cstack++;
			*self->cstack = 0;
#ifdef _DEBUG_
			sp = self->cstack - self->stack;
			printf("[% 8d] pushnil[s%d]", pc, sp);
#endif
			break;
		}
		case V_OPCODE_LOAD: {
			self->cstack++;
			*self->cstack = self->cstack[-(b_opcode_index(ir) + 1)];
#ifdef _DEBUG_
			sp = self->cstack - self->stack;
			printf("[% 8d] load %d(%x)[s%d]", pc, b_opcode_index(ir),
					*self->cstack, sp);
#endif
			break;
		}
		case V_OPCODE_SCONST: {
			void
					* data =
							b_arrayp_get_typed( void , self->stringConstants , b_opcode_index(ir) );
			const char* text = b_achar_text(data);
			int hash = b_achar_hash(data);
			int len = b_achar_len(data);
			self->cstack++;
			if (!(object = b_object_new_string0(self, text, len, hash))) {
				return B_BNI_FAIL;
			}
			*self->cstack = object;
#ifdef _DEBUG_
			sp = self->cstack - self->stack;
			printf("[% 8d] sconst %d(%x)[s%d]", pc, b_opcode_index(ir), object,
					sp);
#endif
			break;
		}
		case V_OPCODE_NCONST: {
			double
					* data =
							b_arrayp_get_typed(double, self->doubleConstants, b_opcode_index(ir));
			self->cstack++;
			if (!(object = b_bni_new_onumber(self, *data))) {
				return B_BNI_FAIL;
			}
			*self->cstack = object;
#ifdef _DEBUG_
			sp = self->cstack - self->stack;
			printf("[% 8d] nconst=%d(%x)[s%d]", pc, b_opcode_index(ir), object,
					sp);
#endif
			break;
		}
		case V_OPCODE_BCONST: {
			self->cstack++;
			if (!(object = b_object_new_boolean(self, b_opcode_index(ir)))) {
				return B_BNI_FAIL;
			}
			*self->cstack = object;
#ifdef _DEBUG_
			sp = self->cstack - self->stack;
			printf("[% 8d] bconst=%d(%x)[s%d]", pc, b_opcode_index(ir), object,
					sp);
#endif
			break;
		}
		case V_OPCODE_ARRAY: {
			index = (ir & 0xFF00) >> 8;
			index2 = (ir & 0xFF);
			self->cstack[-index + 1] = self->cstack[-(index - index2)];
			self->cstack -= index - 1;
#ifdef _DEBUG_
			sp = self->cstack - self->stack;
			printf("[% 8d] array %d %d[s%d]", pc, index, index2, sp);
#endif
			break;
		}
		case V_OPCODE_STORE: {
#ifdef _DEBUG_
			index = b_opcode_index(ir);
			b_object_t* oldobject = self->cstack[-index];
			b_object_t* newobject = self->cstack[0];
#endif
			self->cstack[-b_opcode_index(ir)] = *self->cstack;
			self->cstack--;
#ifdef _DEBUG_
			sp = self->cstack - self->stack;
			printf("[% 8d] store %d o%x,n%x[s%d]", pc, b_opcode_index(ir),
					oldobject, newobject, sp);
#endif
			break;
		}
		case V_OPCODE_THROW: {
			self->othrow = self->cstack[0];
#ifdef _DEBUG_
			sp = self->cstack - self->stack;
			printf("[% 8d] throw [s%d]", pc, sp);
#endif
			return 0;
		}
		case V_OPCODE_TRY: {
#ifdef _DEBUG_
			sp = self->cstack - self->stack;
			printf("[% 8d] try %d[s%d]", pc, b_opcode_index(ir), sp);
#endif
			self->cmemory++;
			index = b_vm_exec(self);
			if (!index) {
				self->cmemory = self->buffer + b_opcode_index(ir) - 1;
			}
			break;
		}
		case V_OPCODE_THROW_TRUE_RET: {
#ifdef _DEBUG_
			sp = self->cstack - self->stack;
			printf("[% 8d] throw_true_ret [s%d]", pc, sp);
#endif
			return 1;
		}
		case V_OPCODE_THROW_FALSE_RET: {
#ifdef _DEBUG_
			sp = self->cstack - self->stack;
			printf("[% 8d] throw_false_ret [s%d]", pc, sp);
#endif
			return 0;
		}
		case V_OPCODE_INC: {
			index = b_opcode_index(ir);
			for (index2 = 0; index2 < index; index2++) {
				self->cstack++;
				*self->cstack = 0;
			}
#ifdef _DEBUG_
			sp = self->cstack - self->stack;
			printf("[% 8d] inc %d[s%d]", pc, b_opcode_index(ir), sp);
#endif
			break;
		}
		case V_OPCODE_DESC: {
			self->cstack -= b_opcode_index(ir);
#ifdef _DEBUG_
			sp = self->cstack - self->stack;
			printf("[% 8d] desc %d[s%d]", pc, b_opcode_index(ir), sp);
#endif
			break;
		}
		case V_OPCODE_JUMP: {
			self->cmemory = self->buffer + b_opcode_index(ir) - 1;
#ifdef _DEBUG_
			printf("[% 8d] jump %d[s%d]", pc, b_opcode_index(ir), sp);
#endif
			break;
		}
		case V_OPCODE_OJUMP: {
			index = b_opcode_a_iabb(ir);
			index2 = b_opcode_b_iabb(ir);
			if (!(object = self->cstack[-index])) {
				return b_bni_throw_null_pointer_exception(self);
			}
			b_chars_t* chars =
					b_arrayp_get_typed(b_chars_t, self->constants, index2);
			b_method_t* method;
			if (b_object_hash_method(object)) {
				method = (b_method_t*) b_hashclose_get1(
						b_object_hash_method(object), chars);
			} else {
				index = b_object_class(object);
				method
						= (b_method_t*) b_hashclose_get1(
								b_object_hash_method(b_arrayp_get_typed(b_object_t, self->caches, index)),
								chars);
			}
			if (!b_array_add(self->stackTrace, self, method->name)) {
				return b_bni_throw_out_of_memory_runtime_exception(self);
			}
			if (method->isNative) {
				self->params = method->params[0];
				self->method = method;
				int
						state =
								b_call_function( int , method->isNative , (b_vm_t*,b_object_t*) )(
										self, object);
				if (b_aint_size(method->params) + 1 > method->returns) {
					self->cstack -= b_aint_size(method->params) + 1
							- method->returns;
				}
				if (!method->isStatic) {
					if (method->returns == 0) {
						self->cstack--;
					}
				}
				if (!state) {
					return 0;
				}
				b_array_dec( self->stackTrace );
			} else {
				self->cmemstack++;
				*self->cmemstack = (self->cmemory - self->buffer) + 1;
				int memindex = method->memIndex;
				self->cmemory = self->buffer + memindex - 1;
			}
#ifdef _DEBUG_
			sp = self->cstack - self->stack;
			printf("[% 8d] ojump %d,%d[s%d] : %s", pc, index, index2, sp,
					method->name);
#endif
			break;
		}
		case V_OPCODE_SJUMP: {
			index = b_opcode_index(ir);
			b_method_t* method =
					b_arrayp_get_typed(b_method_t, self->methods, index);
			if (!b_array_add(self->stackTrace, self, method->name)) {
				return b_bni_throw_out_of_memory_runtime_exception(self);
			}
			if (method->isNative) {
				self->params = b_aint_size(method->params);
				self->method = method;
				b_call_function(int, method->isNative, (b_vm_t*))(self);
				b_arrayp_pop(self->stackTrace);
				if (b_aint_size(method->params) + 1 > method->returns) {
					self->cstack -= b_aint_size(method->params)
							- method->returns;
				}
			} else {
				self->cmemstack++;
				*self->cmemstack = (self->cmemory - self->buffer) + 1;
				int memindex = method->memIndex;
				self->cmemory = self->buffer + memindex - 1;
			}
#ifdef _DEBUG_
			sp = self->cstack - self->stack;
			printf("[% 8d] sjump %d[s%d] : %s", pc, b_opcode_index(ir), sp,
					method->name);
#endif
			break;
		}
		case V_OPCODE_NEW: {
			object = b_object_new_object(self, b_opcode_index(ir));
			self->cstack++;
			*self->cstack = object;
#ifdef _DEBUG_
			sp = self->cstack - self->stack;
			printf("[% 8d] new %d,%d(%x)[s%d]", pc, b_opcode_index(ir),
					b_linker_size(self->objects), object, sp);
#endif
			break;
		}
		case V_OPCODE_CAST: {
			index = b_opcode_index(ir);
			b_class_t
					* cobject =
							b_arrayp_get_typed( b_class_t , self->classs , b_object_class( * self->cstack ) );
			do {
				if (cobject->index == index) {
					break;
				}
				cobject = cobject->extend;
			} while (cobject);
			if (!cobject) {
				return b_vm_throw_cast_runtime_exception(self);
			}
			break;
		}
		case V_OPCODE_RET: {
			if (self->memstack == self->cmemstack) {
				return 1;
			}
#ifdef _DEBUG_
			char* methodname = b_arrayp_get_last(self->stackTrace);
#endif
			index = *self->cmemstack;
			self->cmemory = self->buffer + index - 1;
			self->cmemstack -= 1;
			self->cstack -= b_opcode_index(ir) + 1;
			b_array_dec( self->stackTrace );
#ifdef _DEBUG_
			sp = self->cstack - self->stack;
			printf("[% 8d] ret %d[s%d] : %s", pc, b_opcode_index(ir), sp,
					methodname);
#endif
			break;
		}
		case V_OPCODE_SRET: {
			if (self->memstack == self->cmemstack) {
				return 1;
			}
			index = *self->cmemstack;
			self->cmemory = self->buffer + index - 1;
			self->cmemstack -= 1;
			self->cstack -= b_opcode_index(ir);
			b_array_dec( self->stackTrace );
#ifdef _DEBUG_
			sp = self->cstack - self->stack;
			printf("[% 8d] sret %d,%d[s%d]", pc, b_opcode_index(ir),
					self->cmemstack - self->memstack, sp);
#endif
			break;
		}
		case V_OPCODE_OR: {
			b_object_t* object1 = self->cstack[-1];
			b_object_t* object2 = self->cstack[0];
			if (!object1 || !object2) {
				return b_bni_throw_null_pointer_exception(self);
			}
			int cond = b_object_data( object1 ) || b_object_data( object2 );
			self->cstack--;
			if (!(object = b_object_new_boolean(self, cond))) {
				return B_BNI_FAIL;
			}
			*self->cstack = object;
			break;
		}
		case V_OPCODE_AND: {
			b_object_t* object1 = self->cstack[-1];
			b_object_t* object2 = self->cstack[0];
			if (!object1 || !object2) {
				return b_bni_throw_null_pointer_exception(self);
			}
			int cond = b_object_data( object1 ) && b_object_data( object2 );
			self->cstack--;
			if (!(object = b_object_new_boolean(self, cond))) {
				return B_BNI_FAIL;
			}
			*self->cstack = object;
			break;
		}
		case V_OPCODE_EQUAL: {
			int cond;
			b_object_t* object1 = self->cstack[-1];
			b_object_t* object2 = self->cstack[0];
			if (!object1 || !object2) {
				return b_bni_throw_null_pointer_exception(self);
			}
			if (b_object_class( object1 ) == self->numberClass->index) {
				if (b_object_class( object1 ) == b_object_class( object2 )) {
					double* value1 = (double*) b_object_data( object1 );
					double* value2 = (double*) b_object_data( object2 );
					cond = *value1 == *value2;
				} else {
					cond = 0;
				}
			} else if (b_object_class( object1 ) == self->booleanClass->index) {
				if (b_object_class( object1 ) == b_object_class( object2 )) {
					cond = b_object_data( object1 ) && b_object_data( object2 );
				} else {
					cond = 0;
				}
			} else if (b_object_class( object1 ) == self->stringClass->index) {
				if (b_object_class( object1 ) == b_object_class( object2 )) {
					int* value1 = (int*) b_object_data( object1 );
					int* value2 = (int*) b_object_data( object2 );
					if (*value1 == *value2) {
						if (value1[1] == value2[1]) {
							char* text1 = (char*) (value1 + 2);
							char* text2 = (char*) (value2 + 2);
							cond = !strcmp(text1, text2);
						} else {
							cond = 0;
						}
					} else {
						cond = 0;
					}
				} else {
					cond = 0;
				}
			} else {
				cond = object1 == object2;
			}
			self->cstack--;
			if (!(object = b_object_new_boolean(self, cond))) {
				return B_BNI_FAIL;
			}
			*self->cstack = object;
#ifdef _DEBUG_
			printf("[% 8d] equal=%d", pc, cond);
#endif
			break;
		}
		case V_OPCODE_NEQUAL: {
			int cond;
			b_object_t* object1 = self->cstack[-1];
			b_object_t* object2 = self->cstack[0];
			if (!object1 || !object2) {
				return b_bni_throw_null_pointer_exception(self);
			}
			if (b_object_class( object1 ) == self->numberClass->index) {
				if (b_object_class( object1 ) == b_object_class( object2 )) {
					double* value1 = (double*) b_object_data( object1 );
					double* value2 = (double*) b_object_data( object2 );
					cond = *value1 != *value2;
				} else {
					cond = 1;
				}
			} else if (b_object_class( object1 ) == self->booleanClass->index) {
				if (b_object_class( object1 ) == b_object_class( object2 )) {
					cond = b_object_data( object1 ) != b_object_data( object2 );
				} else {
					cond = 0;
				}
			} else if (b_object_class( object1 ) == self->stringClass->index) {
				if (b_object_class( object1 ) == b_object_class( object2 )) {
					int* value1 = (int*) b_object_data( object1 );
					int* value2 = (int*) b_object_data( object2 );
					if (*value1 == *value2) {
						if (value1[1] == value2[1]) {
							char* text1 = (char*) (value1 + 2);
							char* text2 = (char*) (value2 + 2);
							cond = strcmp(text1, text2);
						} else {
							cond = 1;
						}
					} else {
						cond = 1;
					}
				} else {
					cond = 1;
				}
			} else {
				cond = object1 != object2;
			}
			self->cstack--;
			if (!(object = b_object_new_boolean(self, cond))) {
				return B_BNI_FAIL;
			}
			*self->cstack = object;
#ifdef _DEBUG_
			printf("[% 8d] nequal=%d", pc);
#endif
			break;
		}
		case V_OPCODE_HIGH: {
			int cond;
			b_object_t* object1 = self->cstack[-1];
			b_object_t* object2 = self->cstack[0];
			if (!object1 || !object2) {
				return b_bni_throw_null_pointer_exception(self);
			}
			if (b_object_class( object1 ) == self->numberClass->index) {
				if (b_object_class( object1 ) == b_object_class( object2 )) {
					double* value1 = (double*) b_object_data( object1 );
					double* value2 = (double*) b_object_data( object2 );
					cond = *value1 > *value2;
				} else {
					cond = 0;
				}
			} else if (b_object_class( object1 ) == self->stringClass->index) {
				if (b_object_class( object1 ) == b_object_class( object2 )) {
					int* value1 = (int*) b_object_data( object1 );
					int* value2 = (int*) b_object_data( object2 );
					char* text1 = (char*) (value1 + 2);
					char* text2 = (char*) (value2 + 2);
					cond = strcmp(text1, text2) > 0;
				} else {
					cond = 0;
				}
			} else {
				cond = 0;
			}
			self->cstack--;
			if (!(object = b_object_new_boolean(self, cond))) {
				return B_BNI_FAIL;
			}
			*self->cstack = object;
#ifdef _DEBUG_
			printf("[% 8d] high=%d", pc);
#endif
			break;
		}
		case V_OPCODE_LOW: {
			int cond;
			b_object_t* object1 = self->cstack[-1];
			b_object_t* object2 = self->cstack[0];
			if (!object1 || !object2) {
				return b_bni_throw_null_pointer_exception(self);
			}
			if (b_object_class( object1 ) == self->numberClass->index) {
				if (b_object_class( object1 ) == b_object_class( object2 )) {
					double* value1 = (double*) b_object_data( object1 );
					double* value2 = (double*) b_object_data( object2 );
					cond = *value1 < *value2;
				} else {
					cond = 0;
				}
			} else if (b_object_class( object1 ) == self->stringClass->index) {
				if (b_object_class( object1 ) == b_object_class( object2 )) {
					int* value1 = (int*) b_object_data( object1 );
					int* value2 = (int*) b_object_data( object2 );
					char* text1 = (char*) (value1 + 2);
					char* text2 = (char*) (value2 + 2);
					cond = strcmp(text1, text2) < 0;
				} else {
					cond = 0;
				}
			} else {
				cond = 0;
			}
			self->cstack--;
			if (!(object = b_object_new_boolean(self, cond))) {
				return B_BNI_FAIL;
			}
			*self->cstack = object;
#ifdef _DEBUG_
			printf("[% 8d] low=%d", pc);
#endif
			break;
		}
		case V_OPCODE_HIGHEQUAL: {
			int cond;
			b_object_t* object1 = self->cstack[-1];
			b_object_t* object2 = self->cstack[0];
			if (!object1 || !object2) {
				return b_bni_throw_null_pointer_exception(self);
			}
			if (b_object_class( object1 ) == self->numberClass->index
					|| b_object_class( object1 ) == self->booleanClass->index) {
				if (b_object_class( object1 ) == b_object_class( object2 )) {
					double* value1 = (double*) b_object_data( object1 );
					double* value2 = (double*) b_object_data( object2 );
					cond = *value1 >= *value2;
				} else {
					cond = 0;
				}
			} else if (b_object_class( object1 ) == self->stringClass->index) {
				if (b_object_class( object1 ) == b_object_class( object2 )) {
					int* value1 = (int*) b_object_data( object1 );
					int* value2 = (int*) b_object_data( object2 );
					char* text1 = (char*) (value1 + 2);
					char* text2 = (char*) (value2 + 2);
					cond = strcmp(text1, text2) >= 0;
				} else {
					cond = 0;
				}
			} else {
				cond = 0;
			}
			self->cstack--;
			if (!(object = b_object_new_boolean(self, cond))) {
				return B_BNI_FAIL;
			}
			*self->cstack = object;
#ifdef _DEBUG_
			printf("[% 8d] highequal=%d", pc);
#endif
			break;
		}
		case V_OPCODE_LOWEQUAL: {
			int cond;
			b_object_t* object1 = self->cstack[-1];
			b_object_t* object2 = self->cstack[0];
			if (!object1 || !object2) {
				return b_bni_throw_null_pointer_exception(self);
			}
			if (b_object_class( object1 ) == self->numberClass->index
					|| b_object_class( object1 ) == self->booleanClass->index) {
				if (b_object_class( object1 ) == b_object_class( object2 )) {
					double* value1 = (double*) b_object_data( object1 );
					double* value2 = (double*) b_object_data( object2 );
					cond = *value1 <= *value2;
				} else {
					cond = 0;
				}
			} else if (b_object_class( object1 ) == self->stringClass->index) {
				if (b_object_class( object1 ) == b_object_class( object2 )) {
					int* value1 = (int*) b_object_data( object1 );
					int* value2 = (int*) b_object_data( object2 );
					char* text1 = (char*) (value1 + 2);
					char* text2 = (char*) (value2 + 2);
					cond = strcmp(text1, text2) <= 0;
				} else {
					cond = 0;
				}
			} else {
				cond = 0;
			}
			self->cstack--;
			if (!(object = b_object_new_boolean(self, cond))) {
				return B_BNI_FAIL;
			}
			*self->cstack = object;
#ifdef _DEBUG_
			printf("[% 8d] lowequal=%d", pc);
#endif
			break;
		}
		case V_OPCODE_SUM: {
			b_object_t* object1 = self->cstack[-1];
			b_object_t* object2 = self->cstack[0];
			self->cstack--;
			if (!object1 || !object2) {
				return b_bni_throw_null_pointer_exception(self);
			}
			if (b_object_class( object1 ) == self->numberClass->index) {
				double* value1 = (double*) b_object_data( object1 );
				double* value2 = (double*) b_object_data( object2 );
				double value = *value1 + *value2;
				if (!(object = b_object_new_number(self, value))) {
					return B_BNI_FAIL;
				}
			} else if (b_object_class( object1 ) == self->stringClass->index) {
				int* value1 = (int*) b_object_data( object1 );
				int* value2 = (int*) b_object_data( object2 );
				char* text1 = (char*) (value1 + 2);
				char* text2 = (char*) (value2 + 2);
				char* chars = b_memory_alloc(self, 2 * sizeof(int) + (*value1
						+ *value2 + 1) * sizeof(char));
				strcat ( chars , text1 );
				strcat ( chars , text2 );
				if (!(object = b_object_new_string(self, chars))) {
					return B_BNI_FAIL;
				}
				b_memory_free(chars);
			}
			*self->cstack = object;
#ifdef _DEBUG_
			printf("[% 8d] sum=%d", pc);
#endif
			break;
		}
		case V_OPCODE_SUB: {
			b_object_t* object1 = self->cstack[-1];
			b_object_t* object2 = self->cstack[0];
			self->cstack--;
			if (!object1 || !object2) {
				return b_bni_throw_null_pointer_exception(self);
			}
			double* value1 = (double*) b_object_data( object1 );
			double* value2 = (double*) b_object_data( object2 );
			if (b_object_class( object1 ) == self->numberClass->index) {
				double value = *value1 - *value2;
				if (!(object = b_object_new_number(self, value))) {
					return B_BNI_FAIL;
				}
			}
			*self->cstack = object;
#ifdef _DEBUG_
			printf("[% 8d] sub=%d", pc);
#endif
			break;
		}
		case V_OPCODE_MUL: {
			b_object_t* object1 = self->cstack[-1];
			b_object_t* object2 = self->cstack[0];
			self->cstack--;
			if (!object1 || !object2) {
				return b_bni_throw_null_pointer_exception(self);
			}
			double* value1 = (double*) b_object_data(object1);
			double* value2 = (double*) b_object_data(object2);
			if (b_object_class( object1 ) == self->numberClass->index) {
				double value = *value1 * *value2;
				if (!(object = b_object_new_number(self, value))) {
					return B_BNI_FAIL;
				}
			}
			*self->cstack = object;
#ifdef _DEBUG_
			printf("[% 8d] mul=%d", pc);
#endif
			break;
		}
		case V_OPCODE_DIV: {
			b_object_t* object1 = self->cstack[-1];
			b_object_t* object2 = self->cstack[0];
			self->cstack--;
			if (!object1 || !object2) {
				return b_bni_throw_null_pointer_exception(self);
			}
			double* value1 = (double*) b_object_data( object1 );
			double* value2 = (double*) b_object_data( object2 );
			if (b_object_class( object1 ) == self->numberClass->index) {
				double value = *value1 / *value2;
				if (!(object = b_object_new_number(self, value))) {
					return B_BNI_FAIL;
				}
			}
			*self->cstack = object;
#ifdef _DEBUG_
			printf("[% 8d] div=%d", pc);
#endif
			break;
		}
		case V_OPCODE_FJUMP: {
			object = *self->cstack;
			if (object && !b_object_data(object)) {
				self->cmemory = self->buffer + b_opcode_index(ir) - 1;
			}
			self->cstack--;
#ifdef _DEBUG_
			printf("[% 8d] fjump=%d", pc, b_opcode_index(ir));
#endif
			break;
		}
		case V_OPCODE_TJUMP: {
			object = *self->cstack;
			if (object && b_object_data(object)) {
				self->cmemory = self->buffer + b_opcode_index(ir) - 1;
			}
			self->cstack--;
#ifdef _DEBUG_
			printf("[% 8d] tjump=%d", pc, b_opcode_index(ir));
#endif
			break;
		}
		case V_OPCODE_GETFIELD: {
			b_chars_t
					* chars =
							b_arrayp_get_typed(b_chars_t, self->constants, b_opcode_index(ir));
			object = *self->cstack;
			*self->cstack
					= b_hashclose_get1(b_object_hash_field(object), chars);
#ifdef _DEBUG_
			sp = self->cstack - self->stack;
			printf("[% 8d] getfield=%d[s%d]", pc, b_opcode_index(ir), sp);
#endif
			break;
		}
		case V_OPCODE_SETFIELD: {
			b_chars_t
					* chars =
							b_arrayp_get_typed(b_chars_t, self->constants, b_opcode_index(ir));
			object = *self->cstack;
			b_hashclose_put1(b_object_hash_field(object), chars,
					self->cstack[-1]);
			self->cstack -= 2;
#ifdef _DEBUG_
			sp = self->cstack - self->stack;
			printf("[% 8d] setfield=%d [s%d]", pc, b_opcode_index(ir), sp);
#endif
			break;
		}
		case V_OPCODE_SET_STATIC_FIELD: {
			b_chars_t
					* chars =
							b_arrayp_get_typed(b_chars_t, self->constants, b_opcode_index(ir));
			object = *self->cstack;
			if (object) {
				b_object_used(object) += 1;
			}
			object = b_hashnew_put2(self->sfields, chars, object);
			if (object) {
				b_object_used(object) -= 1;
			}
			self->cstack--;
#ifdef _DEBUG_
			sp = self->cstack - self->stack;
			printf("[% 8d] set_static_field %d[s%d]", pc, b_opcode_index(ir),
					sp);
#endif
			break;
		}
		case V_OPCODE_GET_STATIC_FIELD: {
			b_chars_t
					* chars =
							b_arrayp_get_typed(b_chars_t, self->constants, b_opcode_index(ir));
			self->cstack++;
			*self->cstack = b_hashnew_get2(self->sfields, chars);
#ifdef _DEBUG_
			sp = self->cstack - self->stack;
			printf("[% 8d] get_static_field %d[s%d]", pc, b_opcode_index(ir),
					sp);
#endif
			break;
		}
		default: {
			printf("opcode %d not implemented\n", b_opcode(ir));
			b_so_out_flush();
			exit(0);
		}
		}
#ifdef _DEBUG_
		printf("\n\t{");
		sp = self->cstack - self->stack;
		for (index = 1; index <= sp; index++) {
			object = self->stack[index];
			if (object) {
				int classindex = b_object_class(object);
				b_class_t* clazz = b_arrayp_get(self->classs,classindex);
				printf("%s %x", clazz->name, object);
				if (self->booleanClass->index == classindex) {
					printf(" = ");
					if (b_object_data(object)) {
						printf("true");
					} else {
						printf("false");
					}
				} else if (self->numberClass->index == classindex) {
					printf(" = %f", *(double*) b_object_data(object));
				}
			} else {
				printf("0");
			}
			if (index != sp) {
				printf(",");
			}
		}
		printf("}\n");
		printf("\t{");
		int size = b_array_size(self->stackTrace);
		for (index = 0; index < size; index++) {
			char* methodname = b_arrayp_get(self->stackTrace,index);
			printf("%s", methodname);
			if (index != size - 1) {
				printf(",");
			}
		}
		printf("}\n");
		b_so_out_flush();
#endif
		self->cmemory++;
		if (b_linker_size(self->objects) > 0) {
			b_gc_start(self);
		}
	}
	return 1;
}

int b_vm_opcode_inc(b_vm_t* self, int value) {
	self->cstack += value;
	return 1;
}

int b_vm_opcode_dec(b_vm_t* self, int value) {
	self->cstack -= value;
	return 1;
}

int b_vm_opcode_sjump(b_vm_t* self, int method_index) {
	b_method_t* method =
			b_arrayp_get_typed( b_method_t , self->methods , method_index );
	if (!(b_array_add(self->stackTrace, self, method->name))) {
		return B_BNI_FAIL;
	}
	self->cstack = self->stack;
	self->memory = self->cmemory = self->buffer + method->memIndex;
	self->cmemstack = self->memstack;
	return b_vm_exec(self);
}

b_object_t* b_vm_opcode_object_pop(b_vm_t* self) {
	b_object_t* object = self->cstack[0];
	self->cstack--;
	return object;
}

int b_vm_opcode_ojump(register b_vm_t* self, const char* methodname, int params) {
	b_object_t** cstack = self->cstack;
	b_object_t** stack = self->stack;
	int* memory = self->memory;
	int* cmemory = self->cmemory;
	int* cmemstack = self->cmemstack;
	int* memstack = self->memstack;
	int state = 0;
	{
		b_object_t* object = self->cstack[-params];
		b_method_t* method;
		if (b_object_hash_method(object)) {
			method = (b_method_t*) b_hashclose_get(
					b_object_hash_method(object), methodname);
		} else {
			int index = b_object_class(object);
			b_hashclose_t* hash = b_object_hash_method(
					b_arrayp_get_typed(b_object_t, self->caches, index));
			method = (b_method_t*) b_hashclose_get(hash, methodname);
		}
		self->memstack = self->cmemstack;
		if (!(b_array_add(self->stackTrace, self, method->name))) {
			return B_BNI_FAIL;
		}
		if (method) {
			self->params = params;
			if (method->isNative) {
				b_call_function( void , method->isNative , (b_vm_t*,b_object_t*))(
						self, object);
			} else {
				self->memory = self->cmemory = self->buffer + method->memIndex;
				state = b_vm_exec(self);
			}
		}
		b_array_dec( self->stackTrace );
	}
	self->memory = memory;
	self->cmemory = cmemory;
	self->memstack = memstack;
	self->cmemstack = cmemstack;
	self->stack = stack;
	self->cstack = cstack;
	return state;
}

void b_vm_register_method(b_vm_t* self) {
	int n, m, c, nsize = b_array_size( self->libraryFuncs ), csize =
			b_array_size( self->classs );
	for (n = 0; n < nsize; n++) {
		void* library = b_arrayp_get_typed( void , self->libraryFuncs, n );
		void* func = b_so_dynamic_get(library, "b_native_func");
		if (func) {
			for (c = 0; c < csize; c++) {
				b_class_t* clazz =
						b_arrayp_get_typed( b_class_t , self->classs , c );
				int msize = b_array_size( clazz->methods );
				for (m = 0; m < msize; m++) {
					b_method_t
							* method =
									b_arrayp_get_typed( b_method_t , clazz->methods , m );
					b_call_function( void , func , (char*,char*,int) )(
							clazz->name, method->name, method->index);
				}
			}
		}
	}
}

int b_vm_execute(b_vm_t* self, const char* classname, const char* methodname) {
	b_vm_register_method(self);
	b_vm_init_load_class(self);
	b_vm_init_cache(self);
	return b_vm_init(self, classname, methodname);
}

b_vm_t* b_vm_new() {
	b_vm_t* self = calloc(1, sizeof(b_vm_t));
	self->returns = calloc(1024, sizeof(__inst) );
	self->stack = calloc( 1024 * 1024 , sizeof(__inst) );
	self->buffer = calloc( 1024 * 1024 , sizeof(__inst) );
	self->memstack = calloc( 1024 , sizeof(__inst ) );
	self->constants = b_arrayp_new();
	self->stackTrace = b_arrayp_new();
	self->doubleConstants = b_arrayp_new();
	self->stringConstants = b_arrayp_new();
	self->librarys = b_arrayp_new();
	self->libraryPaths = b_arrayp_new();
	self->nativeMethods = b_arrayp_new();
	self->libraryFuncs = b_arrayp_new();
	self->classs = b_arrayp_new();
	self->hashes = b_arrayp_new();
	self->memIndexs = b_arrayp_new();
	self->caches = b_arrayp_new();
	self->objects = b_linker_new();
	self->sfields = b_hashp_new(null);
	self->classhash = b_hashp_new(null);
	b_linker_add_last(self->objects , self,0 );
	self->methods = b_arrayp_new();
	self->nativeMethods = b_arrayp_new();
	self->output = b_char_dup("binary.b");
	return self;
}

void b_vm_free(b_vm_t* self) {
	free(self->returns);
	free(self->stack);
	free(self->buffer);
	b_arrayp_free1(self->classs, b_class_free);
	b_arrayp_free1(self->memIndexs, free);
	b_arrayp_free1(self->caches, b_object_free);
	b_linker_free1(self->objects, b_object_free);
	free(self);
}

void b_vm_init_load_class(b_vm_t* self) {
	self->objectClass = b_vm_get_class(self, "breder.lang.Object");
	self->numberClass = b_vm_get_class(self, "breder.lang.Number");
	self->booleanClass = b_vm_get_class(self, "breder.lang.Boolean");
	self->stringClass = b_vm_get_class(self, "breder.lang.String");
}

b_object_t* b_vm_new_object(b_vm_t* vm, b_class_t* clazz) {
	b_object_t* ocache =
			b_arrayp_get_typed( b_object_t , vm->caches , clazz->index );
	b_object_t* object = calloc(1, b_object_size());
	memcpy (object, ocache, b_object_size_noproxy());
	if (!(b_linker_add_last(vm->objects, vm, object))) {
		return B_BNI_FAIL;
	}
	return object;
}

int b_vm_throw_generic_exception(b_vm_t* self, char* classname) {
	b_class_t* clazz = b_vm_get_class(self, classname);
	b_object_t* object = b_vm_new_object(self, clazz);
	self->othrow = object;
	b_arrayp_t* data = b_arrayp_new();
	int n, size = b_array_size(self->stackTrace);
	for (n = 0; n < size; n++) {
		char* stacktrace = b_arrayp_get_typed(char, self->stackTrace, n);
		if (!(b_array_add(data, self, stacktrace))) {
			return B_BNI_FAIL;
		}
	}
	b_object_set_data(object, data);
	return 0;
}

int b_vm_throw_null_pointer_runtime_exception(b_vm_t* self) {
	return b_vm_throw_generic_exception(self,
			"breder.lang.NullPointerRuntimeException");
}

int b_vm_throw_cast_runtime_exception(b_vm_t* self) {
	return b_vm_throw_generic_exception(self,
			"breder.lang.CastRuntimeException");
}

int b_vm_init_native_method(b_vm_t* self) {
	int n, m, size = b_array_size( self->librarys );
	for (n = 0; n < size; n++) {
		b_char_t* pathname = b_arrayp_get_typed( b_char_t , self->librarys , n );
		void* library = b_so_dynamic_load(pathname);
		if (!library) {
			for (m = 0; m < b_array_size(self->libraryPaths); m++) {
				b_char_t* lp =
						b_arrayp_get_typed( b_char_t , self->libraryPaths , m );
				char* path = b_char_new3(lp, "/", pathname);
				library = b_so_dynamic_load(path);
				b_char_free(path);
				if (library)
					break;
			}
		}
		if (!library) {
			printf("Cannot open the library '%s'\n", pathname);
			exit(0);
		}
		if (!(b_array_add(self->libraryFuncs, self, library))) {
			return B_BNI_FAIL;
		}
	}
	return 1;
}

int b_vm_init(b_vm_t* self, const char* classname, const char* methodname) {
	int n, m;
	b_method_t* aux = 0;
	int size = b_array_size(self->classs);
	for (n = 0; n < size; n++) {
		b_class_t* class = b_arrayp_get_typed( b_class_t , self->classs , n );
		if (!strcmp(class->name, classname)) {
			int size2 = b_array_size(class->methods);
			for (m = 0; m < size2; m++) {
				b_method_t* method =
						b_arrayp_get_typed( b_method_t , class->methods , m );
				if (!strcmp(methodname, method->name)) {
					aux = method;
					break;
				}
			}
			if (aux) {
				break;
			}
		}
	}
	{
		size = b_array_size( self->methods );
		for (n = 0; n < size; n++) {
			b_method_t* method =
					b_arrayp_get_typed( b_method_t , self->methods , n );
			if (method == aux) {
				return n;
			}
		}
	}
	return b_error_put("not found the classname starter in the class loadeds");
}

void b_vm_init_cache(b_vm_t* self) {
	int n, size = b_array_size(self->classs);
	for (n = 0; n < size; n++) {
		b_class_t* class = b_arrayp_get_typed( b_class_t , self->classs , n );
		b_arrayp_add (self->caches, b_object_new(self, class));
		b_arrayp_add (self->hashes, b_object_cache_methods(class));
	}
	{
		self->ostring
				= b_arrayp_get_typed( b_object_t , self->caches , self->stringClass->index );
		self->onumber
				= b_arrayp_get_typed( b_object_t , self->caches , self->numberClass->index );
		self->oboolean
				= b_arrayp_get_typed( b_object_t , self->caches , self->booleanClass->index );
	}
	{
		self->ostringl = b_object_size_noproxy();
		self->onumberl = b_object_size_noproxy();
		self->obooleanl = b_object_size_noproxy();
	}

}

char* b_vm_load(b_vm_t* self, const char* filename) {
	int n, ok;
	char* classname;
	void* file = b_file_open_read((void*) filename);
	if (!file) {
		printf("Can not found '%s' file.\n", filename);
		goto end;
	}
	{
		int code = b_file_read(file);
		if (code != 'B') {
			printf("Not a Breder binary '%s'.\n", filename);
			goto end;
		}
		code = b_file_read(file);
		if (code != 'B') {
			printf("Not a Breder binary '%s'.\n", filename);
			goto end;
		}
	}
	char c;
	{
		int len;
		b_file_scan1(file, "(%d,", &len);
		classname = calloc(len + 1, sizeof(char));
		b_file_read_buffer(file, classname, len * sizeof(char));
		b_file_read(file);
	}
	{
		c = b_file_read(file); // {
		c = b_file_read(file); // (
		while (c == '(') {
			int size;
			b_file_scan1(file, "%d,", &size);
			char* name = calloc(size + 1, sizeof(char));
			b_file_read_buffer(file, name, size);
			b_file_read(file); // )
			c = b_file_read(file); // ( || }
			b_vm_add_const(self, name);
			free(name);
		}
	}
	{
		c = b_file_read(file); // {
		c = b_file_read(file); // (
		while (c == '(') {
			int size;
			b_file_scan1(file, "%d,", &size);
			char* name = calloc(size + 1, sizeof(char));
			b_file_read_buffer(file, name, size);
			b_file_read(file); // )
			c = b_file_read(file); // ( || }
			b_vm_add_const_string(self, name);
			free(name);
		}
	}
	{
		c = b_file_read(file); // {
		c = b_file_read(file); // (
		while (c == '(') {
			int size;
			b_file_scan1(file, "%d,", &size);
			char* name = calloc(size + 1, sizeof(char));
			b_file_read_buffer(file, name, size);
			b_file_read(file); // )
			c = b_file_read(file); // ( || }
			b_vm_add_const_number(self, name);
			free(name);
		}
	}
	{
		c = b_file_read(file); // {
		c = b_file_read(file); // (
		while (c == '(') {
			int size;
			b_file_scan1(file, "%d,", &size);
			char* name = calloc(size + 1, sizeof(char));
			b_file_read_buffer(file, name, size);
			b_file_read(file); // )
			c = b_file_read(file); // ( || }
			b_vm_add_library(self, name);
		}
	}
	if (!b_vm_init_native_method(self)) {
		return 0;
	}
	{
		if (b_file_read(file) != '{')
			goto end; // {
		{
			int staticFields;
			b_file_scan1(file, "%d,", &staticFields);
			b_vm_set_static_fields(self, staticFields);
		}
		c = b_file_read(file); // (
		while (c == '(') {
			int classindex;
			b_file_scan1(file, "%d,", &classindex);
			aint* extends = b_vm_read_ints(file);
			b_file_scan0(file, ",");
			aint* fields = b_vm_read_ints(file);
			classindex = b_vm_add_class(self, classindex);
			b_vm_set_class_extend(self, classindex, extends);
			ok = b_vm_set_class_fields(self, classindex, fields);
			if (!ok)
				return 0;
			{
				int n, size = b_array_size(self->libraryFuncs);
				b_chars_t
						* classname =
								b_arrayp_get_typed(b_chars_t, self->constants, classindex);
				char* cclassname = b_char_replace2((char*) b_chars_text(
						classname), '.', '_');
				char* method = b_char_new2(cclassname, "$init$");
				for (n = 0; n < size; n++) {
					void* library = b_arrayp_get(self->libraryFuncs, n);
					void* func = b_so_dynamic_get(library, method);
					if (func) {
						if (!b_call_function(int, func, ())()) {
							return 0;
						}
					}
				}
				free(cclassname);
				free(method);
			}
			while (true) {
				if (b_file_read(file) == '[') {
					int nameIndex, memIndex, returns, isStatic, isNative,
							isAbstract, paramCount;
					b_file_scan2(file, "%d,%d,", &returns, &isStatic);
					b_file_scan2(file, "%d,%d,", &isNative, &isAbstract);
					b_file_scan2(file, "%d,%d,", &nameIndex, &memIndex);
					b_file_scan1(file, "%d(", &paramCount);
					b_method_t* method = b_vm_add_method(self, classindex,
							nameIndex);
					b_chars_t* cname = b_vm_get_const(self, nameIndex);
					const char* name = b_chars_text(cname);
					if (name[0] == '<') {
						int* value = calloc(1, sizeof(int));
						*value = memIndex;
						b_arrayp_add( self->memIndexs , value );
					}
					b_class_t
							* clazz =
									b_arrayp_get_typed(b_class_t, self->classs, classindex);
					method->cname = b_chars_new1(b_char_new3(clazz->name, "$",
							name));
					method->absoluteName = b_chars_new1(b_char_new4("_",
							clazz->name, "$", name));
					method->returns = returns;
					method->isStatic = isStatic;
					method->isAbstract = isAbstract != 0;
					method->memIndex = memIndex;
					method->params = calloc(paramCount + 1, sizeof(int));
					method->params[0] = paramCount;
					for (n = 0; n < paramCount; n++) {
						int index;
						b_file_scan1(file, "%d", &index);
						method->params[n + 1] = index;
						if (n != paramCount - 1) {
							b_file_read(file);
						}
					}
					b_file_scan0(file, ")]");
				} else
					break;
			}
			c = b_file_read(file); // (
		}
		if (c != '}')
			goto end;
	}
	{
		c = b_file_read(file); // {
		c = b_file_read(file); // (
		while (c == '(') {
			int method_index, size;
			b_file_scan2(file, "%d,%d,", &method_index, &size);
			char* name = calloc(size + 1, sizeof(char));
			b_file_read_buffer(file, name, size);
			b_file_read(file); // )
			c = b_file_read(file); // ( || }
			b_method_t * method =
					b_arrayp_get_typed(b_method_t, self->methods, method_index);
			method->isNative = b_vm_add_native_method(self, name);
		}
	}
	{
		int n, m, size = b_array_size(self->classs);
		for (n = 0; n < size; n++) {
			b_class_t* class = b_arrayp_get_typed(b_class_t, self->classs, n);
			aint* extends = class->extendindex;
			for (m = 0; m < b_aint_size(extends); m++) {
				int index = b_aint_get(extends, m);
				b_class_t* oclass =
						b_arrayp_get_typed( b_class_t , self->classs , index );
				b_arrayp_add( class->extends , oclass );
			}
		}
	}
	{
		for (;;) {
			__inst i = 0, i1, i2, i3, i4;
			i += (i1 = b_file_read(file)) << 24;
			i += (i2 = b_file_read(file)) << 16;
			i += (i3 = b_file_read(file)) << 8;
			i += (i4 = b_file_read(file)) << 0;
			if (i1 == -1 && i2 == -1 && i3 == -1 && i4 == -1)
				break;
			if (i1 == -1 || i2 == -1 || i3 == -1 || i4 == -1) {
				if (b_file_eof(file))
					break;
				printf("read error : %s\n", b_file_error(file));
				b_so_exit(3);
			}
			b_vm_add_opcode(self, i);
		}
		self->buffer = realloc(self->buffer, self->bufferSize * sizeof(__inst) );
	}
	b_file_close ( file );
	return classname;
	end :
	{
		if (file) {
			b_file_close ( file );
		}
		b_error_put("[error] : error in the binary file\n");
		return 0;
	}
}

		// Método que faz a leitura de arquivo na syntax : '(' ( %d ( ',' %d )* )? ')'
aint* b_vm_read_ints(FILE* f) {
	b_arrayp_t* array = b_arrayp_new();
	int c = b_file_read(f);
	if (c != '(') {
		goto error;
	}
	while (true) {
		int size, state = b_file_scan1(f, "%d", &size);
		if (!state || state == EOF) {
			c = b_file_read(f);
			if (c == ')') {
				break;
			} else {
				goto error;
			}
		} else {
			int* pint = malloc(sizeof(int));
			*pint = size;
			b_arrayp_add(array, pint);
			c = b_file_read(f);
			if (c == ',') {
				continue;
			} else if (c == ')') {
				break;
			} else {
				goto error;
			}
		}
	}
	int size = b_array_size(array);
	aint* data = calloc(size + 1, sizeof(int));
	int* result = data;
	*result++ = size;
	int n;
	for (n = 0; n < size; n++) {
		*result++ = *b_arrayp_get_typed(int, array, n);
	}
	return data;
	error: {
		b_arrayp_free1(array, free);
		return 0;
	}
}

b_chars_t* b_vm_get_const(b_vm_t* self, unsigned int index) {
	return b_arrayp_get_typed(b_chars_t, self->constants, index);
}

void b_vm_call_native(b_vm_t* self, char* constant) {
	printf("call native\n");
}

int b_vm_add_const(b_vm_t* self, const char* constant) {
	int size = b_array_size( self->constants );
	b_arrayp_add(self->constants, b_chars_new(constant));
	return size;
}

int b_vm_add_const_string(b_vm_t* self, const char* constant) {
	int size = b_array_size( self->stringConstants );
	int len = strlen(constant);
	int* data = calloc(1, 2 * sizeof(int) + (len + 1) * sizeof(char));
	*data = len;
	strcpy ((char*)(data+2), constant);
	b_arrayp_add(self->stringConstants, data);
	return size;
}

int b_vm_add_const_number(b_vm_t* self, const char* constant) {
	double value = atof(constant);
	int size = b_array_size( self->doubleConstants );
	double* pointer = calloc(1, sizeof(double));
	*pointer = value;
	b_arrayp_add(self->doubleConstants, pointer);
	return size;
}

int b_vm_add_library(b_vm_t* self, const char* library) {
	int n, size = b_array_size(self->librarys);
	for (n = 0; n < size; n++) {
		b_char_t* c = b_arrayp_get_typed( b_char_t , self->librarys , n );
		if (!strcmp(c, library))
			return n;
	}
	b_arrayp_add(self->librarys, b_char_dup(library));
	return size;
}

void* b_vm_add_native_method(b_vm_t* self, char* nativeMethod) {
	int n, size = b_array_size( self->libraryFuncs );
	for (n = 0; n < size; n++) {
		void* library = b_arrayp_get_typed( void , self->libraryFuncs , n );
		void* func = b_so_dynamic_get(library, nativeMethod);
		if (func) {
			return func;
		}
	}
	{
		printf("Not found native method '%s'", nativeMethod);
		exit(0);
	}
}

int b_vm_add_class(b_vm_t* self, int classnameIndex) {
	b_class_t* class = b_class_new();
	b_chars_t* classname =
			b_arrayp_get_typed(b_chars_t, self->constants, classnameIndex);
	class->name = b_char_dup(b_chars_text(classname));
	class->nameindex = classnameIndex;
	int size = b_array_size(self->classs);
	class->index = size;
	b_arrayp_add(self->classs, class);
	b_hashnew_put2(self->classhash, classname, class);
	return size;
}

void b_vm_set_class_extend(b_vm_t* self, int classnameIndex, aint* extendindex) {
	b_class_t* class =
			b_arrayp_get_typed( b_class_t , self->classs , classnameIndex );
	class->extendindex = extendindex;
}

int b_vm_set_class_fields(b_vm_t* self, int classIndex, aint* fields) {
	if (classIndex < 0 || classIndex > b_array_size(self->classs) - 1)
		return 0;
	b_class_t* class =
			b_arrayp_get_typed( b_class_t , self->classs , classIndex );
	int n, size = b_aint_size(fields);
	for (n = 0; n < size; n++) {
		int nameIndex = b_aint_get(fields, n++);
		int isStatic = b_aint_get(fields, n);
		b_chars_t* name =
				b_arrayp_get_typed(b_chars_t, self->constants, nameIndex);
		b_field_t* field = b_field_new();
		field->cname = name;
		if (isStatic) {
			b_arrayp_add(class->cfields, field);
		} else {
			b_arrayp_add(class->fields, field);
		}
	}
	return 1;
}

b_method_t* b_vm_add_method(b_vm_t* self, int classIndex, int methodnameIndex) {
	b_class_t* class =
			b_arrayp_get_typed( b_class_t , self->classs , classIndex );
	b_method_t* method = b_method_new();
	b_chars_t* methodname =
			b_arrayp_get_typed(b_chars_t, self->constants, methodnameIndex);
	method->name = b_char_dup(b_chars_text(methodname));
	method->nameIndex = methodnameIndex;
	method->memIndex = b_array_size( self->methods );
	method->index = b_array_size( self->methods );
	b_arrayp_add(self->methods, method);
	b_class_add_method(class, method);
	return method;
}

void b_vm_link_method(b_vm_t* self, int classindex, int methodIndex) {
	b_class_t* class =
			b_arrayp_get_typed( b_class_t , self->classs , classindex );
	b_method_t* method =
			b_arrayp_get_typed( b_method_t , class->methods , methodIndex );
	method->memIndex = self->bufferSize;
}

void b_vm_add_cache(b_vm_t* self, b_class_t* class) {
	b_arrayp_add(self->classs, class);
	b_arrayp_add(self->caches, b_object_new(self,class));
}

void b_vm_add_linkerpath(b_vm_t* self, b_char_t* linkerpath) {
	b_arrayp_add(self->libraryPaths, linkerpath);
}

void b_vm_set_output(b_vm_t* self, b_char_t* output) {
	self->output = b_char_dup(output);
}

__inst *b_vm_add_opcode(b_vm_t* self, __inst opcode) {
	self->buffer[self->bufferSize++] = opcode;
	return &self->buffer[self->bufferSize - 1];
}

int b_vm_get_opcode_index(b_vm_t* self) {
	return self->bufferSize;
}

b_class_t* b_vm_get_class(b_vm_t* self, char* name) {
	int n, sizen;
	sizen = b_array_size( self->classs );
	for (n = 0; n < sizen; n++) {
		b_class_t* class = b_arrayp_get_typed( b_class_t , self->classs , n );
		if (!strcmp(class->name, name))
			return class;
	}
	return 0;
}

void b_vm_set_static_fields(b_vm_t* self, int count) {
	self->static_objects = b_arrayp_new();
	b_array_set_size(self->static_objects, count);
}
