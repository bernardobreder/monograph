package breder.org.lang.gui.service.user;

import java.util.Random;

import breder.org.lang.gui.service.LangService;
import breder.webservice.jdbc.DbConnection;
import breder.webservice.service.ClientService;

public class LangAuthenticationService extends LangService implements
		ILangAuthenticationService {

	@Override
	public String login(String username, char[] password) throws Exception {
		String query = "SELECT 1 FROM lang$user where username = ? and password = sha(?)";
		Object[][] nativeSql = DbConnection.getInstance().get().start()
				.nativeSql(query, username, new String(password));
		if (nativeSql.length == 0) {
			return null;
		}
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
