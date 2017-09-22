package logic.task;

public class Task {

	private final Integer id;

	private final String title;

	private final String describer;

	public Task(Integer id, String title, String describer) {
		super();
		this.id = id;
		this.title = title;
		this.describer = describer;
	}

	public Integer getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getDescriber() {
		return describer;
	}

}
