package breder.plugin.element.monografia;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;

import breder.plugin.element.Element;
import breder.plugin.element.Parent;
import breder.plugin.element.breder.BSource;

public class BBrederSourceElement extends BSource {

	public BBrederSourceElement(Parent<?> parent, IContainer file) {
		super(parent, file);
	}

	@Override
	protected Element[] doRefresh() {
		List<Element> list = new ArrayList<Element>();
		try {
			for (IResource resource : this.getResource().members()) {
				if (resource instanceof IFile) {
					IFile file = (IFile) resource;
					if (file.getName().endsWith(".c")) {
						list.add(new BClassCSource(this, file));
					}
				}
			}
		} catch (CoreException e) {
			e.printStackTrace();
		}
		return list.toArray(new Element[0]);
	}

}
