package breder.compiler.exception;

import breder.compiler.compiler.Context;

public class ClassnameNotFoundException extends BrederJRuntimeException {

	private final String classname;

	public ClassnameNotFoundException(String classname, Context context) {
		super(context, null,
				"can not found the classname '%s' in the classpath", classname);
		this.classname = classname;
	}

	public String getClassname() {
		return classname;
	}

}
