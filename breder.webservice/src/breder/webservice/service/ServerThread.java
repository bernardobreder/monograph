package breder.webservice.service;

import breder.webservice.remote.Request;
import breder.webservice.remote.data.MethodInvocationRequest;
import breder.webservice.remote.data.MethodInvocationResponse;
import breder.webservice.util.ObjectUtil;

public class ServerThread extends Thread implements Runnable {

  private final IEngineService service;

  public ServerThread(IEngineService askService) {
    this.setName(this.getClass().getSimpleName());
    this.service = askService;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void run() {
    while (!this.isInterrupted()) {
      try {
        for (;;) {
          MethodInvocationRequest request = service.ask();
          if (request != null) {
            Object result = null;
            Throwable exception = null;
            try {
              Request.request(request);
            }
            catch (Throwable t) {
              exception = t;
            }
            MethodInvocationResponse response =
              new MethodInvocationResponse(request.getId(), ObjectUtil
                .writeToBytes(result).toByteArray(), exception != null,
                ObjectUtil.writeToBytes(exception).toByteArray());
            service.answer(response);
          }
          else
            break;
        }
        try {
          Thread.sleep(1000);
        }
        catch (InterruptedException e) {
          this.interrupt();
        }
      }
      catch (Throwable t) {
      }
    }
  }

}
