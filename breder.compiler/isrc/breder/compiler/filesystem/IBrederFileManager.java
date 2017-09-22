package breder.compiler.filesystem;

import breder.compiler.exception.AmbiguityException;

public interface IBrederFileManager {

	public static final IBrederFileManager DEFAULT = BrederFileManager
			.getInstance();

	public void addClasspath(String classpath);
	
	public void addBar(String filename);

	public String[] getClasspaths();
	
	public String[] getBars();

	/**
	 * Busca pelo arquivo breder nos classpaths. Caso não ache, será retornado
	 * nulo.
	 * 
	 * @param classname
	 *            nome da classe
	 * @return
	 */
	public IResource findBrederFile(String classname) throws AmbiguityException;

	public IResource[] list();

}
