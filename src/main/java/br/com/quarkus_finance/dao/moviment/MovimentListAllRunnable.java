package br.com.quarkus_finance.dao.moviment;

import java.util.List;

import br.com.quarkus_finance.dto.all.MovimentDTO;
import br.com.quarkus_finance.models.all.Moviment;

public class MovimentListAllRunnable implements Runnable{
	
	MovimentDTO movimentDTO;
	List<MovimentDTO> listMovimentDTO;
	
	public MovimentListAllRunnable(MovimentDTO movimentDTO, List<MovimentDTO> listMovimentDTO) {
		this.movimentDTO = movimentDTO;
		this.listMovimentDTO = listMovimentDTO;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		listMovimentDTO.add(movimentDTO);
		System.out.println("meu deus");
	}

}
