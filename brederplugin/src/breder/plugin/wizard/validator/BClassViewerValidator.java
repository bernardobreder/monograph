package breder.plugin.wizard.validator;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import breder.plugin.element.breder.BClass;
import breder.plugin.util.BStatus;

public class BClassViewerValidator extends BSelectionStatusValidator {

	@Override
	public IStatus validate(Object[] selection) {
		for (Object element : selection) {
			if (element.getClass() != BClass.class) {
				return new BStatus(Status.ERROR, "Not a class");
			}
		}
		return new BStatus(Status.OK, "Ok");
	}

}
