package logic.instruction;

import breder.compiler.node.standart.BOpcode;

public abstract class Instruction implements Comparable<Instruction> {

	private final int inst;

	public Instruction(int inst) {
		super();
		this.inst = inst;
	}

	@Override
	public int compareTo(Instruction o) {
		return 0;
	}

	public int getOAAA(int n) {
		if (n == 0) {
			return getOpcode();
		} else if (n == 1) {
			return inst & 0xFFFFFF;
		} else
			throw new ArrayIndexOutOfBoundsException(n);
	}

	public int getOABB(int n) {
		if (n == 0) {
			return getOpcode();
		} else if (n == 1) {
			return (inst & 0xFF0000) >> 16;
		} else if (n == 2) {
			return inst & 0xFFFF;
		} else
			throw new ArrayIndexOutOfBoundsException(n);
	}

	public int getOpcode() {
		return inst >> 24;
	}

	public static Instruction newInstance(int inst) {
		int opcode = inst >> 24;
		switch (opcode) {
		case BOpcode.OPCODE_JUMP: {
			return new Jump(inst);
		}
		case BOpcode.OPCODE_END: {
			return new End(inst);
		}
		case BOpcode.OPCODE_RET: {
			return new Return(inst);
		}
		case BOpcode.OPCODE_SRET: {
			return new StaticReturn(inst);
		}
		case BOpcode.OPCODE_OJUMP: {
			return new OJump(inst);
		}
		case BOpcode.OPCODE_INC: {
			return new Inc(inst);
		}
		case BOpcode.OPCODE_DESC: {
			return new Desc(inst);
		}
		case BOpcode.OPCODE_BCONST: {
			return new BConst(inst);
		}
		case BOpcode.OPCODE_NEW: {
			return new New(inst);
		}
		case BOpcode.OPCODE_LOAD: {
			return new Load(inst);
		}
		case BOpcode.OPCODE_STORE: {
			return new Store(inst);
		}
		case BOpcode.OPCODE_NCONST: {
			return new NConst(inst);
		}
		case BOpcode.OPCODE_CONSTC: {
			return new ConstC(inst);
		}
		case BOpcode.OPCODE_NOT: {
			return new Not(inst);
		}
		case BOpcode.OPCODE_SCONST: {
			return new SConst(inst);
		}
		case BOpcode.OPCODE_SJUMP: {
			return new SJump(inst);
		}
		case BOpcode.OPCODE_SETFIELD: {
			return new SetField(inst);
		}
		case BOpcode.OPCODE_GETFIELD: {
			return new GetField(inst);
		}
		case BOpcode.OPCODE_CAST: {
			return new Cast(inst);
		}
		case BOpcode.OPCODE_SUM_NUMBER: {
			return new SumNumber(inst);
		}
		case BOpcode.OPCODE_SUB: {
			return new SubNumber(inst);
		}
		case BOpcode.OPCODE_MUL: {
			return new MulNumber(inst);
		}
		case BOpcode.OPCODE_DIV: {
			return new DivNumber(inst);
		}
		case BOpcode.OPCODE_NEQUAL_BOOLEAN: {
			return new NotEqualBoolean(inst);
		}
		case BOpcode.OPCODE_NEQUAL_STRING: {
			return new NotEqualString(inst);
		}
		case BOpcode.OPCODE_NEQUAL_NUMBER: {
			return new NotEqualNumber(inst);
		}
		case BOpcode.OPCODE_EQUAL_BOOLEAN: {
			return new EqualBoolean(inst);
		}
		case BOpcode.OPCODE_EQUAL_STRING: {
			return new EqualString(inst);
		}
		case BOpcode.OPCODE_EQUAL_NUMBER: {
			return new EqualNumber(inst);
		}
		case BOpcode.OPCODE_HIGH: {
			return new High(inst);
		}
		case BOpcode.OPCODE_HIGHEQUAL: {
			return new HighEqual(inst);
		}
		case BOpcode.OPCODE_LOW: {
			return new Low(inst);
		}
		case BOpcode.OPCODE_LOWEQUAL: {
			return new LowEqual(inst);
		}
		case BOpcode.OPCODE_TJUMP: {
			return new TrueJump(inst);
		}
		case BOpcode.OPCODE_FJUMP: {
			return new FalseJump(inst);
		}
		case BOpcode.OPCODE_PUSHNIL: {
			return new PushNil(inst);
		}
		case BOpcode.OPCODE_THROW: {
			return new Throw(inst);
		}
		case BOpcode.OPCODE_TRY: {
			return new Try(inst);
		}
		case BOpcode.OPCODE_TRET: {
			return new TryTrueReturn(inst);
		}
		case BOpcode.OPCODE_CJUMP: {
			return new ClassJump(inst);
		}
		case BOpcode.OPCODE_TFRET: {
			return new TryFalseReturn(inst);
		}
		case BOpcode.OPCODE_SET_STATIC_FIELD: {
			return new SetStaticField(inst);
		}
		case BOpcode.OPCODE_GET_STATIC_FIELD: {
			return new GetStaticField(inst);
		}
		case BOpcode.OPCODE_OR: {
			return new Or(inst);
		}
		case BOpcode.OPCODE_AND: {
			return new And(inst);
		}
		case BOpcode.OPCODE_ARRAY: {
			return new Array(inst);
		}
		case BOpcode.OPCODE_TSTORE: {
			return new ThrowStore(inst);
		}
		case BOpcode.OPCODE_NNCAST: {
			return new NotNullCast(inst);
		}
		case BOpcode.OPCODE_SUM_STRING: {
			return new SumString(inst);
		}
		case BOpcode.OPCODE_HIGH_STRING: {
			return new HighString(inst);
		}
		case BOpcode.OPCODE_HIGHEQUAL_STRING: {
			return new HighEqualString(inst);
		}
		case BOpcode.OPCODE_LOW_STRING: {
			return new LowString(inst);
		}
		case BOpcode.OPCODE_LOWEQUAL_STRING: {
			return new LowEqualString(inst);
		}
		case BOpcode.OPCODE_PUSHNIL2: {
			return new PushNil2(inst);
		}
		case BOpcode.OPCODE_PUSHNIL3: {
			return new PushNil3(inst);
		}
		case BOpcode.OPCODE_PUSHNIL4: {
			return new PushNil4(inst);
		}
		case BOpcode.OPCODE_PUSHNIL5: {
			return new PushNil5(inst);
		}
		case BOpcode.OPCODE_IS_EQUAL: {
			return new IsEqual(inst);
		}
		case BOpcode.OPCODE_IS_NOT_EQUAL: {
			return new IsNotEqual(inst);
		}
		case BOpcode.OPCODE_INC_INDEX: {
			return new IncIndex(inst);
		}
		case BOpcode.OPCODE_CAST_NUMBER_TO_INTEGER: {
			return new CastNumberToInteger(inst);
		}
		default: {
			throw new RuntimeException("" + opcode);
		}
		}
	}

	@Override
	public String toString() {
		return String.format("%s", this.getClass().getSimpleName());
	}

}
