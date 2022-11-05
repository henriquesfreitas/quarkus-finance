package br.com.quarkus_finance.dto.all;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.quarkus_finance.dto.common.LogDTO;
import br.com.quarkus_finance.models.all.Moviment;

public class AccountDTO implements Serializable{

	private static final long serialVersionUID = 8340800142458494523L;
	
	private Long idAccount;
	
	@NotBlank(message = "Number may not be blank")
	private String number;
	
	@NotNull(message = "idPerson may not be blank")
	private Long idPerson;
	
	private LogDTO logDTO;

	public AccountDTO(Long idAccount, String number, Long idPerson) {
		this.idAccount = idAccount;
		this.number = number;
		this.idPerson = idPerson;
	}
	
	public LogDTO getLogDTO() {
		return logDTO;
	}

	public void setLogDTO(LogDTO logDTO) {
		this.logDTO = logDTO;
	}

	public Long getIdAccount() {
		return idAccount;
	}

	public void setIdAccount(Long idAccount) {
		this.idAccount = idAccount;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Long getIdPerson() {
		return idPerson;
	}

	public void setIdPerson(Long idPerson) {
		this.idPerson = idPerson;
	}

}
