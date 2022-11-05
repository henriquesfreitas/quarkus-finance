package br.com.quarkus_finance.dao.all;

import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;

import br.com.quarkus_finance.dao.common.GenericDAO;
import br.com.quarkus_finance.dao.common.GenericValidate;
import br.com.quarkus_finance.dto.all.AccountDTO;
import br.com.quarkus_finance.models.all.Account;

@Transactional
@RequestScoped
public class AccountValidateDAO extends GenericValidate{
	
	private static final long serialVersionUID = 6243796835339404722L;

	public void validateSave(Account account) throws DAOException {
		Boolean accountNumberDuplicated = (Boolean) entityManager.createNamedQuery(Account.VERITY_IF_ACCOUNT_NUMBER_ALREADY_EXISTS)
			.setParameter("number", account.getNumber()).getSingleResult();
		
		DAOException DAOException = new DAOException();
		
		if(accountNumberDuplicated) {
			DAOException.getMessages().add("Account Number already exists");
		}
		
		if(account.getPerson() == null) {
			DAOException.getMessages().add("idPerson not found");
		}
		
		if(!DAOException.getMessages().isEmpty()) {
			throw DAOException;
		}
	}

}
