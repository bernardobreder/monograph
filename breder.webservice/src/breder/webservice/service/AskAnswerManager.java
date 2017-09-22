package breder.webservice.service;

import java.util.ArrayList;
import java.util.List;

import breder.webservice.remote.data.MethodInvocationRequest;
import breder.webservice.remote.data.MethodInvocationResponse;

public class AskAnswerManager {

  private final static AskAnswerManager instance = new AskAnswerManager();

  private final List<Entity> entitys = new ArrayList<Entity>();

  private AskAnswerManager() {

  }

  public void setAnswer(MethodInvocationResponse response) {
    synchronized (entitys) {
      for (Entity entity : this.entitys) {
        if (entity.id == response.getId()) {
          entity.response = response;
          Object monitor = entity.monitor;
          synchronized (monitor) {
            monitor.notifyAll();
          }
        }
      }
    }
  }

  public MethodInvocationResponse getAnswer(int id) {
    synchronized (entitys) {
      for (Entity entity : this.entitys) {
        if (entity.id == id) {
          this.entitys.remove(entity);
          return entity.response;
        }
      }
    }
    return null;
  }

  public synchronized Object ask(String code, MethodInvocationRequest request) {
    Entity entity = new Entity(request.getId(), new Object(), code, request);
    synchronized (entitys) {
      entitys.add(entity);
    }
    return entity.monitor;
  }

  public void remote(String code) {
    synchronized (entitys) {
      for (int n = 0; n < this.entitys.size(); n++) {
        Entity entity = entitys.get(n);
        if (entity.code.equals(code)) {
          entitys.remove(n--);
        }
      }
    }
  }

  /**
   * Indicar se existe alguma requisição para um registro espeifico. Caso não
   * haja, será retornado nulo.
   * 
   * @param code código do registro
   * @return requisição ou nulo
   */
  public MethodInvocationRequest ask(String code) {
    synchronized (entitys) {
      for (Entity entity : this.entitys) {
        if (entity.code.equals(code) && !entity.readed) {
          entity.readed = true;
          return entity.request;
        }
      }
    }
    return null;
  }

  public static AskAnswerManager getInstance() {
    return instance;
  }

  private class Entity {

    public boolean readed;

    public int id;

    public Object monitor;

    public String code;

    public MethodInvocationRequest request;

    public MethodInvocationResponse response;

    public Entity(int id, Object monitor, String code,
      MethodInvocationRequest request) {
      super();
      this.id = id;
      this.monitor = monitor;
      this.code = code;
      this.request = request;
    }

  }

  public boolean exist(String code) {
    synchronized (entitys) {
      for (Entity entity : this.entitys) {
        if (entity.code.equals(code)) {
          return true;
        }
      }
    }
    return false;
  }

}
