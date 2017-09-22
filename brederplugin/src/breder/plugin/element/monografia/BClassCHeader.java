package breder.plugin.element.monografia;

import org.eclipse.core.resources.IFile;

import breder.plugin.element.BFile;
import breder.plugin.element.Parent;

public class BClassCHeader extends BFile {

	public BClassCHeader(Parent<?> parent, IFile file) {
		super(parent, file);
	}

	@Override
	public String getName() {
		String name = this.getResource().getName();
		name = name.substring(0, name.length() - ".h".length());
		name = Character.toUpperCase(name.charAt(0)) + name.substring(1);
		return name;
	}

}
