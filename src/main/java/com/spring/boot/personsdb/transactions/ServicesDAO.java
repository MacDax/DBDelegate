package com.spring.boot.personsdb.transactions;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServicesDAO {

	private static final Logger logger = LoggerFactory.getLogger(ServicesDAO.class);
	private DataSource dataSource;
	
	public ServicesDAO(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public List<ServicesDTO> getServicesList() throws Exception{
		try {
			return getDBServicesList("select * from services");
		}catch(Exception ex) {
			logger.info("ex for db services select" + ex.getMessage());
			throw ex;
		}
	}

	private List<ServicesDTO> getDBServicesList(String sql) throws Exception {
		List<ServicesDTO> servicesList = new ArrayList<>();
		try{
			Connection conn = this.dataSource.getConnection();
			try{
				Statement stmt = conn.createStatement();
				logger.info("get Data SQLString : " + sql);
				ResultSet rs = stmt.executeQuery(sql);
				while(rs.next()) {
					ServicesDTO service = new ServicesDTO();
					service.setServiceId(rs.getInt("id"));
					service.setServiceName(rs.getString("servicetype"));
					service.setServiceType(rs.getString("servicename"));
					servicesList.add(service);
				}
			}catch(Exception ex) {
				throw ex;
			}
		}catch(Exception ex) {
			throw ex;
		}
		logger.info("services list retrieved from db : " + servicesList.size());
		return servicesList;
	}
}
