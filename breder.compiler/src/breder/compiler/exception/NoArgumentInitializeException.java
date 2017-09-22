package breder.compiler.exception;

import breder.compiler.BrederArgument;

public class NoArgumentInitializeException extends BrederJRuntimeException {

	public NoArgumentInitializeException() {
		super(
				"\n"
						+ "Ex:\tbrederc <classname>\n"
						+ "\tbrederc <classname> <option> ...\n"
						+ "\n"
						+ "where options include:\n"
						+ "\t-cp <pathname>\n"
						+ "\t\tSpecify where to find the classes\n"
						+ "\t-l <linkname>\n"
						+ "\t\tSpecify the name of the c shared library without extension\n"
						+ String.format("\t-%s\n",
								BrederArgument.NO_DEFAULT_LIBRARY)
						+ "\t\tIndicate that will not use the BDK, only breder.lang.*\n"
						+ String.format("\t-%s\n", BrederArgument.NBH)
						+ "\t\tIndicate that will not consider the BREDER_HOME\n"
						+ String.format("\t-%s\n", BrederArgument.DESCRIBER)
						+ "\t\tNo compiler for describer the classes\n");
	}
	
}
