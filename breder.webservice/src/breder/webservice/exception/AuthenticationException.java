package breder.webservice.exception;

public class AuthenticationException extends Exception {
	
	private final String username;
	
	public AuthenticationException(String username) {
		super(String.format("Usuário ou Senha Inválida"));
		this.username = username;
	}
	
	public String getUsername() {
		return username;
	}
	
}
