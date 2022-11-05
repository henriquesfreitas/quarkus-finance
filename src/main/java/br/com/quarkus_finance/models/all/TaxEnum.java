package br.com.quarkus_finance.models.all;

import java.math.BigDecimal;

public enum TaxEnum {
	
	STANDARD(TaxBuilder.builder().addName("Standard").addValue(BigDecimal.valueOf(0.90)).addValueLimit(BigDecimal.valueOf(100)).build()),
	PRO(TaxBuilder.builder().addName("Pro").addValue(BigDecimal.valueOf(0.95)).addValueLimit(BigDecimal.valueOf(500)).build()),
	PRO_MAX(TaxBuilder.builder().addName("Pro Max").addValue(BigDecimal.valueOf(0.98)).addValueLimit(BigDecimal.valueOf(1000)).build());
	
	private Tax tax;
	
	TaxEnum(Tax tax) {
		this.tax = tax;
	}
	
	public Tax getTax() {
		return tax;
	}
	
}
