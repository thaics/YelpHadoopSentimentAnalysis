package SentimentAnalysis;

import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class SentimentMapper extends Mapper<Object, Text, Text, FloatWritable>{
	public void map(Object key, Text value, Context context) throws IOException, InterruptedException {

		if (!value.toString().contains("\"text\"")) {
            return; // Not a review we're interested in.
		}
            
		String[] strTk;
		String reviewText, starRating, business_id, predictedRating;
        strTk = value.toString().split("\",\"");
        SentimentAnalyze analyzer = new SentimentAnalyze();
        if (strTk.length >= 6) {
            business_id = strTk[2];
            starRating = strTk[3];
            reviewText = strTk[5];
            predictedRating = Float.toString(analyzer.getRating(reviewText));
            context.write(new Text(business_id), new Text(starRating + " " + predictedRating));
        }
	}
}