package breder.webservice.service;

public class User {

  private final String code;

  private long timestamp;

  public User(String code) {
    super();
    this.code = code;
    this.refreshTimestamp();
  }

  public String getCode() {
    return code;
  }

  public long getTimestamp() {
    return timestamp;
  }

  public void refreshTimestamp() {
    this.timestamp = System.currentTimeMillis();
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((code == null) ? 0 : code.hashCode());
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
    User other = (User) obj;
    if (code == null) {
      if (other.code != null)
        return false;
    }
    else if (!code.equals(other.code))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "User [code=" + code + "]";
  }

}
