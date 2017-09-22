package breder.plugin.element.project;

import org.eclipse.core.resources.IProject;

import breder.plugin.builder.BDescriberBinary;
import breder.plugin.element.Parent;

public class BProjectBinary extends Parent<BStructBinary> {

	private BDescriberBinary binary;

	private IProject project;

	public BProjectBinary(Parent<?> parent, IProject project,
			BDescriberBinary binary) {
		super(parent);
		this.binary = binary;
		this.project = project;
	}

	@Override
	protected BStructBinary[] doRefresh() {
		return new BStructBinary[0];
	}

	@Override
	public String getName() {
		return this.project.getName();
	}

}
