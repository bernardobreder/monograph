
package breder.compiler.compiler;

import java.io.IOException;

import breder.compiler.node.standart.BOpcode;
import breder.compiler.parser.javacc.Token;

public class BinaryOutputStream extends AbstractCompilerOutputStream {

	public BinaryOutputStream(Context context, SkipByteArrayOutputStream output) {
		super(context, output);
	}

	public void printConstNumber(int value, Token token) throws IOException {
		this.printlnInt(BOpcode.opConstNum(value), token);
		stack++;
	}

	public void printConstB(int value, Token token) throws IOException {
		this.printlnInt(BOpcode.opConstB(value != 0 ? 1 : 0), token);
		stack++;
	}

	public void printConstC(int value, Token token) throws IOException {
		this.printlnInt(BOpcode.opConstC(value), token);
		stack++;
	}

	public void printConstS(int value, Token token) throws IOException {
		this.printlnInt(BOpcode.opConstS(value), token);
		stack++;
	}

	public void printEnd(short cindex, short line) throws IOException {
		this.printlnInt(BOpcode.opEnd(), cindex, line);
	}

	public void printReturn(int params, Token token) throws IOException {
		this.printlnInt(BOpcode.opReturn(params), token);
		stack -= params;
	}

	public void printDec(int value, Token token) throws IOException {
		this.printlnInt(BOpcode.opDec(value), token);
	}

	public void printInc(int value, Token token) throws IOException {
		if (value <= 0) {
		} else if (value == 1) {
			this.printInc(token);
		} else if (value == 2) {
			this.printInc2(token);
		} else if (value == 3) {
			this.printInc3(token);
		} else if (value == 4) {
			this.printInc4(token);
		} else if (value == 5) {
			this.printInc5(token);
		} else {
			this.printlnInt(BOpcode.opInc(value), token);
		}
	}

	public void printStore(int index, Token token) throws IOException {
		this.printStoreAbs(index + stack, token);
	}

	public void printTStore(int index, Token token) throws IOException {
		this.printTStoreAbs(index + stack, token);
	}

	public void printStoreAbs(int index, Token token) throws IOException {
		this.printlnInt(BOpcode.opStore(index), token);
		stack--;
	}

	public void printTStoreAbs(int index, Token token) throws IOException {
		this.printlnInt(BOpcode.opTStore(index), token);
		stack--;
	}

	public void printlnInt(int value, Token token) throws IOException {
		this.printlnInt(value, this.getContext().getStruct().getIndex(), token == null ? 0 : (short) token.beginLine);
	}

	public void printlnInt(int value, short cindex, short line) throws IOException {
		output.write((value & 0xFF000000) >> 24);
		output.write((value & 0xFF0000) >> 16);
		output.write((value & 0xFF00) >> 8);
		output.write((value & 0xFF) >> 0);
		// output.write((0) >> 24);
		// output.write((0) >> 16);
		// output.write((0) >> 8);
		// output.write((0) >> 0);
		// this.printInt(cindex, line);
		counter++;
	}

	public void printInt(short cindex, short line) throws IOException {
		output.write((cindex & 0xFF00) >> 24);
		output.write((cindex & 0xFF) >> 16);
		output.write((line & 0xFF00) >> 8);
		output.write((line & 0xFF) >> 0);
	}

	public void printSumNumber(Token token) throws IOException {
		this.printlnInt(BOpcode.opSumNumber(), token);
		stack--;
	}

	public void printSumString(Token token) throws IOException {
		this.printlnInt(BOpcode.opSumString(), token);
		stack--;
	}

	public void printSub(Token token) throws IOException {
		this.printlnInt(BOpcode.opSub(), token);
		stack--;
	}

	public void printMul(Token token) throws IOException {
		this.printlnInt(BOpcode.opMul(), token);
		stack--;
	}

	public void printDiv(Token token) throws IOException {
		this.printlnInt(BOpcode.opDiv(), token);
		stack--;
	}

	public void printHigh(Token token) throws IOException {
		this.printlnInt(BOpcode.opHigh(), token);
		stack--;
	}

	public void printHighEqual(Token token) throws IOException {
		this.printlnInt(BOpcode.opHighEqual(), token);
		stack--;
	}

	public void printLow(Token token) throws IOException {
		this.printlnInt(BOpcode.opLow(), token);
		stack--;
	}

	public void printLowEqual(Token token) throws IOException {
		stack--;
		this.printlnInt(BOpcode.opLowEqual(), token);
	}

	public void printHighString(Token token) throws IOException {
		this.printlnInt(BOpcode.opHighString(), token);
		stack--;
	}

	public void printHighEqualString(Token token) throws IOException {
		this.printlnInt(BOpcode.opHighEqualString(), token);
		stack--;
	}

	public void printLowString(Token token) throws IOException {
		this.printlnInt(BOpcode.opLowString(), token);
		stack--;
	}

	public void printLowEqualString(Token token) throws IOException {
		stack--;
		this.printlnInt(BOpcode.opLowEqualString(), token);
	}

	public void printOr(Token token) throws IOException {
		this.printlnInt(BOpcode.opOr(), token);
		stack--;
	}

	public void printAnd(Token token) throws IOException {
		this.printlnInt(BOpcode.opAnd(), token);
		stack--;
	}

	public void printJumpFalse(int index, Token token) throws IOException {
		this.printJumpFalseAbs(index + counter, token);
	}

	public void printJumpFalseAbs(int index, Token token) throws IOException {
		this.printlnInt(BOpcode.opJumpFalse(index), token);
		stack--;
	}

	public void printJump(int index, Token token) throws IOException {
		this.printJumpAbs(index + counter, token);
	}

	public void printJumpAbs(int index, Token token) throws IOException {
		this.printlnInt(BOpcode.opJump(index), token);
	}

	public void printLoad(int index, Token token) throws IOException {
		this.printLoadAbs(index + stack, token);
	}

	public void printLoadAbs(int index, Token token) throws IOException {
		this.printlnInt(BOpcode.opLoad(index) + 1, token);
		stack++;
	}

	public void printJumpTrue(int index, Token token) throws IOException {
		this.printJumpTrueAbs(index + counter, token);
		stack--;
	}

	public void printNew(int index, Token token) throws IOException {
		this.printlnInt(BOpcode.opNew(index), token);
		stack++;
	}

	public void printNull(Token token) throws IOException {
		this.printlnInt(BOpcode.opNull(), token);
		stack++;
	}

	public void printInc(Token token) throws IOException {
		this.printlnInt(BOpcode.opNull(), token);
	}

	public void printInc2(Token token) throws IOException {
		this.printlnInt(BOpcode.opNull2(), token);
	}

	public void printInc3(Token token) throws IOException {
		this.printlnInt(BOpcode.opNull3(), token);
	}

	public void printInc4(Token token) throws IOException {
		this.printlnInt(BOpcode.opNull4(), token);
	}

	public void printInc5(Token token) throws IOException {
		this.printlnInt(BOpcode.opNull5(), token);
	}

	public void printOJump(int params, int index, Token token) throws IOException {
		this.printlnInt(BOpcode.opOJump(index), token);
		stack -= params;
	}

	public void printAJump(int params, int index, Token token) throws IOException {
		this.printlnInt(BOpcode.opAJump(index), token);
		stack -= params;
	}

	public void printSJump(int index, Token token) throws IOException {
		this.printlnInt(BOpcode.opSJump(index), token);
	}

	public void printGetField(int index, Token token) throws IOException {
		this.printlnInt(BOpcode.opGetField(index), token);
	}

	public void printGetStaticField(int index, Token token) throws IOException {
		this.printlnInt(BOpcode.opGetStaticField(index), token);
		stack++;
	}

	public void printSetField(int index, Token token) throws IOException {
		this.printlnInt(BOpcode.opSetField(index), token);
		stack -= 2;
	}

	public void printSetStaticField(int index, Token token) throws IOException {
		this.printlnInt(BOpcode.opSetStaticField(index), token);
		stack--;
	}

	public void printArray(int returns, int number) throws IOException {
		output.write(BOpcode.OPCODE_ARRAY);
		output.write(0);
		output.write(returns);
		output.write(number);
		stack -= returns - 1;
		counter++;
	}

	public void printThrow(int index, Token token) throws IOException {
		this.printlnInt(BOpcode.opThrow(index), token);
	}

	public void printLibrary(String library) throws IOException {
		this.print("(" + library.length() + "," + library + ")");
	}

	public void printNativeMethod(int index, String library) throws IOException {
		this.print("(" + index + "," + library.length() + "," + library + ")");
	}

	public void printJumpTrueAbs(int index, Token token) throws IOException {
		this.printlnInt(BOpcode.opJumpTrue(index), token);
		stack--;
	}

	public void printCast(int index, Token token) throws IOException {
		this.printlnInt(BOpcode.opCast(index), token);
	}

	public void printTry(int index, Token token) throws IOException {
		this.printlnInt(BOpcode.opTry(index + counter), token);
	}

	public void printTryReturn(Token token) throws IOException {
		this.printlnInt(BOpcode.opTryReturn(), token);
	}

	public void printTryFalseReturn(Token token) throws IOException {
		this.printlnInt(BOpcode.opTryFalseReturn(), token);
	}

	public void printClassJump(int index, int classindex, Token token) throws IOException {
		this.printlnInt(BOpcode.opClassJump(index + 1, classindex), token);
	}

	public void printNot(Token token) throws IOException {
		this.printlnInt(BOpcode.opNot(), token);
	}

	public void printNNCast(int index, Token token) throws IOException {
		this.printlnInt(BOpcode.opNNCast(index), token);
	}

	public void printString(String classname) throws IOException {
		this.print("(" + classname.length() + "," + classname + ")");
	}

	public void printIsEqual(Token token) throws IOException {
		this.printlnInt(BOpcode.opIsEqual(), token);
	}

	public void printIsNotEqual(Token token) throws IOException {
		this.printlnInt(BOpcode.opIsNotEqual(), token);
	}

	public void printEqualBoolean(Token token) throws IOException {
		this.printlnInt(BOpcode.opEqualBoolean(), token);
		stack--;
	}

	public void printEqualString(Token token) throws IOException {
		this.printlnInt(BOpcode.opEqualString(), token);
		stack--;
	}

	public void printEqualNumber(Token token) throws IOException {
		this.printlnInt(BOpcode.opEqualNumber(), token);
		stack--;
	}

	public void printNotEqualBoolean(Token token) throws IOException {
		this.printlnInt(BOpcode.opNotEqualBoolean(), token);
		stack--;
	}

	public void printNotEqualString(Token token) throws IOException {
		this.printlnInt(BOpcode.opNotEqualString(), token);
		stack--;
	}

	public void printNotEqualNumber(Token token) throws IOException {
		this.printlnInt(BOpcode.opNotEqualNumber(), token);
		stack--;
	}

	public void printDup(Token token) throws IOException {
		this.printlnInt(BOpcode.opDup(), token);
		stack++;
	}

	public void printIncIndex(int index, Token token) throws IOException {
		this.printlnInt(BOpcode.opIncIndex(index), token);
	}

	public void printCastNumberToInteger(Token token) throws IOException {
		this.printlnInt(BOpcode.opCastNumberToInteger(), token);
	}

	public void printSize(int size) throws IOException {
		this.print(size);
		this.print(",");
	}

	public void printInts(int[] list) throws IOException {
		if (list.length == 0) {
			this.print("(0,)");
			return;
		}
		this.print("(");
		this.print(list.length + ",");
		for (int n = 0; n < list.length; n++) {
			this.print(list[n] + ",");
		}
		this.print(")");
	}

	public void begin() throws IOException {
		this.print("BB|");
	}

	public void save(Integer value) throws IOException {
		this.print(value.toString() + "|");
	}

	public void save(String value) throws IOException {
		this.print(value.length() + "|" + value + "|");
	}

	public void save(int[] array) throws IOException {
		this.print(array.length + "|");
		for (int item : array) {
			this.print(item + "|");
		}
	}

}
