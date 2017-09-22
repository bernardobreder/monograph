package breder.generic.compiler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GccCompiler extends AbstractCompiler {

	private boolean arch32;

	private File output;

	public void setArch32(boolean flag) {
		this.arch32 = flag;
	}

	public void setOutput(File output) {
		this.output = output;
	}

	@Override
	public File[] compile() throws IOException, InterruptedException {
		List<String> l = new ArrayList<String>();
		new ProcessBuilder(l).start().waitFor();
		return new File[] { this.output };
	}

}
