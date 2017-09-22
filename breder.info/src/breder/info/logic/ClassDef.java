package breder.info.logic;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import breder.xml.ITag;

public class ClassDef implements Loader {

	private final String name;

	private final String describer;

	private final List<MethodDef> methods = new ArrayList<MethodDef>();

	public ClassDef(ITag tag) throws ParseException {
		this.name = tag.check("name");
		this.describer = tag.getContentTag("desc");
		for (ITag mtag : tag.getTags("method")) {
			this.methods.add(new MethodDef(mtag));
		}
	}

	public List<MethodDef> getMethods() {
		return Collections.unmodifiableList(methods);
	}

	public String getName() {
		return name;
	}

	public String getDescriber() {
		return describer;
	}

}
