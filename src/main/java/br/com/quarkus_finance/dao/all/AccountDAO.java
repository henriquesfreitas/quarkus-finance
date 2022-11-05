package br.com.quarkus_finance.dao.all;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.quarkus_finance.dao.common.ConvertDAODTO;
import br.com.quarkus_finance.dao.common.GenericDAO;
import br.com.quarkus_finance.dto.all.AccountDTO;
import br.com.quarkus_finance.dto.all.MovimentDTO;
import br.com.quarkus_finance.models.all.Account;
import br.com.quarkus_finance.models.all.Person;

@Transactional
@RequestScoped
//@Stateless
//Quarkus doesnt allow EJB, so you have to use transactional instead
public class AccountDAO extends GenericDAO {

	private static final long serialVersionUID = -6321526905232236895L;
	
	@Inject
	private AccountValidateDAO accountValidateDAO;
	
	public void save(AccountDTO accountDTO) throws DAOException{
		Account account = convertFromDTO(accountDTO);
		accountValidateDAO.validateSave(account);
		
		if(account.getIdAccount() == null) {
			persist(account);
		}else {
			merge(account);
		}
	}
	
//	public void maintenanceTaxes() {
//		List<AccountDTO> listAccountDTO = listAll();
//		listAccountDTO.forEach(accountDTO -> {
//			accountTaxesBusiness.maintenanceTaxes(accountDTO);
//		}) ;
//	}
	
	public void update(AccountDTO accountDTO) throws Exception{
		accountValidateDAO.validateIdUpdate(accountDTO.getIdAccount());
		save(accountDTO);
	}

	public AccountDTO find(Long idAccount) throws Exception {
		Account account = entityManager.find(Account.class, idAccount);
		accountValidateDAO.validateFind(account);
		return convertFromEntity(account);
	}
	
	public List<AccountDTO> listAll() {
		List<Account> listAccount = listAll(Account.class);
		List<AccountDTO> listAccountDTO = new ArrayList<AccountDTO>();
		for (Account account : listAccount) {
			listAccountDTO.add(convertFromEntity(account));
		}
		
		return listAccountDTO;
	}

	public Account convertFromDTO(AccountDTO dto) {
		Account account = new Account();
		account.setIdAccount(dto.getIdAccount());
		account.setNumber(dto.getNumber());
		account.setPerson(entityManager.find(Person.class, dto.getIdPerson()));
		account.setLogEntity(ConvertDAODTO.convertLogDTOToLogEntity(dto.getLogDTO()));
		
		return account;
	}

	public AccountDTO convertFromEntity(Account entity) {
		AccountDTO accountDTO = new AccountDTO(
				entity.getIdAccount(), entity.getNumber(), entity.getPerson().getIdPerson()
		);
		accountDTO.setLogDTO(ConvertDAODTO.convertLogEntityToLogDTO(entity.getLogEntity()));
		
		return accountDTO;
	}
	
}
