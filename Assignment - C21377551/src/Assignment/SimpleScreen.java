/*  This is a Java class that extends the JFrame class and implements the MouseListener interface.
 *  It creates a GUI with a text area, several buttons, and a label. The buttons allow the user to choose two files,
 *  analyze them to determine their similarity, display the most common words in the documents, and clear the text area.
 *  The program uses the TopicModeler class to perform the text analysis. The class also sets the properties of the text area
 *  and adds the buttons and other components to the GUI.
 */

package Assignment;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class SimpleScreen extends JFrame implements MouseListener {

    /**
	 * 
	 */
	//private instance variables
	private static final long serialVersionUID = 1L;
	private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JPanel mainPanel;
    private String document1;
    private String document2;
    private  String fileName;
    private  String fileName2;

    public SimpleScreen() {

    	//creates the text area
    	 JTextArea textArea = new JTextArea();
         textArea.setEditable(false);//prevents text area being edited
         textArea.setLineWrap(true); // Set the line wrap property to true
         textArea.setWrapStyleWord(true); // Set the wrap style property to wrap at word boundaries
         textArea.setBackground(Color.black);//sets background to black
         textArea.setForeground(Color.white);//sets text colour to white

   
         
    	//BUTTON 1  once 2 files are selected correctly, the run button allows the core of the program to run
        button1 = new JButton("Run");  
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {	
            	try {
                    // Read the contents of doc1.txt and doc2.txt files
                    document1 = readFile(fileName);
                    document2 = readFile(fileName2);

                    double overlap = TopicModeler.determineLikelihood(document1, document2);
                    String formattedOverlap = String.format("%.2f", overlap);
                    JOptionPane.showMessageDialog(null, "The similarity between the topics is calculated to be : " + formattedOverlap + "%");

                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }

        });
        
        
        //BUTTON 2 displays the most common words and also the 2 text files which are being compared
        button2 = new JButton("Display Words");
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	textArea.append("The files being compared are  " + fileName +"  and  " + fileName2 + "\n" );
            	textArea.append("The most common words in the documents are: \n\n");
            	
                List<String> mostCommonWords = TopicModeler.analyzeDocument(document1);
                
                for (String element : mostCommonWords) {
                    textArea.append(element + "  ");
                }
                
                textArea.append("\n\n");
                List<String> mostCommonWords2 = TopicModeler.analyzeDocument(document2);
                //System.out.println(mostCommonWords2);
                for (String element : mostCommonWords2) {
                    textArea.append(element + "  ");
                }

            }
        });
        
        //BUTTON 3 to allow user to choose the 2 files for comparison
        button3 = new JButton("File chooser");
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	// Create a new file chooser for first file
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File("."));

                // Show the file chooser dialog
                int result = fileChooser.showOpenDialog(new JFrame());

                // If the user selects a file, get the file name and save it to a string variable
                 fileName = "";
                if (result == JFileChooser.APPROVE_OPTION) {
                    fileName = fileChooser.getSelectedFile().getName(); //sets fileName to the files name if approved
                    System.out.println("Selected file: " + fileName);
                } else {
                    System.out.println("No file selected.");//error check
                }
                
             // Create a new file chooser for second file
                JFileChooser fileChooser2 = new JFileChooser();

                fileChooser2.setCurrentDirectory(new File("."));
                // Show the file chooser dialog
                int result2 = fileChooser2.showOpenDialog(new JFrame());

                // If the user selects a file, get the file name and save it to a string variable
                 fileName2 = "";
                if (result2 == JFileChooser.APPROVE_OPTION) {
                    fileName2 = fileChooser2.getSelectedFile().getName();//sets fileName2 to file name
                    System.out.println("Selected file: " + fileName2);

                } else {
                    System.out.println("No file selected.");//error check
                }

            }
        });
        
        //BUTTON 4 clears the textarea
        button4 = new JButton("Clear");
        button4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	textArea.setText("");
            }
        });

        //create  new panels
        mainPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        ImageIcon icon = new ImageIcon("file.png");
        JLabel label = new JLabel(icon);
        
        
        

        //adds buttons to the button panel
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
        buttonPanel.add(button4);
        
        //adds text area, label and button pannel to the main panel
        mainPanel.add(textArea);
        mainPanel.add(label, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel);
        
        
        JFrame newFrame = new JFrame("Topic Modeller"); // Create a new JFrame object with the specified title
        newFrame.add(mainPanel); // Add mainPanel to the new JFrame
        newFrame.setResizable(false); //prevent window being resized
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit the application when the window is closed
        newFrame.setSize(450, 400); // Set the size of the new JFrame
        newFrame.setVisible(true); //make window visible
        ImageIcon image = new ImageIcon("logo_assignment.png");//set image
		newFrame.setIconImage(image.getImage());//add image to the border
		
		
    }

    
    // A utility method to read the contents of a file
    private static String readFile(String fileName) throws IOException {
    	
        StringBuilder sb = new StringBuilder();//creates StringBuilder object sb
        
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            
            while ((line = br.readLine()) != null) {//reads until end of file
                sb.append(line);
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }
    // MouseListener methods
    public void mouseClicked(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}
    
}//end Simple screen class


