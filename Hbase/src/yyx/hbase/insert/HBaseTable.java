package yyx.hbase.insert;

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
			HTableDescriptor tableDescriptor = new HTableDescriptor(tableName);
			tableDescriptor.addFamily(new HColumnDescriptor(familyName));
			admin.createTable(tableDescriptor);
			System.out.println("create table success!");
			
		}
	}
	
	//put data
	public void put(String row, String column, String data) throws Exception
	{
		HTable table = new HTable(cfg, tableName);
		Put p1 = new Put(Bytes.toBytes(row));
		p1.add(Bytes.toBytes(familyName), Bytes.toBytes(column), Bytes.toBytes(data));
		table.put(p1);
	    System.out.println("put '"+row+"','"+familyName+":"+column+"','"+data+"'");
	}
}
