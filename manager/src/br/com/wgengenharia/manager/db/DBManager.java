package br.com.wgengenharia.manager.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
	
	private DBManager(){}
	
	private static DBManager instance;
	
	public static DBManager getInstace(){
		if (instance==null){
			instance =  new DBManager();
		}
	return instance;
	}
	
	public Connection getConnection() throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","Admin123");
	}
	
	
	public void closeConnnection(Connection con) { 
		try {
			if(con!=null)
			con.close(); 
		} catch (SQLException e) { 
			e.printStackTrace(); 
		} 
	} 
	

}
