package breder.risk.model;

import java.io.Serializable;

public interface IRisk extends Serializable {

	public String getName();

	public RiskEnum getCategory();

	public String getDescriber();

	public String getSolution();

	public void setName(String name);

	public void setCategory(RiskEnum category);

	public void setDescriber(String describer);

	public void setSolution(String solution);

}
