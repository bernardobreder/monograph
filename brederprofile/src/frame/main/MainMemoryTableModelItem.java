package frame.main;

public class MainMemoryTableModelItem {

  private final String address;

  private final Integer size;

  private final Integer length;

  private final Integer count;

  private final String describer;

  private Integer hashcode;

  public MainMemoryTableModelItem(String address, Integer size, Integer length,
    Integer count, String describer) {
    super();
    this.address = address;
    this.size = size;
    this.length = length;
    this.count = count;
    this.describer = describer;
  }

  public String getAddress() {
    return address;
  }

  public Integer getSize() {
    return size;
  }

  public Integer getLength() {
    return length;
  }

  public Integer getCount() {
    return count;
  }

  public String getDescriber() {
    return describer;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((address == null) ? 0 : address.hashCode());
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
    MainMemoryTableModelItem other = (MainMemoryTableModelItem) obj;
    if (address == null) {
      if (other.address != null)
        return false;
    }
    else if (!address.equals(other.address))
      return false;
    return true;
  }

}
