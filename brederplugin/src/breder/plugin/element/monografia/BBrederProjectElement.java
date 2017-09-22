package breder.plugin.element.monografia;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;

import breder.plugin.element.Element;
import breder.plugin.element.Workspace;
import breder.plugin.element.breder.BProject;

public class BBrederProjectElement extends BProject {

	public BBrederProjectElement(Workspace parent, IProject project) {
		super(parent, project);
	}

	@Override
	protected Element[] doRefresh() {
		List<Element> list = new ArrayList<Element>();
		try {
			for (IResource resource : this.getResource().members()) {
				if (resource instanceof IContainer) {
					IContainer container = (IContainer) resource;
					if (container.getName().equals("src")) {
						list.add(new BBrederSourceElement(this, container));
					} else if (container.getName().equals("inc")) {
						list.add(new BBrederIncludeElement(this, container));
					}
				}
			}
		} catch (CoreException e) {
			e.printStackTrace();
		}
		return list.toArray(new Element[0]);
	}

}
