package web.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;

import logic.task.Task;
import logic.task.TaskDAO;

public class TaskServlet extends JspServlet {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String action(Map<String, String> in, Map<String, Object> out)
			throws ServletException, IOException {
		String idStr = in.get("id");
		if (idStr != null) {
			Integer id;
			try {
				id = new Integer(idStr);
			} catch (NumberFormatException e) {
				id = 1;
			}
			Task task = TaskDAO.get(id);
			out.put("subtitle", String.format("Task %s", task.getTitle()));
			out.put("title", out.get("subtitle"));
			out.put("task", task);
			return "taskdesc";
		} else {
			out.put("subtitle", "Task");
			out.put("title", out.get("subtitle"));
			out.put("tasks", TaskDAO.list());
			return "task";
		}
	}

}
