package breder.compiler.node.command;

import java.io.IOException;
import java.util.List;

import breder.compiler.compiler.BinaryOutputStream;
import breder.compiler.compiler.Context;
import breder.compiler.node.ICommand;
import breder.compiler.node.standart.BParam;
import breder.compiler.node.standart.Command;
import breder.compiler.node.standart.VariableDeclare;
import breder.compiler.parser.javacc.ParseException;
import breder.compiler.parser.javacc.Token;
import breder.compiler.util.LightArrayList;

public class Try extends Command implements ICommand {
	
	private Token tryToken;
	
	private Block block;
	
	private final List<VariableDeclare> catchs =
		new LightArrayList<VariableDeclare>();
	
	private final List<Block> blocks = new LightArrayList<Block>();
	
	private int[] declareIndex;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void syntax(Context context) throws ParseException {
		context.pushBlock(this.getBlock());
		this.getBlock().setParent(this);
		this.block.syntax(context);
		context.popBlock();
		for (int n = 0; n < this.catchs.size(); n++) {
			Block block = this.blocks.get(n);
			VariableDeclare param = this.catchs.get(n);
			block.setParent(this.getParent());
			param.setDeclared(true);
			param.syntax(context);
			context.pushBlock(block);
			block.syntax(context);
			context.popBlock();
		}
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * @throws ParseException
	 */
	@Override
	public void semantic(Context context) throws ParseException {
		this.block.semantic(context);
		int size = context.getMethod().getDeclares().size();
		this.declareIndex = new int[size];
		for (int n = 0; n < this.catchs.size(); n++) {
			Block block = this.blocks.get(n);
			BParam param = this.catchs.get(n);
			param.getType().setNotNull(true);
			param.semantic(context);
			block.semantic(context);
			for (int m = 0; m < size; m++) {
				BParam param2 = context.getMethod().getDeclares().get(m);
				if (param == param2) {
					this.declareIndex[n] = size - m - 1;
					break;
				}
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
		this.block.check(context);
		for (int n = 0; n < this.catchs.size(); n++) {
			Block block = this.blocks.get(n);
			BParam param = this.catchs.get(n);
			param.check(context);
			block.check(context);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void build(Context context, BinaryOutputStream output)
		throws IOException {
		int end = 1;
		{
			end += this.block.instCount(context) + 1;
			end += this.catchs.size() * 4;
			if (this.blocks.size() > 1) {
				end += 1;
			}
			for (Block block : this.blocks) {
				end += block.instCount(context);
			}
			end += output.getCounter();
		}
		{
			int index = this.block.instCount(context) + 3;
			output.printTry(index, this.getTryToken());
		}
		this.block.build(context, output);
		output.printTryReturn(this.getTryToken());
		output.printJumpAbs(end, this.getTryToken());
		for (int n = 0; n < this.catchs.size(); n++) {
			BParam declare = this.catchs.get(n);
			int classindex = declare.getType().getStruct().getIndex();
			output.printClassJump(this.catchs.size() - 1, classindex,
				declare.getName());
		}
		output.printTryFalseReturn(this.getTryToken());
		if (this.blocks.size() > 1) {
			for (int index = this.catchs.size(), n = 0; n < this.catchs.size(); index +=
				this.getBlocks().get(n).instCount(context) + 1, n++) {
				output.printJump(index, this.getCatchs().get(index).getName());
			}
		}
		for (int n = 0; n < this.catchs.size(); n++) {
			Block block = this.blocks.get(n);
			output.printTStore(this.declareIndex[n], this.getCatchs().get(n)
				.getName());
			output.setStack(0);
			block.build(context, output);
			if (n != this.catchs.size() - 1) {
				output.printJumpAbs(end, this.getTryToken());
			}
		}
	}
	
	@Override
	public boolean isReturned() {
		if (!this.getBlock().isReturned()) {
			return false;
		}
		for (Block block : this.getBlocks()) {
			if (!block.isReturned()) {
				return false;
			}
		}
		return true;
	}
	
	public int[] getDeclareIndex() {
		return declareIndex;
	}
	
	public Block getBlock() {
		return block;
	}
	
	public void setBlock(Block block) {
		this.block = block;
	}
	
	public List<VariableDeclare> getCatchs() {
		return catchs;
	}
	
	public List<Block> getBlocks() {
		return blocks;
	}
	
	public Token getTryToken() {
		return tryToken;
	}
	
	public void setTryToken(Token tryToken) {
		this.tryToken = tryToken;
	}
	
	public void setDeclareIndex(int[] declareIndex) {
		this.declareIndex = declareIndex;
	}
	
}
