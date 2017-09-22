package breder.webservice.servlet;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import breder.webservice.remote.Request;
import breder.webservice.remote.data.MethodInvocationRequest;
import breder.webservice.remote.data.MethodInvocationResponse;
import breder.webservice.util.ObjectUtil;

/**
 * Servlet que implementa o web server
 * 
 */
public class ServiceServlet extends HttpServlet {
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Object result = null;
		Throwable exception = null;
		ObjectInputStream input;
		try {
			input = new ObjectInputStream(req.getInputStream());
		} catch (EOFException e) {
			resp.getOutputStream().close();
			return;
		}
		try {
			MethodInvocationRequest request = (MethodInvocationRequest) input.readObject();
			result = Request.request(request);
		} catch (Throwable e) {
			exception = e;
		}
		ObjectOutputStream output = new ObjectOutputStream(resp.getOutputStream());
		output
				.writeObject(new MethodInvocationResponse(0, ObjectUtil.writeToBytes(result)
						.toByteArray(), exception != null, ObjectUtil.writeToBytes(exception)
						.toByteArray()));
		output.close();
	}
	
}
