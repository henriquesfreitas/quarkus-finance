package br.com.quarkus_finance.models.all;

import java.math.BigDecimal;

public class TaxBuilder {
	
	private Tax tax;
	
	public TaxBuilder() {
		this.tax = new Tax();
	}
	
	public static TaxBuilder builder() {
		return new TaxBuilder();
	}
	
	public TaxBuilder addName(String name) {
		this.tax.setName(name);
		return this;
	}
	
	public TaxBuilder addValue(BigDecimal value) {
		this.tax.setValue(value);
		return this;
	}
	
	public TaxBuilder addValueLimit(BigDecimal value) {
		this.tax.setValueLimit(value);
		return this;
	}
	
	public Tax build() {
		return this.tax;
	}

}
