
package breder.test.create;

import javax.swing.JOptionPane;

import breder.test.main.MainFrame;
import breder.test.main.task.CompileTask;
import breder.test.model.ITest;
import breder.test.model.Source;
import breder.test.model.TestModel;
import breder.util.task.RemoteTask;

public class ReplaceTestTask extends RemoteTask {

	private CreateFrame frame;

	private ITest test;

	public ReplaceTestTask(CreateFrame frame) {
		this.frame = frame;
	}

	@Override
	public boolean preAction() {
		this.test = frame.getBuildTest();
		if (this.test == null) {
			return false;
		}
		if (this.test.getName().length() == 0) {
			JOptionPane.showMessageDialog(frame, "name is empty");
			return false;
		}
		if (MainFrame.getInstance().getTest() == null) {
			return false;
		}
		if (JOptionPane.showConfirmDialog(frame, "Do you want to replace the Test ?", "Replace a Test",
				JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
			return false;
		}
		return true;
	}

	@Override
	public void perform() throws Throwable {
	}

	@Override
	public void updateUI() {
		ITest oldTest = MainFrame.getInstance().getTest();
		{
			oldTest.setExpected(this.test.getExpected());
			oldTest.setMainClass(this.test.getMainClass());
			oldTest.setName(this.test.getName());
			oldTest.setOk(null);
			oldTest.setSources(this.test.getSources());
			for (Source source : this.test.getSources()) {
				source.setCode(TestModel.formatCode(source.getCode()));
			}
			TestModel.getInstance().save();
			new CompileTask(oldTest).start();
		}
		MainFrame.getInstance().openTest(oldTest);
		MainFrame.getInstance().refresh();
		frame.close();
	}
}
