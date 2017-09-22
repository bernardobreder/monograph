package breder.plugin.util;

import java.io.File;

public class BrederLanguageFile extends File {

	public BrederLanguageFile() {
		super(new HomeFile(), "blng");
	}

	public BrederLanguageFile(String... paths) {
		super(FileUtil.build(new File(new HomeFile(), "blng"), paths)
				.toString());
	}

}
