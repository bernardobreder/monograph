package breder.ide.lang.breder;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import breder.ide.lang.ILanguage;
import breder.ide.lang.ILanguageStyle;
import breder.ide.lang.Project;
import breder.xml.ITag;

public class BrederLanguage implements ILanguage {
	
	public static final String NAME = "breder";
	
	private final BrederStyle style = new BrederStyle();
	
	private String run;
	
	private String build;
	
	private String debug;
	
	@Override
	public void build(ITag projectTag) {
		ITag tag = projectTag.getTag("run");
		this.run = tag.getContent().getText();
		tag = projectTag.getTag("build");
		this.build = tag.getContent().getText();
		tag = projectTag.getTag("debug");
		this.debug = tag.getContent().getText();
	}
	
	@Override
	public String run() {
		return this.run;
	}
	
	@Override
	public String build() {
		return this.build;
	}
	
	@Override
	public String debug() {
		return this.debug;
	}
	
	@Override
	public String getName() {
		return "breder";
	}
	
	@Override
	public ILanguageStyle getStyle() {
		return style;
	}
	
	@Override
	public Project newProject(File dir, ITag projectTag) throws ParseException, IOException {
		return new BrederProject(dir);
	}
	
}
