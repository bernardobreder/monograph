package breder.webservice.service;

import java.io.IOException;

import breder.webservice.IService;
import breder.webservice.remote.data.MethodInvocationRequest;
import breder.webservice.remote.data.MethodInvocationResponse;

public class EngineService extends AbstractService implements IService, IEngineService {
	
	/**
	 * {@inheritDoc}
	 */
	public MethodInvocationRequest ask() throws IOException {
		String code = ClientService.getInstance().getCode();
		return AskAnswerManager.getInstance().ask(code);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void answer(MethodInvocationResponse response) throws IOException {
		AskAnswerManager.getInstance().setAnswer(response);
	}
	
}
