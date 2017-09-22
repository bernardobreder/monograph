#ifndef B_OPCODE_H_
#define B_OPCODE_H_

#define B_OPCODE_END 0
#define B_OPCODE_INC 4
#define B_OPCODE_CONST_NIL 1
#define B_OPCODE_STORE 2
#define B_OPCODE_LOAD 3
#define B_OPCODE_DESC 5
#define B_OPCODE_JUMP 6
#define B_OPCODE_JUMP_OBJECT 9
#define B_OPCODE_NEW 8
#define B_OPCODE_RETURN_OBJECT 11
#define B_OPCODE_PUSHINDEX 12
#define B_OPCODE_SDESC 13
#define B_OPCODE_SRSTORE 14
#define B_OPCODE_RSTORE 15
#define B_OPCODE_RSLOAD 16
#define B_OPCODE_RDESC 17
#define B_OPCODE_RETURN_STATIC 18
#define B_OPCODE_OGETF 19
#define B_OPCODE_OSETF 20
#define B_OPCODE_RSLOADS 21
#define B_OPCODE_SRSTORES 22
#define B_OPCODE_CONST_STRING 23
#define B_OPCODE_CONST_NUMBER 24
#define B_OPCODE_SUM_NUMBER 25
#define B_OPCODE_SUB_NUMBER 26
#define B_OPCODE_MUL_NUMBER 27
#define B_OPCODE_DIV_NUMBER 28
#define B_OPCODE_NOT 29
#define B_OPCODE_NEQUAL_BOOLEAN 30
#define B_OPCODE_NEQUAL_STRING 81
#define B_OPCODE_NEQUAL_NUMBER 82
#define B_OPCODE_EQUAL_BOOLEAN 31
#define B_OPCODE_EQUAL_STRING 79
#define B_OPCODE_EQUAL_NUMBER 80
#define B_OPCODE_HIGH_NUMBER 32
#define B_OPCODE_HIGHEQUAL_NUMBER 33
#define B_OPCODE_LOW_NUMBER 34
#define B_OPCODE_LOWEQUAL_NUMBER 35
#define B_OPCODE_JUMP_TRUE 36
#define B_OPCODE_JUMP_FALSE 37
#define B_OPCODE_GETFIELD 38
#define B_OPCODE_SETFIELD 39
#define B_OPCODE_RCONST 40
#define B_OPCODE_SETMETHOD 41
#define B_OPCODE_ARRAY 42
#define B_OPCODE_STRUCT 43
#define B_OPCODE_NJUMP 44
#define B_OPCODE_NSJUMP 45
#define B_OPCODE_CONST_BOOLEAN 46
#define B_OPCODE_OR 47
#define B_OPCODE_AND 48
#define B_OPCODE_NCJUMP 49
#define B_OPCODE_JUMP_STATIC 50
#define B_OPCODE_CAST_OBJECT 51
#define B_OPCODE_THROW 52
#define B_OPCODE_TRY 53
#define B_OPCODE_THROW_TRUE_RET 54
#define B_OPCODE_JUMP_CLASS 55
#define B_OPCODE_THROW_FALSE_RET 56
#define B_OPCODE_SET_FIELD_STATIC 57
#define B_OPCODE_GET_FIELD_STATIC 58
#define B_OPCODE_THROW_STORE 59
#define B_OPCODE_CONST_CLASS 60
#define B_OPCODE_CAST_NOTNULL 61
#define B_OPCODE_CONST_INT 62
#define B_OPCODE_CONST_NAT 63
#define B_OPCODE_CONST_IND 64
#define B_OPCODE_ARRAY_NEW 65
#define B_OPCODE_ARRAY_RNEW 66
#define B_OPCODE_ARRAY_GET 67
#define B_OPCODE_ARRAY_SET 68
#define B_OPCODE_ARRAY_LEN 69
#define B_OPCODE_SUM_STRING 70
#define B_OPCODE_HIGH_STRING 71
#define B_OPCODE_HIGHEQUAL_STRING 72
#define B_OPCODE_LOW_STRING 73
#define B_OPCODE_LOWEQUAL_STRING 74
#define B_OPCODE_CONST_NIL_2_TIME 75
#define B_OPCODE_CONST_NIL_3_TIME 76
#define B_OPCODE_CONST_NIL_4_TIME 77
#define B_OPCODE_CONST_NIL_5_TIME 78
#define B_OPCODE_IS_EQUAL 83
#define B_OPCODE_IS_NOT_EQUAL 84
#define B_OPCODE_DUP 85
#define B_OPCODE_AJUMP 86
#define B_OPCODE_INC_INDEX 87
#define B_OPCODE_CAST_NUMBER_TO_INTEGER 88


#define NOPCODE 8
#define NBITS 32
#define MAX_INDEX_A 0xFFFFFF
#define MAX_INDEX_AB 0xFFF
#define MAX_OPCODE 0xFF000000
#define NINDEX (NBITS - NOPCODE)

#define b_opcode(inst) (((inst) & MAX_OPCODE) >> NINDEX)
#define b_opcode_index(inst) ((inst) & MAX_INDEX_A)
#define b_opcode_aindex(inst) ((inst) & 8388607)
#define b_opcode_ga(inst) (((inst) >> (NINDEX/2)) & MAX_INDEX_AB)
#define b_opcode_gb(inst) ((inst) & MAX_INDEX_AB)
#define b_opcode_i(i) ((__inst)((i) << NINDEX))
#define b_opcode_ia(i,a) ((__inst)(b_opcode_i(i) + ((a) & MAX_INDEX_A)))
#define b_opcode_iab(i,a,b) ((__inst)(b_opcode_i(i) + (((a) & MAX_INDEX_AB) << (NINDEX/2)) + ((b) & MAX_INDEX_AB)))
#define b_opcode_a_iabb(ir) ((ir & 0x00FF0000) >> 16)
#define b_opcode_b_iabb(ir) (ir & 0x0000FFFF)
#define b_opcode_a_iabc(ir) ((ir & 0x00FF0000) >> 16)
#define b_opcode_b_iabc(ir) ((ir & 0x0000FF00) >> 8)
#define b_opcode_c_iabc(ir) (ir & 0x000000FF)
#define b_opcode_pushnil() b_opcode_i(B_OPCODE_CONST_NIL)
#define b_opcode_pushindex(index) b_opcode_ia(B_OPCODE_PUSHINDEX,index)
#define b_opcode_ret() b_opcode_i(B_OPCODE_RETURN_OBJECT)
#define b_opcode_sret() b_opcode_i(B_OPCODE_RETURN_STATIC)
#define b_opcode_inc(index) b_opcode_ia(B_OPCODE_INC,index)
#define b_opcode_desc(index) b_opcode_ia(B_OPCODE_DESC,index)
#define b_opcode_rdesc(index) b_opcode_ia(B_OPCODE_RDESC,index)
#define b_opcode_sdesc(base,length) b_opcode_iab(B_OPCODE_SDESC,base,length)
#define b_opcode_load(index) b_opcode_ia(B_OPCODE_LOAD,index)
#define b_opcode_sconst(index) b_opcode_ia(B_OPCODE_CONST_STRING,index)
#define b_opcode_nconst(index) b_opcode_ia(B_OPCODE_CONST_NUMBER,index)
#define b_opcode_rconst(index) b_opcode_ia(B_OPCODE_RCONST,index)
#define b_opcode_srstore() b_opcode_i(B_OPCODE_SRSTORE)
#define b_opcode_srstores(count) b_opcode_ia(B_OPCODE_SRSTORES,count)
#define b_opcode_rsload(index) b_opcode_ia(B_OPCODE_RSLOAD,index)
#define b_opcode_rsloads(count) b_opcode_ia(B_OPCODE_RSLOADS,count)
#define b_opcode_store(index) b_opcode_ia(B_OPCODE_STORE,index)
#define b_opcode_rstore(index) b_opcode_ia(B_OPCODE_RSTORE,index)
#define b_opcode_jump(index) b_opcode_ia(B_OPCODE_JUMP,index)
#define b_opcode_tjump(index) b_opcode_ia(B_OPCODE_JUMP_TRUE,index)
#define b_opcode_fjump(index) b_opcode_ia(B_OPCODE_JUMP_FALSE,index)
#define b_opcode_ojump(index) b_opcode_ia(B_OPCODE_JUMP_OBJECT,index)
#define b_opcode_ogetf(index) b_opcode_ia(B_OPCODE_OGETF,index)
#define b_opcode_osetf(index) b_opcode_ia(B_OPCODE_OSETF,index)
#define b_opcode_scall(index) b_opcode_ia(B_OPCODE_SCALL,index)
#define b_opcode_onew(index) b_opcode_ia(B_OPCODE_NEW,index)
#define b_opcode_getfield(index) b_opcode_ia(B_OPCODE_GETFIELD,index)
#define b_opcode_setfield(index) b_opcode_ia(B_OPCODE_SETFIELD,index)
#define b_opcode_setmethod(index) b_opcode_ia(B_OPCODE_SETMETHOD,index)
#define b_opcode_sum() b_opcode_i(B_OPCODE_SUM_NUMBER)
#define b_opcode_sub() b_opcode_i(B_OPCODE_SUB_NUMBER)
#define b_opcode_mul() b_opcode_i(B_OPCODE_MUL_NUMBER)
#define b_opcode_div() b_opcode_i(B_OPCODE_DIV_NUMBER)
#define b_opcode_not() b_opcode_i(B_OPCODE_NOT)
#define b_opcode_equal() b_opcode_i(B_OPCODE_EQUAL)
#define b_opcode_nequal() b_opcode_i(B_OPCODE_NEQUAL)
#define b_opcode_high() b_opcode_i(B_OPCODE_HIGH_NUMBER)
#define b_opcode_highequal() b_opcode_i(B_OPCODE_HIGHEQUAL_NUMBER)
#define b_opcode_low() b_opcode_i(B_OPCODE_LOW_NUMBER)
#define b_opcode_lowequal() b_opcode_i(B_OPCODE_LOWEQUAL_NUMBER)
#define b_opcode_end() b_opcode_i(B_OPCODE_END)

#endif
