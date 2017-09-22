package breder.info.logic;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import breder.xml.ITag;

public class ApiDef implements Loader {

	private final List<ClassDef> classes = new ArrayList<ClassDef>();

	public ApiDef(ITag tag) throws ParseException {
		for (ITag ctag : tag.getTags("class")) {
			this.classes.add(new ClassDef(ctag));
		}
	}

	public List<ClassDef> getClasses() {
		return Collections.unmodifiableList(classes);
	}

}
