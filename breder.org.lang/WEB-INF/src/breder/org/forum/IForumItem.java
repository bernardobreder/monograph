
package breder.org.forum;

public interface IForumItem {

	public Integer getId();

	public String getName();
	
	public boolean isThread();

	public void refresh();

}
