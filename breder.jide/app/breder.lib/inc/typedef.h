#ifndef B_TYPEDEF_H_
#define B_TYPEDEF_H_

typedef struct b_array_t b_arrayp_t ;
typedef void* b_structs_t ;
typedef struct b_linker_t b_linker_t ;
typedef struct b_linker_node_t b_linker_node_t ;

typedef struct b_compiler_t b_compiler_t ;
typedef struct b_token_t b_token_t ;

typedef struct b_parser_t bc_parser_t ;
typedef struct b_throw_t b_throw_t ;
typedef struct b_source_t b_source_t ;
typedef struct b_execute_t b_execute_t ;
typedef struct bc_class_t bc_class_t ;
typedef struct bc_type_t bc_type_t ;
typedef struct bc_method_t bc_method_t ;
typedef struct bc_type_t b_type_t ;
typedef struct bc_field_declare_t bc_field_declare_t ;
typedef struct bc_declare_t bc_declare_t ;
typedef struct bc_block_t bc_block_t ;
typedef struct bc_assign_t bc_assign_t ;
typedef struct b_command_t bc_command_t ;
typedef struct bc_ruget_t bc_ruget_t ;
typedef struct b_luget_t b_luget_t ;
typedef struct b_literal_t b_literal_t ;
typedef struct b_number_t bc_number_t ;
typedef struct bc_string_t bc_string_t ;
typedef struct b_boolean_t bc_boolean_t ;
typedef struct bc_nil_t bc_nil_t ;
typedef struct b_new_t bc_new_t ;
typedef struct bc_getfield_t bc_getfield_t ;
typedef struct bc_expression_t bc_expression_t ;
typedef struct bc_identify_t bc_identify_t ;
typedef struct bc_this_t bc_this_t ;
typedef struct bc_rget_t bc_rget_t ;
typedef struct bc_lvalue_t bc_lvalue_t ;
typedef struct bc_call_t bc_call_t ;
typedef struct bc_return_t bc_return_t ;
typedef struct bc_if_t bc_if_t ;
typedef struct bc_while_t bc_while_t ;
typedef struct bc_for_t bc_for_t ;
typedef struct bc_lfunction_t bc_lfunction_t ;
typedef struct bc_rfunction_t bc_rfunction_t ;
typedef struct bc_ternary_t bc_ternary_t ;
typedef struct bc_sum_t bc_sum_t ;
typedef struct bc_sub_t bc_sub_t ;
typedef struct bc_mul_t bc_mul_t ;
typedef struct bc_div_t bc_div_t ;
typedef struct bc_not_t bc_not_t ;
typedef struct bc_equal_t bc_equal_t ;
typedef struct bc_high_t bc_high_t ;
typedef struct bc_highequal_t bc_highequal_t ;
typedef struct bc_low_t bc_low_t ;
typedef struct bc_lowequal_t bc_lowequal_t ;
typedef struct bc_notequal_t bc_notequal_t ;
typedef struct bc_exp_t bc_exp_t ;
typedef struct bc_getmethod_t bc_getmethod_t ;

typedef struct bc_field_t bc_field_t ;
typedef struct bc_operation_t bc_operation_t ;

typedef struct b_vm_t b_vm_t ;
typedef struct b_class_t b_class_t ;
typedef struct b_method_t b_method_t ;
typedef struct b_field_t b_field_t ;
typedef int* b_object_t ;
typedef int aint;
typedef struct b_chars_t b_chars_t;

typedef struct b_vector_t b_vector_t ;
typedef struct b_hashnew_t b_hashnew_t ;
typedef void* b_hashclose_t ;
typedef void* b_hashlock_t ;
typedef struct b_hashentity_t b_hashentity_t ;
typedef struct b_hashnew_t b_hash_parser_t ;
typedef struct b_array_t b_array_parser_t ;
typedef struct b_array_t b_array_char_t ;
typedef struct b_array_t b_array_chars_t ;
typedef struct b_array_t b_array_achar_t ;
typedef struct b_array_t b_array_void_t ;
typedef struct b_array_t b_array_class_t ;
typedef struct b_hashnew_t b_hash_class_t ;
typedef struct b_array_t b_array_object_t ;
typedef struct b_array_t b_array_hash_t ;
typedef struct b_linker_t b_linker_object_t ;
typedef struct b_array_t b_array_int_t ;
typedef struct b_array_t b_array_token_t ;
typedef struct b_array_t bc_array_type_t ;
typedef struct b_array_t bc_array_field_declare_t;
typedef struct b_array_t b_array_field_t ;
typedef struct b_array_t b_array_method_t ;
typedef struct b_array_t b_array_declare_t ;
typedef struct b_array_t b_array_opcode_t ;
typedef struct b_array_t bc_array_field_t ;
typedef struct b_array_t bc_array_operation_t ;
typedef struct b_array_t bc_array_lvalue_t ;
typedef struct b_array_t bc_array_rvalue_t ;
typedef struct b_array_t bc_array_exp_t ;

#define EXTEND_VALUABLE \
	bc_array_type_t* types ; \
	bc_array_field_t* fieldCount ; \
	bc_array_operation_t* operations ;

#endif
