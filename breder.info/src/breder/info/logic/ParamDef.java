package breder.info.logic;

import java.text.ParseException;

import breder.xml.ITag;

public class ParamDef {

	private String name;

	private String classname;

	private String describer;

	public ParamDef(ITag tag) throws ParseException {
		this.name = tag.check("name");
		this.classname = tag.check("class");
		this.describer = tag.getContent().getText();
	}

	public String getName() {
		return name;
	}

	public String getClassname() {
		return classname;
	}

	public String getDescriber() {
		return describer;
	}

}
