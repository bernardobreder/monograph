package breder.risk.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import breder.risk.BrederRiskConfigurator;
import breder.util.util.AbstractModel;

public class ModelLocator extends AbstractModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final ModelLocator instance = new ModelLocator();

	private List<IRisk> risks;

	private ModelLocator() {
		super(BrederRiskConfigurator.getInstance());
		if (this.risks == null) {
			this.risks = new ArrayList<IRisk>();
		}
	}

	public IRisk[] getRisks() {
		return risks.toArray(new IRisk[risks.size()]);
	}

	public static ModelLocator getInstance() {
		return instance;
	}

	public void addRisk(IRisk test) {
		this.risks.add(test);
		Collections.sort(this.risks, new Comparator<IRisk>() {
			@Override
			public int compare(IRisk o1, IRisk o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		this.save();
	}

	public void removeRisk(IRisk test) {
		this.risks.remove(test);
		this.save();
	}

	public void sort() {
		Collections.sort(this.risks, new Comparator<IRisk>() {
			@Override
			public int compare(IRisk o1, IRisk o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});

	}

	public void removeRisks(IRisk[] risks) {
		for (IRisk risk : risks) {
			this.risks.remove(risk);
		}
		this.save();
	}

}
