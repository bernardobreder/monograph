package breder.webservice.service;

import java.util.ArrayList;
import java.util.List;

import breder.webservice.IClientService;
import breder.webservice.IServerService;
import breder.webservice.IService;

public class ClientService implements IClientService {

	private static final ClientService instance = new ClientService();

	private ThreadLocal<Entity> code = new ThreadLocal<Entity>();

	private List<Entity> infos = new ArrayList<Entity>();

	private ClientService() {
	}

	/**
	 * {@inheritDoc}
	 */
	public String getCode() {
		Entity entity = code.get();
		if (entity == null)
			return null;
		return entity.code;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getUsername() {
		Entity entity = code.get();
		if (entity == null)
			return null;
		return entity.username;
	}

	/**
	 * {@inheritDoc}
	 */
	public String[] getAllCodes() {
		List<String> codes = new ArrayList<String>();
		synchronized (infos) {
			for (Entity entity : this.infos) {
				codes.add(entity.code);
			}
		}
		return codes.toArray(new String[0]);
	}

	/**
	 * {@inheritDoc}
	 */
	public void setCode(String code) {
		synchronized (infos) {
			for (Entity entity : this.infos) {
				if (entity.code.equals(code)) {
					this.code.set(entity);
				}
			}
		}
	}

	/**
	 * Retorna a instancia singleton
	 * 
	 * @return
	 */
	public static ClientService getInstance() {
		return instance;
	}

	public void removeUser() {
		this.removeUser(this.getCode());
	}

	/**
	 * {@inheritDoc}
	 */
	public void removeUser(String code) {
		synchronized (infos) {
			for (Entity entity : this.infos) {
				if (entity.code.equals(code)) {
					this.infos.remove(entity);
					break;
				}
			}
			AskAnswerManager.getInstance().remote(code);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public <E extends IService> E getUserService(Class<E> clazz, String code) {
		return ServiceFactory.getInstance().newServerInstance(clazz, code);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <E extends IService> E getUserService(Class<E> clazz, String code,
			IFeedBack feedback) {
		return ServiceFactory.getInstance().newServerInstance(clazz, code,
				feedback);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <E extends IService> E getUserService(Class<E> clazz) {
		return this.getUserService(clazz, this.getCode());
	}

	public boolean addUser(String code, String username) {
		synchronized (this.infos) {
			for (Entity entity : this.infos) {
				if (entity.code.equals(code)) {
					return false;
				}
			}
			this.infos.add(new Entity(code, System.currentTimeMillis(),
					username));
		}
		return true;
	}

	public void refreshInfos(String code) {
		long time = System.currentTimeMillis();
		long timeout = IServerService.DEFAULT.getConfig().getSessionTimeout();
		synchronized (infos) {
			for (int n = this.infos.size() - 1; n >= 0; n--) {
				Entity entity = this.infos.get(n);
				long etime = time - entity.timestamp;
				if (etime > timeout) {
					this.removeUser(entity.code);
				} else if (entity.code.equals(code)) {
					entity.timestamp = System.currentTimeMillis();
				}
			}
		}
	}

	@Override
	public <E extends IService> E getUserService(Class<E> clazz,
			IFeedBack feedback) {
		return this.getUserService(clazz, this.getCode(), feedback);
	}

	private static class Entity {

		public String code;

		public long timestamp;

		public String username;

		public Entity(String code, long timestamp, String username) {
			super();
			this.code = code;
			this.timestamp = timestamp;
			this.username = username;
		}

	}

	public String getUsername(String code) {
		synchronized (infos) {
			for (Entity entity : this.infos) {
				if (entity.code.equals(code)) {
					return entity.username;
				}
			}
		}
		return null;
	}

}
