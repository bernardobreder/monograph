package org.breder.lng.node;

import java.io.IOException;

import org.breder.lng.node.command.BlockNode;
import org.breder.lng.node.command.ExpressionNode;
import org.breder.lng.node.command.ForNode;
import org.breder.lng.node.command.IfNode;
import org.breder.lng.node.command.NullNode;
import org.breder.lng.node.command.RepeatNode;
import org.breder.lng.node.command.WhileNode;
import org.breder.lng.node.value.binary.AndNode;
import org.breder.lng.node.value.binary.AssignNode;
import org.breder.lng.node.value.binary.DivNode;
import org.breder.lng.node.value.binary.EqNode;
import org.breder.lng.node.value.binary.GeNode;
import org.breder.lng.node.value.binary.GtNode;
import org.breder.lng.node.value.binary.LeNode;
import org.breder.lng.node.value.binary.LtNode;
import org.breder.lng.node.value.binary.MulNode;
import org.breder.lng.node.value.binary.NeNode;
import org.breder.lng.node.value.binary.OrNode;
import org.breder.lng.node.value.binary.SubNode;
import org.breder.lng.node.value.binary.SumNode;
import org.breder.lng.node.value.primitive.BooleanNode;
import org.breder.lng.node.value.primitive.IdentifyNode;
import org.breder.lng.node.value.primitive.NumberNode;
import org.breder.lng.node.value.primitive.StringNode;
import org.breder.lng.node.value.ternary.IfValueNode;
import org.breder.lng.node.value.unary.InvertNode;
import org.breder.lng.node.value.unary.NegativeNode;
import org.breder.lng.node.value.unary.PosDecNode;
import org.breder.lng.node.value.unary.PosIncNode;
import org.breder.lng.node.value.unary.PreDecNode;
import org.breder.lng.node.value.unary.PreIncNode;
import org.breder.lng.util.AbstractDataInputStream;
import org.breder.lng.util.AbstractDataOutputStream;
import org.breder.lng.util.AbstractOpcodeOutputStream;

public abstract class AbstractNode {

	public static final int NUMBER_VALUE = 1;
	public static final int STRING_VALUE = 2;
	public static final int TRUE_VALUE = 3;
	public static final int FALSE_VALUE = 4;
	public static final int IDENTIFY_VALUE = 5;
	public static final int INVERT_VALUE = 6;
	public static final int NEG_VALUE = 7;
	public static final int PRE_INC_VALUE = 8;
	public static final int PRE_DEC_VALUE = 9;
	public static final int POS_INC_VALUE = 10;
	public static final int POS_DEC_VALUE = 11;
	public static final int IF_VALUE = 12;
	public static final int OR_VALUE = 13;
	public static final int AND_VALUE = 14;
	public static final int EQ_VALUE = 15;
	public static final int NE_VALUE = 16;
	public static final int GE_VALUE = 17;
	public static final int LE_VALUE = 18;
	public static final int GT_VALUE = 19;
	public static final int LT_VALUE = 20;
	public static final int SUM_VALUE = 21;
	public static final int SUB_VALUE = 22;
	public static final int MUL_VALUE = 23;
	public static final int DIV_VALUE = 24;
	public static final int ASSIGN_VALUE = 25;
	public static final int IF_CMD = 26;
	public static final int WHILE_CMD = 27;
	public static final int REPEAT_CMD = 28;
	public static final int FOR_CMD = 29;
	public static final int BLOCK_CMD = 30;
	public static final int EXP_CMD = 31;
	public static final int NULL_CMD = 32;

	/**
	 * Realiza a deserialização do objeto
	 * 
	 * @param input
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public static <E extends AbstractNode> E read(AbstractDataInputStream input)
			throws IOException {
		int opcode = input.readIndex();
		switch (opcode) {
		case NUMBER_VALUE:
			return NumberNode.read(input);
		case STRING_VALUE:
			return StringNode.read(input);
		case TRUE_VALUE:
			return (E) BooleanNode.build(true);
		case FALSE_VALUE:
			return (E) BooleanNode.build(false);
		case IDENTIFY_VALUE:
			return IdentifyNode.read(input);
		case INVERT_VALUE:
			return InvertNode.read(input);
		case NEG_VALUE:
			return NegativeNode.read(input);
		case PRE_INC_VALUE:
			return PreIncNode.read(input);
		case PRE_DEC_VALUE:
			return PreDecNode.read(input);
		case POS_INC_VALUE:
			return PosIncNode.read(input);
		case POS_DEC_VALUE:
			return PosDecNode.read(input);
		case OR_VALUE:
			return OrNode.read(input);
		case AND_VALUE:
			return AndNode.read(input);
		case EQ_VALUE:
			return EqNode.read(input);
		case NE_VALUE:
			return NeNode.read(input);
		case GE_VALUE:
			return GeNode.read(input);
		case LE_VALUE:
			return LeNode.read(input);
		case GT_VALUE:
			return GtNode.read(input);
		case LT_VALUE:
			return LtNode.read(input);
		case SUM_VALUE:
			return SumNode.read(input);
		case SUB_VALUE:
			return SubNode.read(input);
		case MUL_VALUE:
			return MulNode.read(input);
		case DIV_VALUE:
			return DivNode.read(input);
		case ASSIGN_VALUE:
			return AssignNode.read(input);
		case IF_VALUE:
			return IfValueNode.read(input);
		case IF_CMD:
			return IfNode.read(input);
		case WHILE_CMD:
			return WhileNode.read(input);
		case REPEAT_CMD:
			return RepeatNode.read(input);
		case FOR_CMD:
			return ForNode.read(input);
		case BLOCK_CMD:
			return BlockNode.read(input);
		case EXP_CMD:
			return ExpressionNode.read(input);
		case NULL_CMD:
			return (E) NullNode.INSTANCE;
		default:
			throw new IllegalStateException();
		}
	}

	/**
	 * Realiza a escrita de opcodes
	 * 
	 * @param output
	 */
	public void build(AbstractOpcodeOutputStream output) throws IOException {
		throw new RuntimeException();
	}

	/**
	 * Realiza a serialização do objeto
	 * 
	 * @param output
	 * @throws IOException
	 */
	public abstract void write(AbstractDataOutputStream output)
			throws IOException;

}
