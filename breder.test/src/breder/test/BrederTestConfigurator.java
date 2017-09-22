package breder.test;

import breder.util.util.GenericConfigurator;

public class BrederTestConfigurator extends GenericConfigurator {

	private static final BrederTestConfigurator instance = new BrederTestConfigurator();

	private BrederTestConfigurator() {
		super("breder.test", "app");
	}

	public static BrederTestConfigurator getInstance() {
		return instance;
	}

}
