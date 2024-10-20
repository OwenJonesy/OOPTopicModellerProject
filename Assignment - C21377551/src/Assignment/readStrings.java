/* This class provides a method to read stop words from a file and store them in a HashSet.
 * The stop words are stored as static final fields, meaning they cannot be modified once set.
 * The readStopWordsFromFile method reads the file line by line, splits each line into words, and adds
 * them to the set in lowercase. The method throws an IOException if the file cannot be read. 
 */


package Assignment;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class readStrings {
    // A set of stop words to be read from a file
    public static final Set<String> STOP_WORDS = new HashSet<>();

    // Reads stop words from a file and adds them to the STOP_WORDS set
    public static void readStopWordsFromFile(String fileName) throws IOException {
        // Open file and create BufferedReader object
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        
        // Read each line of the file
        String line;
        while ((line = reader.readLine()) != null) {
            // Split the line into individual words based on whitespace
            String[] words = line.split("\\s+");
            
            // Add each word to STOP_WORDS set in lowercase
            for (String word : words) {
                STOP_WORDS.add(word.toLowerCase());
            }
        }
        
        // Close BufferedReader to release resources
        reader.close();
    }
}

		
