package breder.plugin.wizard.provider.label;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;

import breder.plugin.BResource;
import breder.plugin.element.Element;
import breder.plugin.element.IBClass;
import breder.plugin.element.IBFolder;
import breder.plugin.element.IBPackage;
import breder.plugin.element.IBProject;
import breder.plugin.element.IBSource;

public class BLabelProvider implements ILabelProvider {

	@Override
	public Image getImage(Object object) {
		if (object instanceof Element) {
			Element element = (Element) object;
			if (element instanceof IBProject) {
				return BResource.getInstance().getProjectImage();
			} else if (element instanceof IBPackage) {
				return BResource.getInstance().getPackageImage();
			} else if (element instanceof IBSource) {
				return BResource.getInstance().getSourceImage();
			} else if (element instanceof IBFolder) {
				return BResource.getInstance().getFolderImage();
			} else if (element instanceof IBClass) {
				return BResource.getInstance().getClassImage();
			}
		}
		return BResource.getInstance().getResourceImage();
	}

	@Override
	public String getText(Object element) {
		if (element instanceof Element) {
			return ((Element) element).getName();
		}
		return element.toString();
	}

	@Override
	public void addListener(ILabelProviderListener listener) {
	}

	@Override
	public void dispose() {
	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {

	}

}
