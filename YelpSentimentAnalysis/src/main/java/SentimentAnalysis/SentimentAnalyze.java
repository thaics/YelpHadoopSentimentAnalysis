package SentimentAnalysis;

import java.io.*;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;

public class SentimentAnalyze {
	Set<String> posWords;
  	Set<String> negWords;
  	
  	public SentimentAnalyze() throws IOException {
  		posWords = new HashSet<String>();
  	  	negWords = new HashSet<String>();

  	  	Scanner negScanner = new Scanner(new File("../YelpSentimentAnalysis/src/main/data/neg_words.txt"));
  	  	Scanner posScanner = new Scanner(new File("../YelpSentimentAnalysis/src/main/data/pos_words.txt"));
  	  	
  	  	// initialize and read in pos/neg words
  	  	while (posScanner.hasNext()) {
  	  		posWords.add(posScanner.nextLine());
  	  	}
  	  	while (negScanner.hasNext()) {
  	  		negWords.add(negScanner.nextLine());
  	  	}
	  
  	  	posScanner.close();
  	  	negScanner.close();
  	}
	
  	
  public float getRating(String input){
	if(input == null || input.isEmpty()) return 0f;
	
    // preprocessing + cleaning up possible dirty data
	input = input.toLowerCase();
	input = input.trim();
	
	// keep only alphanumeric characters
	input = input.replaceAll("[^a-zA-Z0-9\\s]", "");

    float negCount = 0;
    float posCount = 0;
    
    for(String word: input.split(" ")) {
    	if(posWords.contains(word)) {
    		posCount ++;
    	}else if(negWords.contains(word)) {
    		negCount ++;
    	}
    }
    
    float netSentiment = posCount - negCount;
    float total = posCount + negCount;
    
    return total == 0 ? 0f : netSentiment / total;
  }
  
  public static void main(String[] args) {
	  String input = "This is a course about the Transformers good library";
	  try {
		SentimentAnalyze analyzer = new SentimentAnalyze();
		System.out.println(analyzer.getRating(input));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
}