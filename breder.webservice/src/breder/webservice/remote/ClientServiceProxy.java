/**
 * 
 */

package breder.webservice.remote;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import breder.util.net.HttpUrlConnection;
import breder.webservice.IServerConfigurator;
import breder.webservice.IServerService;
import breder.webservice.remote.data.MethodInvocationRequest;
import breder.webservice.remote.data.MethodInvocationResponse;
import breder.webservice.service.ServerService;
import breder.webservice.util.ObjectUtil;

/**
 * @author bbreder
 */
public class ClientServiceProxy implements InvocationHandler, Serializable {

	private final String className;

	public ClientServiceProxy(Class<?> clazz) {
		super();
		if (clazz != null) {
			this.className = clazz.getName();
		} else {
			this.className = null;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		MethodInvocationRequest request = this.buildRequest(method, args);
		HttpUrlConnection connection;
		{
			IServerConfigurator conf = IServerService.DEFAULT.getConfig();
			URL url = new URL(conf.getTomcatUrl());
			url = new URL(url, conf.getServletUrl());
			connection = new HttpUrlConnection(url.toString());
		}
		{
			OutputStream output = connection.getOutputStream();
			output.write(ObjectUtil.writeToBytes(request).toByteArray());
			output.flush();
		}
		{
			MethodInvocationResponse response = ObjectUtil
					.readBytes(MethodInvocationResponse.class,
							connection.getInputStream());
			if (!response.isError()) {
				Object returns = ObjectUtil.readBytes(Object.class,
						response.getResultBytes());
				if (returns == null)
					return null;
				Class<?> type = method.getReturnType();
				if (method.getReturnType().isPrimitive()) {
					if (type.equals(char.class))
						return Character.class.cast(returns).charValue();
					else if (type.equals(int.class))
						return Integer.class.cast(returns).intValue();
					else if (type.equals(long.class))
						return Long.class.cast(returns).longValue();
					else if (type.equals(float.class))
						return Float.class.cast(returns).floatValue();
					else if (type.equals(double.class))
						return Double.class.cast(returns).doubleValue();
					else if (type.equals(boolean.class))
						return Boolean.class.cast(returns).booleanValue();
					else
						throw new RuntimeException();
				} else
					return returns;
			} else {
				Throwable exception = ObjectUtil.readBytes(Throwable.class,
						response.getExceptionBytes());
				throw exception;
			}
		}
	}

	private MethodInvocationRequest buildRequest(Method method, Object[] args)
			throws IOException {
		MethodInvocationRequest request = new MethodInvocationRequest();
		{
			request.setClassName(className);
			String code = ServerService.getInstance().getCode();
			if (code != null) {
				request.setFieldsBytes(ObjectUtil.writeToBytes(code)
						.toByteArray());
			}
			{
				StringBuilder sb = new StringBuilder();
				sb.append(method.getName() + "(");
				Class<?>[] classes = method.getParameterTypes();
				for (int n = 0; n < classes.length; n++) {
					sb.append(classes[n].getName());
					sb.append(",");
				}
				sb.append(")");
				request.setMethodName(sb.toString());
			}
			{
				List<byte[]> bytesList = new ArrayList<byte[]>();
				if (args != null) {
					for (Object arg : args) {
						bytesList.add(ObjectUtil.writeToBytes(arg)
								.toByteArray());
					}
				}
				request.setArgumentInstanceBytes(bytesList.toArray());
			}
		}
		return request;
	}

	public String getClassName() {
		return this.className;
	}

}
