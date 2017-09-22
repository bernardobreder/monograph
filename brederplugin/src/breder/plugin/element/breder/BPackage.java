package breder.plugin.element.breder;

import org.eclipse.core.resources.IContainer;

import breder.plugin.element.Element;
import breder.plugin.element.IBPackage;
import breder.plugin.element.IProjectElement;
import breder.plugin.element.ISourceElement;
import breder.plugin.element.Parent;

public class BPackage extends BSource implements Element, IProjectElement,
		ISourceElement, IBPackage {

	public BPackage(Parent<?> parent, IContainer file) {
		super(parent, file);
	}

}
