package breder.info.logic;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import breder.xml.ITag;

public class MethodDef implements Loader {

	private final String name;

	private final String describer;

	private final List<ParamDef> params = new ArrayList<ParamDef>();

	public MethodDef(ITag tag) throws ParseException {
		this.name = tag.check("name");
		this.describer = tag.getContentTag("desc");
		for (ITag ptag : tag.getTags("param")) {
			this.params.add(new ParamDef(ptag));
		}
	}

	public List<ParamDef> getParams() {
		return Collections.unmodifiableList(params);
	}

	public String getName() {
		return name;
	}

	public String getDescriber() {
		return describer;
	}

}
