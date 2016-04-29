package yyx.hbase.insert;

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
	}

}
