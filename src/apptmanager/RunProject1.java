package apptmanager;

/**
This is the driver class used to run the project.
The main method invokes run() of the Kiosk class.
@author Dharma Wijesinghe, Min Sun You
*/
public class RunProject1 {
    
    /**
    Runs the project by calling the run() method from the Kiosk class.
    @param args String array to hold any argument from the command line.
    */
	public static void main(String[] args) {
	    new Kiosk().run(); 
	}
}