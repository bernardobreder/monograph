
package breder.org.lang.gui.service.user;

import java.util.ArrayList;
import java.util.List;

import breder.org.lang.gui.service.LangService;
import breder.org.lang.gui.service.LogConstant;
import breder.org.lang.gui.table.UserTable;
import breder.org.lang.gui.user.User;
import breder.webservice.exception.PermissionException;
import breder.webservice.jdbc.DbTask;
import breder.webservice.service.ClientService;

public class UserService extends LangService implements IUserService {

	public UserService() {
		this(null);
	}

	public UserService(DbTask<Object> task) {
		this.task = task;
	}

	@Override
	public void logout() {
		ClientService.getInstance().removeUser();
		this.getLogservice().printInfo(LogConstant.LOGOUT, this.getUsername());
	}

	@Override
	public void addUser(final User user, final char[] password) {
		if (!this.getUser().isAdmin()) {
			throw new PermissionException();
		}
		UserTable row = this.getTransaction().select(UserTable.class).equal(UserTable.USERNAME, user.getName()).executeUnique(UserTable.class);
		if (row != null) {
			throw new IllegalArgumentException("Usuario ja existente");
		}
		this.getTransaction().nativeSql("insert into inpa$user (username,password) values (?,sha(?))", user.getName(), new String(password));
	}

	@Override
	public User[] listUser() {
		UserTable[] rows = this.getTransaction().select(UserTable.class).execute(UserTable.class);
		List<User> list = new ArrayList<User>();
		for (UserTable row : rows) {
			list.add(new User(row.id, row.username));
		}
		return list.toArray(new User[0]);
	}

	public User getUser() {
		return this.getUser(this.getUsername());
	}

	public User getUser(String username) {
		UserTable row = this.getTransaction().select(UserTable.class).equal(UserTable.USERNAME, username).executeUnique(UserTable.class);
		if (row == null) {
			return null;
		} else {
			return new User(row.id, username);
		}
	}

}
