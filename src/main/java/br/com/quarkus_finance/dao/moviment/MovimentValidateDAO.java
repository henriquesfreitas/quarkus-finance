package br.com.quarkus_finance.dao.moviment;

import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;

import br.com.quarkus_finance.dao.all.DAOException;
import br.com.quarkus_finance.dao.common.GenericDAO;
import br.com.quarkus_finance.dao.common.GenericValidate;
import br.com.quarkus_finance.dto.all.AccountDTO;
import br.com.quarkus_finance.dto.all.MovimentDTO;
import br.com.quarkus_finance.models.all.Account;
import br.com.quarkus_finance.models.all.Moviment;

@Transactional
@RequestScoped
public class MovimentValidateDAO extends GenericValidate{
	
	private static final long serialVersionUID = 6243796835339404722L;
	
	public void validateSave(MovimentDTO movimentDTO) throws DAOException {
		DAOException DAOException = new DAOException();
		
		if(movimentDTO.getIdAccount() == null) {
			DAOException.getMessages().add("idAccount not found");
		}
		
		if(!DAOException.getMessages().isEmpty()) {
			throw DAOException;
		}
	}

}
