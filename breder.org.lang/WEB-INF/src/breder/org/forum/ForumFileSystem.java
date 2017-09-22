
package breder.org.forum;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

public class ForumFileSystem extends FolderForum implements IForumFileSystem {

	private static final ForumFileSystem instance = new ForumFileSystem();

	static final Map<Integer, IFolderForum> folders = new Hashtable<Integer, IFolderForum>();

	static final Map<Integer, IThreadForum> threads = new Hashtable<Integer, IThreadForum>();

	ForumFileSystem() {
		super(null, "");
	}

	public static ForumFileSystem getInstance() {
		return instance;
	}

	@Override
	public IFolderForum getFolder(Integer id) throws IOException {
		return folders.get(id);
	}

	@Override
	public IThreadForum getThread(Integer id) throws IOException {
		return threads.get(id);
	}

	@Override
	public void refresh() {
		super.refresh();
		folders.clear();
	}

}
