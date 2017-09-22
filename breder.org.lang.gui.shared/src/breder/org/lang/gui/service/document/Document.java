package breder.org.lang.gui.service.document;

public class Document implements IDocument {

	private final Number id;

	private final String name;

	private final Number parentId;

	public Document(Number id, String name, Number parentId) {
		super();
		this.id = id;
		this.name = name;
		this.parentId = parentId;
	}

	public Number getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Number getParentId() {
		return parentId;
	}

}
