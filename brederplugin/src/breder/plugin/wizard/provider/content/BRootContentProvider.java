package breder.plugin.wizard.provider.content;

import breder.plugin.element.Element;
import breder.plugin.element.Parent;

public class BRootContentProvider extends BTreeContentProvider {

	@Override
	public Element[] getChildren(Element element) {
		return element.getChildren();
	}

	@Override
	public Parent<?> getParent(Element element) {
		return element.getParent();
	}

	@Override
	public boolean hasChildren(Element element) {
		return element.hasChildren();
	}

}
