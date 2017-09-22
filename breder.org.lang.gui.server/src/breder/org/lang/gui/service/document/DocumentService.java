package breder.org.lang.gui.service.document;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import breder.jsql.ITransaction;
import breder.org.lang.gui.service.QueryResource;
import breder.util.util.CacheMap;
import breder.util.util.InputStreamUtil;
import breder.webservice.jdbc.DbConnection;
import breder.webservice.service.AbstractService;

public class DocumentService extends AbstractService implements
		IDocumentService {

	private static Map<Number, IDocument[]> PATHS = new CacheMap<Number, IDocument[]>(
			1024);

	@Override
	public IDocument[] list(Number id) {
		if (PATHS.get(id) != null) {
			return PATHS.get(id);
		}
		ITransaction t = DbConnection.getInstance().get().start();
		List<IDocument> docs = new ArrayList<IDocument>();
		{
			String query;
			if (id == null) {
				query = QueryResource.getInstance()
						.get("document.list.id_null");
			} else {
				query = QueryResource.getInstance().get("document.list");
			}
			Object[][] rows = t.nativeSql(query, id);
			for (Object[] row : rows) {
				docs.add(new Document((Number) row[0], (String) row[2],
						(Number) row[1]));
			}
			try {
				t.commit();
			} catch (Exception e) {
			}
		}
		IDocument[] array = docs.toArray(new IDocument[0]);
		Arrays.sort(array, new Comparator<IDocument>() {
			@Override
			public int compare(IDocument o1, IDocument o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		PATHS.put(id, array);
		return array;
	}

	@Override
	public void newDocument(Number parentId, String name, byte[] bytes)
			throws Exception {
		String query = QueryResource.getInstance().get("document.new");
		DbConnection.getInstance().get().start()
				.nativeSql(query, parentId, name, bytes);
		PATHS.remove(parentId);
	}

	@Override
	public byte[] getBytes(Number id) throws Exception {
		String query = QueryResource.getInstance().get("document.get_byte");
		ITransaction t = DbConnection.getInstance().get().start();
		PreparedStatement ps = t.getConnection().prepareStatement(query);
		ps.setObject(1, id);
		ps.execute();
		t.commit();
		ResultSet rs = ps.getResultSet();
		if (rs.next()) {
			InputStream input = rs.getBinaryStream(1);
			if (input == null) {
				return null;
			} else {
				return InputStreamUtil.getBytes(input);
			}
		} else {
			return null;
		}
	}

	@Override
	public void setByte(Number id, byte[] bytes) throws Exception {
		String query = QueryResource.getInstance().get("document.set_byte");
		ITransaction t = DbConnection.getInstance().get().start();
		PreparedStatement ps = t.getConnection().prepareStatement(query);
		ps.setBlob(1, new ByteArrayInputStream(bytes));
		ps.setObject(2, id);
		ps.execute();
		t.commit();
	}

}
