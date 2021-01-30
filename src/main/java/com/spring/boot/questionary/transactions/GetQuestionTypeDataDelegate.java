package com.spring.boot.questionary.transactions;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spring.boot.framework.dbdelegate.configuration.DatabaseManager;

@ManagedBean("GetQuestionTypeDataDelegate")
public class GetQuestionTypeDataDelegate {

	@Inject
	DatabaseManager databaseManager;
	private static final Logger logger = LoggerFactory.getLogger(GetQuestionTypeDataDelegate.class);
	
	private QuestionTypeDAO getQuestionTypeDAO() {
		return new QuestionTypeDAO(this.databaseManager.getDataSource());
	}
	
	public List<QuestionTypeDTO> getQuestionTypeList() throws Exception {		
		try {
			logger.info("get question types data");
			return getQuestionTypeDAO().getQuestionTypeList();
		} catch (Exception e) {
				e.printStackTrace();
				throw e;
		}
		//return null;
	}
}
