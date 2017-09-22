package breder.webservice.service;

import java.io.IOException;

import breder.webservice.IService;
import breder.webservice.remote.data.MethodInvocationRequest;
import breder.webservice.remote.data.MethodInvocationResponse;

public interface IEngineService extends IService {

  public abstract MethodInvocationRequest ask() throws IOException;

  public abstract void answer(MethodInvocationResponse response)
    throws IOException;

}