package com.spring.boot.personsdb.transactions;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spring.boot.framework.dbdelegate.configuration.DatabaseManager;

@ManagedBean(value="ServicesDataDelegate")
public class ServicesDataDelegate {
	
	@Inject
	private DatabaseManager dbManager;
	private static final Logger logger = LoggerFactory.getLogger(ServicesDataDelegate.class);

	public List<ServicesDTO> getServicesDataList() throws Exception {
		try {
			logger.info("get services data from db");
			return getServicesDAO().getServicesList();
		}catch(Exception ex) {
			throw ex;
		}
	}
	
	private ServicesDAO getServicesDAO() {
		return new ServicesDAO(this.dbManager.getDataSource());
	}
}
