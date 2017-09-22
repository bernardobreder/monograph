
package breder.org.forum;

public abstract class ForumItem implements IForumItem {

	private final Integer id;

	private final String name;

	ForumItem(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	@Override
	public Integer getId() {
		return this.id;
	}

	@Override
	public String getName() {
		return this.name;
	}

}
