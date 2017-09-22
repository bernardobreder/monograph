package breder.plugin.wizard.build.bclass;

import java.io.File;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;

import breder.plugin.BrederProjectConstant;
import breder.plugin.wizard.BWizardValidator;
import breder.plugin.wizard.component.ClassUtil;
import breder.plugin.wizard.component.PackageUtil;

public class BClassNewPageOneValidator extends BWizardValidator {

	public BClassNewPageOneValidator(BClassNewPageOne page) {
		super(page);
	}

	@Override
	public String validate() {
		BClassNewPageOne page = (BClassNewPageOne) this.getPage();
		String value = page.getPackagename();
		String source = page.getSourcename();
		if (value.length() == 0) {
			return "No package choosed";
		}
		if (!PackageUtil.isValid(value)) {
			return "BPackage invalied";
		}
		if (!ClassUtil.isValid(page.getClassname())) {
			return "Class invalied";
		}
		IFolder sourceFolder = ResourcesPlugin.getWorkspace().getRoot()
				.getFolder(new Path(source));
		IFolder packageFolder = sourceFolder.getFolder(value.replace('.', File.separatorChar));
		IFile classFile = packageFolder.getFile(page.getClassname()
				+ BrederProjectConstant.BREDER_EXTENSION);
		if (classFile.exists()) {
			return "Class already exist";
		}
		return null;
	}
}
