package com.spring.boot.questionary.transactions;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuestionTypeDAO {

	private static final Logger logger = LoggerFactory.getLogger(QuestionTypeDAO.class);
	private DataSource dataSource;
	
	public QuestionTypeDAO(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public List<QuestionTypeDTO> getQuestionTypeList() throws Exception {
		return getQuestionTypeData("select * from EXQUESTIONTYPES");
	}

	private List<QuestionTypeDTO> getQuestionTypeData(String sql) throws Exception {
		List<QuestionTypeDTO> questionTypes = new ArrayList<>();
		try {
			Connection conn = this.dataSource.getConnection();
			try {
				Statement stmt = conn.createStatement();
				logger.info("get questiontype Data SQLString : " + sql);
				ResultSet rs = stmt.executeQuery(sql);
				while(rs.next()) {
					QuestionTypeDTO questionType = new QuestionTypeDTO();
					questionType.setQuestionTypeCode(rs.getInt("exquestiontype_code"));
					questionType.setQuestionTypeName(rs.getString("question_type"));
					questionTypes.add(questionType);
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}catch(Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return questionTypes;
	}
}
