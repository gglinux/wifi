package yyx.hbase.server;


import java.util.HashSet;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class StartInsert {
	
	static public int consumerNum  = 5;
	static public int producterNum = 5;
	static public String path = "/data";
	static public HashSet<String> searchedFile;
	
	public static String Start(int consumer, int producter, String dataPath) {
		
		HBaseTable hBaseTable = null;
		String msg = "导入成功！";
		try {
			hBaseTable = new HBaseTable();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			msg = e.getMessage();
		}
		Storge storge = new Storge();
		ExecutorService service = Executors.newCachedThreadPool();
		if(producter != 0) {
			producterNum = producter;
		}
		
		if (consumer != 0) {
			consumerNum = consumer;
		}
		
		if (path != null) {
			path = dataPath;
		}
		searchedFile = new HashSet<String>();
		for (int i = 0; i < producterNum; i++) {
			service.submit(new Producer("[producer:"+i+"]", path+"/i", storge));
		}
		for (int i = 0; i < consumerNum; i++) {
			service.submit(new Consumer("[consumer:"+i+"]", hBaseTable, storge));
		}
		return msg;
	}
}

class Storge {
	BlockingQueue<Product> queue = new LinkedBlockingDeque<Product>();
	
	public void push(Product p) throws InterruptedException
	{
		queue.put(p);
	}
	public Product pop() throws InterruptedException
	{
		return queue.take();
	}
}

class Product 
{
	/*
	public String device_mac;
	public String record_time;
	public String user_mac;
	public String ap_mac;
	public String data_rate;
	public String rssi_signal;
	public String channel_id;
	public String app_type;
	*/
	private String[] data;
	
	public Product(String line)
	{
		this.data = line.split("\t");
	}
	
	public String[] getData()
	{
		return this.data; 
	}
}
