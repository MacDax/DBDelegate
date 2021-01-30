package com.spring.boot.questionary.transactions;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spring.boot.framework.dbdelegate.configuration.DatabaseManager;

@ManagedBean
public class ExQuestionDataDelegate {

	private static final Logger logger = LoggerFactory.getLogger(ExQuestionDataDelegate.class);
	@Inject
	private DatabaseManager dbManager;
	
	private ExQuestionDAO getExQuestionDAO() {
		return new ExQuestionDAO(this.dbManager.getDataSource());
	}
	
	public List<ExQuestionDTO> getExQuestionsList() throws Exception {
		logger.info("Ex question data delegate :");
		return getExQuestionDAO().getQuestionsData();
	}
}
