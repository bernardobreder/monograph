/**
 * 
 */

package breder.webservice.remote.data;

import java.io.Serializable;

/**
 * @author bbreder
 */
public class MethodInvocationResponse implements Serializable {

  private final int id;

  private final byte[] resultBytes;

  private final boolean isError;

  private final byte[] exceptionBytes;

  public MethodInvocationResponse(int id, byte[] resultByte, boolean isError,
    byte[] exceptionBytes) {
    this.id = id;
    this.resultBytes = resultByte;
    this.isError = isError;
    this.exceptionBytes = exceptionBytes;
  }

  public byte[] getResultBytes() {
    return this.resultBytes;
  }

  public boolean isError() {
    return isError;
  }

  public byte[] getExceptionBytes() {
    return this.exceptionBytes;
  }

  public int getId() {
    return id;
  }

}
