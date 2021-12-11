package SentimentAnalysis;

import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class SentimentReducer extends Reducer<Text, FloatWritable, Text, FloatWritable> {
	public void reduce(Text key, Iterable<FloatWritable> values, Context context) throws IOException, InterruptedException {
		float sentimentTotal = 0, count = 0;
		for(FloatWritable value : values) {
			sentimentTotal += value.get();
			count++;
		}
		
		FloatWritable result;
		if(count == 0) result = new FloatWritable(0);
		else result = new FloatWritable(sentimentTotal / count);
		
		// key = review_id
 		context.write(key, result);
	}
}