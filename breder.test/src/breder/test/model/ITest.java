package breder.test.model;

import java.io.Serializable;
import java.util.List;

public interface ITest extends Serializable {

	public String getName();

	public void setName(String name);

	public List<Source> getSources();

	public void setSources(List<Source> sources);

	public List<String> getExpected();

	public void setExpected(List<String> expected);

	public String getMainClass();

	public void setMainClass(String mainClass);

	public Boolean getOk();

	public void setOk(Boolean ok);

	public boolean isEnabled();

	public void setEnabled(boolean enabled);

	public void setTimer(long timer);

	public long getTimer();

	public Long getMemory();

	public void setMemory(Long memory);

}
