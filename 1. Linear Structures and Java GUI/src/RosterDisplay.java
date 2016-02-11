 import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;

/**
 *Class holds all the components for the JFrame
 *
 *@author Yavneeka Patel
 **/
public class RosterDisplay extends JPanel {

    //_________________________________________________________________________________//
    //_________________________________________________________________________________//
    /***************************** CLASS PROPERTIES **************************/
    //no class properties

    /***************************** INSTANCE PROPERTIES ***********************/
    //text field for user input
    private JTextField userInputField;

    //sets the user input equal to a blank string
    String userInput = " ";

    //declares a displayPanel of type JPanel
    JPanel displayPanel;

    //declares JTextArea for regular roster students
    JTextArea regField;
    //declares JTextArea for waitlisted students
    JTextArea waitField;
    
    //create a new instance of the roster
    RosterInfo roster = new RosterInfo();
    
    //_________________________________________________________________________________//
    //_________________________________________________________________________________//	
    /***************************** CLASS METHODS **********************/
    //none

    /***************************** INSTANCE METHODS **********************/	
    /**
     * Constructor
     */
    public RosterDisplay() {
	//use Border Layout
	setLayout (new BorderLayout());
		
	//create and add panel for user input in the South
	add (createUserInputPanel(), BorderLayout.SOUTH);
		
	//create and add display panel in the center
	add (createDisplayPanel(), BorderLayout.CENTER);
    }
	
    /**
     * Create a panel for user input with text field and button
     * @param none
     * @return input JPanel
     */
    public JPanel createUserInputPanel() {
	//create panel (by default the panel has FlowLayout)
	JPanel userInputPanel = new JPanel();
	//set this panel background black
	userInputPanel.setBackground (Color.black);
		
	//create a JTextField for the user to enter the news story
	//30 is the number of columns
	userInputField = new JTextField (30);
		
	//add to user input panel
	userInputPanel.add (userInputField);
		
	//create a button
	//display text "ADD the class!"
	JButton addButton = new JButton ("ADD the class!");
		
	//add an action listener for button's action (click)
	addButton.addActionListener (
				       new ActionListener()
				       {
					   /**
					    * invoked when associated action is performed
					    */
					   public void actionPerformed (ActionEvent e) {
					       //print out if the button was clicked
					       System.out.println ("ADD Button was clicked");
					       //get the name of the person
					       String input = userInputField.getText(); 
					       //update the student list
					       roster.updateStudent(input, "add");
					      //refresh the GUI
					       modifyDisplay();
					   }
				       }
				       );
	
	//create a button
		//display text "REMOVE the class!"
		JButton removeButton = new JButton ("REMOVE the class!");
			
		//add an action listener for button's action (click)
		removeButton.addActionListener (
					       new ActionListener()
					       {
						   /**
						    * invoked when associated action is performed
						    */
						   public void actionPerformed (ActionEvent e) {
						       //print out if the button was clicked
						       System.out.println ("REMOVE Button was clicked");
						       //get the name of the person to be removed
						       String input = userInputField.getText();
						       //update the student list
						       roster.updateStudent(input, "remove");
						       //invoke modify display
						       modifyDisplay();
						      
						   }
					       }
					       );
		//add the enter button to the input panel
		userInputPanel.add (addButton);
		
		//add the enter button to the input panel
		userInputPanel.add (removeButton);
	
	//return the user input panel
	return userInputPanel;
    }

    /**
     * Create a panel that holds the Class Type & it's intro
     * @param none
     * @return JPanel
     **/
    public JPanel createDisplayPanel() {
	//create a new display panel
	displayPanel = new JPanel();
	//set the layout to a grid layout with 2 rows, 2 columns
	displayPanel.setLayout(new GridLayout (2,2));
	//add the class type (regular or waitlisted)
	addClassType();
	//add the people in the list
	addClassStanding();
	//return the panel
	return displayPanel;
    }

    /**
     * Adds the class options (enrolled vs. waitlisted)
     * For colors, referenced: http://cloford.com/resources/colours/500col.htm
     * @param none
     * @return void
     **/
    public void addClassType() {
	//initialize instance field of regular news label
	JLabel reg = new JLabel ("ENROLLED STUDENT ROSTER (Max 5 Students): ", JLabel.CENTER);
	//set the font to Monospaced, italic, size 16
	reg.setFont(new Font ("Monospaced", Font.ITALIC, 16));
	//create new color
	Color hotPink3 = new Color (205,96,144);
	//set text equal to the color created
	reg.setForeground(hotPink3);
	//set opaque
	reg.setOpaque (true);
	//add the regular label to the display panel
	displayPanel.add (reg);
	
	//initialize instance field of waitlist news label
	JLabel wait = new JLabel ("WAITLISTED STUDENT ROSTER: ", JLabel.CENTER);
	//set the font to Monospaced, italic, size 16
	wait.setFont(new Font ("Monospaced", Font.ITALIC, 16));
	//create new color
	Color violetRed3 = new Color (205,50,120);
	//set text equal to the color created
	wait.setForeground(violetRed3);
	//set opaque
	wait.setOpaque (true);
	//add the cnn label to the display panel
	displayPanel.add (wait);
    }

    /**
     * Adds the class rosters (enrolled & waitlisted)
     * @param none
     * @return void
     **/
    public void addClassStanding() {
	//create new JText area that holds the fox news intro and breaking news
	regField = new JTextArea(roster.getEnrolled());
	//set opaque
	regField.setOpaque (true);
	//don't allow editing
	regField.setEditable (false);
	//set line wrap
	regField.setLineWrap (true);
	//set word wrapping
	regField.setWrapStyleWord (true);
	//add to display panel
	displayPanel.add (regField);
    
	//create new JText area that holds the cnn news intro and breaking news
	waitField = new JTextArea(roster.getWaitlisted());
	//set opaque
    waitField.setOpaque (true);
	//don't allow editing
    waitField.setEditable (false);
	//set line wrap
	waitField.setLineWrap (true);
	//set word wrapping
	waitField.setWrapStyleWord (true);
	//add to display panel
    displayPanel.add (waitField);
    
	//performs relayout
	//invalid content is asked for all the sizes and all the subcomponents sizes are set to proper values by Layout Manager
	//Reference: Stack Overflow (http://stackoverflow.com/questions/9510125/difference-between-validate-revalidate-and-invalidate-in-swing-gui)
	displayPanel.validate();
	//repaint
	//gets the component to repaint itself
	//Reference: Stack Overflow (http://stackoverflow.com/questions/10768619/paint-and-repaint-in-java)
	displayPanel.repaint();
    }

    public void modifyDisplay() {
    	//set the text to update
    	regField.setText (roster.getEnrolled());
    	//don't allow editing
    	regField.setEditable (false);
    	//set line wrap
    	regField.setLineWrap (true);
    	//set word wrapping
    	regField.setWrapStyleWord (true);
    	
    	//set the text to update
    	waitField.setText (roster.getWaitlisted());
    	//don't allow editing
    	waitField.setEditable (false);
    	//set line wrap
    	waitField.setLineWrap (true);
    	//set word wrapping
    	waitField.setWrapStyleWord (true);
    	
    	//performs relayout
    	displayPanel.validate();
    	//repaints component
    	displayPanel.repaint();
    }
}
