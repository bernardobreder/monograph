
package breder.org.forum;

import java.io.IOException;

public interface IForumFileSystem extends IFolderForum {

	public static final IForumFileSystem DEFAULT = ForumFileSystem.getInstance();

	public IFolderForum getFolder(Integer id) throws IOException;
	
	public IThreadForum getThread(Integer id) throws IOException;

}
