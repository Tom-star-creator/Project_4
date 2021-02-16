import java.awt.GridLayout;
import java.awt.TextArea;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class WordGUI extends JFrame {
	

/**
 * @author Tommaso Coraci
 * @since 12/09/20
 * @version 1
 * @description this is where all the ArrayLists and TreeMap are display to be outputed into a GUI
 * @param title takes in the title of the GUI
 * @param height takes in the height of the GUI
 * @param width takes in the width of the GUI
 * @param sortedWords used to take in the TreeMap objects in the EditMenu Handler Class
 * 
 **/
	TreeMap<String, Word> sortedWords;
   
	public WordGUI(String title, int height, int width) {
        setTitle(title);
        setSize(height, width);
        setLocation(400, 200);
        createFileMenu(); // this is the used for the createFileMenu method
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(1, 2));
        this.setVisible(true);
    }
/**
 * 
 * @param f takes in the file thats being used to be read through the strings
**/
    public void createColumns(File f) { 
        Scanner filescanner = null; 
        try {
            filescanner = new Scanner(f);// used a scanner to take in the file
        } catch (FileNotFoundException e1) { // uses an exeception in case there isn't a file to use
            return;
        }
        
       // Unsorted words = arrayList
       // Sorted words = TreeMap
        
         sortedWords = new TreeMap<>();
        
        
        
        ArrayList<Word> unsorted = new ArrayList<Word>();  // created an ArrayList that takes in a Word object from the Word class
      // ArrayList<Word> sorted = new ArrayList<Word>(); // this is similar to the sorted ArrayList
      //ArrayList<String> erroneous = new ArrayList<String>(); // this Arraylist uses a String instead of a Word
       
        while (filescanner.hasNextLine()) { // use a while loop to go through each line inside the scanner that contains the file
            // fill ArrayList from words in file
            String line = filescanner.nextLine();
            String[] words = line.split("\\W+");
            // store the right words in the right ArrayList
            for (int i = 0; i < words.length; i++) {
            	Word word = new Word(words[i]);
            	//unsorted.add(word);
            	 if(words[i].matches("[a-zA-Z]+")){ // matches to see if the String is erroneous or not
                   	//throw new IllegalWordException("Erroneous word");
            		 unsorted.add(word);
            		 sortedWords.put(words[i],word); // used to put in key-value pairs into the TreeMap
                 }
            	//sortedWords.put(word, words[i]);
            	
            }
            
            
        }
        
        
        
       
            
        
        filescanner.close(); // closes the Scanner object
        
        TextArea unsortedTextArea = new TextArea();
        TextArea sortedTextArea = new TextArea();
        
        //Used iterator to display the words in the sortedTexArea
        
        Set<Entry<String, Word>> set = sortedWords.entrySet();
        Iterator<Entry<String, Word>> i = set.iterator();
        
        while (i.hasNext()) {
            Entry<String, Word> me = i.next();
            sortedTextArea.append(me.getValue() + "\n");
        }
        
        for(Word word : unsorted) {
        	unsortedTextArea.append(word.toString() +"\n");
        	//System.out.println(word.toString() +"\n");
        }
        

       
        this.add(unsortedTextArea);
        this.add(sortedTextArea);
       // this.add(erroneousTextArea);
        
        
        this.validate();
        this.repaint();
    }

    private void createFileMenu() {
        // this whole section allows for you to go thought the Project4 file
    	
    	/*
    	 * Add a second menu to the GUI called Edit which will have one menu item called Search. 
    	 * Clicking on search should prompt the user using a JOptionPane input dialog to enter a Word.
			The program should then use a JOptionPane message dialog to tell if the word is in the TreeMap or not. 
			You will need to write a second menu handler called EditMenuHandler.
    	 */
    	
    	JMenuItem item; 
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit"); // This is used to create a JMenu for the EditMenuHandler
        FileMenuHandler fmh = new FileMenuHandler(this);

        item = new JMenuItem("Open"); // Open...
        item.addActionListener(fmh);
        fileMenu.add(item);

        fileMenu.addSeparator(); // add a horizontal separator line

        item = new JMenuItem("Quit"); // Quit
        item.addActionListener(fmh);
        fileMenu.add(item);
        
        item = new JMenuItem("Search"); 
        EditMenuHandler editMenuHandler = new EditMenuHandler(this);
        item.addActionListener(editMenuHandler);
        editMenu.add(item);

        setJMenuBar(menuBar);
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
    }
}

