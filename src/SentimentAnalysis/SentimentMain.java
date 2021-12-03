package SentimentAnalysis;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.hadoop.io.Text;

public class SentimentMain extends Configured implements Tool {
	
	public static void main(String[] args) throws Exception {
        int res = ToolRunner.run(new Configuration(), new SentimentMain(), args);
        System.exit(res);
    }
	
	public int run(String[] args) throws Exception {
		
		Configuration conf =  new Configuration();
		conf.set("mapreduce.output.textoutputformat.separator", ",");
		conf.set("textinputformat.record.delimiter", Character.toString((char)13));

		Job job = Job.getInstance(conf, "Sentiment Analysis: phase 1");

		job.setJarByClass(SentimentMain.class);
		
		// set mapper + reducer 
		job.setMapperClass(SentimentMapper.class);
		job.setReducerClass(SentimentReducer.class);
		
		// set input/output value
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		// set input/output paths
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		return job.waitForCompletion(true) ? 0 : 1;
	}
	
}