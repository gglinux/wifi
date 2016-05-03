package yyx.hive.client;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Wifi extends HiveJdbc{
	
	private String pathString = "/data";
	private String tableName = "wifi";
	
	//load data
	public void loadData() throws SQLException
	{
		ArrayList<File> files = getListFiles(pathString);
		for (File file : files) {
			String sql = "load data local inpath ' " + file.toString() + "'overwrite into table"+tableName;  
			executeSql(sql);
		}
		
	}
	
	
	public void getUserTrack(String startTime, String endTime, String userMac) throws SQLException
	{
		String sqlString = "select * from "+ tableName +"where record_time between "+startTime+" and "+endTime+" and user_mac = "+userMac;
		ResultSet result = executeSql(sqlString);
		cleanData(result);
	}
	public void getUserAccompany(String startTime, String endTime, String userMac, String time1, String time2) throws SQLException
	{
		String sql = "select * from "+tableName+"where " +
					"device_mac in " +
					"(select * from "+tableName +"where record_time between "+startTime+" and "+endTime+" and user_mac = "+userMac+")" +
					"and record_time between " + time1 +" and "+time2;
		ResultSet result = executeSql(sql);
		cleanData(result);
	}
	public void getUserCrash(String deviceMac, String startTime, String endTime) throws SQLException
	{
		String sqlString = "select * from "+ tableName +"where record_time between "+startTime+" and "+endTime+" and device_mac = "+deviceMac;
		ResultSet result = executeSql(sqlString);
		cleanData(result);
	}
	
	private void cleanData(ResultSet result) {
		
	}
	/***
	 * 获取指定目录下的所有的文件（不包括文件夹），采用了递归
	 * 
	 * @param obj
	 * @return
	 */
	private ArrayList<File> getListFiles(Object obj) {
		File directory = null;
		if (obj instanceof File) {
			directory = (File) obj;
		} else {
			directory = new File(obj.toString());
		}
		ArrayList<File> files = new ArrayList<File>();
		if (directory.isFile()) {
			files.add(directory);
			//System.out.println(nameString + " path: "+obj.toString());
			return files;
		} else if (directory.isDirectory()) {
			File[] fileArr = directory.listFiles();
			for (int i = 0; i < fileArr.length; i++) {
				File fileOne = fileArr[i];
				files.addAll(getListFiles(fileOne));
			}
		}
		//System.out.println(nameString+" path: "+obj.toString());
		return files;
	}
}
