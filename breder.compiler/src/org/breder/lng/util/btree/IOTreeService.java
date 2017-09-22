package org.breder.lng.util.btree;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Estrutura que irá armazenar e carregar os nodes
 * 
 * @author bernardobreder
 * 
 */
public interface IOTreeService {

	/**
	 * Retorna a stream da arvore
	 * 
	 * @return
	 * @throws IOException
	 */
	public OutputStream getTreeOutputStream() throws IOException;

	/**
	 * Retorna a stream da arvore
	 * 
	 * @return
	 * @throws IOException
	 */
	public InputStream getTreeInputStream() throws IOException;

	/**
	 * Retorna a stream do node
	 * 
	 * @param nodeId
	 * @return
	 * @throws IOException
	 */
	public OutputStream getTreeNodeOutputStream(int nodeId) throws IOException;

	/**
	 * Retorna a stream do node
	 * 
	 * @param nodeId
	 * @return
	 * @throws IOException
	 */
	public InputStream getTreeNodeInputStream(int nodeId) throws IOException;

	/**
	 * Realiza a escrita do objeto na stream
	 * 
	 * @param output
	 * @param value
	 * @throws IOException
	 */
	public void writeValue(DataOutputStream output, Object value)
			throws IOException;

	/**
	 * Realiza a leitura de um objeto baseado na stream
	 * 
	 * @param input
	 * @return
	 * @throws IOException
	 */
	public Object readValue(DataInputStream input) throws IOException;

	/**
	 * Fecha a stream da arvore
	 * 
	 * @param input
	 * @throws IOException
	 */
	public void closeTreeStream(InputStream input) throws IOException;

	/**
	 * Fecha a stream da arvore
	 * 
	 * @param output
	 * @throws IOException
	 */
	public void closeTreeStream(OutputStream output) throws IOException;

	/**
	 * Fecha a stream
	 * 
	 * @param output
	 * @throws IOException
	 */
	public void closeTreeNodeStream(OutputStream output) throws IOException;

	/**
	 * Fecha a stream
	 * 
	 * @param input
	 * @throws IOException
	 */
	public void closeTreeNodeStream(InputStream input) throws IOException;

	/**
	 * Indica se a arvore está vazia
	 * 
	 * @return arvore está vazia
	 * @throws IOException
	 */
	public boolean exists(int nodeId) throws IOException;

	/**
	 * Indica se a arvore existe
	 * 
	 * @return arvore está vazia
	 * @throws IOException
	 */
	public boolean exists() throws IOException;

	/**
	 * Cria um novo identificador único
	 * 
	 * @return identificador único
	 */
	public int buildSequence();

}
