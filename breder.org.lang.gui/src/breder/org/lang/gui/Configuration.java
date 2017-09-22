package breder.org.lang.gui;

import java.io.FileInputStream;
import java.util.Properties;

public class Configuration {

	private static final Configuration instance = new Configuration();

	private Configuration() {
		try {
			Properties prop = new Properties();
			prop.load(new FileInputStream("config.properties"));
		} catch (Exception e) {
			throw new Error(e);
		}
	}

	public static Configuration getInstance() {
		return instance;
	}

}
