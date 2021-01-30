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
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

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
		
		
		/* url = url + 
	             "INIT=CREATE SCHEMA IF NOT EXISTS hrm\\;" + 
	                  "SET SCHEMA hrm";*/
	/* url = url + ";" +
	             "INIT=RUNSCRIPT FROM '~/schema.sql'\\;" + 
	                  "RUNSCRIPT FROM '~/data.sql'";*/
		
		
		  /* EmbeddedDatabase db = new EmbeddedDatabaseBuilder()
                   .setType(EmbeddedDatabaseType.H2)
                   .setName("testhrmDb;MODE=Oracle;INIT=create " +
                           "schema if not exists " +
                           "schema_a\\;create schema if not exists testhrmDb;" +
                           "DB_CLOSE_DELAY=-1;")
                   .addScript("schema.sql")
                   .addScript("data.sql")
                   .build();*/
		
		/*EmbeddedDatabase db = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("schema.sql")
                .addScript("data.sql")
                .build();
		
		System.out.println("db created : " + db);*/
		
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
			
			String tabsql = "SELECT  * 	FROM    INFORMATION_SCHEMA.TABLES 	WHERE   TABLE_NAME  = 'QUESTIONTYPES';";
			
			
			Statement sqlTableCon = con.createStatement();
			ResultSet rsTable = sqlTableCon.executeQuery(tabsql);
			ResultSet tabcols = con.getMetaData().getColumns(null, null, "subjects", null);
			
			
		    while (tabcols.next()) {
		      logger.info("tabcols 1 : " + tabcols.getString(1)); 
		      logger.info("tabcols 1 : " + tabcols.getString(2)); 
		    }
		    
			
			logger.info("tabcols : " + tabcols.getFetchSize());
			while(rsTable.next()) {
				logger.info(" inside rstable : " );
				logger.info("tab rs 1 : " + rsTable.getString(1));
				logger.info("tab rs 2 : " + rsTable.getString(2));
				logger.info("tab rs 3 : " + rsTable.getString(3));
				//int rs1 = rsTable.findColumn("QUESTIONTYPE_CODE");
				//logger.info("rs1 : " + rs1);
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		Connection con = null;
		try {
			con = cp.getConnection();
			//Statement sqlDrop = con.createStatement();
			//String dropSql = "drop table exam_questions";
			//sqlDrop.execute(dropSql);
			/*Statement st = con.createStatement();
			String sql = "CREATE TABLE services(id INTEGER AUTO_INCREMENT PRIMARY KEY, "
					+ "servicetype VARCHAR(40) NOT NULL, servicename VARCHAR(80));";*/
			//boolean success = st.execute(sql);
			//st.close();
			
			/*String sql = "CREATE TABLE  subjects(subjectcode INTEGER PRIMARY KEY, subjectname VARCHAR(620));";
			Statement insertStmt = con.createStatement();
			boolean success3 = insertStmt.execute(sql);
			insertStmt.close();*/
			//String qtypesql = "CREATE TABLE questiontypes(questiontype_code INTEGER PRIMARY KEY, question_type VARCHAR(225));";
			/*String qtypesql = "CREATE TABLE exquestiontypes(exquestiontype_code INTEGER PRIMARY KEY, question_type VARCHAR(225));";
			Statement insterqtype = con.createStatement();
			boolean qtsuccess = insterqtype.execute(qtypesql);
			insterqtype.close();
			*/
			/*String insertQtype = "insert into  exquestiontypes(exquestiontype_code, question_type) values(10, 'true/false');";
			Statement insertQtypeSt = con.createStatement();
			boolean iqt = insertQtypeSt.execute(insertQtype);
			insertQtypeSt.close();
			
			String insertQtype1 = "insert into  exquestiontypes(exquestiontype_code, question_type) values(20, 'multiple choice');";
			Statement insertQtypeSt1 = con.createStatement();
			boolean iqt1 = insertQtypeSt1.execute(insertQtype1);
			insertQtypeSt1.close();*/
			
			/*String insertQtype2 = "insert into  exquestiontypes(exquestiontype_code, question_type) values(30, 'fill-in-the-blank');";
			Statement insertQtypeSt2 = con.createStatement();
			boolean iqt2 = insertQtypeSt2.execute(insertQtype2);
			insertQtypeSt2.close();
			
			String insertQtype3 = "insert into  exquestiontypes(exquestiontype_code, question_type) values(40, 'short answer');";
			Statement insertQtypeSt3 = con.createStatement();
			boolean iqt3 = insertQtypeSt3.execute(insertQtype3);
			insertQtypeSt3.close();
			
			String insertQtype4 = "insert into  exquestiontypes(exquestiontype_code, question_type) values(50, 'essay');";
			Statement insertQtypeSt4 = con.createStatement();
			boolean iqt4 = insertQtypeSt4.execute(insertQtype4);
			insertQtypeSt4.close();
			
			String insertQtype5 = "insert into  exquestiontypes(exquestiontype_code, question_type) values(60, 'ordering');";
			Statement insertQtypeSt5 = con.createStatement();
			boolean iqt5 = insertQtypeSt5.execute(insertQtype5);
			insertQtypeSt5.close();
			
			String insertQtype7 = "insert into  exquestiontypes(exquestiontype_code, question_type) values(70, 'matching');";
			Statement insertQtypeSt7 = con.createStatement();
			boolean iqt7 = insertQtypeSt7.execute(insertQtype7);
			insertQtypeSt7.close(); */
			
			
			String selectQtype = "select * from  exquestiontypes;";
			Statement selectQtypeSt = con.createStatement();
			ResultSet iqts = selectQtypeSt.executeQuery(selectQtype);
			logger.info("selectQtypeSt executed : " );
			while(iqts.next()) {
				logger.info("selectQtypeSt data :" + iqts.getString(1));
				/*logger.info("qtype data :" + iqts.getInt("exquestiontype_code"));
				logger.info("qtype data :" + iqts.getString("question_type"));*/
			}
			//selectQtypeSt.close();			
			
			
			//String qsql = "CREATE TABLE exam_questions(id INTEGER AUTO_INCREMENT PRIMARY KEY, subjectcode INTEGER, FOREIGN KEY(subjectcode) REFERENCES subjects(subjectcode), teacherid INTEGER)"; //, questiontype_id INTEGER, FOREIGN KEY(exquestiontype_code) REFERENCES exquestiontypes(exquestiontype_code));";
			//String qsql = "ALTER TABLE exam_questions  ADD FOREIGN KEY(teacherid) REFERENCES persons(id);";
			//String qsql = "ALTER TABLE exam_questions ADD exquestiontype_id INTEGER";
			//String qsql = "ALTER TABLE exam_questions  ADD FOREIGN KEY (exquestiontype_id) REFERENCES exquestiontypes(exquestiontype_code);";
			//String qsql = "ALTER TABLE exam_questions ADD question_txt VARCHAR(733)";
			//String qsql = "ALTER TABLE exam_questions ADD updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP";
			//Statement insertqStmt = con.createStatement();
			//boolean successq = insertqStmt.execute(qsql);
			//insertqStmt.close();
			
		
			
			String selectColms = "show columns from exam_questions;";
			Statement selectColnames = con.createStatement();
			ResultSet colnames = selectColnames.executeQuery(selectColms);
			logger.info("selectColnames executed : " );
			while(colnames.next()) {
				logger.info("selectColnames data :" + colnames.getString(1));
			}
			selectColnames.close();
			
			
			
			
/*			String sql = "CREATE TABLE persons(id INTEGER AUTO_INCREMENT PRIMARY KEY, fname VARCHAR(30) NOT NULL, lname VARCHAR(30), birthdate DATE);";
			String insertSQL = "insert into services(servicetype, servicename) values('Driving', 'KidsPickup-Droppoff');";
			String insertSQL = "insert into persons(fname, lname, birthdate) values('Ethan', 'Patelia', '2010-01-27');";
			Statement insertStmt = con.createStatement();
			boolean success3 = insertStmt.execute(insertSQL);
			insertStmt.close();
			
			
			String sql1 = "CREATE TABLE personscontacts(id INTEGER AUTO_INCREMENT PRIMARY KEY, phonenumber varchar(11), email varchar(30), personId INTEGER, FOREIGN KEY (id) REFERENCES persons(id));";
			Statement st1 = con.createStatement();
			boolean success1 = st1.execute(sql1);
			
			String sql3 = "CREATE TABLE rolenames(id INTEGER AUTO_INCREMENT PRIMARY KEY, rolename varchar(225));";
			Statement st3 = con.createStatement();
			boolean success3 = st3.execute(sql3);
			
			String insertRoleNameSQL1 = "insert into rolenames(rolename) values('teacher');";
			Statement insertRoleNameStmt1 = con.createStatement();
			boolean success6 = insertRoleNameStmt1.execute(insertRoleNameSQL1);
			
			String insertRoleNameSQL2 = "insert into rolenames(rolename) values('student');";
			Statement insertRoleNameStmt2 = con.createStatement();
			boolean success5 = insertRoleNameStmt2.execute(insertRoleNameSQL2);
			
			String sql2 = "CREATE TABLE personsroles(id INTEGER AUTO_INCREMENT PRIMARY KEY, roleid INTEGER, FOREIGN KEY (id) REFERENCES rolenames(id), personId INTEGER, FOREIGN KEY (id) REFERENCES persons(id));";
			Statement st2 = con.createStatement();
			boolean success2 = st2.execute(sql2);
			
			String insertRoleSQL1 = "insert into personsroles(roleid, personId) values(1, 1);";
			Statement insertStmt1 = con.createStatement();
			boolean success4 = insertStmt1.execute(insertRoleSQL1);
			
			String insertRoleSQL2 = "insert into personsroles(roleid, personId) values(2, 33);";
			Statement insertRoleStmt2 = con.createStatement();
			boolean success7 = insertRoleStmt2.execute(insertRoleSQL2);
			
			String insertSQL1 = "insert into personscontacts(phonenumber, email, personId) values('5104995471', 'Patelia@gmail.com', 1);";
			Statement insertStmt7 = con.createStatement();
			boolean success9 = insertStmt1.execute(insertSQL1);
			
			String insertSQL2 = "insert into personscontacts(phonenumber, email, personId) values('4804995471', 'pateliaet@gmail.com', 65);";
			Statement insertStmt2 = con.createStatement();
			boolean success8 = insertStmt2.execute(insertSQL2);
			
			String insertSubSQL1 = "insert into subjects(subjectcode, subjectname) values(101, 'Maths-1');";
			Statement insertSubStmt1 = con.createStatement();
			boolean successInserSub1 = insertSubStmt1.execute(insertSubSQL1);
			
			String insertSubSQL2 = "insert into subjects(subjectcode, subjectname) values(102, 'English-1');";
			Statement insertSubStmt2 = con.createStatement();
			boolean successInserSub2 = insertSubStmt2.execute(insertSubSQL2);
			
			String insertSubSQL3 = "insert into subjects(subjectcode, subjectname) values(103, 'Science-1');";
			Statement insertSubStmt3 = con.createStatement();
			boolean successInserSub3 = insertSubStmt3.execute(insertSubSQL3);
			
			String insertSubSQL4 = "insert into subjects(subjectcode, subjectname) values(104, 'History-1');";
			Statement insertSubStmt4 = con.createStatement();
			boolean successInserSub4 = insertSubStmt4.execute(insertSubSQL4);
			
			String insertSubSQL5 = "insert into subjects(subjectcode, subjectname) values(105, 'Social Study-1');";
			Statement insertSubStmt5 = con.createStatement();
			boolean successInserSub5 = insertSubStmt5.execute(insertSubSQL5);*/
			
							
			String selectPersons = "select * from  persons;";
			Statement selectPersonSt = con.createStatement();
			ResultSet personrs = selectPersonSt.executeQuery(selectPersons);
			logger.info("selectPersonSt executed : " );
			while(personrs.next()) {
				logger.info("selectPersonSt data :" + personrs.getString(1));
				/*logger.info("qtype data :" + iqts.getInt("exquestiontype_code"));
				logger.info("qtype data :" + iqts.getString("question_type"));*/
			}
			selectPersonSt.close();
			
			
			/*String selectSubs = "select * from  subjects;";
			Statement selectSubSt = con.createStatement();
			ResultSet subrs = selectSubSt.executeQuery(selectSubs);
			logger.info("selectSubSt executed : " );
			while(subrs.next()) {
				logger.info("sub  :" + subrs.getString(1));
				}
			selectSubSt.close();*/
			
			//String insertQ = "insert into  exam_questions(subjectcode,teacherid,exquestiontype_id,question_txt) values(102,33,50, 'essay about president');";
			//"insert into  exam_questions(subjectcode, teacherid, exquestiontype_id, question_txt) values(102, 33, 10, 'name of a person is noun');";
			//Statement insertQst = con.createStatement();
			//boolean inq = insertQst.execute(insertQ);
			//insertQst.close();
			
			String selectExQs = "select * from  exam_questions;";
			Statement selectExSt = con.createStatement();
			ResultSet exsts = selectExSt.executeQuery(selectExQs);
			logger.info("selectExSt executed : " );
			while(exsts.next()) {
				logger.info("ex id :" + exsts.getString(1));
				logger.info("sub code :" + exsts.getInt("subjectcode"));
				logger.info("teacherid : " + exsts.getInt("teacherid"));
				logger.info("exquestiontype_id : " + exsts.getInt("exquestiontype_id"));
				logger.info("qtype data :" + exsts.getString("question_txt"));
				logger.info("updated at : " + exsts.getTimestamp("updated_at"));
			}
			selectExSt.close();
			
			
			con.close();
			}catch(Exception ex) {
				ex.printStackTrace();
				logger.info("ex to create table :" + ex.getMessage());
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
			}
		
		return cp;
	}

}
