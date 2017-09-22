
package breder.org.forum;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import db.jdbc.JDBC;

public class FolderForum extends ForumItem implements IFolderForum {

	private IForumItem[] list;

	FolderForum(Integer id, String name) {
		super(id, name);
		ForumFileSystem.folders.put(id, this);
	}

	@Override
	public IForumItem[] list() throws IOException {
		if (this.list == null) {
			List<IForumItem> list = new ArrayList<IForumItem>();
			List<IForumItem> threads = new ArrayList<IForumItem>();
			List<IForumItem> folders = new ArrayList<IForumItem>();
			Object[][] rows = JDBC.getInstance().query(
					"select id, name, is_thread from blng_forum where parent_id = ?", this.getId());
			for (Object[] row : rows) {
				Integer id = (Integer) row[0];
				String name = (String) row[1];
				Boolean isThread = (Boolean) row[2];
				IForumItem item;
				if (isThread) {
					item = new ThreadForum(id, name);
					threads.add(item);
				} else {
					item = new FolderForum(id, name);
					folders.add(item);
				}
			}
			list.addAll(folders);
			list.addAll(threads);
			this.list = list.toArray(new IForumItem[list.size()]);
		}
		return this.list;
	}

	@Override
	public void refresh() {
		this.list = null;
	}

	@Override
	public boolean isThread() {
		return false;
	}

}
