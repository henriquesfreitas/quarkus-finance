package br.com.quarkus_finance.dao.common;

import br.com.quarkus_finance.dto.common.LogDTO;
import br.com.quarkus_finance.models.common.LogEntity;

public class ConvertDAODTO {
	
	public static LogDTO convertLogEntityToLogDTO(LogEntity logEntity) {
		return new LogDTO(
				logEntity.getCreation(), logEntity.getIdCreator(), logEntity.getChange(), logEntity.getIdChanger(),
				logEntity.isDisabled(), logEntity.getIdDeactivator()
		);
	}
	
	public static LogEntity convertLogDTOToLogEntity(LogDTO logDTO) {
		if(logDTO == null)
			return new LogEntity();
		
		LogEntity logEntity = new LogEntity();
		logEntity.setCreation(logDTO.getCreation());
		logEntity.setIdCreator(logDTO.getIdCreator());
		logEntity.setChange(logDTO.getChange());
		logEntity.setIdChanger(logDTO.getIdChanger());
		logEntity.setDisabled(logDTO.isDisabled());
		logEntity.setIdDeactivator(logDTO.getIdDeactivator());
		
		return logEntity;
	}

}
