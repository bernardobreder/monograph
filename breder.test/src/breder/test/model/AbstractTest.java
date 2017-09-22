package breder.test.model;

import java.util.ArrayList;
import java.util.List;

public class AbstractTest implements ITest {

	private static final long serialVersionUID = 1L;

	private String name;

	private List<Source> sources;

	private List<String> expected;

	private String mainClass;

	private boolean enabled;

	private boolean endWith;

	private Boolean ok;

	private long timer;

	private Long memory;

	public AbstractTest() {
		super();
		this.enabled = true;
		this.expected = new ArrayList<String>();
		this.sources = new ArrayList<Source>();
		this.endWith = true;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Source> getSources() {
		return sources;
	}

	public void setSources(List<Source> sources) {
		this.sources = sources;
	}

	public List<String> getExpected() {
		return expected;
	}

	public void setExpected(List<String> expected) {
		this.expected = expected;
	}

	public String getMainClass() {
		return mainClass;
	}

	public void setMainClass(String mainClass) {
		this.mainClass = mainClass;
	}

	public Boolean getOk() {
		return ok;
	}

	public void setOk(Boolean ok) {
		this.ok = ok;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public void setTimer(long timer) {
		this.timer = timer;
	}

	@Override
	public long getTimer() {
		return this.timer;
	}

	public Long getMemory() {
		return memory;
	}

	public void setMemory(Long memory) {
		this.memory = memory;
	}

}
