package com.spring.boot.framework.dbdelegate.configuration;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.inject.Provider;

import org.h2.jdbcx.JdbcConnectionPool;

@DBQualifier
public class DataSourceFactoryProvider implements Provider<JdbcConnectionPool>{

	private static final String DB_DRIVER = "org.h2.Driver";
	
	
	public JdbcConnectionPool get() {
		String url = "jdbc:h2:file:e:/GITPROJECTS/personsdb";
		String username = "sa";
		String password = null;
		System.out.println("db url : " + url);
		try{
			Class.forName(DB_DRIVER);
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
			//throw e;
		}
		JdbcConnectionPool cp = JdbcConnectionPool.create(url, username, password);
		try{
			System.out.println("cp : " + cp.getConnection().getSchema());
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		Connection con = null;
		try {
			con = cp.getConnection();
			Statement st = con.createStatement();
			String sql = "CREATE TABLE persons(id INTEGER AUTO_INCREMENT PRIMARY KEY, "
					+ "fname VARCHAR(30) NOT NULL, lname VARCHAR(30), address VARCHAR(120), birthdate date";
			boolean success = st.execute(sql);
			if(success) {
				System.out.println("table persons created ");
			}
			st.close();
			con.close();
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return cp;
	}

}
