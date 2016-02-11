import javax.swing.JFrame;

/**
 * RosterApplication
 * Creates the frame for the roster
 * @author patel22y
 */
public class RosterApplication {

	/**
	 * * main method starts the program
	**/
	public static void main( String[] args )
	{
	//1. Create a new frame
	JFrame frame = new JFrame("Get Your Horoscope Here!!!");
	
	//2. Add the RosterDisplay into the frame
	frame.add(new RosterDisplay());
	
	//3. Set the size of the Frame
	frame.setSize(1000,500);
	
	//4. Ensures proper closure of frame
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	//5. Set the frame visible
	frame.setVisible(true);
	}
}
