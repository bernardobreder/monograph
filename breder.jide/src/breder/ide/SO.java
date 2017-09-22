package breder.ide;

public class SO {
	
	private static final SO instance = new SO();
	
	public boolean isWindows() {
		return !this.isUnix();
	}
	
	public boolean isUnix() {
		return System.getenv("HOME").startsWith("/");
	}
	
	public static SO getInstance() {
		return instance;
	}
	
}
