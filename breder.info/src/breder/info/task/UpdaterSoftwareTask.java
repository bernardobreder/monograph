package breder.info.task;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import breder.info.logic.StructLocator;
import breder.util.net.HttpConnection;
import breder.util.util.InputStreamUtil;

public class UpdaterSoftwareTask extends UpdaterTask {

	@Override
	public void perform() throws Throwable {
		HttpConnection c = new HttpConnection("http://www.breder.org/disk/info/version");
		Float version = new Float(new String(InputStreamUtil.getBytes(c.getInputStream())));
		c.close();
		if (version.equals(StructLocator.getInstance().getVersion())) {
			return;
		} else {
			c = new HttpConnection("http://www.breder.org/disk/info/data");
			OutputStream output = new FileOutputStream(new File("data.xml"));
			InputStreamUtil.copyStream(c.getInputStream(), output);
			c.close();
			output.close();
			StructLocator.getInstance().restart();
			this.setUpdated(true);
		}
	}

}
