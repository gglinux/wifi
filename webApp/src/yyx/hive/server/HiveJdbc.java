package yyx.hive.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HiveJdbc {
	
	private String ConnectUrl = "jdbc:hive://master::";
	
	private static Connection conn = null;
	
	private Connection getConnection() throws SQLException
	{
		HiveJdbc.conn = DriverManager.getConnection(ConnectUrl);
		return HiveJdbc.conn;
	}
	
	public ResultSet executeSql(String sql) throws SQLException
	{
		Statement statement = getConnection().createStatement();
		System.out.println("Running sql:"+sql);
		return statement.executeQuery(sql);
	}
	
	
}
