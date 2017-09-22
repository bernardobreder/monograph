
package breder.compiler.node.standart;

import java.util.List;

import breder.compiler.filesystem.BrederFile;
import breder.compiler.filesystem.IDirectory;
import breder.compiler.filesystem.IResource;
import breder.compiler.util.LightArrayList;

public class BPackage {

	protected List<BPackage> packages;

	protected List<BrederFile> classes;

	private final IDirectory dir;

	private final BPackage parent;

	public BPackage(IDirectory dir, BPackage parent) {
		super();
		this.dir = dir;
		this.parent = parent;
	}

	public List<BrederFile> getFiles() {
		if (classes == null) {
			classes = new LightArrayList<BrederFile>();
			IResource[] resources = dir.list();
			for (IResource resource : resources) {
				if (resource instanceof BrederFile) {
					classes.add((BrederFile) resource);
				}
			}
		}
		return classes;
	}

	public List<BPackage> getPackages() {
		if (packages == null) {
			packages = new LightArrayList<BPackage>();
			IResource[] resources = dir.list();
			for (IResource resource : resources) {
				if (resource instanceof IDirectory) {
					IDirectory dir = (IDirectory) resource;
					packages.add(new BPackage(dir, null));
				}
			}
		}
		return packages;
	}

	public String getName() {
		return dir.toFile().getName();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		if (parent == null) {
			return dir.toString();
		} else {
			return parent.toString() + "." + dir.toString();
		}
	}

}
