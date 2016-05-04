package yyx.hbase.server;

public class Consumer implements Runnable{
	
	private String name;
	private HBaseTable hBaseTable;
	private Storge storge;
	
	public Consumer(String name, HBaseTable hBaseTable, Storge storge)
	{
		this.name = name;
		this.hBaseTable = hBaseTable;
		this.storge = storge;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		//get the product,input to hbase
		try {	
				String[] data = storge.pop().getData();
				String rowKey = data[0]+System.nanoTime();
				if (data.length != 8) {
					System.out.println(name+"!!!!!!!!!!!!!!Wrong!!!!!!!!!!!!");
					System.exit(-1);
				}
				
				hBaseTable.put(rowKey, "device_mac", data[0]);
				hBaseTable.put(rowKey, "record_time", data[1]);
				hBaseTable.put(rowKey, "user_mac", data[2]);
				hBaseTable.put(rowKey, "ap_mac", data[3]);
				hBaseTable.put(rowKey, "data_rate", data[4]);
				hBaseTable.put(rowKey, "rssi_signal", data[5]);
				hBaseTable.put(rowKey, "channel_id", data[6]);
				hBaseTable.put(rowKey, "app_type", data[7]);
				hBaseTable.put(rowKey, "app_info", data[8]);
	
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
	}

}
