package breder.webservice.service;

import breder.webservice.IService;
import breder.webservice.exception.AuthenticationException;

public interface IAuthenticationService extends IService {
	
	public String login(String username, char[] password) throws AuthenticationException, Exception;
	
}
