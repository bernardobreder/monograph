package breder.plugin.wizard.filter;

import org.eclipse.jface.viewers.Viewer;

import breder.plugin.element.Element;
import breder.plugin.element.Parent;
import breder.plugin.element.Workspace;
import breder.plugin.element.breder.BProject;
import breder.plugin.element.breder.BSource;

public class BSourceViewerFilter extends BViewerFilter {

	@Override
	public boolean select(Viewer viewer, Parent<?> parent, Element element) {
		return element.getClass() == Workspace.class
				|| element.getClass() == BProject.class
				|| element.getClass() == BSource.class;
	}

}
