package breder.org.lang.gui.user;

import java.io.Serializable;

import breder.util.library.Person;

public class User extends Person implements Serializable {

	public User(String username) {
		this(null, username);
	}

	public User(Number id, String username) {
		super(id, username);
	}

	public boolean isAdmin() {
		return this.getName().equals("admin");
	}

}
