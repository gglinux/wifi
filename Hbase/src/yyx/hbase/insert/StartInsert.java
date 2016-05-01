package yyx.hbase.insert;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class StartInsert {
	
	static public int consumerNum  = 5;
	static public int producterNum = 5;
	static public String path = "/data";
	
	public static void main(String[] args) throws Exception {
		HBaseTable hBaseTable = new HBaseTable();
		Storge storge = new Storge();
		ExecutorService service = Executors.newCachedThreadPool();
		
		for (int i = 0; i < producterNum; i++) {
			service.submit(new Producer("[producer:"+i+"]", path+"/i", storge));
		}
		for (int i = 0; i < consumerNum; i++) {
			service.submit(new Consumer("[consumer:"+i+"]", hBaseTable, storge));
		}
	}
}

class Storge {
	BlockingQueue<Product> queue = new LinkedBlockingDeque<>();
	
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
