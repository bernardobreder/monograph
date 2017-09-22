package breder.webservice.service;

import java.util.Random;

import breder.webservice.ILoginFrame;
import breder.webservice.IServerConfigurator;
import breder.webservice.IServerService;
import breder.webservice.IService;
import breder.webservice.exception.AuthenticationException;

/**
 * Classe que implementa o IRegister
 * 
 * @author Tecgraf
 */
public class ServerService implements IServerService {

	private static final ServerService instance = new ServerService();

	/**
	 * Estrutura que configura a execu��o do modo cliente
	 */
	private final IServerConfigurator conf = new ServerConfigurator();

	private Class<? extends IAuthenticationService> authenticationServiceClass;

	private Class<? extends ILoginFrame> loginFrameClass;

	/**
	 * C�digo do usuario a ser cadastrado
	 */
	private String code;

	/**
	 * Thread que solicita pergungas ao servidor e o responde
	 */
	private Thread thread;

	private String username;

	private char[] password;

	private ServerService() {
	}

	public void init() throws AuthenticationException {
		this.init(username, password);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws Exception
	 * @throws AuthenticationException
	 */
	public void init(String username, char[] password)
			throws AuthenticationException {
		if (code == null) {
			IAuthenticationService service = ServiceFactory.getInstance()
					.newClientInstanceSimple(this.authenticationServiceClass,
							null);
			while (true) {
				try {
					this.code = service.login(username, password);
					if (code != null) {
						break;
					} else {
						throw new AuthenticationException(username);
					}
				} catch (AuthenticationException e) {
					throw e;
				} catch (RuntimeException e) {
					throw e;
				} catch (Exception e) {
					try {
						e.printStackTrace();
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
					}
				}
			}
			if (this.username == null) {
				this.username = username;
				this.password = password;
			}
			// if (false) {
			// service = ServiceFactory.getInstance()
			// .newClientInstance(IEngineService.class, code);
			// thread = new ServerThread(service);
			// thread.start();
			// }
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public <E extends IService> E lookup(Class<E> clazz) {
		if (code == null)
			throw new IllegalStateException();
		return ServiceFactory.getInstance().newClientInstance(clazz, code);
	}

	public String getCode() {
		return code;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IServerConfigurator getConfig() {
		return conf;
	}

	@Override
	public void finish() {
		if (thread != null) {
			thread.interrupt();
		}
		ClientService.getInstance().removeUser(code);
		this.code = null;
	}

	public void setAuthenticationServiceClass(
			Class<? extends IAuthenticationService> authenticationServiceClass) {
		this.authenticationServiceClass = authenticationServiceClass;
	}

	public Class<? extends ILoginFrame> getLoginFrameClass() {
		return loginFrameClass;
	}

	@Override
	public void setLoginFrameClass(Class<? extends ILoginFrame> loginFrameClass) {
		this.loginFrameClass = loginFrameClass;
	}

	public Class<? extends IAuthenticationService> getAuthenticationServiceClass() {
		return authenticationServiceClass;
	}

	public static String buildCode(String username) {
		String code;
		do {
			Random random = new Random(System.currentTimeMillis());
			StringBuilder sb = new StringBuilder();
			for (int n = 0; n < 255; n++) {
				sb.append((char) random.nextInt(255));
			}
			code = sb.toString();
		} while (AskAnswerManager.getInstance().exist(code));
		ClientService.getInstance().addUser(code, username);
		return code;
	}

	public static ServerService getInstance() {
		return instance;
	}

	public void setCode(String code) {
		this.code = code;
	}

};
