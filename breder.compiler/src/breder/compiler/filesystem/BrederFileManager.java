
package breder.compiler.filesystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import breder.compiler.exception.AmbiguityException;
import breder.compiler.util.InputStreamUtil;
import breder.compiler.util.LightArrayList;

public class BrederFileManager implements IBrederFileManager {

	private static final BrederFileManager instance = new BrederFileManager();

	public final Map<String, InputStream> CACHE = new HashMap<String, InputStream>();

	private final List<String> classpaths = new LightArrayList<String>();

	private final List<String> bars = new LightArrayList<String>();

	private final Map<String, IResource> resources = new HashMap<String, IResource>(1024);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addClasspath(String classpath) {
		classpaths.add(classpath);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addBar(String filename) {
		File dir = new File(filename);
		if (dir.exists()) {
			if (dir.isDirectory()) {
				for (File file : dir.listFiles()) {
					if (!file.isHidden()) {
						if (file.isDirectory()) {
							this.addBar(file.toString());
						} else if (file.getName().endsWith(".bar")) {
							this.bars.add(file.toString());
						}
					}
				}
			} else {
				this.bars.add(filename);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String[] getClasspaths() {
		return classpaths.toArray(new String[0]);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String[] getBars() {
		return bars.toArray(new String[0]);
	}

	public void cleanClasspaths() {
		this.classpaths.clear();
	}

	public void cleanCaches() {
		this.CACHE.clear();
		this.resources.clear();
	}

	public void cleanCaches(String pathname) {
		for (String key : this.CACHE.keySet().toArray(new String[0])) {
			for (String classpath : this.classpaths) {
				File file = new File(classpath, pathname);
				if (key.startsWith(file.getAbsolutePath())) {
					this.CACHE.remove(key);
				}
			}
		}
		this.resources.clear();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IResource findBrederFile(String classname) throws AmbiguityException {
		IResource resource = this.resources.get(classname);
		if (resource != null) {
			return resource;
		}
		try {
			String classpath = classname.replace('.', File.separatorChar) + ".breder";
			for (String cp : classpaths) {
				File file = new File(cp, classpath);
				if (new BFile(file).exist()) {
					return new BrederFile(file);
				}
			}
			for (String bar : this.bars) {
				File barFile = new File(bar);
				if (barFile.exists()) {
					resource = this.readBar(barFile, classpath);
					if (resource != null) {
						return resource;
					}
				}
			}
			return resource = new Resource(new File(classpath));
		} finally {
			this.resources.put(classname, resource);
		}
	}

	private IResource readBar(File file, String classpath) {
		System.out.println(classpath);
		ZipInputStream input = null;
		InputStream finput = null;
		try {
			finput = this.CACHE.get(file.toString());
			if (finput == null) {
				this.CACHE.put(file.toString(), finput = new CacheInputStream(new FileInputStream(file)));
			}
			input = new ZipInputStream(finput);
			ZipEntry nextEntry = input.getNextEntry();
			while (nextEntry != null) {
				if (nextEntry.getName().equals(classpath)) {
					ZipBrederFile resource = new ZipBrederFile(new File(file, classpath),
							InputStreamUtil.getBytes(input));
					return resource;
				}
				nextEntry = input.getNextEntry();
			}
		} catch (IOException e) {
		} finally {
			if (input != null) {
				try {
					finput.close();
				} catch (IOException e) {
				}
			}
		}
		return null;
	}

	public IResource[] list() {
		List<IResource> list = new LightArrayList<IResource>();
		for (String cp : classpaths) {
			File f = new File(cp);
			if (f.isDirectory()) {
				list.add(new Directory(f));
			} else {
				if (f.getName().endsWith(".breder")) {
					list.add(new BrederFile(f));
				} else {
					list.add(new Resource(f));
				}
			}
		}
		return list.toArray(new IResource[0]);
	}

	public static BrederFileManager getInstance() {
		return instance;
	}

}
