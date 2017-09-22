package breder.org.lang.gui.service.document;

import java.io.Serializable;

public interface IDocument extends Serializable {

	public Number getId();

	public String getName();

	public Number getParentId();

}
