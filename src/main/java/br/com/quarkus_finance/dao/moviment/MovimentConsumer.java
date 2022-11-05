package br.com.quarkus_finance.dao.moviment;

import java.util.concurrent.CompletionStage;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;

import br.com.quarkus_finance.dto.all.MovimentDTO;
import br.com.quarkus_finance.models.all.Moviment;
import io.smallrye.common.annotation.Blocking;
import io.vertx.core.json.JsonObject;

@ApplicationScoped
public class MovimentConsumer {

	@Inject
	private MovimentDAO movimentDAO;
	
	
	@Incoming("moviment-save-producer")
	@Blocking
	public CompletionStage<Void> consume(Message<MovimentDTO> message) {
		MovimentDTO movimentDTO = message.getPayload();
		try {
			Moviment moviment = movimentDAO.convertFromDTO(movimentDTO);
			movimentDAO.save(moviment);
			return message.ack();
		} catch (Exception e) {
			e.printStackTrace();
            return message.nack(new RuntimeException("Erro ao processar mensagem"));
		}
		
	}
	
}
