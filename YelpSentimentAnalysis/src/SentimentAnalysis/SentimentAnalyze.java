package SentimentAnalysis;

import java.io.*;
import java.util.Set;
import java.util.HashSet;

public class SentimentAnalyze {
	Set<String> posWords;
  	Set<String> negWords;
  	
  	public SentimentAnalyze() throws IOException {
  		posWords = new HashSet<String>();
  	  	negWords = new HashSet<String>();
  	
  	  	InputStream negFile = getClass().getResourceAsStream("neg_words.txt"); 
  	  	InputStream posFile = getClass().getResourceAsStream("pos_words.txt"); 
  	  	BufferedReader negReader = new BufferedReader(new InputStreamReader(negFile));
  	  	// initialize and read in pos/neg words
  	  	String line;
  	  	while ((line = negReader.readLine()) != null){
  	  		negWords.add(line);
  	  	}
  	  	negReader.close();
  	  	
  	  	BufferedReader posReader = new BufferedReader(new InputStreamReader(posFile));
  	  	while ((line = posReader.readLine()) != null){
	  		posWords.add(line);
	  	}
  	  	posReader.close();
  	}
	
  	
  public float getSentiment(String input){
	if(input == null || input.isEmpty()) return 0f;
	
    // preprocessing + cleaning up possible dirty data
	input = input.toLowerCase();
	input = input.trim();
	
	// keep only alphanumeric characters
	input = input.replaceAll("[^a-zA-Z0-9\\s]", "");

    float sentimentScore = 0f;
    
    for(String word: input.split(" ")) {
    	if(posWords.contains(word)) {
    		sentimentScore++;
    	}else if(negWords.contains(word)) {
    		sentimentScore--;
    	}
    }
 
    return sentimentScore;
  }
}