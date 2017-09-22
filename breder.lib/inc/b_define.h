#ifndef B_DEFINE_H_
#define B_DEFINE_H_

#define b_state_t char
#define B_STATE_SUCCESS 1
#define B_STATE_FAIL 0
#define true 1
#define false 0
#define null 0

#define B_COMPILER 1
#define B_DECLARE 2
#define B_ASSIGN 3
#define B_ASSIGNS 15
#define B_BLOCK 4
#define B_NUMBER 5
#define B_STRING 6
#define B_BOOLEAN 7
#define B_NIL 8
#define B_NEW 9
#define B_EXPRESSION 10
#define B_IDENTIFY 11
#define B_GET 12
#define B_RETURN 13
#define B_LGET 14
#define B_CALL 16
#define B_FIELD_DECLARE 17
#define B_IF 18
#define B_TERNARY 18
#define B_SUM 19
#define B_SUB 20
#define B_MUL 21
#define B_DIV 22
#define B_NOT 23
#define B_EQUAL 24
#define B_NOTEQUAL 25
#define B_HIGH 26
#define B_HIGHEQUAL 27
#define B_LOW 28
#define B_LOWEQUAL 29
#define B_WHILE 30
#define B_THIS 31
#define B_FOR 32
#define B_GETFIELD 33
#define B_LFUNCTION 34
#define B_RFUNCTION 35
#define B_GETMETHOD 36

#define B_OP_STORE 1
#define B_OP_PUSHN 2
#define B_OP_PUSHS 3
#define B_OP_PUSHB 4
#define B_OP_PUSHNIL 5

#define B_NUMBER_POOL_MAX 1024

#define b_assert_generic(exp, task) if((exp) == B_STATE_FAIL) { task; }
#define b_assert_end(exp) b_assert_generic(exp, goto end)
#define b_assert_return(exp) b_assert_generic(exp, return B_STATE_FAIL;)
#define b_assert_outofmemory(exp) b_assert_generic(exp, printf("[bvm] : out of memory for load the bvm.\n"); goto end; )
#define B_VM_STACK_MAX 1024
#define B_VM_STACKTRACE_MAX 1024
#define B_VM_MEMTRACE_MAX 1024
#define B_VM_THROWTRACE_MAX 1024

#define B_VM_GC_COUNTER 1024*1024

#endif
