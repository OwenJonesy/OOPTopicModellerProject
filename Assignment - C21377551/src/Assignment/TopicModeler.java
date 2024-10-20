/* This is a Java class called "TopicModeler" that analyzes two input documents to determine how likely they are to be
 * about the same topic. It does this by analyzing the words in each document and returning a list of the most common words,
 * excluding stop words. It then calculates the overlap between the two sets of most common words and returns the result as
 * a percentage. The class includes methods for analyzing a document, getting the most common words in a document, 
 * calculating the overlap between two sets of words, and determining the likelihood that two documents are about the same topic.
 */

package Assignment;
import java.util.*;


public class TopicModeler {
	
	//declare variables
    public double likelihood;
    public  List<String> Words;
    private List<String> mostCommonWords; // declare mostCommonWords as a field
    
    // Analyze the words in a document and return the most common words (excluding stop words), takes in string, returns list of most common
    public static List<String> analyzeDocument(String document) {
    	
    	//converts words to lowercase for comparison
        List<String> words = Arrays.asList(document.toLowerCase().split("[\\W']+"));
        
        //creates hashmap for word frequency
        Map<String, Integer> wordCounts = new HashMap<>();
        
        //adds word to map if it does not contain a stopword
        for (String word : words) {
            if (!readStrings.STOP_WORDS.contains(word)) {
                wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
            }
        }
        
        //contains all entries from the Map and stores it into a list
        List<Map.Entry<String, Integer>> sortedWords = new ArrayList<>(wordCounts.entrySet());
        
        //sorts them in reverse so descending instead os ascending
        sortedWords.sort(Collections.reverseOrder(Map.Entry.comparingByValue()));
        
        //stores common words and loops through the most common (x) in this case its 25 words
        List<String> mostCommonWords = new ArrayList<>();
        for (int i = 0; i < 25 && i < sortedWords.size(); i++) {
            mostCommonWords.add(sortedWords.get(i).getKey());
        }
        return mostCommonWords;
    }
    
    public List<String> getMostCommonWords() {
        return mostCommonWords;
    }
    
 // Calculate the overlap between two sets of words
    private static double calculateOverlap(List<String> words1, List<String> words2) {
        
        // Create two sets of unique words from the input lists
        Set<String> set1 = new HashSet<>(words1);
        Set<String> set2 = new HashSet<>(words2);
        
        // Initialize a counter variable to count the number of overlapping words
        int intersectionSize = 0;
        
        // Iterate over the words in set1
        for (String word : set1) {
            
            // If the word also appears in set2, increment the counter
            if (set2.contains(word)) {
                intersectionSize++;
            }
        }
        
        // Calculate the overlap as the ratio of intersectionSize to the size of the smaller set
        // and return it as a double
        return (double) intersectionSize / Math.min(words1.size(), words2.size());
    }

    
 // Determine the likelihood that two documents are about the same topic based on the overlap in their most common words
    public static double determineLikelihood(String document1, String document2) {
        
        // Extract the words from document1 and document2 using the analyzeDocument method
        List<String> words1 = analyzeDocument(document1);
        List<String> words2 = analyzeDocument(document2);
        
        // Calculate the overlap between the two sets of words using the calculateOverlap method
        double overlap = calculateOverlap(words1, words2);
        
        // Return the overlap as a percentage
        return overlap * 100;
    }


}
