package breder.plugin.builder;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.ui.console.ConsolePlugin;

import breder.plugin.BrederProjectConstant;
import breder.plugin.InputStreamUtil;
import breder.plugin.console.BConsole;
import breder.plugin.element.breder.BProject;
import breder.plugin.element.project.BDescriberBinaryManager;
import breder.plugin.element.project.BRootBinary;
import breder.plugin.properties.ProjectPropertyPage;
import breder.plugin.util.BUITask;
import breder.plugin.util.BrederLanguageFile;
import breder.plugin.util.FileUtil;
import breder.plugin.util.InterruptedThread;
import breder.plugin.util.SoUtil;
import breder.plugin.view.project.ProjectView;

public class BCompiler {

	private final IFile file;

	private final IProject project;

	private final List<String> classpaths = new ArrayList<String>();

	private final List<String> arguments = new ArrayList<String>();

	private Process process;

	private final BConsole console;

	public BCompiler(IProject project, IFile classname, BConsole console)
			throws CoreException {
		super();
		if (classname == null)
			throw new IllegalArgumentException("classname is null");
		this.file = classname;
		if (project == null)
			throw new IllegalArgumentException("classname is null");
		this.project = project;
		this.console = console;
	}

	public void launch(String dir) throws Exception {
		file.deleteMarkers(BBuilder.MARKER_TYPE, false, IResource.DEPTH_ZERO);
		File brederCompilerFile = new File(new File(new BrederLanguageFile(),
				"bin"), "brederc" + SoUtil.getExtension());
		if (SoUtil.isUnix()) {
			new ProcessBuilder("chmod", "+x", brederCompilerFile.toString())
					.start().waitFor();
		}
		List<String> args = new ArrayList<String>();
		{
			args.add(brederCompilerFile.toString());
			{
				String classname = file.getLocation().toOSString();
				classname = classname.substring(project.getLocation()
						.toOSString().length()
						+ ("/" + BrederProjectConstant.SOURCE_FOLDER).length()
						+ 1);
				classname = classname.replace(File.separatorChar, '.');
				classname = classname.substring(0, classname.lastIndexOf('.'));
				args.add(classname);
			}
			IFolder binaryFolder = this.project
					.getFolder(BrederProjectConstant.BINARY_FOLDER);
			for (File file : FileUtil.list(binaryFolder.getLocation().toFile(),
					SoUtil.getLibraryExtensionWithoutDot())) {
				args.add("-l");
				args.add(file.getName().substring(
						0,
						file.getName().length()
								- SoUtil.getLibraryExtension().length()));
			}
			for (String arg : this.arguments) {
				args.add(arg);
			}
			args.add("-no_breder_home");
			args.add("-o");
			args.add(project.getLocation().toFile().toString()
					+ File.separatorChar + BrederProjectConstant.BINARY_FOLDER
					+ File.separatorChar + "binary.b");
			for (IFolder classpath : BProject.getSources(project)) {
				args.add("-cp");
				args.add(classpath.getLocation().toFile().toString());
			}
			args.add("-cp");
			args.add(new File(new BrederLanguageFile(), "src")
					.getAbsolutePath());
			for (IFolder barDir : BProject.getBars(project)) {
				args.add("-bar");
				args.add(barDir.getLocation().toFile().toString());
			}
			{
				String prop = this.project
						.getPersistentProperty(new QualifiedName("",
								ProjectPropertyPage.COMPILER_OPTIONS));
				if (prop != null && prop.trim().length() > 0) {
					String[] splits = prop.split(" ");
					for (String split : splits) {
						args.add(split);
					}
				}
			}
		}
		this.console.print(args);
		boolean complete;
		String out = "", err = "";
		try {
			this.process = Runtime.getRuntime().exec(
					args.toArray(new String[0]), null, new File(dir));
			new InterruptedThread(10000, null).start();
			try {
				process.waitFor();
			} catch (InterruptedException e) {
				console.println("[bvm-plugin] : native compiler was interrupted for timeout...");
				throw e;
			}
			out = new String(InputStreamUtil.getBytes(
					this.process.getInputStream(), 10000, new Runnable() {
						@Override
						public void run() {
							process.destroy();
						}
					})).trim();
			err = new String(InputStreamUtil.getBytes(
					this.process.getErrorStream(), 10000, new Runnable() {
						@Override
						public void run() {
							process.destroy();
						}
					})).trim();
			if (out.length() > 0) {
				this.printConsole("\tOutput Message : ");
				this.printConsole(out);
			}
			if (err.length() > 0) {
				this.printConsole("\tError Message : ");
				this.printConsole(err);
			}
			boolean inflag = this.readStream(new ByteArrayInputStream(out
					.getBytes()));
			boolean errflag = this.readStream(new ByteArrayInputStream(err
					.getBytes()));
			complete = (!inflag && !errflag) || (inflag && !errflag);
		} catch (Exception e) {
			// ILaunch launch = new Launch(null, ILaunchManager.RUN_MODE, null);
			// Process process = Runtime.getRuntime().exec(
			// args.toArray(new String[0]), null, new File(dir));
			// IProcess eprocess = new RuntimeProcess(launch, process,
			// "Breder Language", new HashMap());
			// IConsole console = new ProcessConsole(eprocess,
			// new ConsoleColorProvider());
			// ConsolePlugin.getDefault().getConsoleManager().addConsoles(
			// new IConsole[] { console });
			ConsolePlugin.log(e);
		}
		project.refreshLocal(IResource.DEPTH_INFINITE, null);
		if (false) {
			this.buildDescriber(args, dir);
		}
	}

	private void buildDescriber(List<String> args, String dir)
			throws IOException {
		args.add("-describer");
		this.process = Runtime.getRuntime().exec(args.toArray(new String[0]),
				null, new File(dir));
		BDescriberBinary binary = new BDescriberBinary(
				this.process.getInputStream());
		binary.start();
		BDescriberBinaryManager.getInstance().put(project.getName(), binary);
		new BUITask() {
			@Override
			public void updateUI() {
				ProjectView.getInstance().getViewer()
						.refresh(BRootBinary.getInstance());
			}
		}.start();
	}

	private void printConsole(String compileLine) throws IOException {
		this.console.println(compileLine);
	}

	private boolean readStream(InputStream input) throws IOException {
		String line = null;
		InputStreamReader isr = new InputStreamReader(input);
		BufferedReader br = new BufferedReader(isr);
		int count = 0;
		// try {
		try {
			while (true) {
				line = br.readLine();
				if (line == null)
					if (count == 0) {
						return false;
					} else {
						return true;
					}
				{
					int index = line.lastIndexOf(':');
					String classname, token;
					int lin, col;
					{
						String pre = line.substring(0, index).trim();
						pre = pre.substring(1, pre.length() - 1);
						String[] splits = pre.split(",");
						classname = splits[0].substring(1,
								splits[0].length() - 1);
						lin = new Integer(splits[1].substring(1,
								splits[1].length() - 1));
						col = new Integer(splits[2].substring(1,
								splits[2].length() - 1));
						token = splits[3].substring(1, splits[3].length() - 1);
					}
					String message = line.substring(index + 1).trim();
					addMarket(classname, message, IMarker.SEVERITY_ERROR, lin,
							col, token);
				}
			}
		} finally {
			br.close();
		}
		// } catch (Throwable e) {
		// StringBuilder sb = new StringBuilder();
		// for (StackTraceElement element : e.getStackTrace()) {
		// sb.append(element.toString() + "\n");
		// }
		// StringBuilder sb1 = new StringBuilder(line + "\n");
		// for (;;) {
		// try {
		// line = br.readLine();
		// } catch (IOException e1) {
		// line = null;
		// }
		// if (line == null) {
		// break;
		// }
		// sb1.append(line + "\n");
		// }
		// JOptionPane.showMessageDialog(null, String.format(
		// "%s : %s\n%s\n%s", e.getClass().getName(), e.getMessage(),
		// sb.toString(), sb1.toString()));
		// e.printStackTrace();
		// return false;
		// }
	}

	public void readDescriberStream(InputStream input) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		String begin = reader.readLine();
		if (begin == null || !begin.startsWith("[info] : number of source")) {
			return;
		}
	}

	public void addClasspath(String classpath) {
		this.classpaths.add(classpath);
	}

	public void addMarket(IFile file, String msg, int severity, int line,
			Integer char_start, Integer char_end) {
		try {
			IMarker marker = file.createMarker(BBuilder.MARKER_TYPE);
			marker.setAttribute(IMarker.MESSAGE, msg);
			marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_ERROR);
			marker.setAttribute(IMarker.LINE_NUMBER, line);
			marker.setAttribute(IMarker.LOCATION, line);
			if (char_start != null && char_end != null) {
				marker.setAttribute(IMarker.CHAR_START, char_start);
				marker.setAttribute(IMarker.CHAR_END, char_end);
			}
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

	public void addMarket(String classname, String msg, Integer severity,
			int line, int column, String word) {
		IFile file = FileUtil.getFile(project, classname);
		if (file != null) {
			int start = -1;
			try {
				int len = 0;
				LineNumberReader reader = new LineNumberReader(
						new InputStreamReader(file.getContents()));
				for (int n = 0; n < line - 1; n++) {
					String l = reader.readLine();
					len += l.length() + 2;
				}
				String l = reader.readLine();
				for (int n = 0; n < column; n++) {
					if (l.charAt(n) == '\t') {
						column -= 7;
					}
				}
				start = len + column - 1;
			} catch (Exception e) {
			}
			this.addMarket(file, msg, severity, line, start,
					start + word.length());
		} else {
			JOptionPane.showMessageDialog(null, String.format(
					"Error in the class '%s' in the line %d and column %d",
					classname, line, column));
		}
	}

	public void addArgument(String key, String value) {
		this.arguments.add(key);
		this.arguments.add(value);
	}

	public void addArgument(String flag) {
		this.arguments.add(flag);
	}

}
