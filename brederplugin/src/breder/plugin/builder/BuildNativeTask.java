package breder.plugin.builder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.QualifiedName;

import breder.plugin.BActivator;
import breder.plugin.BrederProjectConstant;
import breder.plugin.InputStreamUtil;
import breder.plugin.console.BConsole;
import breder.plugin.element.breder.BProject;
import breder.plugin.monitor.BProgressMonitor;
import breder.plugin.preferences.PreferenceConstants;
import breder.plugin.properties.ProjectPropertyPage;
import breder.plugin.util.BTask;
import breder.plugin.util.BrederLanguageFile;
import breder.plugin.util.FileUtil;
import breder.plugin.util.InterruptedThread;
import breder.plugin.util.SoUtil;

public class BuildNativeTask extends BTask {

	private final IProject project;

	private BConsole console;

	private static String gccPath;

	private static final Map<IProject, Long> lastModifieds = new HashMap<IProject, Long>();

	public BuildNativeTask(IProject project, BConsole console) {
		super();
		this.project = project;
		this.console = console;
	}

	@Override
	public void action() throws Exception {
		IFolder sourceNativeFolder = project
				.getFolder(BrederProjectConstant.SOURCE_NATIVE_FOLDER);
		if (!sourceNativeFolder.exists()) {
			sourceNativeFolder.create(true, true, BProgressMonitor.DEFAULT);
		}
		final IFolder binaryFolder = project
				.getFolder(BrederProjectConstant.BINARY_FOLDER);
		IFolder[] sourceNativeFolders = BProject
				.getSourceNativeFolders(project);
		Thread[] threads = new Thread[sourceNativeFolders.length];
		for (int n = 0; n < threads.length; n++) {
			final IFolder libraryFolder = sourceNativeFolders[n];
			threads[n] = new Thread() {
				@Override
				public void run() {
					File outputFile = binaryFolder
							.getFile(
									libraryFolder.getName()
											+ SoUtil.getLibraryExtension())
							.getLocation().toFile();
					File[] srcFiles = FileUtil.list(libraryFolder.getLocation()
							.toFile(), "c");
					Long timer = 0l;
					{
						for (File file : srcFiles) {
							timer = Math.max(timer, file.lastModified());
						}
						Long lastTimer = lastModifieds.get(project);
						if (lastTimer != null
								&& timer.compareTo(lastTimer) == 0
								&& outputFile.exists()) {
							return;
						}
						outputFile.delete();
					}
					Boolean release = true;
					try {
						release = new Boolean(
								project.getPersistentProperty(new QualifiedName(
										"", ProjectPropertyPage.RELEASE_OPTION)));
					} catch (CoreException e1) {
					}
					release &= BActivator.getDefault().getPreferenceStore()
							.getBoolean(PreferenceConstants.IS_DEBUG);
					try {

						String flags = project
								.getPersistentProperty(new QualifiedName("",
										libraryFolder.getName()));
						if (flags == null) {
							flags = "";
						}
						List<String> cmds = new ArrayList<String>();
						cmds.add(getGccPath());
						cmds.add("-shared");
						cmds.add("-o");
						cmds.add(outputFile.toString());
						if (release) {
							cmds.add("-O3");
						} else {
							cmds.add("-g");
						}
						if (false) {
							String arch = BActivator.getDefault()
									.getPreferenceStore()
									.getString(PreferenceConstants.ARCH);
							if ("-m32".equals(arch)) {
								cmds.add("-m32");
							} else if ("-m64".equals(arch)) {
								cmds.add("-m64");
							}
						}
						cmds.add("-w");
						cmds.add("-I");
						cmds.add(new BrederLanguageFile("inc").toString());
						{
							Set<File> paths = new HashSet<File>();
							File[] incFiles = FileUtil.list(libraryFolder
									.getLocation().toFile(), "h");
							for (File incFile : incFiles) {
								paths.add(incFile.getParentFile());
							}
							for (File file : paths) {
								cmds.add("-I");
								cmds.add(file.toString());
							}
						}
						{
							File[] files = FileUtil.list(libraryFolder
									.getLocation().toFile(), "c");
							if (files.length == 0) {
								return;
							}
							for (File file : files) {
								cmds.add(file.toString());
							}
						}
						if (SoUtil.isLinux()) {
							cmds.add("-ldl");
							cmds.add("-lm");
							cmds.add("-fPIC");
						}
						{
							cmds.add("-lbreder");
							cmds.add("-L"
									+ new BrederLanguageFile("lib").toString());
						}
						cmds.addAll(Arrays.asList(flags.split(" ")));
						console.print(cmds);
						outputFile.getParentFile().mkdirs();
						final Process process = new ProcessBuilder(cmds)
								.start();
						new InterruptedThread(10000, null).start();
						try {
							process.waitFor();
						} catch (InterruptedException e) {
							console.println("[bvm-plugin] : native compiler was interrupted for timeout...");
							throw new InterruptedException();
						}
						String out = new String(InputStreamUtil.getBytes(
								process.getInputStream(), 10000,
								new Runnable() {
									@Override
									public void run() {
										process.destroy();
									}
								})).trim();
						String err = new String(InputStreamUtil.getBytes(
								process.getErrorStream(), 10000,
								new Runnable() {
									@Override
									public void run() {
										process.destroy();
									}
								})).trim();
						if (out.length() > 0) {
							console.println("\tOutput Message : ");
							console.println(out);
						}
						if (err.length() > 0) {
							console.println("\tError Message : ");
							console.println(err);
						}
						console.println("");
						if (outputFile.exists()) {
							lastModifieds.put(project, timer);
						}
					} catch (Exception e) {
					}
				}
			};
			threads[n].start();
		}
		for (int n = 0; n < threads.length; n++) {
			threads[n].join();
			binaryFolder.refreshLocal(IResource.DEPTH_INFINITE,
					BProgressMonitor.DEFAULT);
		}
	}

	public synchronized String getGccPath() {
		if (gccPath == null) {
			try {
				gccPath = "gcc";
				new ProcessBuilder(gccPath).start();
			} catch (IOException e1) {
				if (SoUtil.isUnix()) {
					try {
						gccPath = "/usr/bin/gcc";
						new ProcessBuilder(gccPath).start();
					} catch (IOException e2) {
						try {
							gccPath = "/usr/local/bin/gcc";
							new ProcessBuilder(gccPath).start();
						} catch (IOException e3) {
							gccPath = "gcc";
						}
					}
				} else {
					try {
						gccPath = "c:\\mingw\\bin\\gcc";
						new ProcessBuilder(gccPath).start();
					} catch (IOException e2) {

					}
				}
			}
		}
		return gccPath;
	}

	@Override
	public synchronized void start() {
		super.start();
	}

}
