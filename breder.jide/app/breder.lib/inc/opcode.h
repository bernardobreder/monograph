#ifndef B_OPCODE_H_
#define B_OPCODE_H_

#define V_OPCODE_END 0
#define V_OPCODE_PUSHNIL 1
#define V_OPCODE_STORE 2
#define V_OPCODE_LOAD 3
#define V_OPCODE_INC 4
#define V_OPCODE_DESC 5
#define V_OPCODE_JUMP 6
#define V_OPCODE_OJUMP 9
#define V_OPCODE_SCALL 10
#define V_OPCODE_CMP 7
#define V_OPCODE_NEW 8
#define V_OPCODE_RET 11
#define V_OPCODE_PUSHINDEX 12
#define V_OPCODE_SDESC 13
#define V_OPCODE_SRSTORE 14
#define V_OPCODE_RSTORE 15
#define V_OPCODE_RSLOAD 16
#define V_OPCODE_RDESC 17
#define V_OPCODE_SRET 18
#define V_OPCODE_OGETF 19
#define V_OPCODE_OSETF 20
#define V_OPCODE_RSLOADS 21
#define V_OPCODE_SRSTORES 22
#define V_OPCODE_SCONST 23
#define V_OPCODE_NCONST 24
#define V_OPCODE_SUM 25
#define V_OPCODE_SUB 26
#define V_OPCODE_MUL 27
#define V_OPCODE_DIV 28
#define V_OPCODE_NOT 29
#define V_OPCODE_NEQUAL 30
#define V_OPCODE_EQUAL 31
#define V_OPCODE_HIGH 32
#define V_OPCODE_HIGHEQUAL 33
#define V_OPCODE_LOW 34
#define V_OPCODE_LOWEQUAL 35
#define V_OPCODE_TJUMP 36
#define V_OPCODE_FJUMP 37
#define V_OPCODE_GETFIELD 38
#define V_OPCODE_SETFIELD 39
#define V_OPCODE_RCONST 40
#define V_OPCODE_SETMETHOD 41
#define V_OPCODE_ARRAY 42
#define V_OPCODE_STRUCT 43
#define V_OPCODE_NJUMP 44
#define V_OPCODE_NSJUMP 45
#define V_OPCODE_BCONST 46
#define V_OPCODE_OR 47
#define V_OPCODE_AND 48
#define V_OPCODE_NCJUMP 49
#define V_OPCODE_SJUMP 50
#define V_OPCODE_CAST 51
#define V_OPCODE_THROW 52
#define V_OPCODE_TRY 53
#define V_OPCODE_THROW_TRUE_RET 54
#define V_OPCODE_CJUMP 55
#define V_OPCODE_THROW_FALSE_RET 56
#define V_OPCODE_SET_STATIC_FIELD 57
#define V_OPCODE_GET_STATIC_FIELD 58

#define NOPCODE 8
#define NBITS 32
#define MAX_INDEX_A 0xFFFFFF
#define MAX_INDEX_AB 0xFFF
#define MAX_OPCODE 0xFF000000
typedef int __inst ;
#define NINDEX (NBITS - NOPCODE)

#define b_opcode(inst) (((inst) & MAX_OPCODE) >> NINDEX)
#define b_opcode_index(inst) ((inst) & MAX_INDEX_A)
#define b_opcode_ga(inst) (((inst) >> (NINDEX/2)) & MAX_INDEX_AB)
#define b_opcode_gb(inst) ((inst) & MAX_INDEX_AB)
#define b_opcode_i(i) ((__inst)((i) << NINDEX))
#define b_opcode_ia(i,a) ((__inst)(b_opcode_i(i) + ((a) & MAX_INDEX_A)))
#define b_opcode_iab(i,a,b) ((__inst)(b_opcode_i(i) + (((a) & MAX_INDEX_AB) << (NINDEX/2)) + ((b) & MAX_INDEX_AB)))
#define b_opcode_a_iabb(ir) ((ir & 0x00FF0000) >> 16)
#define b_opcode_b_iabb(ir) (ir & 0x0000FFFF)
#define b_opcode_pushnil() b_opcode_i(V_OPCODE_PUSHNIL)
#define b_opcode_pushindex(index) b_opcode_ia(V_OPCODE_PUSHINDEX,index)
#define b_opcode_ret() b_opcode_i(V_OPCODE_RET)
#define b_opcode_sret() b_opcode_i(V_OPCODE_SRET)
#define b_opcode_inc(index) b_opcode_ia(V_OPCODE_INC,index)
#define b_opcode_desc(index) b_opcode_ia(V_OPCODE_DESC,index)
#define b_opcode_rdesc(index) b_opcode_ia(V_OPCODE_RDESC,index)
#define b_opcode_sdesc(base,length) b_opcode_iab(V_OPCODE_SDESC,base,length)
#define b_opcode_load(index) b_opcode_ia(V_OPCODE_LOAD,index)
#define b_opcode_sconst(index) b_opcode_ia(V_OPCODE_SCONST,index)
#define b_opcode_nconst(index) b_opcode_ia(V_OPCODE_NCONST,index)
#define b_opcode_rconst(index) b_opcode_ia(V_OPCODE_RCONST,index)
#define b_opcode_srstore() b_opcode_i(V_OPCODE_SRSTORE)
#define b_opcode_srstores(count) b_opcode_ia(V_OPCODE_SRSTORES,count)
#define b_opcode_rsload(index) b_opcode_ia(V_OPCODE_RSLOAD,index)
#define b_opcode_rsloads(count) b_opcode_ia(V_OPCODE_RSLOADS,count)
#define b_opcode_store(index) b_opcode_ia(V_OPCODE_STORE,index)
#define b_opcode_rstore(index) b_opcode_ia(V_OPCODE_RSTORE,index)
#define b_opcode_jump(index) b_opcode_ia(V_OPCODE_JUMP,index)
#define b_opcode_tjump(index) b_opcode_ia(V_OPCODE_TJUMP,index)
#define b_opcode_fjump(index) b_opcode_ia(V_OPCODE_FJUMP,index)
#define b_opcode_ojump(index) b_opcode_ia(V_OPCODE_OJUMP,index)
#define b_opcode_ogetf(index) b_opcode_ia(V_OPCODE_OGETF,index)
#define b_opcode_osetf(index) b_opcode_ia(V_OPCODE_OSETF,index)
#define b_opcode_scall(index) b_opcode_ia(V_OPCODE_SCALL,index)
#define b_opcode_onew(index) b_opcode_ia(V_OPCODE_NEW,index)
#define b_opcode_getfield(index) b_opcode_ia(V_OPCODE_GETFIELD,index)
#define b_opcode_setfield(index) b_opcode_ia(V_OPCODE_SETFIELD,index)
#define b_opcode_setmethod(index) b_opcode_ia(V_OPCODE_SETMETHOD,index)
#define b_opcode_sum() b_opcode_i(V_OPCODE_SUM)
#define b_opcode_sub() b_opcode_i(V_OPCODE_SUB)
#define b_opcode_mul() b_opcode_i(V_OPCODE_MUL)
#define b_opcode_div() b_opcode_i(V_OPCODE_DIV)
#define b_opcode_not() b_opcode_i(V_OPCODE_NOT)
#define b_opcode_equal() b_opcode_i(V_OPCODE_EQUAL)
#define b_opcode_nequal() b_opcode_i(V_OPCODE_NEQUAL)
#define b_opcode_high() b_opcode_i(V_OPCODE_HIGH)
#define b_opcode_highequal() b_opcode_i(V_OPCODE_HIGHEQUAL)
#define b_opcode_low() b_opcode_i(V_OPCODE_LOW)
#define b_opcode_lowequal() b_opcode_i(V_OPCODE_LOWEQUAL)
#define b_opcode_end() b_opcode_i(V_OPCODE_END)

__inst *b_opcode_new ( __inst inst , const char* msg ) ;
int b_opcode_add ( b_arrayp_t* array , __inst inst , const char* msg ) ;

#endif
