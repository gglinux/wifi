package yyx.hbase.server;

public class Consumer implements Runnable{
	
	private String name;
	private Storge storge;
	
	public Consumer(String name, Storge storge)
	{
		this.name = name;
		this.storge = storge;
		System.out.println("[info] Thread:"+name+" start!");
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		//get the product,input to hbase
		try {	
				String[] data = storge.pop().getData();
				String rowKey = data[0]+":"+System.nanoTime()+":"+data[1];
				
				if (data.length != 9) {
					System.out.println(name+"!!!!!!!!!!!!!!Wrong!!!!!!!!!!!!"+data.length);
					System.exit(-1);
				}
				HBaseTable.put(rowKey, "device_mac", data[0]);
				HBaseTable.put(rowKey, "record_time", data[1]);
				HBaseTable.put(rowKey, "user_mac", data[2]);
				HBaseTable.put(rowKey, "ap_mac", data[3]);
				HBaseTable.put(rowKey, "data_rate", data[4]);
				HBaseTable.put(rowKey, "rssi_signal", data[5]);
				HBaseTable.put(rowKey, "channel_id", data[6]);
				HBaseTable.put(rowKey, "app_type", data[7]);
				HBaseTable.put(rowKey, "app_info", data[8]);
				Thread.sleep(1000);
					
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
	}

}
