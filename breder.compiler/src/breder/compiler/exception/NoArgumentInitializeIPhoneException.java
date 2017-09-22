package breder.compiler.exception;

public class NoArgumentInitializeIPhoneException extends BrederJRuntimeException {
	
	public NoArgumentInitializeIPhoneException() {
		super("breder_iphone  <classname> { -cp <pathname> | -l <pathname> }");
	}
	
}
