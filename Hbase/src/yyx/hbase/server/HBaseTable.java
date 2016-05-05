package yyx.hbase.server;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableExistsException;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.RetriesExhaustedWithDetailsException;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;

public class HBaseTable 
{
	private static String tableName = "wifi_record2";
	private static String familyName = "info";
	
	
	static Configuration cfg = new Configuration();
	
	// create the hbase table;
	public static void create() throws Exception
	{
		HBaseAdmin admin = new HBaseAdmin(cfg);
		if (admin.tableExists(tableName)) {
			System.out.println("[info]table has created!");
		} else {
			TableName table = TableName.valueOf(tableName);
			HTableDescriptor tableDescriptor = new HTableDescriptor(table);
			tableDescriptor.addFamily(new HColumnDescriptor(familyName));
			try{
				admin.createTable(tableDescriptor);
			}catch(TableExistsException e)
			{
				System.out.println("[warning] table exists!");
			}
		}
		System.out.println("[info]create table success!");
	}
	
	//put data
    //添加一条数据，通过HTable Put为已经存在的表来添加数据
    public static void put(String row,String column,String data) throws Exception {
        HTable table = new HTable(cfg, tableName);
        table.setAutoFlush(false);
        table.setWriteBufferSize(10*1024*1024);
        Put p1=new Put(Bytes.toBytes(row));
        p1.add(Bytes.toBytes(familyName), Bytes.toBytes(column), Bytes.toBytes(data));
        table.put(p1);
        System.out.println("put '"+row+"','"+familyName+":"+column+"','"+data+"'");
    }
    //显示所有数据，通过HTable Scan来获取已有表的信息
    public static void scan() throws Exception{
         HTable table = new HTable(cfg, tableName);
         Scan s = new Scan();
         ResultScanner rs = table.getScanner(s);
         for(Result r:rs){
             System.out.println("Scan: "+r);
         }
    }
}
