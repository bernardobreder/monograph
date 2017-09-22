package org.breder.lng.vm;

import java.io.IOException;
import java.io.InputStream;

import org.breder.lng.util.AbstractOpcodeInputStream;
import org.breder.lng.util.StreamOpcodeInputStream;

public class BrederVM {

	/**
	 * Executa uma stream de bytes
	 * 
	 * @param input
	 * @return
	 * @throws IOException
	 */
	public Object execute(InputStream input) throws IOException {
		AbstractOpcodeInputStream in = new StreamOpcodeInputStream(input);
		try {
			Object[] stack = new Object[1024];
			int stackIndex = -1;
			for (;;) {
				switch (in.readOpcode()) {
				case Opcode.STACK_INC: {
					stackIndex += in.readIndex();
					break;
				}
				case Opcode.STACK_DEC: {
					stackIndex += in.readIndex();
					break;
				}
				case Opcode.STACK_INTEGER: {
					stack[++stackIndex] = in.readInteger();
					break;
				}
				case Opcode.STACK_DOUBLE: {
					stack[++stackIndex] = in.readDouble();
					break;
				}
				case Opcode.STACK_STRING: {
					stack[++stackIndex] = in.readString();
					break;
				}
				case Opcode.STACK_TRUE: {
					stack[++stackIndex] = Boolean.TRUE;
					break;
				}
				case Opcode.STACK_FALSE: {
					stack[++stackIndex] = Boolean.FALSE;
					break;
				}
				case Opcode.STACK_TERNARY: {
					stackIndex -= 2;
					if (stack[stackIndex] == Boolean.TRUE) {
						stack[stackIndex] = stack[stackIndex + 1];
					} else {
						stack[stackIndex] = stack[stackIndex + 2];
					}
					break;
				}
				case Opcode.NUMBER_SUM: {
					stackIndex--;
					stack[stackIndex] = (Double) stack[stackIndex]
							+ (Double) stack[stackIndex + 1];
					break;
				}
				case Opcode.NUMBER_SUB: {
					stackIndex--;
					stack[stackIndex] = (Double) stack[stackIndex]
							- (Double) stack[stackIndex + 1];
					break;
				}
				case Opcode.NUMBER_MUL: {
					stackIndex--;
					stack[stackIndex] = (Double) stack[stackIndex]
							* (Double) stack[stackIndex + 1];
					break;
				}
				case Opcode.NUMBER_DIV: {
					stackIndex--;
					stack[stackIndex] = (Double) stack[stackIndex]
							/ (Double) stack[stackIndex + 1];
					break;
				}
				case Opcode.NUMBER_EQ: {
					stackIndex--;
					stack[stackIndex] = ((Double) stack[stackIndex])
							.equals((Double) stack[stackIndex + 1]) ? Boolean.TRUE
							: Boolean.FALSE;
					break;
				}
				case Opcode.NUMBER_NEQ: {
					stackIndex--;
					stack[stackIndex] = ((Double) stack[stackIndex])
							.equals((Double) stack[stackIndex + 1]) ? Boolean.FALSE
							: Boolean.TRUE;
					break;
				}
				case Opcode.NUMBER_COMPARE: {
					stackIndex--;
					stack[stackIndex] = Double
							.valueOf(((Double) stack[stackIndex])
									.compareTo((Double) stack[stackIndex + 1]));
					break;
				}
				case Opcode.NUMBER_GT: {
					stackIndex--;
					stack[stackIndex] = ((Double) stack[stackIndex])
							.compareTo((Double) stack[stackIndex + 1]) > 0 ? Boolean.TRUE
							: Boolean.FALSE;
					break;
				}
				case Opcode.NUMBER_GE: {
					stackIndex--;
					stack[stackIndex] = ((Double) stack[stackIndex])
							.compareTo((Double) stack[stackIndex + 1]) >= 0 ? Boolean.TRUE
							: Boolean.FALSE;
					break;
				}
				case Opcode.NUMBER_LT: {
					stackIndex--;
					stack[stackIndex] = ((Double) stack[stackIndex])
							.compareTo((Double) stack[stackIndex + 1]) < 0 ? Boolean.TRUE
							: Boolean.FALSE;
					break;
				}
				case Opcode.NUMBER_LE: {
					stackIndex--;
					stack[stackIndex] = ((Double) stack[stackIndex])
							.compareTo((Double) stack[stackIndex + 1]) <= 0 ? Boolean.TRUE
							: Boolean.FALSE;
					break;
				}
				case Opcode.NUMBER_NEG: {
					stack[stackIndex] = ((Double) stack[stackIndex]) * -1;
					break;
				}
				case Opcode.NUMBER_MOD: {
					stackIndex--;
					stack[stackIndex] = ((Double) stack[stackIndex])
							% ((Double) stack[stackIndex + 1]);
					break;
				}
				case Opcode.NUMBER_INT_DIV: {
					stackIndex--;
					stack[stackIndex] = (int) ((Double) stack[stackIndex])
							.doubleValue()
							/ ((Double) stack[stackIndex + 1]).doubleValue();
					break;
				}
				case Opcode.NUMBER_TO_STRING: {
					stack[stackIndex] = ((Double) stack[stackIndex]).toString();
					break;
				}
				case Opcode.NUMBER_IS_NAN: {
					stack[stackIndex] = ((Double) stack[stackIndex]).isNaN();
					break;
				}
				case Opcode.NUMBER_IS_INFINITY: {
					stack[stackIndex] = ((Double) stack[stackIndex])
							.isInfinite();
					break;
				}
				case Opcode.NUMBER_HASH: {
					stack[stackIndex] = ((Double) stack[stackIndex]).hashCode();
					break;
				}
				case Opcode.INTEGER_SUM: {
					stackIndex--;
					stack[stackIndex] = (Integer) stack[stackIndex]
							+ (Integer) stack[stackIndex + 1];
					break;
				}
				case Opcode.INTEGER_SUB: {
					stackIndex--;
					stack[stackIndex] = (Integer) stack[stackIndex]
							- (Integer) stack[stackIndex + 1];
					break;
				}
				case Opcode.INTEGER_MUL: {
					stackIndex--;
					stack[stackIndex] = (Integer) stack[stackIndex]
							* (Integer) stack[stackIndex + 1];
					break;
				}
				case Opcode.INTEGER_DIV: {
					stackIndex--;
					stack[stackIndex] = (Integer) stack[stackIndex]
							/ (Integer) stack[stackIndex + 1];
					break;
				}
				case Opcode.INTEGER_EQ: {
					stackIndex--;
					stack[stackIndex] = ((Integer) stack[stackIndex])
							.equals((Integer) stack[stackIndex + 1]) ? Boolean.TRUE
							: Boolean.FALSE;
					break;
				}
				case Opcode.INTEGER_NEQ: {
					stackIndex--;
					stack[stackIndex] = ((Integer) stack[stackIndex])
							.equals((Integer) stack[stackIndex + 1]) ? Boolean.FALSE
							: Boolean.TRUE;
					break;
				}
				case Opcode.INTEGER_COMPARE: {
					stackIndex--;
					stack[stackIndex] = ((Integer) stack[stackIndex])
							.compareTo((Integer) stack[stackIndex + 1]);
					break;
				}
				case Opcode.INTEGER_GT: {
					stackIndex--;
					stack[stackIndex] = ((Integer) stack[stackIndex])
							.compareTo((Integer) stack[stackIndex + 1]) > 0 ? Boolean.TRUE
							: Boolean.FALSE;
					break;
				}
				case Opcode.INTEGER_GE: {
					stackIndex--;
					stack[stackIndex] = ((Integer) stack[stackIndex])
							.compareTo((Integer) stack[stackIndex + 1]) >= 0 ? Boolean.TRUE
							: Boolean.FALSE;
					break;
				}
				case Opcode.INTEGER_LT: {
					stackIndex--;
					stack[stackIndex] = ((Integer) stack[stackIndex])
							.compareTo((Integer) stack[stackIndex + 1]) < 0 ? Boolean.TRUE
							: Boolean.FALSE;
					break;
				}
				case Opcode.INTEGER_LE: {
					stackIndex--;
					stack[stackIndex] = ((Integer) stack[stackIndex])
							.compareTo((Integer) stack[stackIndex + 1]) <= 0 ? Boolean.TRUE
							: Boolean.FALSE;
					break;
				}
				case Opcode.INTEGER_NEG: {
					stack[stackIndex] = -((Integer) stack[stackIndex]);
					break;
				}
				case Opcode.INTEGER_AND: {
					stackIndex--;
					stack[stackIndex] = ((Integer) stack[stackIndex])
							& ((Integer) stack[stackIndex + 1]);
					break;
				}
				case Opcode.INTEGER_OR: {
					stackIndex--;
					stack[stackIndex] = ((Integer) stack[stackIndex])
							| ((Integer) stack[stackIndex + 1]);
					break;
				}
				case Opcode.INTEGER_MOD: {
					stackIndex--;
					stack[stackIndex] = ((Integer) stack[stackIndex])
							% ((Integer) stack[stackIndex + 1]);
					break;
				}
				case Opcode.INTEGER_TO_STRING: {
					stack[stackIndex] = ((Integer) stack[stackIndex])
							.toString();
					break;
				}
				case Opcode.INTEGER_HASH: {
					break;
				}
				case Opcode.STRING_SUM: {
					stackIndex--;
					stack[stackIndex] = (String) stack[stackIndex]
							+ (String) stack[stackIndex + 1];
					break;
				}
				case Opcode.CONTROL_JUMP: {
					in.goTo(in.readIndex());
					break;
				}
				case Opcode.BOOLEAN_AND: {
					stackIndex--;
					stack[stackIndex] = stack[stackIndex] == Boolean.TRUE
							&& (Boolean) stack[stackIndex + 1] == Boolean.TRUE;
					break;
				}
				case Opcode.BOOLEAN_OR: {
					stackIndex--;
					stack[stackIndex] = stack[stackIndex] == Boolean.TRUE
							|| (Boolean) stack[stackIndex + 1] == Boolean.TRUE;
					break;
				}
				case Opcode.BOOLEAN_NOT: {
					stack[stackIndex] = stack[stackIndex] == Boolean.TRUE ? Boolean.FALSE
							: Boolean.TRUE;
					break;
				}
				case Opcode.CONTROL_JUMP_TRUE: {
					if (stack[stackIndex] == Boolean.TRUE) {
						in.goTo(in.readIndex());
					}
					stackIndex--;
					break;
				}
				case Opcode.CONTROL_JUMP_FALSE: {
					if (stack[stackIndex] == Boolean.FALSE) {
						in.goTo(in.readIndex());
					}
					stackIndex--;
					break;
				}
				case Opcode.CONTROL_JUMP_INT: {
					if ((Integer) stack[stackIndex] == in.readIndex()) {
						in.goTo(in.readIndex());
					}
					stackIndex--;
					break;
				}
				case Opcode.CONTROL_RETURN: {
					return stack[stackIndex];
				}
				case Opcode.HALF: {
					return stackIndex < 0 ? null : stack[stackIndex];
				}
				}
			}
		} finally {
			in.close();
		}
	}
}
