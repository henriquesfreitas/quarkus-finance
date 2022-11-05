package br.com.quarkus_finance.models.common;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@MappedSuperclass
//classe abstrata cria um objeto pai que não pode ser instanciado diretamente
//apenas pela a classe filha
public abstract class GenericEntity implements Serializable {

	private static final long serialVersionUID = -742732293473227633L;
	
	private LogEntity logEntity = new LogEntity();
	
	@PrePersist
	protected void onCreate() {
		logEntity.setCreation(LocalDateTime.now());
	}

	@PreUpdate
	protected void onUpdate() {
		logEntity.setChange(LocalDateTime.now());
	}

	public LogEntity getLogEntity() {
		return logEntity;
	}

	public void setLogEntity(LogEntity logEntity) {
		this.logEntity = logEntity;
	}
	
}

