
package breder.compiler.node.command;

import java.io.IOException;
import java.util.List;

import breder.compiler.compiler.BinaryOutputStream;
import breder.compiler.compiler.Context;
import breder.compiler.exception.BrederJRuntimeException;
import breder.compiler.node.ICommand;
import breder.compiler.node.IRValue;
import breder.compiler.node.rvalue.ConditionalBinary;
import breder.compiler.node.rvalue.EqualConditionalBinary;
import breder.compiler.node.rvalue.NotEqual;
import breder.compiler.node.rvalue.RNull;
import breder.compiler.node.standart.BType;
import breder.compiler.node.standart.Command;
import breder.compiler.parser.javacc.ParseException;
import breder.compiler.util.LightArrayList;

public class If extends Command implements ICommand {

	private IRValue condition;

	private Block trueBlock;

	private final List<IRValue> otherConditions = new LightArrayList<IRValue>();

	private final List<Block> otherBlocks = new LightArrayList<Block>();

	private Block falseBlock;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void syntax(Context context) throws ParseException {
		this.getTrueBlock().setParent(this);
		this.condition.syntax(context);
		List<BType> types = new LightArrayList<BType>();
		List<Boolean> oldTypes = new LightArrayList<Boolean>();
		{
			this.applyTypeNotNull(this.getCondition(), oldTypes, types);
			this.trueBlock.syntax(context);
		}
		for (IRValue cond : this.getOtherConditions()) {
			cond.syntax(context);
		}
		for (Block block : this.getOtherBlocks()) {
			block.setParent(this);
			block.syntax(context);
		}
		if (this.falseBlock != null) {
			for (BType type : types) {
				type.setNotNull(!type.isNotNull());
			}
			this.falseBlock.setParent(this);
			this.falseBlock.syntax(context);
		}
		for (int n = 0; n < types.size(); n++) {
			types.get(n).setNotNull(oldTypes.get(n));
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws ParseException
	 */
	@Override
	public void semantic(Context context) throws ParseException {
		this.condition.semantic(context);
		if (this.condition.getTypes().length == 0) {
			throw new BrederJRuntimeException(context, this.condition.getToken(),
					"condition not return a boolean value");
		}
		// if (!this.condition.getType().getStruct().isBoolean()) {
		// this.condition = new NotEqual(this.condition, new RNull(null),
		// this.condition.getToken());
		// this.condition.syntax(context);
		// this.condition.semantic(context);
		// }
		List<BType> types = new LightArrayList<BType>();
		List<Boolean> oldTypes = new LightArrayList<Boolean>();
		this.applyTypeNotNull(this.getCondition(), oldTypes, types);
		this.trueBlock.semantic(context);
		for (int n = 0; n < this.getOtherConditions().size(); n++) {
			IRValue cond = this.getOtherConditions().get(n);
			Block block = this.getOtherBlocks().get(n);
			List<BType> elseTypes = new LightArrayList<BType>();
			List<Boolean> elseOldTypes = new LightArrayList<Boolean>();
			this.applyTypeNotNull(cond, elseOldTypes, elseTypes);
			cond.semantic(context);
			block.semantic(context);
			for (int m = 0; m < elseTypes.size(); m++) {
				elseTypes.get(m).setNotNull(elseOldTypes.get(m));
			}
		}
		if (this.falseBlock != null) {
			for (BType type : types) {
				type.setNotNull(!type.isNotNull());
			}
			this.falseBlock.semantic(context);
		}
		for (int n = 0; n < types.size(); n++) {
			types.get(n).setNotNull(oldTypes.get(n));
		}
	}

	private void applyTypeNotNull(IRValue condition, List<Boolean> oldTypes, List<BType> types) {
		BType ltype = null;
		if (condition instanceof EqualConditionalBinary) {
			EqualConditionalBinary cond = (EqualConditionalBinary) condition;
			if (cond.getRight() instanceof RNull) {
				ltype = cond.getLeft().getType();
				oldTypes.add(ltype.isNotNull());
				ltype.setNotNull(cond instanceof NotEqual);
				types.add(ltype);
			}
		} else if (condition instanceof ConditionalBinary) {
			ConditionalBinary cond = (ConditionalBinary) condition;
			if (cond.getLeft() instanceof ConditionalBinary) {
				this.applyTypeNotNull((ConditionalBinary) cond.getLeft(), oldTypes, types);
			}
			if (cond.getRight() instanceof ConditionalBinary) {
				this.applyTypeNotNull((ConditionalBinary) cond.getRight(), oldTypes, types);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws ParseException
	 */
	@Override
	public void check(Context context) throws ParseException {
		this.condition.check(context);
		if (!this.condition.getType().getStruct().isBoolean()) {
			throw new BrederJRuntimeException(context, this.condition.getToken(),
					"condition of the if statement need be a boolean expression");
		}
		this.trueBlock.check(context);
		for (IRValue cond : this.getOtherConditions()) {
			if (!this.condition.getType().getStruct().isBoolean()) {
				throw new BrederJRuntimeException(context, cond.getToken(),
						"condition of the if statement need be a boolean expression");
			}
			cond.check(context);
		}
		for (Block block : this.getOtherBlocks()) {
			block.check(context);
		}
		if (this.falseBlock != null) {
			this.falseBlock.check(context);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void build(Context context, BinaryOutputStream output) throws IOException {
		int inc = this.falseBlock != null || this.otherBlocks.size() > 0 ? 1 : 0;
		int end = 0;
		{
			end += inc + 1;
			end += this.condition.instCount(context);
			end += this.trueBlock.instCount(context);
			for (int n = 0; n < this.otherConditions.size(); n++) {
				end += this.otherConditions.get(n).instCount(context);
				end += this.otherBlocks.get(n).instCount(context);
			}
			if (this.falseBlock != null) {
				end += this.falseBlock.instCount(context);
			}
			end += this.otherConditions.size() * 2;
			end += output.getCounter();
		}
		this.condition.build(context, output);
		{
			int index = this.trueBlock.instCount(context) + inc + 1;
			output.printJumpFalse(index, this.getCondition().getToken());
		}
		this.trueBlock.build(context, output);
		if (inc == 1) {
			output.printJumpAbs(end, this.getCondition().getToken());
			for (int n = 0; n < this.otherBlocks.size(); n++) {
				Block block = this.otherBlocks.get(n);
				IRValue cond = this.otherConditions.get(n);
				cond.build(context, output);
				int index = block.instCount(context) + 2;
				output.printJumpFalse(index, cond.getToken());
				block.build(context, output);
				output.printJumpAbs(end, cond.getToken());
			}
			if (falseBlock != null) {
				this.falseBlock.build(context, output);
			}
		}
	}

	@Override
	public boolean isReturned() {
		if (this.falseBlock == null) {
			return false;
		}
		if (!this.getTrueBlock().isReturned()) {
			return false;
		}
		for (Block block : this.otherBlocks) {
			if (!block.isReturned()) {
				return false;
			}
		}
		return true;
	}

	public IRValue getCondition() {
		return condition;
	}

	public Block getTrueBlock() {
		return trueBlock;
	}

	public Block getFalseBlock() {
		return falseBlock;
	}

	public void setCondition(IRValue condition) {
		this.condition = condition;
	}

	public void setTrueBlock(Block trueBlock) {
		this.trueBlock = trueBlock;
	}

	public void setFalseBlock(Block falseBlock) {
		this.falseBlock = falseBlock;
	}

	public List<IRValue> getOtherConditions() {
		return otherConditions;
	}

	public List<Block> getOtherBlocks() {
		return otherBlocks;
	}

}
