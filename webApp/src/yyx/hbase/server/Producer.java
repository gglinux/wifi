package yyx.hbase.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

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
		// get the file,read one line,insert to queue
		ArrayList<File> files = getListFiles(this.pathString);
		for (File file : files) {
	        BufferedReader reader = null;
	        try {
	            //System.out.println("以行为单位读取文件内容，一次读一整行：");
	            reader = new BufferedReader(new FileReader(file));
	            String tempString = null;
	            int line = 1;
	            // 一次读入一行，直到读入null为文件结束
	            while ((tempString = reader.readLine()) != null) {
	                // 显示行号
	            	storge.push(new Product(tempString));
	                //System.out.println("line " + line + ": " + tempString);
	                line++;
	            }
	            reader.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            if (reader != null) {
	                try {
	                    reader.close();
	                } catch (IOException e1) {
	                }
	            }
	        }
		}
	}
	
	/***
	 * 获取指定目录下的所有的文件（不包括文件夹），采用了递归
	 * 
	 * @param obj
	 * @return
	 */
	public ArrayList<File> getListFiles(Object obj) {
		File directory = null;
		if (obj instanceof File) {
			directory = (File) obj;
		} else {
			directory = new File(obj.toString());
		}
		ArrayList<File> files = new ArrayList<File>();
		if (directory.isFile()) {
			files.add(directory);
			//System.out.println(nameString + " path: "+obj.toString());
			return files;
		} else if (directory.isDirectory()) {
			File[] fileArr = directory.listFiles();
			for (int i = 0; i < fileArr.length; i++) {
				File fileOne = fileArr[i];
				files.addAll(getListFiles(fileOne));
			}
		}
		//System.out.println(nameString+" path: "+obj.toString());
		return files;
	}
}
