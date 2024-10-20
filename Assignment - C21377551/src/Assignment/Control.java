/*  Title: Topic Modeler
 *  Author: Owen Jones C21377551
 *  Date : 07/04/2023
 *  The "Control" class has a "main" method that initializes the program. It first calls the "readStopWordsFromFile"
 *  method from the "readStrings" class to read in a list of stop words from a file named "stopwords.txt". 
 *  If an exception is thrown while reading the file, an error message is printed. Then, it creates a new instance of
 *  the "SimpleScreen" class, which is responsible for displaying a user interface for the program.
 */

package Assignment;

import java.io.IOException;

public class Control {

	public static void main(String[] args) {
		try {
	        readStrings.readStopWordsFromFile("stopwords.txt");//reads in stopwords text file
	       
	    } catch (IOException e) {
	        System.out.println("Error reading stop words file: " + e.getMessage());//error checks
	    }
		
    	new SimpleScreen();
    }
}
