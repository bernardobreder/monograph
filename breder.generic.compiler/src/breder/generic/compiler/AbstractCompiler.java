package breder.generic.compiler;

import java.io.File;
import java.io.IOException;

public abstract class AbstractCompiler {

	public abstract File[] compile() throws IOException, InterruptedException;

}
