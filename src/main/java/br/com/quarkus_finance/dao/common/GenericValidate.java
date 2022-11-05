package br.com.quarkus_finance.dao.common;

public class GenericValidate extends GenericDAO {
	
	public <T> void validateFind(T entity) throws Exception {
		if(entity == null)
			throw new Exception("Register not found");
	}
	
	public void validateIdUpdate(Long id) throws Exception {
		if(id == null) {
			throw new Exception("Primary Key's Id may not be null");
		}
	}

}
