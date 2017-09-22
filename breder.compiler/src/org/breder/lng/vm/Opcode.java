package org.breder.lng.vm;

public class Opcode {

	public static final int STACK_INC = 1;
	public static final int STACK_DEC = 2;
	public static final int STACK_STRING = 3;
	public static final int STACK_DOUBLE = 4;
	public static final int STACK_INTEGER = 5;
	public static final int STACK_TRUE = 6;
	public static final int STACK_FALSE = 7;
	public static final int STACK_NULL = 8;
	public static final int STACK_TERNARY = 9;
	
	public static final int NUMBER_SUM = 20;
	public static final int NUMBER_SUB = 21;
	public static final int NUMBER_MUL = 22;
	public static final int NUMBER_DIV = 23;
	public static final int NUMBER_EQ = 24;
	public static final int NUMBER_NEQ = 25;
	public static final int NUMBER_COMPARE = 26;
	public static final int NUMBER_GT = 27;
	public static final int NUMBER_GE = 28;
	public static final int NUMBER_LT = 29;
	public static final int NUMBER_LE = 30;
	public static final int NUMBER_MOD = 31;
	public static final int NUMBER_INT_DIV = 32;
	public static final int NUMBER_TO_STRING = 33;
	public static final int NUMBER_IS_NAN = 34;
	public static final int NUMBER_IS_INFINITY = 35;
	public static final int NUMBER_HASH = 36;
	public static final int NUMBER_NEG = 37;
	public static final int NUMBER_INC = 38;
	public static final int NUMBER_DEC = 39;

	public static final int INTEGER_SUM = 60;
	public static final int INTEGER_SUB = 61;
	public static final int INTEGER_MUL = 62;
	public static final int INTEGER_DIV = 63;
	public static final int INTEGER_EQ = 64;
	public static final int INTEGER_NEQ = 65;
	public static final int INTEGER_COMPARE = 66;
	public static final int INTEGER_GT = 67;
	public static final int INTEGER_GE = 68;
	public static final int INTEGER_LT = 69;
	public static final int INTEGER_LE = 70;
	public static final int INTEGER_AND = 71;
	public static final int INTEGER_OR = 72;
	public static final int INTEGER_MOD = 73;
	public static final int INTEGER_TO_STRING = 74;
	public static final int INTEGER_HASH = 75;
	public static final int INTEGER_NEG = 76;
	public static final int INTEGER_INC = 77;
	public static final int INTEGER_DEC = 78;

	public static final int BOOLEAN_NOT = 90;
	public static final int BOOLEAN_AND = 91;
	public static final int BOOLEAN_OR = 92;

	public static final int STRING_SUM = 100;

	public static final int CONTROL_JUMP = 150;
	public static final int CONTROL_JUMP_TRUE = 151;
	public static final int CONTROL_JUMP_FALSE = 152;
	public static final int CONTROL_JUMP_INT = 153;
	public static final int CONTROL_RETURN = 154;
	
	public static final int HALF = 255;

}
