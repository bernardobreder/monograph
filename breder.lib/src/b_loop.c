#include "breder.h"
#ifdef _DEBUG_
#include <time.h>
#endif

#define b_vm_exec_rollback() \
		if (self->cprogthrowstack == self->progthrowstack) { \
			return B_STATE_FAIL; \
		} else { \
			self->cprogmem = self->progmem + *self->cprogthrowstack; \
		}

#define b_vm_exec_test(value,func) \
		if (value) { \
			func(self); \
			b_vm_exec_rollback(); \
		}

int b_vm_exec (register b_vm_t* self) {
	register __inst ir, index1;
	register b_object_t* object, *object2;
	for (;;) {
#ifdef _DEBUG_
		int pc = self->cprogmem - self->progmem;
		int sp = self->cobjstack - self->objstack;
#endif
		if (self->cobjstack - self->objstack >= B_VM_STACK_MAX) {
			printf ("Stack Overflow\n");
			exit (0);
		}
		switch (b_opcode(ir = *self->cprogmem++)) {
			case B_OPCODE_CONST_NIL : {
				* ++ self->cobjstack = null;
				b_log_print("[% 8d] null\n", pc);
				break;
			}
			case B_OPCODE_CONST_NIL_2_TIME : {
				* ++ self->cobjstack = null;
				* ++ self->cobjstack = null;
				b_log_print("[% 8d] null_2\n", pc);
				break;
			}
			case B_OPCODE_CONST_NIL_3_TIME : {
				* ++ self->cobjstack = null;
				* ++ self->cobjstack = null;
				* ++ self->cobjstack = null;
				b_log_print("[% 8d] null_3\n", pc);
				break;
			}
			case B_OPCODE_CONST_NIL_4_TIME : {
				* ++ self->cobjstack = null;
				* ++ self->cobjstack = null;
				* ++ self->cobjstack = null;
				* ++ self->cobjstack = null;
				b_log_print("[% 8d] null_4\n", pc);
				break;
			}
			case B_OPCODE_CONST_NIL_5_TIME : {
				* ++ self->cobjstack = null;
				* ++ self->cobjstack = null;
				* ++ self->cobjstack = null;
				* ++ self->cobjstack = null;
				* ++ self->cobjstack = null;
				b_log_print("[% 8d] null_5\n", pc);
				break;
			}
			case B_OPCODE_CONST_NUMBER : {
				* ++ self->cobjstack = self->onumbers[b_opcode_index(ir)];
				b_log_print("[% 8d] constn %d\n", pc, b_opcode_index(ir));
				break;
			}
			case B_OPCODE_CONST_STRING : {
				* ++ self->cobjstack = self->ostrings[b_opcode_index(ir)];
				b_log_print("[% 8d] consts %d\n", pc, b_opcode_index(ir));
				break;
			}
			case B_OPCODE_CONST_BOOLEAN : {
				* ++ self->cobjstack = self->obooleans[b_opcode_index(ir)];
				b_log_print("[% 8d] constb %d\n", pc, b_opcode_index(ir));
				break;
			}
			case B_OPCODE_INC : {
				index1 = b_opcode_index(ir);
				memset(self->cobjstack + 1, null, index1 * sizeof(b_object_t*));
				self->cobjstack += index1;
				b_log_print("[% 8d] inc %d\n", pc, b_opcode_index(ir));
				break;
			}
			case B_OPCODE_DESC : {
				self->cobjstack -= b_opcode_index(ir);
				b_log_print("[% 8d] desc %d\n", pc, b_opcode_index(ir));
				break;
			}
			case B_OPCODE_LOAD : {
				* ++ self->cobjstack = self->cobjstack[ - b_opcode_index(ir)];
				b_log_print("[% 8d] load %d\n", pc, b_opcode_index(ir));
				break;
			}
			case B_OPCODE_STORE : {
				self->cobjstack[ - b_opcode_index(ir)] = * self->cobjstack -- ;
				b_log_print("[% 8d] store %d\n", pc, b_opcode_index(ir));
				break;
			}
			case B_OPCODE_NEW : {
				* ++ self->cobjstack = b_object_new (self, b_opcode_index(ir));
				b_log_print("[% 8d] new %d\n", pc, b_opcode_index(ir));
				break;
			}
			case B_OPCODE_ARRAY : {
				index1 = b_opcode_b_iabc(ir);
				self->cobjstack[ - index1 + 1] = self->cobjstack[b_opcode_c_iabc(ir) - index1];
				self->cobjstack += 1 - index1;
				b_log_print("[% 8d] array %d\n", pc, b_opcode_index(ir));
				break;
			}
			case B_OPCODE_JUMP : {
				self->cprogmem = self->progmem + b_opcode_index(ir);
				b_log_print("[% 8d] jump %d\n", pc, b_opcode_index(ir));
				break;
			}
			case B_OPCODE_INC_INDEX : {
				index1 = b_opcode_index(ir);
				self->cobjstack[ - index1] = b_bni_new_oindex(self, b_bni_onumber_to_primitive(self, self->cobjstack[-index1]) + 1);
				b_log_print("[% 8d] inc_index %d\n", pc, b_opcode_index(ir));
				break;
			}
			case B_OPCODE_JUMP_OBJECT : {
				index1 = b_opcode_aindex(ir);
				b_method_t* method = b_method_get(self, index1);
				object = self->cobjstack[ - b_aint_size(method->params)];
				b_vm_exec_test(!object, b_bni_throw_null_pointer_exception);
				if (ir & 0x800000) {
					method = b_method_get(self, self->mvirtual[object->cindex][index1]);
				}
				b_array_add_safely(self->stackTrace, method->name);
				if (method->isNative) {
					b_method_t* oldMethod = self->method;
					int* memstack = self->memstack;
					int* throwstack = self->progthrowstack;
					self->memstack = self->cmemstack;
					self->method = method;
					self->progthrowstack = self->cprogthrowstack;
					void * data = object->datas[self->dataIndexs[object->cindex][method->cindex]];
					index1 = b_call_function(int, method->isNative, (b_vm_t*,b_object_t*, void*)) (self, object, data);
					self->memstack = memstack;
					self->progthrowstack = throwstack;
					self->method = oldMethod;
					b_array_pop(self->stackTrace);
					if (index1 == B_STATE_FAIL) {
						b_vm_exec_rollback();
					}
				} else {
					* ++ self->cmemstack = self->cprogmem - self->progmem;
					self->cprogmem = self->progmem + method->memIndex;
				}
				b_log_print("[% 8d] ojump %d\n", pc, b_opcode_index(ir));
				break;
			}
			case B_OPCODE_JUMP_STATIC : {
				b_method_t* method = b_method_get(self, b_opcode_index(ir));
				b_array_add_safely(self->stackTrace, method->name);
				if (method->isNative) {
					b_method_t* oldMethod = self->method;
					self->method = method;
					index1 = b_call_function(int, method->isNative, (b_vm_t*)) (self);
					self->method = oldMethod;
					b_array_pop(self->stackTrace);
					if (index1 == B_STATE_FAIL) {
						b_vm_exec_rollback();
					}
				} else {
					* ++ self->cmemstack = self->cprogmem - self->progmem;
					self->cprogmem = self->progmem + method->memIndex;
				}
				b_log_print("[% 8d] sjump %d\n", pc, b_opcode_index(ir));
				break;
			}
			case B_OPCODE_JUMP_FALSE : {
				object = * self->cobjstack -- ;
				b_vm_exec_test(!object, b_bni_throw_null_pointer_exception);
				if ( ! b_object_data(object)) {
					self->cprogmem = self->progmem + b_opcode_index(ir);
				}
				b_log_print("[% 8d] fjump %d\n", pc, b_opcode_index(ir));
				break;
			}
			case B_OPCODE_JUMP_TRUE : {
				object = * self->cobjstack -- ;
				b_vm_exec_test(!object, b_bni_throw_null_pointer_exception);
				if (b_object_data(object)) {
					self->cprogmem = self->progmem + b_opcode_index(ir);
				}
				b_log_print("[% 8d] tjump %d\n", pc, b_opcode_index(ir));
				break;
			}
			case B_OPCODE_ARRAY_NEW : {
				object = b_object_new (self, b_bni_class_object_id (self));
				b_object_set_data(object, b_memory_alloc0(sizeof(int) + b_opcode_index(ir) * sizeof(b_object_t*)));
				* ++ self->cobjstack = object;
				b_log_print("[% 8d] array_new %d\n", pc, b_opcode_index(ir));
				break;
			}
			case B_OPCODE_ARRAY_RNEW : {
				object = * self->cobjstack;
				b_object_set_data(object, b_memory_realloc0(b_object_data(object), b_opcode_index(ir) * sizeof(b_object_t*)));
				b_log_print("[% 8d] array_rnew %d\n", pc, b_opcode_index(ir));
				break;
			}
			case B_OPCODE_ARRAY_GET : {
				object = self->cobjstack[ - 1];
				* self->cobjstack = ((b_object_t**) (b_object_data_typed(int, object) + 1))[b_opcode_index(ir)];
				b_log_print("[% 8d] array_get %d\n", pc, b_opcode_index(ir));
				break;
			}
			case B_OPCODE_ARRAY_SET : {
				object = self->cobjstack[ - 1];
				((b_object_t**) (b_object_data_typed(int, object) + 1))[b_opcode_index(ir)] = * self->cobjstack;
				self->cobjstack -= 2;
				b_log_print("[% 8d] array_set %d\n", pc, b_opcode_index(ir));
				break;
			}
			case B_OPCODE_ARRAY_LEN : {
				b_object_new_number (self, * b_object_data_typed(int, *self->cobjstack));
				b_log_print("[% 8d] array_len \n", pc);
				break;
			}
			case B_OPCODE_CAST_OBJECT : {
				b_vm_exec_test(!*self->cobjstack, b_bni_throw_null_pointer_exception);
				b_bni_test_fail_classcastexception(self, *self->cobjstack, b_opcode_index(ir));
				b_log_print("[% 8d] cast %d\n", pc, b_opcode_index(ir));
				break;
			}
			case B_OPCODE_CAST_NUMBER_TO_INTEGER : {
				* self->cobjstack = b_bni_new_ointeger(self, (int)b_bni_onumber_to_primitive(self, *self->cobjstack));
				b_log_print("[% 8d] castn2i %d\n", pc, b_opcode_index(ir));
				break;
			}
			case B_OPCODE_CAST_NOTNULL : {
				b_vm_exec_test(!*self->cobjstack, b_bni_throw_null_pointer_exception);
				b_log_print("[% 8d] notnull\n", pc);
				break;
			}
			case B_OPCODE_RETURN_OBJECT : {
				self->cobjstack -= b_opcode_index(ir);
				if (self->memstack == self->cmemstack) {
					return B_STATE_SUCCESS;
				}
				self->cprogmem = self->progmem + * self->cmemstack -- ;
				b_array_dec(self->stackTrace);
				b_log_print("[% 8d] ret %d\n", pc, b_opcode_index(ir));
				break;
			}
			case B_OPCODE_DUP : {
				object = * self->cobjstack;
				* ++ self->cobjstack = object;
				b_log_print("[% 8d] dup\n", pc);
				break;
			}
			case B_OPCODE_NOT : {
				object = * self->cobjstack;
				b_vm_exec_test(!object, b_bni_throw_null_pointer_exception);
				* self->cobjstack = b_object_new_boolean(self, b_object_data(object) == null);
				b_log_print("[% 8d] not\n", pc);
				break;
			}
			case B_OPCODE_GETFIELD : {
				object = * self->cobjstack;
				b_vm_exec_test(!object, b_bni_throw_null_pointer_exception);
				index1 = self->fvirtual[object->cindex][b_opcode_index(ir)];
				* self->cobjstack = object->fields[index1];
				b_log_print("[% 8d] gfield %d\n", pc, b_opcode_index(ir));
				break;
			}
			case B_OPCODE_SETFIELD : {
				object = * self->cobjstack;
				b_vm_exec_test(!object, b_bni_throw_null_pointer_exception);
				index1 = self->fvirtual[object->cindex][b_opcode_index(ir)];
				if (object->fields[index1]) {
					b_object_used(object->fields[index1]) -= 1;
				}
				object2 = self->cobjstack[ - 1];
				object->fields[index1] = object2;
				if (object2) {
					b_object_used(object2) += 1;
				}
				self->cobjstack -= 2;
				b_log_print("[% 8d] sfield %d\n", pc, b_opcode_index(ir));
				break;
			}
			case B_OPCODE_GET_FIELD_STATIC : {
				* ++ self->cobjstack = self->ofields[b_opcode_index(ir)];
				b_log_print("[% 8d] sgfield %d\n", pc, b_opcode_index(ir));
				break;
			}
			case B_OPCODE_SET_FIELD_STATIC : {
				index1 = b_opcode_index(ir);
				object = self->ofields[index1];
				if (object) {
					b_object_used(object) -= 1;
				}
				object = * self->cobjstack;
				if (object) {
					b_object_used(object) += 1;
				}
				self->ofields[index1] = object;
				self->cobjstack -- ;
				b_log_print("[% 8d] ssfield %d\n", pc, b_opcode_index(ir));
				break;
			}
			case B_OPCODE_TRY : {
				* ++ self->cobjthrowstack = self->cobjstack - self->objstack;
				* ++ self->cprogthrowstack = b_opcode_index(ir);
				* ++ self->cmemthrowstack = self->cmemstack - self->memstack;
				b_log_print("[% 8d] try %d\n", pc, b_opcode_index(ir));
				break;
			}
			case B_OPCODE_THROW : {
				self->othrow = * self->cobjstack -- ;
				if (self->progthrowstack == self->cprogthrowstack) {
					return B_STATE_FAIL;
				}
				self->cprogmem = self->progmem + * self->cprogthrowstack;
				b_log_print("[% 8d] throw\n", pc);
				break;
			}
			case B_OPCODE_THROW_TRUE_RET : {
				self->cobjthrowstack -- ;
				self->cprogthrowstack -- ;
				self->cmemthrowstack -- ;
				b_log_print("[% 8d] throw_true_ret\n", pc);
				break;
			}
			case B_OPCODE_THROW_FALSE_RET : {
				self->cobjthrowstack -- ;
				self->cprogmem = self->progmem + * -- self->cprogthrowstack;
				self->cmemthrowstack -- ;
				if (self->cprogthrowstack == self->progthrowstack) {
					return B_STATE_FAIL;
				}
				b_log_print("[% 8d] throw_false_ret\n", pc);
				break;
			}
			case B_OPCODE_THROW_STORE : {
				self->cprogthrowstack -- ;
				index1 = self->cmemstack - self->memstack - * self->cmemthrowstack;
				b_array_decs(self->stackTrace, index1);
				self->cmemstack = self->memstack + * self->cmemthrowstack -- ;
				self->cobjstack = self->objstack + * self->cobjthrowstack -- ;
				self->cobjstack[ - b_opcode_index(ir)] = self->othrow;
				self->othrow = 0;
				b_log_print("[% 8d] storet %d\n", pc, b_opcode_index(ir));
				break;
			}
			case B_OPCODE_JUMP_CLASS : {
				index1 = b_opcode_b_iabb(ir);
				if (self->othrow && self->cast[b_object_class(self->othrow)][index1]) {
					self->cprogmem += b_opcode_a_iabb(ir);
				}
				b_log_print("[% 8d] cjump %d\n", pc, b_opcode_index(ir));
				break;
			}
			case B_OPCODE_OR : {
				object2 = * self->cobjstack -- ;
				object = * self->cobjstack;
				b_vm_exec_test(!object || !object2, b_bni_throw_null_pointer_exception);
				* self->cobjstack = b_object_new_boolean(self, b_object_data(object) || b_object_data(object2));
				b_log_print("[% 8d] or\n", pc);
				break;
			}
			case B_OPCODE_AND : {
				object2 = * self->cobjstack -- ;
				object = * self->cobjstack;
				b_vm_exec_test(!object || !object2, b_bni_throw_null_pointer_exception);
				* self->cobjstack = b_object_new_boolean(self, b_object_data(object) && b_object_data(object2));
				b_log_print("[% 8d] and\n", pc);
				break;
			}
			case B_OPCODE_IS_EQUAL : {
				object = * self->cobjstack;
				* self->cobjstack = b_object_new_boolean(self, object == null);
				b_log_print("[% 8d] isequal\n", pc);
				break;
			}
			case B_OPCODE_IS_NOT_EQUAL : {
				object = * self->cobjstack;
				* self->cobjstack = b_object_new_boolean(self, object != null);
				b_log_print("[% 8d] isnotequal\n", pc);
				break;
			}
			case B_OPCODE_EQUAL_BOOLEAN : {
				object2 = * self->cobjstack -- ;
				object = * self->cobjstack;
				* self->cobjstack
				    = b_object_new_boolean(self, (!object && !object2) || (object && object2 && b_object_data(object) && b_object_data( object2 )));
				b_log_print("[% 8d] equal\n", pc);
				break;
			}
			case B_OPCODE_EQUAL_STRING : {
				object2 = * self->cobjstack -- ;
				object = * self->cobjstack;
				if (object) {
					if (object2) {
						int* value1 = (int*)b_object_data(object);
						int* value2 = (int*)b_object_data(object2);
						if ( * value1 == * value2) {
							index1 = value1[1];
							if ( ! index1) {
								value1[1] = b_char_hash_build ((char*) (value1 + 2));
							}
							index1 = value2[1];
							if ( ! index1) {
								value2[1] = b_char_hash_build ((char*) (value2 + 2));
							}
							if (value1[1] == value2[1]) {
								char* text1 = (char*) (value1 + 2);
								char* text2 = (char*) (value2 + 2);
								index1 = ! b_char_compare(text1, text2);
							} else {
								index1 = 0;
							}
						} else {
							index1 = 0;
						}
					} else {
						index1 = 0;
					}
				} else {
					index1 = ! object2;
				}
				* self->cobjstack = b_object_new_boolean(self, index1);
				b_log_print("[% 8d] equal\n", pc);
				break;
			}
			case B_OPCODE_EQUAL_NUMBER : {
				object2 = * self->cobjstack -- ;
				object = * self->cobjstack;
				if (object) {
					if (object2) {
						index1 = b_object_onumber_to_primitive(object) == b_object_onumber_to_primitive(object2);
					} else {
						index1 = 0;
					}
				} else {
					index1 = ! object2;
				}
				* self->cobjstack = b_object_new_boolean(self, index1);
				b_log_print("[% 8d] equal\n", pc);
				break;
			}
			case B_OPCODE_NEQUAL_BOOLEAN : {
				object2 = * self->cobjstack -- ;
				object = * self->cobjstack;
				* self->cobjstack
				    = b_object_new_boolean(self, (object || object2) && !(object && object2 && b_object_data(object) && b_object_data( object2 )));
				if (object) {
					if (object2) {
						index1 = b_object_data(object) != b_object_data(object2);
					} else {
						index1 = 1;
					}
				} else {
					if (object2) {
						index1 = 1;
					} else {
						index1 = 0;
					}
				}
				* self->cobjstack = b_object_new_boolean(self, index1);
				b_log_print("[% 8d] nequal\n", pc);
				break;
			}
			case B_OPCODE_NEQUAL_STRING : {
				object2 = * self->cobjstack -- ;
				object = * self->cobjstack;
				if (object) {
					if (object2) {
						int* value1 = (int*)b_object_data(object);
						int* value2 = (int*)b_object_data(object2);
						if ( * value1 == * value2) {
							if (value1[1] == value2[1]) {
								char* text1 = (char*) (value1 + 2);
								char* text2 = (char*) (value2 + 2);
								index1 = b_char_compare(text1, text2);
							} else {
								index1 = 1;
							}
						} else {
							index1 = 1;
						}
					} else {
						index1 = 1;
					}
				} else {
					index1 = object2 != null;
				}
				* self->cobjstack = b_object_new_boolean(self, index1);
				b_log_print("[% 8d] nequal\n", pc);
				break;
			}
			case B_OPCODE_NEQUAL_NUMBER : {
				object2 = * self->cobjstack -- ;
				object = * self->cobjstack;
				if (object) {
					if (object2) {
						index1 = b_object_onumber_to_primitive(object) != b_object_onumber_to_primitive(object2);
					} else {
						index1 = 1;
					}
				} else {
					index1 = object2 != null;
				}
				* self->cobjstack = b_object_new_boolean(self, index1);
				b_log_print("[% 8d] nequal\n", pc);
				break;
			}
			case B_OPCODE_HIGH_NUMBER : {
				object2 = * self->cobjstack -- ;
				object = * self->cobjstack;
				b_vm_exec_test(!object || !object2, b_bni_throw_null_pointer_exception);
				* self->cobjstack = b_object_new_boolean(self, b_object_onumber_to_primitive(object) > b_object_onumber_to_primitive(object2));
				b_log_print("[% 8d] high\n", pc);
				break;
			}
			case B_OPCODE_HIGH_STRING : {
				object2 = * self->cobjstack -- ;
				object = * self->cobjstack;
				b_vm_exec_test(!object || !object2, b_bni_throw_null_pointer_exception);
				* self->cobjstack
				    = b_object_new_boolean(self, b_char_compare(b_object_ostring_to_text(self, object), b_object_ostring_to_text(self, object2))
						    > 0);
				b_log_print("[% 8d] high\n", pc);
				break;
			}
			case B_OPCODE_LOW_NUMBER : {
				object2 = * self->cobjstack -- ;
				object = * self->cobjstack;
				b_vm_exec_test(!object || !object2, b_bni_throw_null_pointer_exception);
				* self->cobjstack = b_object_new_boolean(self, b_object_onumber_to_primitive(object) < b_object_onumber_to_primitive(object2));
				b_log_print("[% 8d] low\n", pc);
				break;
			}
			case B_OPCODE_LOW_STRING : {
				object2 = * self->cobjstack -- ;
				object = * self->cobjstack;
				b_vm_exec_test(!object || !object2, b_bni_throw_null_pointer_exception);
				* self->cobjstack
				    = b_object_new_boolean(self, b_char_compare(b_object_ostring_to_text(self, object), b_object_ostring_to_text(self, object2))
						    < 0);
				b_log_print("[% 8d] low\n", pc);
				break;
			}
			case B_OPCODE_HIGHEQUAL_NUMBER : {
				object2 = * self->cobjstack -- ;
				object = * self->cobjstack;
				b_vm_exec_test(!object || !object2, b_bni_throw_null_pointer_exception);
				* self->cobjstack = b_object_new_boolean(self, b_object_onumber_to_primitive(object) >= b_object_onumber_to_primitive(object2));
				b_log_print("[% 8d] highequal\n", pc);
				break;
			}
			case B_OPCODE_HIGHEQUAL_STRING : {
				object2 = * self->cobjstack -- ;
				object = * self->cobjstack;
				b_vm_exec_test(!object || !object2, b_bni_throw_null_pointer_exception);
				* self->cobjstack
				    = b_object_new_boolean(self, b_char_compare(b_object_ostring_to_text(self, object), b_object_ostring_to_text(self, object2))
						    >= 0);
				b_log_print("[% 8d] highequal\n", pc);
				break;
			}
			case B_OPCODE_LOWEQUAL_NUMBER : {
				object2 = * self->cobjstack -- ;
				object = * self->cobjstack;
				b_vm_exec_test(!object || !object2, b_bni_throw_null_pointer_exception);
				* self->cobjstack = b_object_new_boolean(self, b_object_onumber_to_primitive(object) <= b_object_onumber_to_primitive(object2));
				b_log_print("[% 8d] lowequal\n", pc);
				break;
			}
			case B_OPCODE_LOWEQUAL_STRING : {
				object2 = * self->cobjstack -- ;
				object = * self->cobjstack;
				b_vm_exec_test(!object || !object2, b_bni_throw_null_pointer_exception);
				* self->cobjstack
				    = b_object_new_boolean(self, b_char_compare(b_object_ostring_to_text(self, object), b_object_ostring_to_text(self, object2))
						    <= 0);
				b_log_print("[% 8d] lowequal\n", pc);
				break;
			}
			case B_OPCODE_SUM_NUMBER : {
				object2 = * self->cobjstack -- ;
				object = * self->cobjstack;
				b_vm_exec_test(!object || !object2, b_bni_throw_null_pointer_exception);
				* self->cobjstack = b_object_new_number (self, b_object_onumber_to_primitive(object) + b_object_onumber_to_primitive(object2));
				b_log_print("[% 8d] sum_number\n", pc);
				break;
			}
			case B_OPCODE_SUM_STRING : {
				object2 = * self->cobjstack -- ;
				object = * self->cobjstack;
				b_vm_exec_test(!object || !object2, b_bni_throw_null_pointer_exception);
				int* value1 = (int*)b_object_data(object);
				int* value2 = (int*)b_object_data(object2);
				char* text1 = (char*) (value1 + 2);
				char* text2 = (char*) (value2 + 2);
				char* chars = (char*)b_memory_alloc (self, 2 * sizeof(int) + ( * value1 + * value2 + 1) * sizeof(char));
				strcat(chars, text1);
				strcat(chars, text2);
				* self->cobjstack = b_object_new_string_generic (self, chars, b_char_size (text1) + b_char_size (text2), 0, 0);
				b_memory_free (chars);
				b_log_print("[% 8d] sum_string\n", pc);
				break;
			}
			case B_OPCODE_SUB_NUMBER : {
				object2 = * self->cobjstack -- ;
				object = * self->cobjstack;
				b_vm_exec_test(!object || !object2, b_bni_throw_null_pointer_exception);
				* self->cobjstack = b_object_new_number (self, b_object_onumber_to_primitive(object) - b_object_onumber_to_primitive(object2));
				b_log_print("[% 8d] sub\n", pc);
				break;
			}
			case B_OPCODE_MUL_NUMBER : {
				object2 = * self->cobjstack -- ;
				object = * self->cobjstack;
				b_vm_exec_test(!object || !object2, b_bni_throw_null_pointer_exception);
				* self->cobjstack = b_object_new_number (self, b_object_onumber_to_primitive(object) * b_object_onumber_to_primitive(object2));
				b_log_print("[% 8d] mul\n", pc);
				break;
			}
			case B_OPCODE_DIV_NUMBER : {
				object2 = * self->cobjstack -- ;
				object = * self->cobjstack;
				b_vm_exec_test(!object || !object2, b_bni_throw_null_pointer_exception);
				b_vm_exec_test(b_object_onumber_to_primitive(object2) == 0.0, b_bni_throw_arithmetic_runtime_exception);
				* self->cobjstack = b_object_new_number (self, b_object_onumber_to_primitive(object) / b_object_onumber_to_primitive(object2));
				b_log_print("[% 8d] div\n", pc);
				break;
			}
			default : {
				b_log_print_error("opcode %d not implemented!", b_opcode(ir));
				exit (0);
			}
		}
#ifdef _DEBUG_
		printf("\tso={");
		sp = self->cobjstack - self->objstack;
		for (index1 = 2; index1 <= sp; index1++) {
			object = self->objstack[index1];
			if (object) {
				int cindex = b_object_class(object);
				b_class_t* clazz = b_class_get(self, cindex);
				printf("%s %x", clazz->name, object);
				if (b_bni_class_boolean_id(self) == cindex) {
					printf(" = ");
					if (b_object_data(object)) {
						printf("true");
					} else {
						printf("false");
					}
				} else if (b_bni_class_number_id(self) == cindex) {
					printf(" = %lf", *(b_number_t*) b_object_data(object));
				} else if (b_bni_class_integer_id(self) == cindex
						|| b_bni_class_natural_id(self) == cindex
						|| b_bni_class_index_id(self) == cindex) {
					printf(" = %lf", *(b_number_t*) b_object_data(object));
				}
			} else {
				printf("0");
			}
			if (index1 != sp) {
				printf(";");
			}
		}
		printf("}\n");
		printf("\tst={");
		int buffersize = b_array_size(self->stackTrace);
		for (index1 = 0; index1 < buffersize; index1++) {
			char* methodname = b_array_get(self->stackTrace, index1);
			printf("%s", methodname);
			if (index1 != buffersize - 1) {
				printf(";");
			}
		}
		printf("}\n");
		b_so_out_flush();
#endif
		if (self->gc > B_VM_GC_COUNTER) {
			self->gc = 0;
			b_gc_start (self);
		}
	}
	return B_STATE_SUCCESS;
}
