package breder.plugin.wizard.build.bproject.template;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;

import breder.plugin.BActivator;
import breder.plugin.BrederProjectConstant;
import breder.plugin.monitor.BProgressMonitor;

public class HelloWorldTemplate extends AbstractTemplate {

	@Override
	public void build(IProject project) throws CoreException {
		IFolder sourceFolder = project
				.getFolder(BrederProjectConstant.SOURCE_FOLDER);
		IFile mainClassFile = sourceFolder.getFile("Main"
				+ BrederProjectConstant.BREDER_EXTENSION);
		{
			Map<String, String> map = new HashMap<String, String>();
			InputStream input = BActivator.getTemplate(
					"helloworld/main.breder", map);
			mainClassFile.create(input, true, BProgressMonitor.DEFAULT);
		}
	}

	@Override
	public String getTemplateName() {
		return "Hello World";
	}

}
