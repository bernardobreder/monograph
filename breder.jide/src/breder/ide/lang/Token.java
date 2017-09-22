package breder.ide.lang;

public class Token {
	
	private final int begin;
	
	private final String word;
	
	public Token(int begin, String word) {
		super();
		this.begin = begin;
		this.word = word;
	}
	
	public int getBegin() {
		return begin;
	}
	
	public String getWord() {
		return word;
	}
	
	public String toString() {
		return this.getWord();
	}
	
}
