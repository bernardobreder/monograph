package breder.plugin.properties;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.PropertyPage;

import breder.plugin.BrederProjectConstant;
import breder.plugin.element.breder.BProject;
import breder.plugin.monitor.BProgressMonitor;
import breder.plugin.swt.BCheckBox;
import breder.plugin.swt.BGridBagLayout;
import breder.plugin.swt.BLabel;
import breder.plugin.swt.BPanel;
import breder.plugin.swt.BSWT;
import breder.plugin.swt.BText;
import breder.plugin.swt.GBC;
import breder.plugin.util.SoUtil;

public class ProjectPropertyPage extends PropertyPage {

	public static final String LINKERS = "LINKERS";

	public static final String LINKER_PATHS = "LINKER_PATHS";

	public static final String COMPILER_OPTIONS = "COMPILER_OPTIONS";

	public static final String RELEASE_OPTION = "RELEASE";

	private IFolder[] folders;

	private BText[] texts;

	private Text option;

	private BCheckBox releaseCombo;

	public ProjectPropertyPage() {
		super();
	}

	protected Control createContents(Composite parent) {
		BText option;
		BSWT.setRoot(parent);
		BGridBagLayout glayout = new BGridBagLayout();
		BPanel panel = new BPanel(glayout);
		int m = 1;
		panel.addChild(new BLabel("Option Flags : "), new GBC(m, 1));
		panel.addChild(option = new BText(), new GBC(m++, 2).horizontal());
		try {
			if (this.getElement() instanceof IProject) {
				IProject project = (IProject) this.getElement();
				this.folders = BProject.getSourceNativeFolders(project);
				this.texts = new BText[this.folders.length];
				for (int n = 0; n < folders.length; n++) {
					BText text;
					IFolder folder = folders[n];
					panel.addChild(new BLabel(folder.getName()
							+ SoUtil.getLibraryExtension() + " Flags : "),
							new GBC(m, 1));
					panel.addChild(text = new BText(), new GBC(m++, 2)
							.horizontal());
					this.texts[n] = text;
				}
			}
		} catch (CoreException e) {
		}
		panel.addChild(new BLabel("Release : "), new GBC(m, 1));
		panel.addChild(releaseCombo = new BCheckBox(), new GBC(m++, 2)
				.horizontal());
		Control control = panel.build();
		{
			this.option = option.getComposite();
			try {
				{
					String value = this.getElement().getPersistentProperty(
							new QualifiedName("", COMPILER_OPTIONS));
					this.option.setText(value == null ? "" : value);
				}
				for (int n = 0; n < this.folders.length; n++) {
					String value = this.getElement().getPersistentProperty(
							new QualifiedName("", this.folders[n].getName()));
					this.texts[n].getComposite().setText(
							value == null ? "" : value);
				}
				{
					String value = this.getElement().getPersistentProperty(
							new QualifiedName("", RELEASE_OPTION));
					this.releaseCombo.getComposite().setSelection(
							value == null ? true : new Boolean(value));
				}
			} catch (CoreException e) {
			}
		}
		return control;
	}

	public boolean performOk() {
		if (this.getElement() instanceof IProject) {
			try {
				IProject project = (IProject) this.getElement();
				this.getElement().setPersistentProperty(
						new QualifiedName("", COMPILER_OPTIONS),
						this.option.getText());
				for (int n = 0; n < this.folders.length; n++) {
					this.getElement().setPersistentProperty(
							new QualifiedName("", this.folders[n].getName()),
							this.texts[n].getComposite().getText());
				}
				{
					this.getElement().setPersistentProperty(
							new QualifiedName("", RELEASE_OPTION),
							((Boolean) this.releaseCombo.getComposite()
									.getSelection()).toString());
				}
				project.getFolder(BrederProjectConstant.BINARY_FOLDER).delete(
						true, BProgressMonitor.DEFAULT);
				project.build(IncrementalProjectBuilder.FULL_BUILD,
						BProgressMonitor.DEFAULT);
			} catch (CoreException e) {
				return false;
			}
		}
		return true;
	}

	protected void performDefaults() {
		try {
			this.getElement().setPersistentProperty(
					new QualifiedName("", COMPILER_OPTIONS), "");
			for (int n = 0; n < this.folders.length; n++) {
				this.getElement().setPersistentProperty(
						new QualifiedName("", this.folders[n].getName()), "");
			}
			{
				this.getElement().setPersistentProperty(
						new QualifiedName("", RELEASE_OPTION), "true");
			}
		} catch (CoreException e) {
		}
	}

	@Override
	public IResource getElement() {
		return (IResource) super.getElement();
	}

}