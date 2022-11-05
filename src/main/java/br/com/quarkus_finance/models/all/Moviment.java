package br.com.quarkus_finance.models.all;

import java.math.BigDecimal;

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
//@NamedQuery(
//name="GET_LAST_MOVIMENT_BY_ID_ACCOUNT",
//query="SELECT m 0 FROM Moviment m WHERE id_moviment = :idMoviment ORDER BY criacao DESC"
//)
public class Moviment extends GenericEntity{

	private static final long serialVersionUID = 2106197641859665925L;

//	public static final String GET_LAST_MOVIMENT_BY_ID_ACCOUNT = "GET_LAST_MOVIMENT_BY_ID_ACCOUNT";
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name  = "id_account")
	private Long idMoviment;
	
	@ManyToOne
	@NotNull
	private Account account;
	
	private BigDecimal value;

	public Long getIdMoviment() {
		return idMoviment;
	}

	public void setIdMoviment(Long idMoviment) {
		this.idMoviment = idMoviment;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}
	
}
