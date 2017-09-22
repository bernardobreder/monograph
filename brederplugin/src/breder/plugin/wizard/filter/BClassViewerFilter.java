package breder.plugin.wizard.filter;

import org.eclipse.jface.viewers.Viewer;

import breder.plugin.element.Element;
import breder.plugin.element.Parent;
import breder.plugin.element.breder.BClass;
import breder.plugin.element.breder.BProject;
import breder.plugin.element.breder.BSource;

public class BClassViewerFilter extends BViewerFilter {

	@Override
	public boolean select(Viewer viewer, Parent<?> parent, Element element) {
		return element instanceof BClass || element instanceof BProject
				|| element instanceof BSource;
	}

}
