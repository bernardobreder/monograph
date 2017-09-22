/**
 * 
 */

package breder.webservice.remote;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import breder.webservice.IServerService;
import breder.webservice.exception.TimeoutException;
import breder.webservice.remote.data.MethodInvocationRequest;
import breder.webservice.remote.data.MethodInvocationResponse;
import breder.webservice.service.AskAnswerManager;
import breder.webservice.service.IFeedBack;
import breder.webservice.util.ObjectUtil;

/**
 * @author bbreder
 */
public class ServerServiceProxy implements InvocationHandler, Serializable {
	
	private final String className;
	
	private final String code;
	
	private final boolean feedbackFlag;
	
	private final IFeedBack feedback;
	
	public ServerServiceProxy(Class<?> clazz, String code) {
		this(clazz, code, false, null);
	}
	
	public ServerServiceProxy(Class<?> clazz, String code, boolean feedbackFlag, IFeedBack feedback) {
		super();
		this.className = clazz.getName();
		this.code = code;
		this.feedbackFlag = feedbackFlag;
		this.feedback = feedback;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Object invoke(Object proxy, final Method method, final Object[] args) throws Throwable {
		if (feedbackFlag) {
			new FeedBackThread(method, args).start();
			return null;
		} else {
			return this.action(method, args);
		}
	}
	
	private Object action(Method method, Object[] args) throws Throwable {
		{
			MethodInvocationRequest request = new MethodInvocationRequest();
			{
				request.setClassName(className);
				if (code != null)
					request.setFieldsBytes(ObjectUtil.writeToBytes(code).toByteArray());
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
							bytesList.add(ObjectUtil.writeToBytes(arg).toByteArray());
						}
					}
					request.setArgumentInstanceBytes(bytesList.toArray());
				}
			}
			Object monitor = AskAnswerManager.getInstance().ask(code, request);
			synchronized (monitor) {
				try {
					monitor.wait(IServerService.DEFAULT.getConfig().getSessionTimeout());
				} catch (InterruptedException e) {
					Thread.interrupted();
				}
			}
			MethodInvocationResponse response = AskAnswerManager.getInstance().getAnswer(
					request.getId());
			if (response == null)
				throw new TimeoutException();
			if (!response.isError()) {
				Object returns = ObjectUtil.readBytes(Object.class, response.getResultBytes());
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
					else if (type.equals(short.class))
						return Short.class.cast(returns).shortValue();
					else if (type.equals(byte.class))
						return Byte.class.cast(returns).byteValue();
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
				Exception exception = ObjectUtil.readBytes(Exception.class, response
						.getExceptionBytes());
				if (exception.getCause() != null) {
					throw exception.getCause();
				} else {
					throw exception;
				}
			}
		}
	}
	
	public String getClassName() {
		return this.className;
	}
	
	private class FeedBackThread extends Thread {
		
		private final Method method;
		
		private final Object[] args;
		
		public FeedBackThread(Method method, Object[] args) {
			super();
			this.method = method;
			this.args = args;
		}
		
		public void run() {
			try {
				Object result = action(method, args);
				if (!method.getReturnType().getName().equals("void"))
					if (feedback != null) {
						feedback.run(result);
					}
			} catch (Throwable e) {
				if (feedback != null) {
					feedback.handler(e);
				}
			}
		}
		
	}
	
}
