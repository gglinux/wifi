package yyx.hbase.server;

public class Consumer implements Runnable{
	
	private String name;
	private Storge storge;
	private int count = 10000;
	
	public Consumer(String name, Storge storge)
	{
		this.name = name;
		this.storge = storge;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {		
			//get the product,input to hbase
			Thread.sleep(10000);
			System.out.println(storge.queue.size());
			
			while(!storge.queue.isEmpty()) {
				
				///////////////////Use HTable.put(Put p)////////////////
				String[] data = storge.pop().getData();
				String rowKey = data[0]+":"+System.nanoTime()+":"+data[1];
		       
	        	
	        	for(int i = 0; i < data.length; i++){
	        		HBaseTable.put(rowKey, "col"+i, data[0]);
	        	    System.out.println("[info:put] '"+this.name+rowKey+"data "+data[i]);
	        	}
	        	/////////////////////////////////////////////////////////
	        	
	        	/////////////////Use HTable.put(List<Put>)///////////////////
	        	
/*					String[] data = storge.pop().getData();
					String rowKey = data[0]+":"+System.nanoTime()+":"+data[1];
			       
					
		        	Put p1=new Put(Bytes.toBytes(rowKey));
		        	
		        	for(int i = 0; i < data.length; i++){
		        		p1.add(Bytes.toBytes(HBaseTable.familyName), Bytes.toBytes("col"+i), Bytes.toBytes(data[i]));
		        		StartInsert.list.add(p1);
		        	    System.out.println("[info:add] '"+this.name+rowKey+"data "+data[i]);
		        	}
	        		if(StartInsert.list.size() > this.count) {
	        	        
	        			   StartInsert.table.put(StartInsert.list);
			               StartInsert.list.clear();    
			               StartInsert.table.flushCommits();
					       System.out.println("线程:"+Thread.currentThread().getId()+"插入数据："+count+" name:"+ this.name);
	
	        		}  
 */
	        	////////////////////////////////////////////////////////////////
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}

}
