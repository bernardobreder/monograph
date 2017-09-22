package breder.org.lang.gui.service.user;

import breder.org.lang.gui.user.User;
import breder.webservice.IService;

public interface IUserService extends IService {

	public void addUser(User user, char[] password);

	public User[] listUser();

	public User getUser();

	public User getUser(String username);

	public void logout();

}
