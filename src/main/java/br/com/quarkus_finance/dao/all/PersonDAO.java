package br.com.quarkus_finance.dao.all;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;

import br.com.quarkus_finance.dao.common.ConvertDAODTO;
import br.com.quarkus_finance.dao.common.GenericDAO;
import br.com.quarkus_finance.dto.all.PersonDTO;
import br.com.quarkus_finance.models.all.Person;

@Transactional
@RequestScoped
//@Stateless
//Quarkus doesnt allow EJB, so you have to use transactional instead
public class PersonDAO extends GenericDAO {

	private static final long serialVersionUID = -6321526905232236895L;
	
	public void save(PersonDTO personDTO){
		Person person = convertFromDTO(personDTO);
		
		if(person.getIdPerson() == null) {
			persist(person);
		}else {
			merge(person);
		}
	}
	
	public void update(PersonDTO personDTO) throws Exception{
		if(personDTO.getIdPerson() == null) {
			throw new Exception("idPerson may not be blank");
		}
		
		save(personDTO);
	}
	
	public PersonDTO find(Long idPerson) {
		Person person = entityManager.find(Person.class, idPerson);
		return convertFromEntity(person);
	}
	
	public List<PersonDTO> listAll() {
		List<Person> listPerson = listAll(Person.class);
		List<PersonDTO> listPersonDTO = new ArrayList<PersonDTO>();
		for (Person person : listPerson) {
			listPersonDTO.add(convertFromEntity(person));
		}
		
		return listPersonDTO;
	}

	public Person convertFromDTO(PersonDTO dto) {
		Person person = new Person();
		person.setIdPerson(dto.getIdPerson());
		person.setCpf(dto.getCpf());
		person.setDateBirth(dto.getDateBirth());
		person.setEmail(dto.getEmail());
		person.setName(dto.getName());
		person.setPhone(dto.getPhone());
		person.setLogEntity(ConvertDAODTO.convertLogDTOToLogEntity(dto.getLogDTO()));
		
		return person;
	}

	public PersonDTO convertFromEntity(Person entity) {
		PersonDTO personDTO = new PersonDTO(
				entity.getIdPerson(), entity.getCpf(), entity.getDateBirth(), entity.getEmail(),
				entity.getName(), entity.getPhone()
		);
		personDTO.setLogDTO(ConvertDAODTO.convertLogEntityToLogDTO(entity.getLogEntity()));
		
		return personDTO;
	}
	
}
