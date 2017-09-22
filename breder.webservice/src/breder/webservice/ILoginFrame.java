package breder.webservice;

import java.awt.Component;

import breder.util.task.Task;

public interface ILoginFrame {
	
	public String getUsername();
	
	public char[] getPassword();
	
	public void reset();
	
	public void open();
	
	public Task getLoginTask(ILoginFrame frame, Component... components);

	public void dispose();
	
}
