
package breder.installer.lang.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import breder.installer.lang.task.DownloadBrederEclipsePluginTask;
import breder.installer.lang.task.DownloadBrederEclipseTask;
import breder.installer.lang.task.DownloadBrederLangTask;
import breder.installer.lang.task.FinishTask;
import breder.installer.lang.task.InstallBrederLangTask;
import breder.installer.logic.IInstallTask;
import breder.installer.logic.StepFrame;
import breder.util.io.BrederLanguageFile;
import breder.util.so.SoUtil;
import breder.util.swing.layout.VerticalLayout;

public class WelcomeStepFrame extends StepFrame {

	private JCheckBox eclipse;

	private JCheckBox plugin;

	private JCheckBox arch;

	public WelcomeStepFrame() {
		this.setLayout(new BorderLayout());
		this.add(this.buildHeader(), BorderLayout.NORTH);
		this.add(this.buildCenter(), BorderLayout.CENTER);
	}

	private Component buildCenter() {
		JPanel panel = new JPanel(new VerticalLayout());
		panel.add(this.arch = new JCheckBox("Download Breder Language 64-Bit - " + new BrederLanguageFile().toString(),
				SoUtil.is64Bit()));
		panel.add(this.plugin = new JCheckBox("Download Eclipse Plugin - "
				+ new BrederLanguageFile("beclipse", "plugins").toString(), false));
		panel.setBackground(Color.WHITE);
		return panel;
	}

	private Component buildHeader() {
		List<String> list = new ArrayList<String>();
		list.add("Welcome to the Breder Languagem Installer.");
		list.add("");
		list.add("The installer will download the Breder Language at runtime and will install in you machine.");
		list.add("In addition, the installer will download the Eclipse IDE prepared to work with the Breder Language.");
		list.add("");
		list.add("The IDE Eclipse can found the original link in http://www.eclipse.org");
		list.add("When the installer finishes, the Operating System must be rebooted.");
		list.add("It because, same environment variable will be set for the Breder Language work fine.");
		StringBuilder sb = new StringBuilder();
		for (int n = 0; n < list.size(); n++) {
			sb.append(list.get(n));
			if (n != list.size() - 1) {
				sb.append("\n");
			}
		}
		JTextArea area = new JTextArea(sb.toString());
		area.setEditable(false);
		area.setWrapStyleWord(true);
		area.setBackground(Color.WHITE);
		return area;
	}

	@Override
	public Collection<? extends IInstallTask> buildTasks() {
		List<IInstallTask> list = new ArrayList<IInstallTask>();
		boolean arch64 = this.arch.isSelected();
		list.add(new DownloadBrederLangTask(arch64));
		list.add(new InstallBrederLangTask());
		if (this.eclipse.isSelected()) {
			list.add(new DownloadBrederEclipseTask());
		}
		if (this.plugin.isSelected()) {
			list.add(new DownloadBrederEclipsePluginTask());
		}
		list.add(new FinishTask());
		return list;
	}
}
