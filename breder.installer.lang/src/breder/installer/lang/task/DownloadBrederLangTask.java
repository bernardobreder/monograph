package breder.installer.lang.task;

import java.io.ByteArrayInputStream;
import java.util.zip.ZipInputStream;

import javax.swing.text.JTextComponent;

import breder.installer.logic.IInstallTask;
import breder.util.io.BrederLanguageFile;
import breder.util.net.HttpUrlConnection;
import breder.util.so.SoUtil;
import breder.util.util.FileUtil;
import breder.util.util.InputStreamUtil;

public class DownloadBrederLangTask implements IInstallTask {

	private boolean arch64;

	public DownloadBrederLangTask(boolean arch64) {
		this.arch64 = arch64;
	}

	@Override
	public void perform(JTextComponent label) throws Exception {
		String path = null;
		Number arch = this.arch64 ? 64 : 32;
		String so = SoUtil.getName();
		path = String.format("http://www.breder.org/pub/breder_%s_%d.zip", so,
				arch);
		HttpUrlConnection c = new HttpUrlConnection(path);
		byte[] bytes = InputStreamUtil.getBytes(c.getInputStream());
		ZipInputStream input = new ZipInputStream(new ByteArrayInputStream(
				bytes));
		FileUtil.remove(new BrederLanguageFile("bin"));
		FileUtil.remove(new BrederLanguageFile("src"));
		FileUtil.remove(new BrederLanguageFile("inc"));
		FileUtil.remove(new BrederLanguageFile("nat"));
		FileUtil.remove(new BrederLanguageFile("lib"));
		InputStreamUtil.extractZip(input, new BrederLanguageFile());
		c.close();
	}

	@Override
	public String getName() {
		return String.format("Downloading the Breder Language (%s).",
				new BrederLanguageFile().getAbsolutePath());
	}

}
