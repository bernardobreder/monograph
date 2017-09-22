package breder.ide.lang.c;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import breder.ide.lang.ILanguage;
import breder.ide.lang.ILanguageStyle;
import breder.ide.lang.Project;
import breder.xml.ITag;

public class CLanguage implements ILanguage {
	
	private final CStyle style = new CStyle();
	
	@Override
	public String getName() {
		return "c";
	}
	
	@Override
	public ILanguageStyle getStyle() {
		return this.style;
	}
	
	@Override
	public Project newProject(File dir, ITag projectTag) throws ParseException, IOException {
		String type = projectTag.check("type");
		if (type.equals("exec")) {
			return new CExecProject(dir);
		} else if (type.equals("static")) {
			return new CStaticProject(dir);
		} else if (type.equals("shared")) {
			return new CSharedProject(dir);
		} else {
			throw new ParseException("type unknow", 0);
		}
	}
	
	@Override
	public String run() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void build(ITag projectTag) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String build() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String debug() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
