package yyx.hive.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HiveJdbc {

	private static String driverName = "org.apache.hive.jdbc.HiveDriver";

	private Connection getConnection() throws SQLException {
		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return DriverManager.getConnection(
				"jdbc:hive2://localhost:10000/default", "", "");
	}

	public ResultSet executeSql(String sql) throws SQLException {
		System.out.println("Running sql:" + sql);
		Statement statement = getConnection().createStatement();
		return statement.executeQuery(sql);
	}

	public int executeDDL(String sql) throws SQLException {
		System.out.println("Running sql:" + sql);
		Statement statement = getConnection().createStatement();
		return statement.executeUpdate(sql);
	}

}
