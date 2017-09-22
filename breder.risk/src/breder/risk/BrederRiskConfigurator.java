package breder.risk;

import breder.util.util.GenericConfigurator;

public class BrederRiskConfigurator extends GenericConfigurator {

	private static final BrederRiskConfigurator instance = new BrederRiskConfigurator();

	private BrederRiskConfigurator() {
		super("breder.risk", "app");
	}

	public static BrederRiskConfigurator getInstance() {
		return instance;
	}

}
