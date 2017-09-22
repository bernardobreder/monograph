
package breder.compiler.node.standart;

public class BOpcode {

	public static final int OPCODE_END = 0;

	public static final int OPCODE_PUSHNIL = 1;

	public static final int OPCODE_STORE = 2;

	public static final int OPCODE_LOAD = 3;

	public static final int OPCODE_INC = 4;

	public static final int OPCODE_DESC = 5;

	public static final int OPCODE_JUMP = 6;

	public static final int OPCODE_OJUMP = 9;

	public static final int OPCODE_CMP = 7;

	public static final int OPCODE_NEW = 8;

	public static final int OPCODE_RET = 11;

	public static final int OPCODE_PUSHINDEX = 12;

	public static final int OPCODE_SDESC = 13;

	public static final int OPCODE_SRSTORE = 14;

	public static final int OPCODE_RSTORE = 15;

	public static final int OPCODE_RSLOAD = 16;

	public static final int OPCODE_RDESC = 17;

	public static final int OPCODE_SRET = 18;

	public static final int OPCODE_OGETF = 19;

	public static final int OPCODE_OSETF = 20;

	public static final int OPCODE_RSLOADS = 21;

	public static final int OPCODE_SRSTORES = 22;

	public static final int OPCODE_SCONST = 23;

	public static final int OPCODE_NCONST = 24;

	public static final int OPCODE_SUM_NUMBER = 25;

	public static final int OPCODE_SUB = 26;

	public static final int OPCODE_MUL = 27;

	public static final int OPCODE_DIV = 28;

	public static final int OPCODE_NOT = 29;

	public static final int OPCODE_HIGH = 32;

	public static final int OPCODE_HIGHEQUAL = 33;

	public static final int OPCODE_LOW = 34;

	public static final int OPCODE_LOWEQUAL = 35;

	public static final int OPCODE_TJUMP = 36;

	public static final int OPCODE_FJUMP = 37;

	public static final int OPCODE_GETFIELD = 38;

	public static final int OPCODE_SETFIELD = 39;

	public static final int OPCODE_RCONST = 40;

	public static final int OPCODE_SETMETHOD = 41;

	public static final int OPCODE_ARRAY = 42;

	public static final int OPCODE_NJUMP = 44;

	public static final int OPCODE_NSJUMP = 45;

	public static final int OPCODE_BCONST = 46;

	public static final int OPCODE_OR = 47;

	public static final int OPCODE_AND = 48;

	public static final int OPCODE_NCJUMP = 49;

	public static final int OPCODE_SJUMP = 50;

	public static final int OPCODE_CAST = 51;

	public static final int OPCODE_THROW = 52;

	public static final int OPCODE_TRY = 53;

	public static final int OPCODE_TRET = 54;

	public static final int OPCODE_CJUMP = 55;

	public static final int OPCODE_TFRET = 56;

	public static final int OPCODE_SET_STATIC_FIELD = 57;

	public static final int OPCODE_GET_STATIC_FIELD = 58;

	public static final int OPCODE_TSTORE = 59;

	public static final int OPCODE_CONSTC = 60;

	public static final int OPCODE_NNCAST = 61;

	public static final int OPCODE_SUM_STRING = 70;

	public static final int OPCODE_HIGH_STRING = 71;

	public static final int OPCODE_HIGHEQUAL_STRING = 72;

	public static final int OPCODE_LOW_STRING = 73;

	public static final int OPCODE_LOWEQUAL_STRING = 74;

	public static final int OPCODE_PUSHNIL2 = 75;

	public static final int OPCODE_PUSHNIL3 = 76;

	public static final int OPCODE_PUSHNIL4 = 77;

	public static final int OPCODE_PUSHNIL5 = 78;

	public static final int OPCODE_EQUAL_BOOLEAN = 31;

	public static final int OPCODE_EQUAL_STRING = 79;

	public static final int OPCODE_EQUAL_NUMBER = 80;

	public static final int OPCODE_NEQUAL_BOOLEAN = 30;

	public static final int OPCODE_NEQUAL_STRING = 81;

	public static final int OPCODE_NEQUAL_NUMBER = 82;

	public static final int OPCODE_IS_EQUAL = 83;

	public static final int OPCODE_IS_NOT_EQUAL = 84;

	public static final int OPCODE_DUP = 85;

	public static final int OPCODE_AJUMP = 86;

	public static final int OPCODE_INC_INDEX = 87;

	public static final int OPCODE_CAST_NUMBER_TO_INTEGER = 88;

	public static int opConstNum(int value) {
		return op(OPCODE_NCONST) + value;
	}

	public static int opConstC(int index) {
		return op(OPCODE_CONSTC) + index;
	}

	public static int opTStore(int value) {
		return op(OPCODE_TSTORE) + value;
	}

	public static int opConstB(int value) {
		return op(OPCODE_BCONST) + value;
	}

	public static int opConstS(int value) {
		return op(OPCODE_SCONST) + value;
	}

	public static int opEnd() {
		return op(OPCODE_END);
	}

	public static int opThrow(int index) {
		return op(OPCODE_THROW) + index;
	}

	public static int opSReturn(int params) {
		return op(OPCODE_SRET) + params;
	}

	public static int opReturn(int params) {
		return op(OPCODE_RET) + params;
	}

	public static int opDec(int value) {
		return op(OPCODE_DESC) + value;
	}

	public static int opInc(int value) {
		return op(OPCODE_INC) + value;
	}

	public static int opStore(int value) {
		return op(OPCODE_STORE) + value;
	}

	public static int opSumNumber() {
		return op(OPCODE_SUM_NUMBER);
	}

	public static int opSumString() {
		return op(OPCODE_SUM_STRING);
	}

	public static int opMul() {
		return op(OPCODE_MUL);
	}

	public static int opDiv() {
		return op(OPCODE_DIV);
	}

	public static int opSub() {
		return op(OPCODE_SUB);
	}

	public static int opEqualBoolean() {
		return op(OPCODE_EQUAL_BOOLEAN);
	}

	public static int opEqualString() {
		return op(OPCODE_EQUAL_STRING);
	}

	public static int opEqualNumber() {
		return op(OPCODE_EQUAL_NUMBER);
	}

	public static int opNotEqualBoolean() {
		return op(OPCODE_NEQUAL_BOOLEAN);
	}

	public static int opNotEqualString() {
		return op(OPCODE_NEQUAL_STRING);
	}

	public static int opNotEqualNumber() {
		return op(OPCODE_NEQUAL_NUMBER);
	}

	public static int opHigh() {
		return op(OPCODE_HIGH);
	}

	public static int opHighEqual() {
		return op(OPCODE_HIGHEQUAL);
	}

	public static int opLow() {
		return op(OPCODE_LOW);
	}

	public static int opLowEqual() {
		return op(OPCODE_LOWEQUAL);
	}

	public static int opHighString() {
		return op(OPCODE_HIGH_STRING);
	}

	public static int opHighEqualString() {
		return op(OPCODE_HIGHEQUAL_STRING);
	}

	public static int opLowString() {
		return op(OPCODE_LOW_STRING);
	}

	public static int opLowEqualString() {
		return op(OPCODE_LOWEQUAL_STRING);
	}

	public static int opOr() {
		return op(OPCODE_OR);
	}

	public static int opAnd() {
		return op(OPCODE_AND);
	}

	public static int opJumpFalse(int index) {
		return op(OPCODE_FJUMP) + index;
	}

	public static int opJump(int index) {
		return op(OPCODE_JUMP) + index;
	}

	public static int opLoad(int index) {
		return op(OPCODE_LOAD) + index;
	}

	public static int opJumpTrue(int index) {
		return op(OPCODE_TJUMP) + index;
	}

	public static int opCast(int index) {
		return op(OPCODE_CAST) + index;
	}

	public static int opNNCast(int index) {
		return op(OPCODE_NNCAST) + index;
	}

	public static int opNew(int index) {
		return op(OPCODE_NEW) + index;
	}

	public static int opNull() {
		return op(OPCODE_PUSHNIL);
	}

	public static int opNull2() {
		return op(OPCODE_PUSHNIL2);
	}

	public static int opNull3() {
		return op(OPCODE_PUSHNIL3);
	}

	public static int opNull4() {
		return op(OPCODE_PUSHNIL4);
	}

	public static int opNull5() {
		return op(OPCODE_PUSHNIL5);
	}

	public static int opIsEqual() {
		return op(OPCODE_IS_EQUAL);
	}

	public static int opIsNotEqual() {
		return op(OPCODE_IS_NOT_EQUAL);
	}

	public static int opNot() {
		return op(OPCODE_NOT);
	}

	public static int opOJump(int index) {
		return op(OPCODE_OJUMP) + index + 0x800000;
	}

	public static int opAJump(int index) {
		return op(OPCODE_OJUMP) + index;
	}

	public static int opSJump(int index) {
		return op(OPCODE_SJUMP) + index;
	}

	public static int opGetField(int index) {
		return op(OPCODE_GETFIELD) + index;
	}

	public static int opGetStaticField(int index) {
		return op(OPCODE_GET_STATIC_FIELD) + index;
	}

	public static int opSetField(int index) {
		return op(OPCODE_SETFIELD) + index;
	}

	public static int opSetStaticField(int index) {
		return op(OPCODE_SET_STATIC_FIELD) + index;
	}

	public static int opArray(int returns, int number) {
		return op(OPCODE_ARRAY) + (returns & 0xFF) << 16;
	}

	public static int op(int opcode) {
		return opcode << 24;
	}

	public static boolean isOpcode(int instruction, int opcode) {
		return ((instruction & 0xFF000000) >> 24) == opcode;
	}

	public static int opIABB(int opcode, int a, int b) {
		return ((opcode & 0xFF) << 24) + ((a & 0xFF) << 16) + (b & 0xFFFF);
	}

	public static int opTry(int index) {
		return op(OPCODE_TRY) + index;
	}

	public static int opTryReturn() {
		return op(OPCODE_TRET);
	}

	public static int opTryFalseReturn() {
		return op(OPCODE_TFRET);
	}

	public static int opClassJump(int index, int classindex) {
		return opIABB(OPCODE_CJUMP, index, classindex);
	}

	public static int opDup() {
		return op(OPCODE_DUP);
	}

	public static int opIncIndex(int index) {
		return op(OPCODE_INC_INDEX) + index;
	}

	public static int opCastNumberToInteger() {
		return op(OPCODE_CAST_NUMBER_TO_INTEGER);
	}

}
