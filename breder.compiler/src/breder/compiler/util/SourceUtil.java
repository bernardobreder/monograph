
package breder.compiler.util;

import java.util.List;

import breder.compiler.compiler.Context;
import breder.compiler.exception.AmbiguityException;
import breder.compiler.exception.BrederJRuntimeException;
import breder.compiler.filesystem.IBrederFileManager;
import breder.compiler.filesystem.IFile;
import breder.compiler.filesystem.IResource;
import breder.compiler.node.standart.BSource;
import breder.compiler.node.standart.BStruct;
import breder.compiler.parser.javacc.ParseException;
import breder.compiler.parser.javacc.Token;
import breder.compiler.parser.javacc.TokenUtil;

public class SourceUtil {

	public static int findClassIndex(Context context, BStruct struct) {
		BSource[] sources = context.getCompiler().getSources();
		for (int n = 0; n < sources.length; n++) {
			if (sources[n].getStruct() == struct) {
				return n;
			}
		}
		return -1;
	}

	public static BSource findType(Context context, Token name) throws ParseException {
		BSource source = context.getSource().getImport(name.image);
		if (source != null) {
			return source;
		}
		List<Token> imports = context.getSource().getImports();
		int dotIndex = name.image.indexOf('.');
		if (dotIndex != -1) {
			source = SourceUtil.getType(context, name);
		} else {
			if (context.getSource().getStruct().getName().image.equals(name.image)) {
				source = context.getSource();
			} else {
				for (Token importToken : imports) {
					if (importToken.image.endsWith(".*")) {
						String path = importToken.image.substring(0, importToken.image.length() - 2);
						BSource t = SourceUtil.getType(context, TokenUtil.newInstance(path + "." + name.image));
						if (source != null && t != null && !source.equals(t)) {
							throw new BrederJRuntimeException(context, name, "ambiguity in type with name '%s'",
									name.image);
						} else if (source == null && t != null) {
							source = t;
						}
					} else {
						if (importToken.image.endsWith("." + name.image)) {
							BSource t = SourceUtil.getType(context, importToken);
							if (source != null && t != null) {
								throw new BrederJRuntimeException(context, name, "ambiguity in type with name '%s'",
										name.image);
							} else if (source == null && t != null) {
								source = t;
							}
						}
					}
				}
			}
		}
		if (source == null) {
			throw new BrederJRuntimeException(context, name,
					"not found the variable or type '%s' in the imports and classpath", name.image);
		} else {
			context.getSource().putImport(name.image, source);
			return source;
		}
	}

	public static BSource getType(Context context, Token name) throws ParseException {
		int dotIndex = name.image.lastIndexOf('.');
		String pack = name.image.substring(0, dotIndex);
		String classname = name.image.substring(dotIndex + 1);
		BSource source = context.getCompiler().getSource(pack + "." + classname);
		if (source != null) {
			return source;
		}
		IResource resource;
		try {
			resource = IBrederFileManager.DEFAULT.findBrederFile(name.image);
		} catch (AmbiguityException e) {
			throw new BrederJRuntimeException(context, name, e.getMessage());
		}
		if (resource.exist() && resource instanceof IFile) {
			source = context.getCompiler().findSource(name.image);
			return source;
		}
		return null;
	}

}
