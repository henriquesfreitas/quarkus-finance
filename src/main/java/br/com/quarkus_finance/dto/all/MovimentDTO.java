package br.com.quarkus_finance.dto.all;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.enterprise.context.RequestScoped;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.quarkus_finance.dto.common.LogDTO;

public class MovimentDTO implements Serializable{

	private static final long serialVersionUID = 8340800142458494523L;
	
	private Long idMoviment;
	
	@NotNull(message = "Value may not be blank")
	private BigDecimal value;
	
	@NotNull(message = "idAccount may not be blank")
	private Long idAccount;
	
	private LogDTO logDTO;
	
	public MovimentDTO(Long idMoviment,
			BigDecimal value,
			 Long idAccount) {
		super();
		this.idMoviment = idMoviment;
		this.value = value;
		this.idAccount = idAccount;
	}

	public Long getIdMoviment() {
		return idMoviment;
	}

	public void setIdMoviment(Long idMoviment) {
		this.idMoviment = idMoviment;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public Long getIdAccount() {
		return idAccount;
	}

	public void setIdAccount(Long idAccount) {
		this.idAccount = idAccount;
	}

	public LogDTO getLogDTO() {
		return logDTO;
	}

	public void setLogDTO(LogDTO logDTO) {
		this.logDTO = logDTO;
	}
	
}
