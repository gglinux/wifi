package yyx.hadoop.study;

import java.io.IOException;
import java.util.HashMap;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Mapper.Context;


public class Sort {
	
	public static class Map extends Mapper<Object, Text, IntWritable, IntWritable> 
	{
		private static IntWritable data = new IntWritable();
		public void map(Object key, Text value, Context context) throws IOException,InterruptedException
		{
			String line = value.toString();
			data.set(Integer.parseInt(line));
			context.write(data, new IntWritable(1));
		}
	}
	
	public static class Reduce extends Reducer<IntWritable, IntWritable, IntWritable, IntWritable>
	{
		private static IntWritable linenum = new IntWritable();
		public void reduce(IntWritable key,Iterable<IntWritable> values, Context context) throws IOException, InterruptedException
		{
			for(IntWritable val : values) {
				context.write(linenum, key);
				linenum =  new IntWritable(linenum.get()+1);
			}
		}
	}
	public static class Partition extends Partitioner<IntWritable, IntWritable>
	{

		@Override
		public int getPartition(IntWritable key, IntWritable value,
				int numPartitions) {
			// TODO Auto-generated method stub
			
			
			return 0;
		}
		
	}
}
