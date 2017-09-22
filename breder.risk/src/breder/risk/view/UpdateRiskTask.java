package breder.risk.view;

import breder.risk.main.MainFrame;
import breder.risk.model.IRisk;
import breder.risk.model.ModelLocator;
import breder.risk.model.RiskEnum;
import breder.util.task.LocalTask;

public class UpdateRiskTask extends LocalTask {

	private ViewFrame frame;

	private String name;

	private RiskEnum category;

	private String describer;

	private String solution;

	public UpdateRiskTask(ViewFrame frame) {
		this.frame = frame;
	}

	@Override
	public boolean preAction() {
		this.name = this.frame.getPanel().getNameField().getText();
		this.category = RiskEnum.getEnum(this.frame.getPanel().getCategory()
				.getSelectedItem().toString());
		this.describer = this.frame.getPanel().getDescriber().getText();
		this.solution = this.frame.getPanel().getSolution().getText();
		return this.category != null;
	}

	@Override
	public void updateUI() {
		IRisk risk = this.frame.getRisk();
		risk.setName(name);
		risk.setCategory(category);
		risk.setDescriber(describer);
		risk.setSolution(solution);
		MainFrame.getInstance().refresh();
		ModelLocator.getInstance().save();
		frame.close();
	}
}
