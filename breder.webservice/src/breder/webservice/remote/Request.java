package breder.webservice.remote;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.Set;

import sun.reflect.misc.MethodUtil;
import breder.webservice.remote.data.MethodInvocationRequest;
import breder.webservice.service.ClientService;
import breder.webservice.util.ObjectUtil;

public class Request {

	private static Set<String> inited = new HashSet<String>();

	/**
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static Object request(MethodInvocationRequest request)
			throws Throwable {
		try {
			if (request.getFieldsBytes() != null) {
				String code = ObjectUtil.readBytes(Object.class,
						request.getFieldsBytes()).toString();
				ClientService.getInstance().setCode(code);
				ClientService.getInstance().refreshInfos(code);
			}
			Object[] params = new Object[request.getArgumentInstanceBytes().length];
			for (int n = 0; n < params.length; n++) {
				byte[] bytes = (byte[]) request.getArgumentInstanceBytes()[n];
				ObjectInputStream objinput = new ObjectInputStream(
						new ByteArrayInputStream(bytes));
				params[n] = objinput.readObject();
			}
			Class<?> interfaceClass;
			Class<?> clazz;
			{
				String name = request.getClassName();
				int index = name.lastIndexOf('.');
				interfaceClass = Class.forName(name);
				name = name.substring(0, index + 1) + name.substring(index + 2);
				clazz = Class.forName(name);
			}
			Class<?>[] paramClass = new Class[params.length];
			Method method;
			Object proxy, original = clazz.newInstance();
			{
				String name = request.getMethodName();
				int index = name.indexOf('(');
				String methodname = name.substring(0, index);
				name = name.substring(index + 1);
				int n = 0;
				while (name.indexOf(')') != 0) {
					index = name.indexOf(',');
					String aux = name.substring(0, index);
					if (aux.equals("int"))
						paramClass[n++] = int.class;
					else if (aux.equals("float"))
						paramClass[n++] = float.class;
					else if (aux.equals("double"))
						paramClass[n++] = double.class;
					else if (aux.equals("short"))
						paramClass[n++] = short.class;
					else if (aux.equals("char"))
						paramClass[n++] = char.class;
					else if (aux.equals("long"))
						paramClass[n++] = long.class;
					else if (aux.equals("byte"))
						paramClass[n++] = byte.class;
					else
						paramClass[n++] = Class.forName(aux);
					name = name.substring(index + 1);
				}
				Method proxyMethod = MethodUtil.getMethod(clazz, "getProxy",
						new Class[] { interfaceClass.getClass() });
				proxy = proxyMethod.invoke(original, interfaceClass);
				if (proxy == null) {
					proxy = original;
				}
				method = MethodUtil.getMethod(interfaceClass, methodname,
						paramClass);
			}
			synchronized (inited) {
				if (!inited.contains(interfaceClass.getName())) {
					MethodUtil
							.getMethod(interfaceClass, "init", new Class[] {})
							.invoke(original);
					inited.add(interfaceClass.getName());
				}
			}
			return method.invoke(proxy, params);
		} catch (Throwable e) {
			e.printStackTrace();
			throw e;
//			e.printStackTrace();
//			Throwable t = e.getCause();
//			while (t instanceof InvocationTargetException) {
//				t = ((InvocationTargetException) t).getCause();
//			}
//			if (t != null) {
//				t.printStackTrace();
//			}
//			throw t;
		}
	}
}
