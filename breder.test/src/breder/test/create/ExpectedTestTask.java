package breder.test.create;

public class ExpectedTestTask extends CompileTestTask {

	public ExpectedTestTask(CreateFrame frame) {
		super(frame);
	}

	@Override
	public void updateUI() {
		super.updateUI();
		this.frame.copyToExpected();
	}

}
