package com.spring.boot.personsdb.transactions;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.inject.Inject;

import com.spring.boot.framework.dbdelegate.configuration.DatabaseManager;

@ManagedBean(value="GetPersonalDataDelegate")
public class getPersonalDataDelegate {
  
	@Inject
	private DatabaseManager dbManager;
	
	public List<PersonalDTO> getPersonsDataList() throws Exception {
		try{
			return getPersonalDAO().getPersonsData();
		}catch(Exception ex) {
			throw ex;
		}
	}

	private PersonalDAO getPersonalDAO() {
		return new PersonalDAO(this.dbManager.getDataSource());
	}
	
	public boolean savePersonsData(List<PersonalDTO> persons) throws Exception {
		try {
			return getPersonalDAO().savePersonsData(persons);
		}catch(Exception ex) {
			throw ex;
		}
	}
	
}
