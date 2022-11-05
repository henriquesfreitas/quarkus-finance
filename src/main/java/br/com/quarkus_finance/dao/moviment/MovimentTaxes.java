package br.com.quarkus_finance.dao.moviment;


import java.math.BigDecimal;

import br.com.quarkus_finance.models.all.Moviment;
import br.com.quarkus_finance.models.all.Tax;

public abstract class MovimentTaxes {
	
	private Tax tax;
	
	public void calculate(Moviment moviment, BigDecimal taxBelow) {
		if(!applyTax(moviment, taxBelow)) {
			System.out.println("not valid");
			return;
		}
		
		moviment.setValue(moviment.getValue().multiply(getTax().getValue()));
		calculate(moviment);
	}
	
	public abstract void calculate(Moviment moviment);
	
	public boolean applyTax(Moviment moviment, BigDecimal taxBelow) {
		return moviment.getValue().compareTo(getTax().getValueLimit()) <= 0
				&& moviment.getValue().compareTo(taxBelow) == 1;
	}

	public Tax getTax() {
		return tax;
	}

	public void setTax(Tax tax) {
		this.tax = tax;
	}
	
}
