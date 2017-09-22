
package breder.compiler.compiler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import breder.compiler.exception.AmbiguityException;
import breder.compiler.exception.BrederJRuntimeException;
import breder.compiler.exception.ClassnameNotFoundException;
import breder.compiler.exception.FileNotFoundException;
import breder.compiler.filesystem.BrederFileManager;
import breder.compiler.filesystem.IBrederFileManager;
import breder.compiler.filesystem.IFile;
import breder.compiler.filesystem.IResource;
import breder.compiler.node.standart.BClass;
import breder.compiler.node.standart.BField;
import breder.compiler.node.standart.BMethod;
import breder.compiler.node.standart.BSource;
import breder.compiler.node.standart.BStruct;
import breder.compiler.node.standart.BType;
import breder.compiler.parser.javacc.ParseException;
import breder.compiler.parser.javacc.Parser;
import breder.compiler.util.LightArrayList;

public abstract class AbstractCompiler {

	protected static AbstractCompiler compiler;

	protected final List<BSource> sources = new ArrayList<BSource>();

	protected final Map<String, BSource> sourceMap = new HashMap<String, BSource>();

	protected final Context context = new Context(this);

	protected final List<String> constants = new ArrayList<String>();

	protected final List<String> stringConstants = new ArrayList<String>();

	protected final List<Double> doubleConstants = new ArrayList<Double>();

	protected final List<String> librarys = new LightArrayList<String>();

	protected final Set<String> bars = new HashSet<String>();

	protected final List<BMethod> nativeMethod = new ArrayList<BMethod>();

	protected final List<BField> staticFields = new ArrayList<BField>();

	protected final Map<String, BType> types = new HashMap<String, BType>();

	protected String output;

	protected boolean isDebug;

	protected String classname;

	public AbstractCompiler() {
		this.output = "./binary.b";
	}

	public BSource header(Context context, String classname) throws ParseException {
		this.context.pushClassname(classname);
		IResource resource;
		try {
			resource = IBrederFileManager.DEFAULT.findBrederFile(classname);
		} catch (AmbiguityException e) {
			throw new BrederJRuntimeException(context, null, "ambiguity the classname '%s' in the classpath", classname);
		}
		if (!resource.exist() || resource instanceof IFile == false) {
			throw new ClassnameNotFoundException(classname, context);
		}
		IFile file = (IFile) resource;
		InputStream input;
		try {
			input = file.getInputStream();
		} catch (FileNotFoundException e) {
			throw new ClassnameNotFoundException(classname, context);
		}
		return this.init(classname, input);
	}

	public void body(Context context, String classname) throws ParseException {
		this.context.pushClassname(classname);
		this.getSource(classname).body(context);
		this.context.popClassname();
	}

	private BSource init(String classname, InputStream input) throws ParseException {
		BSource source = new Parser(this, classname, input).init();
		this.addSource(source);
		source.syntax(context);
		this.context.popClassname();
		return source;
	}

	public Integer indexOf(BMethod method) {
		int index = 0;
		for (int n = 0; n < this.sources.size(); n++) {
			BStruct struct = this.sources.get(n).getStruct();
			List<BMethod> methods = struct.getMethods();
			for (int m = 0; m < struct.getMethods().size(); m++, index++) {
				if (methods.get(m) == method) {
					return index;
				}
			}
		}
		return -1;
	}

	public BType findType(String classname) throws ParseException {
		BType type = this.types.get(classname);
		if (type == null) {
			this.types.put(classname, type = new BType(this.findSource(classname).getStruct()));
		}
		return type;
	}

	public BSource findSource(String classname) throws ParseException {
		BSource source = this.sourceMap.get(classname);
		if (source != null) {
			return source;
		}
		source = this.header(context, classname);
		body(context, classname);
		switch (context.getState()) {
			case SEMANTIC_HEADER: {
				source.semanticHeader(context);
				break;
			}
			case SEMANTIC_BODY: {
				source.semanticHeader(context);
				source.semanticBody(context);
				break;
			}
		}
		this.addSource(source);
		return source;
	}

	public void start(String classname) throws Exception {
		this.classname = classname;
		this.context.setState(CompilerState.SYNTAX);
		this.header(context, classname);
		if(false){
			int index = 0;
			for (BSource source : sources) {
				if (source.getStruct() instanceof BClass) {
					BClass bstruct = (BClass) source.getStruct();
					for (BField field : bstruct.getFields()) {
						if (!field.isStatic()) {
							field.setIndex(index++);
						}
					}
				}
			}
		}
		if(false){
			int index = 0;
			for (BSource source : this.getSources()) {
				for (BMethod method : source.getStruct().getMethods()) {
					method.setIndex(index++);
				}
			}
		}
		this.body(context, classname);
		this.startLang();
		this.startOthers();
		for (String bar : BrederFileManager.getInstance().getBars()) {
			File barFile = new File(bar);
			this.startBar(barFile);
		}
		this.context.setState(CompilerState.SEMANTIC_HEADER);
		for (BSource source : sources) {
			source.semanticHeader(context);
		}
		this.context.setState(CompilerState.SEMANTIC_BODY);
		for (BSource source : sources) {
			source.semanticBody(context);
		}
		this.context.setState(null);
		for (BSource source : sources) {
			source.check(context);
		}
		this.save();
	}

	private void startLang() throws ParseException {
		this.findSource("breder.lang.standard.Method");
		this.findSource("breder.util.standard.ArrayList");
	}

	private void startOthers() throws ParseException {
		Set<String> set = new HashSet<String>();
		set.add("breder.math");
		for (BSource source : this.sources) {
			set.add(source.getPackageToken().image);
		}
		for (String pack : set) {
			for (String classpath : BrederFileManager.getInstance().getClasspaths()) {
				File dir = new File(classpath, pack.replace('.', File.separatorChar));
				if (dir.exists()) {
					for (File file : dir.listFiles()) {
						if (!file.isHidden()) {
							if (file.isFile() && file.getName().endsWith(".breder")) {
								String classname = file.toString().substring(classpath.length() + 1)
										.replace(File.separatorChar, '.');
								classname = classname.substring(0, classname.length() - ".breder".length());
								this.findSource(classname);
							}
						}
					}
				}
			}
		}
	}

	private void startBar(File barDir) throws ParseException {
		ZipInputStream input = null;
		try {
			input = new ZipInputStream(new FileInputStream(barDir));
			ZipEntry nextEntry = input.getNextEntry();
			while (nextEntry != null) {
				String name = nextEntry.getName();
				String simpleclassname = name.lastIndexOf(File.separatorChar) == -1 ? name : name.substring(name
						.lastIndexOf(File.separatorChar) + 1);
				if (!simpleclassname.startsWith(".") && name.endsWith(".breder")) {
					String classname = nextEntry.getName().replace(File.separatorChar, '.');
					classname = classname.substring(0, classname.length() - ".breder".length());
					this.findSource(classname);
				}
				nextEntry = input.getNextEntry();
			}
		} catch (IOException e) {
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
				}
			}
		}
	}

	protected abstract void save() throws Exception;

	public int indexOf(BClass clazz) {
		for (int n = 0; n < this.sources.size(); n++) {
			if (this.sources.get(n).getStruct() == clazz) {
				return n;
			}
		}
		return -1;
	}

	public void addSource(BSource source) {
		if (this.sourceMap.containsKey(source.getClassname())) {
			return;
		}
		this.sourceMap.put(source.getClassname(), source);
		if (this.sources.size() > Math.pow(2, 16)) {
			throw new BrederJRuntimeException(context, source.getStruct().getName(),
					"compiler work with number of classe lower then 65536");
		}
		source.getStruct().setIndex((short) this.sources.size());
		this.sources.add(source);
		if (this.sources.size() > Math.pow(2, 16)) {
			throw new BrederJRuntimeException(context, source.getStruct().getName(),
					"project has source more than %d.", Math.pow(2, 16));
		}
	}

	public int addConstant(String constant) {
		int found = this.constants.indexOf(constant);
		if (found != -1)
			return found;
		int index = this.constants.size();
		this.constants.add(constant);
		return index;
	}

	public int addStringConstant(String constant) {
		int found = this.stringConstants.indexOf(constant);
		if (found != -1)
			return found;
		int index = this.stringConstants.size();
		this.stringConstants.add(constant);
		return index;
	}

	public int addNumberConstant(Double constant) {
		int found = this.doubleConstants.indexOf(constant);
		if (found != -1)
			return found;
		int index = this.doubleConstants.size();
		this.doubleConstants.add(constant);
		return index;
	}

	public void addClasspath(String classpath) {
		IBrederFileManager.DEFAULT.addClasspath(classpath);
	}

	public BSource[] getSources() {
		return sources.toArray(new BSource[0]);
	}

	public void addLibrary(String pathname) {
		librarys.add(pathname);
	}

	public String[] getLibrarys() {
		return librarys.toArray(new String[0]);
	}

	public int addNativeMethod(Context context, BMethod method) throws ParseException {
		String name = method.toNativeNameString(context);
		for (int n = 0; n < this.nativeMethod.size(); n++) {
			BMethod m = this.nativeMethod.get(n);
			if (m.getNativeName().equals(name)) {
				return n;
			}
		}
		int found = this.nativeMethod.indexOf(method);
		if (found != -1)
			return found;
		int index = this.nativeMethod.size();
		this.nativeMethod.add(method);
		return index;
	}

	public String[] getNativeMethods() {
		return nativeMethod.toArray(new String[0]);
	}

	public Context getContext() {
		return context;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public boolean isDebug() {
		return isDebug;
	}

	public void setDebug(boolean isDebug) {
		this.isDebug = isDebug;
	}

	public List<BField> getStaticFields() {
		return staticFields;
	}

	public void addStaticField(BField field) {
		this.staticFields.add(field);
	}

	public BSource getSource(String format) {
		return this.sourceMap.get(format);
	}

	public void addBar(String value) {
		this.bars.add(value);
		IBrederFileManager.DEFAULT.addBar(value);
	}

	public static AbstractCompiler getCompiler() {
		return compiler;
	}

	public static void setCompiler(AbstractCompiler compiler) {
		AbstractCompiler.compiler = compiler;
	}

}
