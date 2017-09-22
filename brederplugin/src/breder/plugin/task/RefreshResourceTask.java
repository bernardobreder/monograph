package breder.plugin.task;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;

import breder.plugin.monitor.BProgressMonitor;
import breder.plugin.util.BTask;

public class RefreshResourceTask extends BTask {

	@Override
	public void action() throws Exception {
		ResourcesPlugin.getWorkspace().getRoot().refreshLocal(
				IResource.DEPTH_INFINITE, BProgressMonitor.DEFAULT);
	}

}
