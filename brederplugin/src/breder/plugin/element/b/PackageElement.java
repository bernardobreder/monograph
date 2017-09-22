package breder.plugin.element.b;

import breder.plugin.element.Element;
import breder.plugin.element.Parent;

public class PackageElement extends Parent {

	private final String name;

	public PackageElement(Parent parent, String name) {
		super(parent);
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	protected Element[] doRefresh() {
		return null;
	}

}
