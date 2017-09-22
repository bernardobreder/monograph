
package web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import breder.org.forum.ForumFileSystem;
import breder.org.forum.IFolderForum;
import breder.org.forum.IForumItem;

public class ForumServlet extends JspServlet {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String action(Map<String, String> in, Map<String, Object> out) throws ServletException, IOException {
		IFolderForum folder = null;
		{
			Integer id = null;
			String idStr = in.get("id");
			try {
				id = new Integer(idStr);
			} catch (NumberFormatException e) {
			}
			folder = ForumFileSystem.getInstance().getFolder(id);
			if (folder == null) {
				folder = ForumFileSystem.getInstance();
			}
		}
		IForumItem[] child = folder.list();
		List<String> folderNames = new ArrayList<String>();
		List<String> threadNames = new ArrayList<String>();
		for (IForumItem item : child) {
			if (item.isThread()) {
				threadNames.add(item.getName());
			} else {
				folderNames.add(item.getName());
			}
		}
		out.put("folder_names", folderNames);
		out.put("thread_names", threadNames);
		return "forum";
	}
}
