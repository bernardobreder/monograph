package breder.lang.deploy;
import java.util.Properties;

public class BrederProperties extends Properties {

	@Override
	public String getProperty(String key) {
		return (String) this.get(key);
	}

	public String checkProperty(String key) {
		String value = this.getProperty(key);
		if (value == null) {
			throw new NotFoundPropertiesException(key);
		}
		return value;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public synchronized Object get(Object key) {
		String k = key.toString();
		Object value = super.get(k);
		if (value == null)
			return null;
		String v = value.toString();
		if (v.startsWith("%")) {
			String otherk = v.substring(1, v.length() - 1);
			v = System.getenv(otherk);
		}
		return v;
	}

}
