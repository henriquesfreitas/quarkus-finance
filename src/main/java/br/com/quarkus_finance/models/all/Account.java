package br.com.quarkus_finance.models.all;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NamedQuery;

import br.com.quarkus_finance.models.common.GenericEntity;

@Entity
@NamedQuery(
name="VERITY_IF_ACCOUNT_NUMBER_ALREADY_EXISTS",
query="SELECT COUNT(*) > 0 FROM Account WHERE number = :number"
)
public class Account extends GenericEntity {

	private static final long serialVersionUID = 1L;
	
	public static String VERITY_IF_ACCOUNT_NUMBER_ALREADY_EXISTS = "VERITY_IF_ACCOUNT_NUMBER_ALREADY_EXISTS";
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name  = "id_account")
	private Long idAccount;
	
	private String number;
	
	@ManyToOne
	@NotNull
	private Person person;

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

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
}
