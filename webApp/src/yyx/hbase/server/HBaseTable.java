package yyx.hbase.server;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

public class HBaseTable 
{
	public static String tableName = "wifi_record";
	public static String familyName = "info";
	
	static Configuration cfg = new Configuration();
	
	// create the hbase table;
	public HBaseTable() throws Exception
	{
		HBaseAdmin admin = new HBaseAdmin(cfg);
		if (admin.tableExists(tableName)) {
			System.out.println("table has created!");
			System.exit(0);
		} else {
			@SuppressWarnings("deprecation")
			HTableDescriptor tableDescriptor = new HTableDescriptor(tableName);
			tableDescriptor.addFamily(new HColumnDescriptor(familyName));
			admin.createTable(tableDescriptor);
			System.out.println("create table success!");
		}
		admin.close();
	}
	
	//put data
	public void put(String row, String column, String data) throws Exception
	{
		HTable table = new HTable(cfg, tableName);

		//column 1:device_mac+insertTime
		Put p1 = new Put(Bytes.toBytes(row));
		//columnFamily info
		
		//column 0:device_mac
		//column 1:record_time
		//column 2:user_mac
		//column 3:ap_mac
		//column 4:data_rate
		//column 5:rssi_signal
		//column 6:channel_id
		//column 7:app_type
		//column 8:app_info

		p1.add(Bytes.toBytes(familyName), Bytes.toBytes(column), Bytes.toBytes(data));
		table.put(p1);
	    //System.out.println("put '"+row+"','"+familyName+":"+column+"','"+data+"'");
		table.close();
	}
}
