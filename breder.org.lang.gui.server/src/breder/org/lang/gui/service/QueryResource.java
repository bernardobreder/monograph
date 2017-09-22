package breder.org.lang.gui.service;

import java.util.Properties;

public class QueryResource {

	private static final QueryResource instance = new QueryResource();
	private Properties properties;

	private QueryResource() {
		this.properties = new Properties();
		try {
			this.properties.load(this
					.getClass()
					.getClassLoader()
					.getResourceAsStream(
							"breder/org/lang/gui/service/query.properties"));
		} catch (Exception e) {
			throw new Error();
		}
	}

	public static QueryResource getInstance() {
		return instance;
	}

	public String get(String string) {
		return this.properties.getProperty(string);
	}

}
