/**
 * 
 */

package breder.webservice.remote.data;

import java.io.Serializable;

/**
 * @author bbreder
 */
public class MethodInvocationRequest implements Serializable {

  private final int id;

  protected byte[] fieldsBytes;

  protected String className;

  protected String methodName;

  protected Object[] argumentInstanceBytes;

  private static Integer counter = 0;

  public MethodInvocationRequest() {
    synchronized (counter) {
      this.id = ++counter;
    }
  }

  public byte[] getFieldsBytes() {
    return this.fieldsBytes;
  }

  public void setFieldsBytes(byte[] instanceBytes) {
    this.fieldsBytes = instanceBytes;
  }

  public String getClassName() {
    return this.className;
  }

  public void setClassName(String className) {
    this.className = className;
  }

  public String getMethodName() {
    return this.methodName;
  }

  public void setMethodName(String methodName) {
    this.methodName = methodName;
  }

  public Object[] getArgumentInstanceBytes() {
    return this.argumentInstanceBytes;
  }

  public void setArgumentInstanceBytes(Object[] argumentInstanceBytes) {
    this.argumentInstanceBytes = argumentInstanceBytes;
  }

  public int getId() {
    return id;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + id;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    MethodInvocationRequest other = (MethodInvocationRequest) obj;
    if (id != other.id)
      return false;
    return true;
  }

}
