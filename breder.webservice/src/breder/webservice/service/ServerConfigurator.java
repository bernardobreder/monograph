package breder.webservice.service;

import breder.webservice.IServerConfigurator;

public class ServerConfigurator implements IServerConfigurator {
	
	private String tomcatUrl = "http://localhost:8080";
	
	private String servletUrl = "service";
	
	private long sessionTimeout = 25 * 1000;
	
	public String getTomcatUrl() {
		return tomcatUrl;
	}
	
	public void setTomcatUrl(String tomcatUrl) {
		this.tomcatUrl = tomcatUrl;
	}
	
	public String getServletUrl() {
		return servletUrl;
	}
	
	public void setServletUrl(String servletUrl) {
		this.servletUrl = servletUrl;
	}
	
	public long getSessionTimeout() {
		return sessionTimeout;
	}
	
	public void setSessionTimeout(long sessionTimeout) {
		this.sessionTimeout = sessionTimeout;
	}
	
}
