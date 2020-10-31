package com.spring.boot.framework.dbdelegate.configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

import javax.inject.Provider;

import org.h2.jdbcx.JdbcConnectionPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spring.boot.framework.envconfig.EnvConfigUtil;
import com.spring.boot.personsdb.transactions.PersonalDAO;

@DBQualifier
public class DataSourceFactoryProvider implements Provider<JdbcConnectionPool>{

	private static final String DB_DRIVER = "org.h2.Driver";
	private static final Logger logger = LoggerFactory.getLogger(DataSourceFactoryProvider.class);
	
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
		logger.info("db url : " + url);
		JdbcConnectionPool cp = JdbcConnectionPool.create(url, username, password);
		//JdbcConnectionPool cp = JdbcConnectionPool.create("jdbc:h2:file:E:/GITPROJECTS/personsdb", "sa", "sa");
		 
		try{
			logger.info("cp : " + cp.getConnection().getSchema());
			//logger.info("cp : " + cp.getConnection().getMetaData());
			String sql = "SELECT * FROM INFORMATION_SCHEMA.TABLES";
			Connection con = cp.getConnection();
			Statement sqlCon = con.createStatement();
			ResultSet rs = sqlCon.executeQuery(sql);
			while(rs.next()) {
				//logger.info(rs.getString(1));
				//logger.info(rs.getString(2));
				logger.info(rs.getString(3));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	/*	Connection con = null;
		try {
			con = cp.getConnection();
			Statement sqlDrop = con.createStatement();
			//String dropSql = "drop table persons";
			//sqlDrop.execute(dropSql);
			Statement st = con.createStatement();
			String sql = "CREATE TABLE services(id INTEGER AUTO_INCREMENT PRIMARY KEY, "
					+ "servicetype VARCHAR(40) NOT NULL, servicename VARCHAR(80));";
			
			String sql = "CREATE TABLE persons(id INTEGER AUTO_INCREMENT PRIMARY KEY, "
					+ "fname VARCHAR(30) NOT NULL, lname VARCHAR(30), address VARCHAR(120), birthdate DATE, service VARCHAR(100));";
			
			boolean success = st.execute(sql);
			//String insertSQL = "insert into services(servicetype, servicename) values('Driving', 'KidsPickup-Droppoff');";
			String insertSQL = "insert into persons(fname, lname, address, service) values('Ramesh', 'Mehata', '123 lll', 'KidsPickup-Droppoff');";
			Statement insertStmt = con.createStatement();
			boolean success3 = insertStmt.execute(insertSQL);
			st.close();
			insertStmt.close();
			con.close();
			}catch(Exception ex) {
				logger.info("ex to create table :" + ex.getMessage());
			}*/
		
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
