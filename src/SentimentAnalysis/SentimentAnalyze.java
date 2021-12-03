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

  	  	Scanner negScanner = new Scanner(new File("/Users/thai/Documents/Git/YelpHadoopSentimentAnalysis/src/wordsFile/neg_words.txt"));
  	  	Scanner posScanner = new Scanner(new File("/Users/thai/Documents/Git/YelpHadoopSentimentAnalysis/src/wordsFile/pos_words.txt"));
  	  	
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
    // removing all non alphabetic/numbers
	input = input.toLowerCase();
	input = input.trim();
	input = input.replaceAll("[^a-zA-Z0-9\\s]", "");

    int negCount = 0;
    int posCount = 0;
    
    for(String word: input.split(" ")) {
    	if(posWords.contains(word)) {
    		posCount ++;
    	}else if(negWords.contains(word)) {
    		negCount ++;
    	}
    }
    
    float netSentiment = posCount - negCount;
    float total = posCount + negCount;
    
    return netSentiment / total;
  }
}