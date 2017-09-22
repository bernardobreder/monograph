package breder.test.model;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import breder.test.BrederTestConfigurator;
import breder.util.util.AbstractModel;

public class TestModel extends AbstractModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final TestModel instance = new TestModel();

	private List<ITest> tests;

	private TestModel() {
		super(BrederTestConfigurator.getInstance());
		if (this.tests == null) {
			this.tests = new ArrayList<ITest>();
		}
	}

	public List<ITest> getTests() {
		return tests;
	}

	public static TestModel getInstance() {
		return instance;
	}

	public void save() {
		this.sort();
		super.save();
	}

	public void load(File file) throws Exception {
		super.load(file);
		this.sort();
	}

	public void addTest(ITest test) {
		formatTest(test);
		this.tests.add(test);
		this.sort();
		this.save();
	}

	public void removeTest(ITest test) {
		this.tests.remove(test);
		this.save();
	}

	public static void formatTest(ITest test) {
		for (Source source : test.getSources()) {
			source.setCode(formatCode(source.getCode()));
		}
	}

	public static String formatCode(String code) {
		StringBuilder sb = new StringBuilder();
		String[] lines = code.split("\n");
		int tab = 0;
		for (String line : lines) {
			line = line.trim();
			if (line.length() == 0) {
				continue;
			}
			if (line.startsWith("}")) {
				tab--;
			}
			for (int n = 0; n < tab; n++) {
				line = '\t' + line;
			}
			if (line.endsWith("{")) {
				tab++;
			}
			sb.append(line + "\n");
		}
		String result = sb.toString().trim();
		return result;
	}

	public void sort() {
		Collections.sort(this.tests, new Comparator<ITest>() {
			@Override
			public int compare(ITest o1, ITest o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});

	}

}
