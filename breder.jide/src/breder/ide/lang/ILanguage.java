package breder.ide.lang;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import breder.xml.ITag;

public interface ILanguage {
	
	public void build(ITag projectTag);
	
	public ILanguageStyle getStyle();
	
	public String getName();
	
	public Project newProject(File dir, ITag projectTag) throws ParseException, IOException;
	
	public String run();
	
	public String build();
	
	public String debug();
	
}
