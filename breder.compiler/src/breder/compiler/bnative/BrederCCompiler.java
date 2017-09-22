package breder.compiler.bnative;

import java.io.FileOutputStream;
import java.io.IOException;

import breder.compiler.BrederArgument;
import breder.compiler.GenericCompiler;
import breder.compiler.compiler.AbstractCompiler;
import breder.compiler.node.ICommand;
import breder.compiler.node.command.Block;
import breder.compiler.node.standart.BMethod;
import breder.compiler.node.standart.BParam;
import breder.compiler.node.standart.BSource;

public class BrederCCompiler extends AbstractCompiler {

	private BrederCOutputStream output;

	public static void main(String[] args) throws Throwable {
		BrederArgument arguments = new BrederArgument(args);
		AbstractCompiler compiler = new BrederCCompiler();
		String classname = arguments.configure(compiler);
		GenericCompiler.main(compiler, classname);
	}

	public BrederCCompiler() {
		this.setOutput("binary.c");
		AbstractCompiler.setCompiler(this);
	}

	@Override
	protected void save() throws IOException {
		try {
			this.output = new BrederCOutputStream(new FileOutputStream(
					this.getOutput()));
			this.saveLibrary();
			this.saveDefines();
			this.saveTypeDefs();
			this.saveMethodDeclares();
			this.saveMethods();
			output.close();
		} finally {
			if (this.output != null) {
				this.output.close();
			}
		}
	}

	private void saveLibrary() throws IOException {
		output.println("#include <stdlib.h>");
		output.println("#include <stdio.h>");
		this.output.println();
	}

	private void saveDefines() throws IOException {
		// TODO Auto-generated method stub

	}

	private void saveTypeDefs() throws IOException {
		output.println("typedef char b_state_t ;");
		this.output.println();
	}

	private void saveMethodDeclares() throws IOException {
		for (BSource source : this.getSources()) {
			for (BMethod method : source.getStruct().getMethods()) {
				String name = method.getNativeName();
				StringBuilder params = new StringBuilder();
				for (int n = 0; n < method.getParams().size(); n++) {
					BParam param = method.getParams().get(n);
					String mname = param.getType().getStruct().getNativeName();
					String pname = param.getName().image;
					params.append(mname + " " + pname);
					if (n != method.getParams().size() - 1) {
						params.append(", ");
					}
				}
				this.output.println(String.format("b_state_t %s (%s) ;", name,
						params));
			}
		}
		this.output.println();
	}

	private void saveMethods() throws IOException {
		for (BSource source : this.getSources()) {
			for (BMethod method : source.getStruct().getMethods()) {
				String name = method.getNativeName();
				StringBuilder params = new StringBuilder();
				for (int n = 0; n < method.getParams().size(); n++) {
					BParam param = method.getParams().get(n);
					String mname = param.getType().getStruct().getNativeName();
					String pname = param.getName().image;
					params.append(mname + " " + pname);
					if (n != method.getParams().size() - 1) {
						params.append(", ");
					}
				}
				this.output.print(String.format("b_state_t %s (%s) ", name,
						params));
				if (method.isNative()) {
					this.output.println(";");
				} else if (source.getStruct().isInterfaceStruct()) {
				} else {
					this.output.println();
					this.saveBlock(method.getBlock());
				}
				this.output.println();
			}
		}
	}

	private void saveBlock(Block block) throws IOException {
		this.output.println("{");
		this.output.incTab();
		for (ICommand command : block.getCommands()) {
		}
		this.output.decTab();
		this.output.println("}");
	}

}
