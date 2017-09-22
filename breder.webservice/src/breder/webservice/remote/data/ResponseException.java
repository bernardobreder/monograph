/**
 * 
 */

package breder.webservice.remote.data;

import java.io.Serializable;

/**
 * @author bbreder
 */
public class ResponseException implements Serializable {

  protected Throwable throwable;

  public ResponseException(Throwable throwable) {
    super();
    this.throwable = throwable;
  }

  public Throwable getThrowable() {
    return this.throwable;
  }

}
