package com.spring.boot.questionary.transactions;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ExQuestionDAO {

	private static final Logger logger = LoggerFactory.getLogger(QuestionTypeDAO.class);
	private DataSource dataSource;
	
	public ExQuestionDAO(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public List<ExQuestionDTO> getQuestionsData() throws Exception {
		
		return getQuestionsList("select * from exam_questions");
	}

	private List<ExQuestionDTO> getQuestionsList(String string) throws Exception {
		List<ExQuestionDTO> questions = new ArrayList<>();
		Connection conn = null;
		try {
			conn = this.dataSource.getConnection();
			try {
				logger.info("get questiontype Data SQLString : " + string);
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(string);
				while(rs.next()) {
					ExQuestionDTO exQuestion = new ExQuestionDTO();
					exQuestion.setQuestionId(rs.getInt("id"));
					exQuestion.setQuestionText(rs.getString("question_txt"));
					exQuestion.setQuestionTypeCode(rs.getInt("exquestiontype_id"));
					exQuestion.setSubjectCode(rs.getInt("subjectcode"));
					exQuestion.setTeacherId(rs.getInt("teacherid"));
					Timestamp  QupdatedAtDBDate = rs.getTimestamp("updated_at");
					long updatedAt = QupdatedAtDBDate.getTime();
					logger.info("Question updated at DB time : " + updatedAt);
					Date questionUpdatedDate = new Date(QupdatedAtDBDate.getTime());
					logger.info("Question updated java Date : " + questionUpdatedDate);
					exQuestion.setLastUpdated(questionUpdatedDate);
					questions.add(exQuestion);
				}
			}catch(SQLException ex) {
				ex.printStackTrace();
				throw ex;
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return questions;
	}
}
