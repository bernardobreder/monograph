package breder.ide.plugin;


public interface IPlugin {

	public String getName();

	public IMenuItem[] getMenuItens();
	
	public IView[] getViews();

}
