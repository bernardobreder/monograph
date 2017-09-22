package breder.ide.lang;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import breder.xml.ITag;
import breder.xml.XmlReader;

public class LanguageManager {
	
	private static final LanguageManager instance = new LanguageManager();
	
	private Map<String, ILanguage> languages = new HashMap<String, ILanguage>();
	
	private LanguageManager() {
		try {
			XmlReader reader = new XmlReader(new FileInputStream("config.xml"));
			reader.start();
			List<ITag> tags = reader.getTags("language");
			for (ITag tag : tags) {
				Class<? extends ILanguage> langClass = (Class<? extends ILanguage>) this.getClass()
						.forName(tag.check("class"));
				ILanguage language = langClass.newInstance();
				language.build(tag);
				this.languages.put(language.getName(), language);
			}
		} catch (Exception e) {
			throw new Error(e);
		}
	}
	
	public static LanguageManager getInstance() {
		return instance;
	}
	
	public ILanguage get(String name) {
		return this.languages.get(name);
	}
	
}
