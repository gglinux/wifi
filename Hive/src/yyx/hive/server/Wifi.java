package yyx.hive.server;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Wifi extends HiveJdbc{
	
	private String pathString = "/mnt/hgfs/yyx/data/test";
	private String tableName = "wifi";
	
	public static void main(String[] args) throws SQLException
	{
		String startTime = "'2016-04-13 10:43:36'";
		String endTime = "'2016-04-14 11:43:36'";
		String userMac = "'ff:ff:ff:ff:ff:ff'";
		String deviceMac = "'b8:27:eb:1c:c0:09'";
		
		String time1 = "'2016-04-13 10:43:36'";
		String time2 = "'2016-04-14 11:43:36'";
		
		Wifi test = new Wifi();
		//test.createTable();
		//test.loadData();
		//test.testData();
		
		test.getUserTrack(startTime, endTime, userMac);
		//test.getUserAccompany(userMac, time1, time2);
		//test.getUserCrash(deviceMac, startTime, endTime);
	}
	
	//load data
	public void loadData() throws SQLException
	{
		ArrayList<File> files = getListFiles(pathString);

		for (File file : files) {		
			System.out.println(file.toString());
			String sql = "load data local inpath '" + file.toString() + "'overwrite into table "+tableName;  
			executeDDL(sql);
		}
	}
	
	public void createTable() throws SQLException
	{
        String sql = "CREATE TABLE IF NOT EXISTS "+tableName+" ( " +
        		"device_mac string, " +
        		"record_time string, " +
        		"user_mac string, " +
        		"ap_mac string, " +
        		"data_rate string, " + 
        		"rssi_signal string, " + 
        		"channel_id string, " + 
        		"app_type string, " + 
        		"app_info string)" +  
        		"ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t' " +
        		"STORED AS TEXTFILE";
        executeDDL(sql);
	}
	public void testData() throws SQLException
	{
		String sqlString = "select * from "+ tableName +" limit 100";
		ResultSet result = executeSql(sqlString);
		cleanData(result);
	}
	
	/**
	 * 用户轨迹：根据指定时间，查询device_mac
	 * @param startTime 开始时间
	 * @param endTime 指定时间
	 * @param userMac 用户mac
	 * @throws SQLException
	 */
	public void getUserTrack(String startTime, String endTime, String userMac) throws SQLException
	{
		String sqlString = "select * from "+ tableName +" where record_time between "+startTime+" and "+endTime+" and user_mac = "+userMac;
		ResultSet result = executeSql(sqlString);
		cleanData(result);
	}
	
	/**
	 * 伴随情况
	 * @param userMac 用户mac
	 * @param time1 伴随开始时间
	 * @param time2 伴随结束时间
	 * @throws SQLException
	 */
	public void getUserAccompany(String userMac, String time1, String time2) throws SQLException
	{
		String subSql = "select distinct device_mac from "+tableName +" where user_mac = "+userMac;
		ResultSet subResult = executeSql(subSql);

		while(subResult.next()) {
			//System.out.println("!!!!!!Sub query: "+subResult.getString("device_mac"));
			String sql = "select * from "+tableName+" where " +
					"device_mac = '" + subResult.getString("device_mac") + 
					"' and record_time between " + time1 +" and "+time2;
			ResultSet result = executeSql(sql);
			cleanData(result);
		}
	}
	
	/**
	 * 碰撞分析
	 * @param deviceMac 设备mac
	 * @param startTime 查询开始时间
	 * @param endTime 结束时间
	 * @throws SQLException
	 */
	public void getUserCrash(String deviceMac, String startTime, String endTime) throws SQLException
	{
		String sqlString = "select * from "+ tableName +" where record_time between "+startTime+" and "+endTime+" and device_mac = "+deviceMac;
		cleanData(executeSql(sqlString));
	}
	
	/**
	 * 整理数据 输出
	 * @param result
	 * @throws SQLException
	 */
	private void cleanData(ResultSet result) throws SQLException {
		 while (result.next()) {
		      System.out.println(String.valueOf(
		    		  result.getString(1)) + "\t" + 
		    		  result.getString(2) + "\t" + 
		    		  result.getString(3)+"\t"+
		    		  result.getString(4)+"\t"+
		    		  result.getString(5)+"\t"+
		    		  result.getString(6)+"\t"+
		    		  result.getString(7)+"\t"+
		    		  result.getString(8)+"\t"+
		    		  result.getString(9)+"\t"
		    		  );
		    }
	}
	/***
	 * 鑾峰彇鎸囧畾鐩綍涓嬬殑鎵�鏈夌殑鏂囦欢锛堜笉鍖呮嫭鏂囦欢澶癸級锛岄噰鐢ㄤ簡閫掑綊
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
