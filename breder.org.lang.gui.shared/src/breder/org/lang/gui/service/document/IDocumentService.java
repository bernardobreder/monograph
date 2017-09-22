package breder.org.lang.gui.service.document;

import breder.webservice.IService;

public interface IDocumentService extends IService {

	public IDocument[] list(Number id) throws Exception;

	public void newDocument(Number parentId, String name, byte[] bytes)
			throws Exception;

	public byte[] getBytes(Number id) throws Exception;

	public void setByte(Number id, byte[] bytes) throws Exception;

}
