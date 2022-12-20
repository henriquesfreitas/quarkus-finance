package br.com.quarkus_finance.dao.moviment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Message;

import br.com.quarkus_finance.dao.all.DAOException;
//import br.com.quarkus_finance.common.JMSMessage;
//import br.com.quarkus_finance.common.JMSProducer;
import br.com.quarkus_finance.dao.common.ConvertDAODTO;
import br.com.quarkus_finance.dao.common.GenericDAO;
import br.com.quarkus_finance.dto.all.MovimentDTO;
import br.com.quarkus_finance.models.all.Account;
import br.com.quarkus_finance.models.all.Moviment;

@Transactional
@RequestScoped
//@Stateless
//Quarkus doesnt allow EJB, so you have to use transactional instead
public class MovimentDAO extends GenericDAO {

	private static final long serialVersionUID = -6321526905232236895L;
	
	@Inject
	private MovimentValidateDAO movimentValidateDAO;
	
	@Inject
	private MovimentTaxesBusiness movimentTaxesBusiness;
	
	@Inject
    @Channel("moviment-save-producer")
    Emitter<MovimentDTO> movimentEmitter;
	
	public void sendToJMSToSave(MovimentDTO movimentDTO) throws DAOException{
		movimentValidateDAO.validateSave(movimentDTO);
    	movimentEmitter.send(Message.of(movimentDTO)); 
	}
		
	public void save(Moviment moviment) throws DAOException{
		movimentTaxesBusiness.calculateTaxes(moviment);
		
		if(moviment.getIdMoviment() == null) {
			persist(moviment);
		}else {
			merge(moviment);
		}
	}
	
	public void update(MovimentDTO movimentDTO) throws Exception{
		sendToJMSToSave(movimentDTO);
	}

	public MovimentDTO find(Long idMoviment) throws Exception {
		Moviment moviment = entityManager.find(Moviment.class, idMoviment);
		movimentValidateDAO.validateFind(moviment);
		return convertFromEntity(moviment);
	}
	
//	public MovimentDTO balance(Long idAccount) throws Exception {
//		Moviment moviment = entityManager.find(Moviment.class, idMoviment);
//		movimentValidateDAO.validateFind(moviment);
//		return convertFromEntity(moviment);
//	}
	
	public List<MovimentDTO> listAll() {
		List<Moviment> listMoviment = listAll(Moviment.class);
		List<MovimentDTO> listMovimentDTO = Collections.synchronizedList(new ArrayList<MovimentDTO>());
		List<Thread> listThreadMoviment = new ArrayList<Thread>();
		for (Moviment moviment : listMoviment) {
			Thread threadMoviment = new Thread(new MovimentListAllRunnable(convertFromEntity(moviment), listMovimentDTO));
			threadMoviment.start();
			listThreadMoviment.add(threadMoviment);
			
//			without the threads:
//			try {
//				Thread.sleep(500);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			listMovimentDTO.add(convertFromEntity(moviment));
		}
		for (Thread threadMoviment : listThreadMoviment) {
			try {
				threadMoviment.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		return listMovimentDTO;
	}

	public Moviment convertFromDTO(MovimentDTO dto) {
		Moviment moviment = new Moviment();
		moviment.setIdMoviment(dto.getIdMoviment());
		moviment.setValue(dto.getValue());
		moviment.setAccount(entityManager.find(Account.class, dto.getIdAccount()));
		moviment.setLogEntity(ConvertDAODTO.convertLogDTOToLogEntity(dto.getLogDTO()));
		
		return moviment;
	}

	public MovimentDTO convertFromEntity(Moviment entity) {
		MovimentDTO movimentDTO = new MovimentDTO(
				entity.getIdMoviment(), entity.getValue(), entity.getAccount().getIdAccount()
		);
		movimentDTO.setLogDTO(ConvertDAODTO.convertLogEntityToLogDTO(entity.getLogEntity()));
		
		return movimentDTO;
	}

//	public Moviment getLastMoviment(AccountDTO accountDTO) {
//		return (Moviment) entityManager.createNamedQuery(Moviment.GET_LAST_MOVIMENT_BY_ID_ACCOUNT)
//		.setParameter("idAccount", accountDTO.getIdAccount()).getSingleResult();
//
//	}
	
}
