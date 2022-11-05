package br.com.quarkus_finance.dao.moviment;

import java.math.BigDecimal;

import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;

import br.com.quarkus_finance.models.all.Moviment;
import br.com.quarkus_finance.models.all.TaxEnum;
@Transactional
@RequestScoped
public class MovimentTaxesProBusiness extends MovimentTaxes{

	public MovimentTaxesProBusiness() {
		setTax(TaxEnum.PRO.getTax());
	}

	@Override
	public void calculate(Moviment moviment) {
		System.out.println("do something else pro");
	}

}
