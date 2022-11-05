package br.com.quarkus_finance.dao.moviment;

import java.math.BigDecimal;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.quarkus_finance.models.all.Moviment;

@Transactional
@RequestScoped
public class MovimentTaxesBusiness {
	
	@Inject
	private MovimentTaxesStandardBusiness movimentTaxesStandardBusiness;
	
	@Inject
	private MovimentTaxesProBusiness movimentTaxesProBusiness;
	
	@Inject
	private MovimentTaxesProMaxBusiness movimentTaxesProMaxBusiness;

	public void calculateTaxes(Moviment moviment) {
		movimentTaxesStandardBusiness.calculate(moviment, new BigDecimal(0));
		movimentTaxesProBusiness.calculate(moviment, movimentTaxesStandardBusiness.getTax().getValueLimit());
		movimentTaxesProMaxBusiness.calculate(moviment, movimentTaxesProBusiness.getTax().getValueLimit());
	}

}
