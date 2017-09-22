package breder.info.logic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;

import breder.util.net.HttpConnection;
import breder.util.util.InputStreamUtil;
import breder.xml.ITag;
import breder.xml.XmlReader;

public class StructLocator implements Loader {

	private static final StructLocator instance = new StructLocator();

	private Float version;

	private ApiDef apiDef;

	private StructLocator() {
		try {
			this.restart();
		} catch (Exception e) {
			throw new Error(e);
		}
	}

	public static StructLocator getInstance() {
		return instance;
	}

	public float getVersion() {
		return this.version;
	}

	public void restart() throws Exception {
		File file = new File("data.xml");
		if (!file.exists()) {
			HttpConnection c = new HttpConnection("http://www.breder.org/disk/info/data");
			OutputStream output = new FileOutputStream(file);
			InputStreamUtil.copyStream(c.getInputStream(), output);
			output.close();
		}
		{
			InputStream input = new FileInputStream(file);
			XmlReader reader = new XmlReader(input);
			reader.start();
			ITag tag = reader.getTag("root");
			this.version = new Float(tag.check("version"));
			this.load(tag);
		}
	}

	public void load(ITag tag) throws ParseException {
		this.apiDef = new ApiDef(tag.getTag("api"));
	}

	public ApiDef getApi() {
		return apiDef;
	}

}
