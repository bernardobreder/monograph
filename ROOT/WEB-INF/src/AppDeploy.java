

import java.io.File;

import breder.util.deploy.TomcatDeploy;

/**
 * Realiza o deploy
 * 
 * 
 * @author Bernardo Breder
 */
public class AppDeploy extends TomcatDeploy {

  /**
   * {@inheritDoc}
   */
  @Override
  protected String getProjectName() {
    return "ROOT";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected File[] getServerDirs() {
    return new File[] {};
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected File getTomcatProject() {
    return new File("../ROOT");
  }

  /**
   * Inicializador
   * 
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    new AppDeploy().init();
  }

}
