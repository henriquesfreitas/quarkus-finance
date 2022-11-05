package br.com.quarkus_finance.models.all;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Tax {
	
	private String name;
	
	private BigDecimal value;
	
	private BigDecimal valueLimit;
	
	public BigDecimal getValueLimit() {
		return valueLimit;
	}

	public void setValueLimit(BigDecimal valueLimit) {
		this.valueLimit = valueLimit;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

}
