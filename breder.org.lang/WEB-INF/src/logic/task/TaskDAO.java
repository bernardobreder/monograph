package logic.task;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import db.jdbc.JDBC;
import db.jdbc.StreamUtil;

public class TaskDAO {

	public static Task[] list() throws IOException {
		Object[][] rows = JDBC.getInstance().query(
				"select * from breder_org_lang$task");
		List<Task> tasks = new ArrayList<Task>();
		for (Object[] row : rows) {
			tasks.add(newInstance(row));
		}
		return tasks.toArray(new Task[0]);
	}

	public static Task get(Integer id) throws IOException {
		Object[][] rows = JDBC.getInstance().query(
				"select * from breder_org_lang$task where id = ?", id);
		if (rows.length == 0)
			return null;
		return newInstance(rows[0]);
	}

	private static Task newInstance(Object[] row) {
		Integer id = (Integer) row[0];
		String title = (String) row[1];
		String describer = new String((byte[]) row[2]);
		Task task = new Task(id, title, describer);
		return task;
	}

}
