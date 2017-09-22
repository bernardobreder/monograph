package web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import breder.org.disk.BFileSystem;
import breder.org.disk.IFile;
import breder.org.disk.IFileSystem;
import breder.util.util.InputStreamUtil;

public class Servlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getPathInfo();
		IFile file = BFileSystem.getFile(IFileSystem.DEFAULT, "/remote" + path);
		if (file == null) {
			resp.getOutputStream().write("file not found".getBytes());
			return;
		}
		InputStreamUtil.copyStream(file.getInputStream(), resp.getOutputStream());
	}

}
