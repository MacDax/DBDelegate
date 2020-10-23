package com.spring.boot.framework.dbdelegate.configuration;

import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import javax.sql.DataSource;

import org.h2.jdbcx.JdbcConnectionPool;

@ManagedBean
public class DatabaseManager {

	private JdbcConnectionPool dataSource;
	DataSourceFactoryProvider provider;
	
	@Inject
	public DatabaseManager() {
		provider = new DataSourceFactoryProvider();
		System.out.println("provider : " + provider);
		this.dataSource = provider.get();
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			System.out.println(" Connection to db done");
		}catch(Throwable th){
			th.printStackTrace();
			//throw th;
		}finally {
			try {
				if(null != connection) {
				 connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public DataSource getDataSource() {
		return this.dataSource;
	}
	
	public Connection getConnection() throws SQLException {
		return this.dataSource.getConnection();
	}
}
