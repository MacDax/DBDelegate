package com.spring.boot.framework.dbdelegate.configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

import javax.inject.Provider;

import org.h2.jdbcx.JdbcConnectionPool;

import com.spring.boot.framework.envconfig.EnvConfigUtil;

@DBQualifier
public class DataSourceFactoryProvider implements Provider<JdbcConnectionPool>{

	private static final String DB_DRIVER = "org.h2.Driver";
	
	
	public JdbcConnectionPool get() {
		/*String url = "jdbc:h2:file:E:/GITPROJECTS/personsdb";
		String username = "sa";
		String password = null;
		System.out.println("db url : " + url);*/
		try{
			Class.forName(DB_DRIVER);
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
			//throw e;
		}
		/*String url = "jdbc:h2:file:E:/GITPROJECTS/personsdb";
		String username = "sa";
		String password = null;
		System.out.println("db url : " + url);*/
		String url = EnvConfigUtil.getAsString("hrservice.api.db.url", "");
		String username = EnvConfigUtil.getAsString("hrservice.api.db.username", "");
		String password = EnvConfigUtil.getAsString("hrservice.api.db.password", "");
		System.out.println("db url : " + url);
		JdbcConnectionPool cp = JdbcConnectionPool.create(url, username, password);
		//JdbcConnectionPool cp = JdbcConnectionPool.create("jdbc:h2:file:E:/GITPROJECTS/personsdb", "sa", "sa");
		 
		try{
			System.out.println("cp : " + cp);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		/*Connection con = null;
		try {
			con = cp.getConnection();
			Statement sqlDrop = con.createStatement();
			String dropSql = "drop table persons";
			sqlDrop.execute(dropSql);
			Statement st = con.createStatement();
						
			String sql = "CREATE TABLE persons(id INTEGER AUTO_INCREMENT PRIMARY KEY, "
					+ "fname VARCHAR(30) NOT NULL, lname VARCHAR(30), address VARCHAR(120), birthdate DATE, service VARCHAR(12));";
			boolean success = st.execute(sql);
			String insertPersonsSQL = "insert into persons(fname, lname, address, service) values('Ethan', 'Patelia', '123 lll', 'CEO');";
			Statement insertSql = con.createStatement();
			boolean success3 = insertSql.execute(insertPersonsSQL);
			if(success3) {
				System.out.println("insert done");
			}
			String sql1 = "CREATE TABLE personscontacts(id INTEGER AUTO_INCREMENT PRIMARY KEY, phonenumber varchar(11), email varchar(30), personId INTEGER, FOREIGN KEY (id) REFERENCES persons(id));";
			Statement st1 = con.createStatement();
			boolean success1 = st1.execute(sql1);
			if(success) {
				System.out.println("table persons created ");
			}else {
				System.out.println("table persons not created ");
			}
			if(success1) {
				System.out.println("table personscontacts created ");
			}else {
				System.out.println("table personscontacts not created ");
			}
			//st.close();
			st1.close();
			con.close();
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			if(null != con) {
			try {
				if(!(con.isClosed())) {
				con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} 
	}*/
		
		return cp;
	}

}
