package yyx.hbase.server;

import java.util.HashSet;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;

public class StartInsert {

	static public int consumerNum = 5;
	static public int producterNum = 5;
	static public String path = "/mnt/hgfs/yyx/data/wifi_ftp_backup";

	static public HashSet<String> searchedFile = null;
	static public CopyOnWriteArrayList<Put> list = null;
	static public HTable table = null;

	public static void main(String[] argc) throws Exception {

		try {
			HBaseTable.create();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Storge storge = new Storge();
		searchedFile = new HashSet<String>();
		ExecutorService service = Executors.newCachedThreadPool();

		// ///////////////Use HTable.put(List<Put>)///////////////////

		/*
		 * table = new HTable(HBaseTable.cfg,HBaseTable.tableName);
		 * table.setAutoFlush(false); table.setWriteBufferSize(20*1024*1024);
		 * list = new CopyOnWriteArrayList<Put>();
		 */

		// //////////////////////////////////////////////////////////////

		for (int i = 0; i < producterNum; i++) {
			service.submit(new Producer("[producer:" + i + "]", path, storge));
		}
		for (int i = 0; i < consumerNum; i++) {
			service.submit(new Consumer("[consumer:" + i + "]", storge));
		}
	}

	public static void webApp(int producer, int consumer, String dataPath)
			throws Exception {

		HBaseTable.create();

		Storge storge = new Storge();
		searchedFile = new HashSet<String>();
		ExecutorService service = Executors.newCachedThreadPool();

		// ///////////////Use HTable.put(List<Put>)///////////////////

		/*
		 * table = new HTable(HBaseTable.cfg,HBaseTable.tableName);
		 * table.setAutoFlush(false); table.setWriteBufferSize(20*1024*1024);
		 * list = new CopyOnWriteArrayList<Put>();
		 */

		// //////////////////////////////////////////////////////////////
		if (dataPath.compareTo("") == 0) {
			dataPath = path;
		}

		for (int i = 0; i < producer; i++) {
			service.submit(new Producer("[producer:" + i + "]", dataPath,
					storge));
		}
		for (int i = 0; i < consumer; i++) {
			service.submit(new Consumer("[consumer:" + i + "]", storge));
		}
	}
}

class Storge {
	public BlockingQueue<Product> queue = new LinkedBlockingDeque<Product>();

	public void push(Product p) throws InterruptedException {
		// System.out.println("[product queue size] "+queue.size());
		queue.put(p);
	}

	public Product pop() throws InterruptedException {
		System.out.println("[consumer queue size] " + queue.size());
		return queue.take();
	}
}

class Product {
	/*
	 * public String device_mac; public String record_time; public String
	 * user_mac; public String ap_mac; public String data_rate; public String
	 * rssi_signal; public String channel_id; public String app_type;
	 */
	private String[] data;

	public Product(String line) {
		this.data = line.split("\t");
	}

	public String[] getData() {
		return this.data;
	}
}
