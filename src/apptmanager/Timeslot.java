package apptmanager;

/**
This class defines a Timeslot object which contains a Date and Time object as values.
It contains methods to compare Timeslot objects and to convert them to a string.
@author Dharma Wijesinghe, Min Sun You
*/
public class Timeslot implements Comparable<Timeslot> {
    private Date date;
    private Time time;
    
    /**
    Initializes Timeslot object with given Date and Time.
    @param date.
    @param time.
    */
    public Timeslot(Date date, Time time) {
        this.date = date;
        this.time = time;
    }
    
    /**
    Returns the Date of this Timeslot.
    @return Date.
    */
    public Date getDate() {
        return date;
    }
    
    /**
    Returns the Time of this Timeslot.
    @return time.
    */
    public Time getTime() {
        return time;
    }
    
    /**
    Returns String representation of the calling Timeslot object.
    @return String.
    */
    @Override
    public String toString() {
        return date.toString() + ", " + time.toString() + ", ";
    }
    
    /**
    Compares two Timeslots.
    @param slot the Timeslot being compared to the calling Timeslot object.
    @return 1 if calling Timeslot object is greater than the parameter timeslot, 0 if equal, -1 otherwise.
    */
    @Override
    public int compareTo(Timeslot slot) {
        if(this.date.compareTo(slot.date) == 1) { //this.date > slot.date
            return 1;
        }
        else if(this.date.compareTo(slot.date) == -1) { //this.date < slot.date
            return -1;
        }
        else { //equal dates
            if(this.time.compareTo(slot.time) == 1) {
                return 1;
            }
            else if(this.time.compareTo(slot.time) == -1) {
                return -1;
            }
            else {
                return 0;
            }
        }
    }
    
    /**
    Testbed main for the Timeslot class.
    @param args String array to hold any argument from the command line.
    */
    public static void main(String[] args) {
        //Test case 1
        System.out.println("Test 1: calling slot has later date");
        Timeslot testSlot1 = new Timeslot(new Date("2/6/2022"), new Time("9:15"));
        Timeslot testSlot2 = new Timeslot(new Date("1/12/2022"), new Time("10:00"));
        System.out.println("Output: " + testSlot1.compareTo(testSlot2));
        if(testSlot1.compareTo(testSlot2) == 1) {
            System.out.println("Test passed");
        } else {
            System.out.println("Test failed");
        }
        
        //Test case 2
        System.out.println();
        System.out.println("Test 2: calling slot has earlier date");
        Timeslot testSlot3 = new Timeslot(new Date("12/17/2021"), new Time("9:15"));
        Timeslot testSlot4 = new Timeslot(new Date("3/21/2022"), new Time("10:00"));
        System.out.println("Output: " + testSlot3.compareTo(testSlot4));
        if(testSlot3.compareTo(testSlot4) == -1) {
            System.out.println("Test passed");
        } else {
            System.out.println("Test failed");
        }
        
        //Test case 3
        System.out.println();
        System.out.println("Test 3: both slots have same date, calling slot has later time");
        Timeslot testSlot5 = new Timeslot(new Date("6/24/2022"), new Time("11:15"));
        Timeslot testSlot6 = new Timeslot(new Date("6/24/2022"), new Time("10:30"));
        System.out.println("Output: " + testSlot5.compareTo(testSlot6));
        if(testSlot5.compareTo(testSlot6) == 1) {
            System.out.println("Test passed");
        } else {
            System.out.println("Test failed");
        }
        
        //Test case 4
        System.out.println();
        System.out.println("Test 4: both slots have same date, calling slot has earlier time");
        Timeslot testSlot7 = new Timeslot(new Date("4/18/2022"), new Time("11:15"));
        Timeslot testSlot8 = new Timeslot(new Date("4/18/2022"), new Time("14:45"));
        System.out.println("Output: " + testSlot7.compareTo(testSlot8));
        if(testSlot7.compareTo(testSlot8) == -1) {
            System.out.println("Test passed");
        } else {
            System.out.println("Test failed");
        }
        
        //Test case 5
        System.out.println();
        System.out.println("Test 5: both slots have same date and time");
        Timeslot testSlot9 = new Timeslot(new Date("7/4/2022"), new Time("15:30"));
        Timeslot testSlot10 = new Timeslot(new Date("7/4/2022"), new Time("15:30"));
        System.out.println("Output: " + testSlot9.compareTo(testSlot10));
        if(testSlot9.compareTo(testSlot10) == 0) {
            System.out.println("Test passed");
        } else {
            System.out.println("Test failed");
        }
    }
}