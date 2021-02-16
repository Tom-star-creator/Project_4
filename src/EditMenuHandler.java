import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TreeMap;

import javax.swing.JOptionPane;

public class EditMenuHandler implements ActionListener {

	
	private WordGUI wordGUI;
	
	/**
	 * @author Tommaso Coraci
	 * @since 12/09/20
	 * @version 1
	 * @description this is used to display the action of the buttons 
	 * and explains how each of the button works inside method 
	 * @param wordGUI
	 */
	
	EditMenuHandler(WordGUI wordGUI){
		
		this.wordGUI = wordGUI;
	}
	
	
	@Override
	
	public void actionPerformed(ActionEvent e) {
		
		 String word= JOptionPane.showInputDialog(null, "What word do you want to search?");
		 if(word == null) {
			 return; // returns if a word is a null meaning if you click on the cancel button 
		 }
		 
		 Word w = wordGUI.sortedWords.get(word); // Store the word inside a Word object
		 
		 if(w == null) {
		 JOptionPane.showMessageDialog(null, "The word isn't there" ); // indicates if the word isn't there in the TreeMap
		 }
		 else {
			 JOptionPane.showMessageDialog(null,"The word is there"); /// does indicate that the word is in the TreeMap
		 }
		
	}

	
	
	
	
}
