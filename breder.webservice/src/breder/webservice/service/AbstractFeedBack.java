package breder.webservice.service;

public abstract class AbstractFeedBack implements IFeedBack {

  @Override
  public void handler(Throwable e) {
    e.printStackTrace();
  }

}
