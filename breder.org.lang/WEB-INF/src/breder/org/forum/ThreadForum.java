
package breder.org.forum;

public class ThreadForum extends ForumItem implements IThreadForum {

	public ThreadForum(Integer id, String name) {
		super(id, name);
	}

	@Override
	public void refresh() {
	}

	@Override
	public boolean isThread() {
		return true;
	}

}
