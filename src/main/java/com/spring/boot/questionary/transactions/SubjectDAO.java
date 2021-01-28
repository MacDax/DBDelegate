package com.spring.boot.questionary.transactions;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spring.boot.personsdb.transactions.PersonalDTO;

public class SubjectDAO {

	private static final Logger logger = LoggerFactory.getLogger(SubjectDAO.class);
	private DataSource dataSource;


	public SubjectDAO(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public List<SubjectDTO> getSubjectsList() throws Exception {		
		try {
			return getSubjectsData("select * from subjects");
		}catch(Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

	private List<SubjectDTO> getSubjectsData(String sql) throws Exception {
		List<SubjectDTO> subjectList = new ArrayList<>();
		try{
			Connection conn = this.dataSource.getConnection();
			try{
				Statement stmt = conn.createStatement();
				logger.info("get Data SQLString : " + sql);
				ResultSet rs = stmt.executeQuery(sql);
				while(rs.next()) {
					SubjectDTO subject = new SubjectDTO();
					subject.setSubjectId(rs.getInt("subjectcode"));
					subject.setSubjectName(rs.getString("subjectname"));
					subjectList.add(subject);
				}
			}catch(Exception ex) {
				throw ex;
			}
		}catch(Exception ex) {
			throw ex;
		}
		return subjectList;
	}
}
