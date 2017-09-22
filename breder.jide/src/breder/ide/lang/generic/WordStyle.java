package breder.ide.lang.generic;

public class WordStyle implements IWordStyle {
	
	private final String word;
	
	public WordStyle(String word) {
		super();
		this.word = word;
	}
	
	@Override
	public boolean accept(String word) {
		return this.word.equals(word);
	}
	
	public String getWord() {
		return word;
	}
	
}
