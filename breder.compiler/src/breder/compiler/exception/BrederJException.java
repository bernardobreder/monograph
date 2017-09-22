package breder.compiler.exception;

public class BrederJException extends Exception {

	public BrederJException(String message, Throwable cause) {
		super(message, cause);
	}

	public BrederJException(String message) {
		super(message);
	}

	public BrederJException(Throwable cause) {
		super(cause);
	}

}
