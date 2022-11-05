package br.com.quarkus_finance.dto.all;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import br.com.quarkus_finance.dto.common.LogDTO;

public class PersonDTO implements Serializable{

	private static final long serialVersionUID = 8340800142458494523L;
	
	private Long idPerson;
	
	@NotBlank(message = "Name may not be blank")
	private String name;
	
	@NotBlank(message = "CPF may not be blank")
	private String cpf;
	
	private LocalDate dateBirth;
	
	private String phone;
	
	private String email;
	
	private LogDTO logDTO;

	public PersonDTO(Long idPerson, String cpf, LocalDate dateBirth, String email, String name, String phone) {
		this.idPerson = idPerson;
		this.cpf = cpf;
		this.dateBirth = dateBirth;
		this.email = email;
		this.name = name;
		this.phone = phone;
	}

	public PersonDTO() {
		// TODO Auto-generated constructor stub
	}

	public Long getIdPerson() {
		return idPerson;
	}

	public void setIdPerson(Long idPerson) {
		this.idPerson = idPerson;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDateBirth() {
		return dateBirth;
	}

	public void setDateBirth(LocalDate dateBirth) {
		this.dateBirth = dateBirth;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LogDTO getLogDTO() {
		return logDTO;
	}

	public void setLogDTO(LogDTO logDTO) {
		this.logDTO = logDTO;
	}
	
}
