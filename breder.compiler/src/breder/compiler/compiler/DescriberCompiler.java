package breder.compiler.compiler;

import java.io.IOException;

import breder.compiler.node.standart.BBasicStruct;
import breder.compiler.node.standart.BClass;
import breder.compiler.node.standart.BField;
import breder.compiler.node.standart.BGeneric;
import breder.compiler.node.standart.BMethod;
import breder.compiler.node.standart.BSource;
import breder.compiler.node.standart.BStruct;
import breder.compiler.node.standart.BType;
import breder.compiler.parser.javacc.Token;

public class DescriberCompiler extends AbstractCompiler {

	@Override
	protected void save() throws IOException {
		this.info(0, "number of source : %d", this.getSources().length);
		for (BSource source : this.getSources()) {
			this.printClassname(source);
			this.printImports(source);
			this.printStruct(source.getStruct());
		}
	}

	private void printClassname(BSource source) {
		this.info(1, "Starting with the source : %s", source.getClassname());
	}

	private void printImports(BSource source) {
		this.info(2, "number of imports : %d", source.getImports().size());
		for (Token token : source.getImports()) {
			this.info(3, "import : %s", token.image);
		}
	}

	private void printStruct(BStruct struct) {
		this.printTypeStruct(struct);
		this.printGenerics(struct);
		this.printExtends(struct);
		this.printImplements(struct);
		this.printFields(struct);
		this.printMethods(struct);
		this.line();
	}

	private void printGenerics(BStruct struct) {
		int size;
		if (struct instanceof BBasicStruct) {
			size = ((BBasicStruct) struct).getGenerics().size();
		} else {
			size = 0;
		}
		this.info(2, "number of generics : %d", size);
		if (struct instanceof BBasicStruct) {
			BBasicStruct bstruct = (BBasicStruct) struct;
			for (BGeneric generic : bstruct.getGenerics()) {
				this.info(3, "generic : %s", generic);
			}
		}
	}

	private void printMethods(BStruct struct) {
		this.info(2, "number of methods : %d", struct.getMethods().size());
		for (BMethod method : struct.getMethods()) {
			this.info(3, "method : %s", method.toAbsoluteString());
		}
	}

	private void printFields(BStruct struct) {
		this.info(2, "number of fields : %d", struct.getFields().size());
		for (BField field : struct.getFields()) {
			this.info(3, "field : %s %s", field.getType(),
					field.getName().image);
		}
	}

	private void printImplements(BStruct struct) {
		if (struct.isClassStruct()) {
			BClass cstruct = (BClass) struct;
			this.info(2, "number of implements : %d", cstruct.getImplements()
					.size());
			for (BType type : cstruct.getImplements()) {
				this.info(3, "implement : %s", type);
			}
		} else {
			this.info(2, "number of implements : 0");
		}
	}

	private void printExtends(BStruct struct) {
		this.info(2, "number of extends : %d", struct.getExtends().size());
		for (BType type : struct.getExtends()) {
			this.info(3, "extend : %s", type);
		}
	}

	private void printTypeStruct(BStruct struct) {
		String type;
		if (struct.isClassStruct()) {
			type = "class";
		} else if (struct.isInterfaceStruct()) {
			type = "interface";
		} else {
			type = "unknown";
		}
		this.info(2, "type : %s", type);
	}

	private void info(int level, String format, Object... objects) {
		StringBuilder sb = new StringBuilder(level);
		for (int n = 0; n < level; n++) {
			sb.append('\t');
		}
		System.out.println(sb.toString() + "[info] : "
				+ String.format(format, objects));
	}

	private void line() {
		System.out.println();
	}

}
