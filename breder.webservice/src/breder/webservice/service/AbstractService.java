package breder.webservice.service;

import breder.webservice.IService;

public abstract class AbstractService implements IService {

	@Override
	public void dispose() {
	}

	@Override
	public void init() {
	}

	public <E extends IService> E getProxy(Class<E> c) {
		return null;
	}

	public String getUsername() {
		String username = ClientService.getInstance().getUsername();
		if (username == null) {
			username = "";
		}
		return username;
	}

}
