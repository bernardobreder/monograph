
package breder.org.forum;

import java.io.IOException;

public interface IFolderForum extends IForumItem {

	public IForumItem[] list() throws IOException;

}
