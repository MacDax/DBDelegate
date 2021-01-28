package com.spring.boot.questionary.transactions;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spring.boot.framework.dbdelegate.configuration.DatabaseManager;

@ManagedBean(value="GetSubjectsDataDelegate")
public class GetSubjectsDataDelegate {

	@Inject
	private DatabaseManager dbManager;
	private static final Logger logger = LoggerFactory.getLogger(GetSubjectsDataDelegate.class);
	
	private SubjectDAO getSubjectDAO() {
		try {
			return new SubjectDAO(this.dbManager.getDataSource());
		}catch(Exception ex) {
			throw ex;
		}
	}
	
	public List<SubjectDTO> getSubjectsData() throws Exception {
		try {
			logger.info("getSubjects Data delegate ");
			return getSubjectDAO().getSubjectsList();
		}catch(Exception ex) {
			throw ex;
		}
	}
	
	/*public List<SubjectDTO> getSubjectsData() {
		List<SubjectDTO> subjectList = db
	}*/
}
