package yyx.hbase.insert;

public class Producer implements Runnable{
	
	private String nameString;
	private String pathString;
	private Storge storge;
	
	public Producer(String name, String path, Storge storge)
	{
		this.nameString = name;
		this.pathString = path;
		this.storge = storge;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		//read the all file in path
	}
	
}
