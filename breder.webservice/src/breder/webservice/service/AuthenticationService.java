package breder.webservice.service;

import java.util.Random;

import breder.webservice.exception.AuthenticationException;

public class AuthenticationService extends AbstractService implements
		IAuthenticationService {

	@Override
	public String login(String username, char[] password)
			throws AuthenticationException, Exception {
		String code;
		do {
			Random random = new Random(System.currentTimeMillis());
			StringBuilder sb = new StringBuilder();
			for (int n = 0; n < 255; n++) {
				sb.append((char) random.nextInt(255));
			}
			code = sb.toString();
		} while (!ClientService.getInstance().addUser(code, username));
		return code;
	}

}
