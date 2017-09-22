package breder.risk.model;

public class AbstractRisk implements IRisk {

	private static final long serialVersionUID = 1L;

	private String name;

	private RiskEnum category;

	private String describer;

	private String solution;
	
	public String getName() {
		return name;
	}

	public RiskEnum getCategory() {
		return category;
	}

	public String getDescriber() {
		return describer;
	}

	public String getSolution() {
		return solution;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCategory(RiskEnum category) {
		this.category = category;
	}

	public void setDescriber(String describer) {
		this.describer = describer;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}

}
