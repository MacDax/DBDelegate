package com.spring.boot.personsdb.transactions;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spring.boot.framework.dbdelegate.configuration.DatabaseManager;

@ManagedBean(value="GetPersonalDataDelegate")
public class GetPersonalDataDelegate {
  
	@Inject
	private DatabaseManager dbManager;
	private static final Logger logger = LoggerFactory.getLogger(PersonalDAO.class);
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
			logger.info("savePersons Data input : " + persons.size());
			return getPersonalDAO().savePersonsData(persons);
		}catch(Exception ex) {
			throw ex;
		}
	}
	
}
