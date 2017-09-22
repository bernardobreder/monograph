package breder.ide.lang.c;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import breder.ide.lang.IBuilder;
import breder.ide.lang.ILanguage;
import breder.ide.lang.LanguageManager;
import breder.ide.lang.Project;
import breder.util.util.FileUtil;
import breder.xml.ITag;
import breder.xml.XmlReader;

public abstract class CProject extends Project implements IBuilder {

	private final List<File> sources = new ArrayList<File>();

	private final List<File> includes = new ArrayList<File>();

	private final List<String> links = new ArrayList<String>();

	private final List<File> linkDirs = new ArrayList<File>();

	private final List<String> flags = new ArrayList<String>();

	public CProject(File dir) throws ParseException, IOException {
		super(dir);
		this.configure();
	}

	private void configure() throws ParseException, IOException {
		ITag projectTag;
		{
			File file = new File(this.getDir(), "project.xml");
			XmlReader reader = new XmlReader(new FileInputStream(file));
			reader.start();
			projectTag = reader.getTag("project");
		}
		{
			List<ITag> tags = projectTag.getTags("source");
			for (ITag tag : tags) {
				this.addSource(new File(this.getDir(), tag.check("src")));
			}
		}
		{
			List<ITag> tags = projectTag.getTags("include");
			for (ITag tag : tags) {
				this.addInclude(new File(this.getDir(), tag.check("src")));
			}
		}
		{
			List<ITag> tags = projectTag.getTags("target");
			for (ITag tag : tags) {
				File dir = new File(this.getDir(), tag.check("dir"));
				String file = tag.get("file");
				if (file == null) {
					file = this.getDir().getName();
				}
				file = file + FileUtil.getExecutableExtension();
				this.addTarget(new File(dir, file).toString());
			}
		}
		{
			List<ITag> tags = projectTag.getTags("link");
			for (ITag tag : tags) {
				this.addLink(tag.check("name"));
			}
		}
		{
			List<ITag> tags = projectTag.getTags("linkdir");
			for (ITag tag : tags) {
				this.addLinkDir(new File(this.getDir(), tag.check("src")));
			}
		}
		{
			List<ITag> tags = projectTag.getTags("depend");
			for (ITag tag : tags) {
				this.addDependency(tag.check("name"));
			}
		}
		{
			List<ITag> tags = projectTag.getTags("compiler");
			for (ITag tag : tags) {
				this.addFlag(tag.check("flag"));
			}
		}
		{
			List<ITag> tags = projectTag.getTags("run");
			for (ITag tag : tags) {
				if (tag.getContents().size() > 0) {
					this.addArgument(tag.getContents().get(0).toString());
				}
			}
		}
	}

	@Override
	public ILanguage getLanguage() {
		return LanguageManager.getInstance().get("c");
	}

	public void addSource(File file) {
		this.sources.add(file);
	}

	public void addInclude(File file) {
		this.includes.add(file);
	}

	public void addLink(String check) {
		this.links.add(check);
	}

	public void addLinkDir(File file) {
		this.linkDirs.add(file);
	}

	public void addFlag(String flag) {
		this.flags.add(flag);
	}

	public List<File> getSources() {
		return Collections.unmodifiableList(this.sources);
	}

	public List<File> getIncludes() {
		return Collections.unmodifiableList(this.includes);
	}

	public List<String> getLinks() {
		return Collections.unmodifiableList(links);
	}

	public List<File> getLinkDirs() {
		return Collections.unmodifiableList(linkDirs);
	}

	public List<String> getFlags() {
		return Collections.unmodifiableList(flags);
	}

}
