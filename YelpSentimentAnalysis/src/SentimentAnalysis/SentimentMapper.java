package SentimentAnalysis;

import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.codehaus.jackson.map.ObjectMapper;

public class SentimentMapper extends Mapper<Object, Text, Text, FloatWritable>{
	
	private SentimentAnalyze sentimentAnalyzer;
	private ObjectMapper deserializer;
	
	@Override
	protected void setup(Context context) throws IOException, InterruptedException {
		sentimentAnalyzer = new SentimentAnalyze();
		deserializer = new ObjectMapper();
    }
	
	public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
		Review review = deserializer.readValue(value.toString(), Review.class);
		
		String businessId = review.business_id;
		String text = review.text;
		
		FloatWritable predictedRating = new FloatWritable(sentimentAnalyzer.getSentiment(text));
		
		context.write(new Text(businessId), predictedRating);
	}
}