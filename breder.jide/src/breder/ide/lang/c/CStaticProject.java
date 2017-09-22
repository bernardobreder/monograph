package breder.ide.lang.c;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import breder.ide.lang.IBuilder;
import breder.util.util.FileUtil;

public class CStaticProject extends CProject implements IBuilder {

	public CStaticProject(File dir) throws ParseException, IOException {
		super(dir);
	}

	@Override
	public void build() throws IOException {
		this.buildDepend();
		File binaryFile = new File(this.getTargets().get(0));
		FileUtil.remove(binaryFile.getParentFile());
		binaryFile.getParentFile().mkdirs();
		List<String> sources = new ArrayList<String>();
		for (File source : this.getSources()) {
			this.listSource(sources, source);
		}
		List<String> cobjects = new ArrayList<String>();
		for (String source : sources) {
			GccProcess process = new GccProcess();
			process.addOther("-c");
			process.addOther("-g");
			for (File include : this.getIncludes()) {
				process.addInclude(include.getAbsolutePath());
			}
			process.addSource(source);
			String name = source;
			name = name.substring(binaryFile.getParentFile().getAbsolutePath().length() + 1);
			name = name.substring(0, name.length() - ".c".length()) + ".o";
			name = new File(binaryFile.getParentFile(), name).getAbsolutePath();
			new File(name).getParentFile().mkdirs();
			cobjects.add(name);
			process.setOutput(name);
			process.start();
		}
		String libraryname = "lib" + binaryFile.getName() + ".a";
		for (int n = 0; n < this.getTargets().size(); n++) {
			File targetFile = new File(this.getTargets().get(n));
			ArProcess process = new ArProcess();
			File outputFile = new File(targetFile.getParentFile(), libraryname);
			process.setOutput(outputFile.getAbsolutePath());
			for (String cobject : cobjects) {
				process.addSource(cobject);
			}
			process.start();
		}
		// for (String cobject : cobjects) {
		// // new File(cobject).delete();
		// }
		// for (File file : targetFile0.listFiles()) {
		// if (!file.getName().equals(libraryname)) {
		// // FileUtil.remove(file);
		// }
		// }
	}

	private void listSource(List<String> list, File source) {
		for (File file : source.listFiles()) {
			if (file.isFile()) {
				if (file.getName().endsWith(".c")) {
					String name = file.getAbsolutePath();
					list.add(name);
				}
			} else {
				this.listSource(list, file);
			}
		}
	}

}
