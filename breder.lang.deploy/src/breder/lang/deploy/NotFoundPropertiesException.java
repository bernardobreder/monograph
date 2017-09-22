package breder.lang.deploy;
public class NotFoundPropertiesException extends RuntimeException {

	public NotFoundPropertiesException(String properties) {
		super(String.format("not found the properties '%s'", properties));
	}

}
