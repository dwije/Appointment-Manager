package apptmanager;

import java.util.Calendar;

/**
This class defines a date object which holds a day, month, and year value.
It contains methods to check if a Date is valid, compare two Date objects, and convert a Date into a string.
@author Dharma Wijesinghe, Min Sun You
*/
public class Date implements Comparable<Date> {
	private int year;
	private int month;
	private int day;
	
	//Constants
	private static final int[] LONG_MONTHS = {1, 3, 5, 7, 8, 10, 12}; //Array of months containing 31 days
	private static final int LONG_MONTH_DAYS = 31;
	private static final int SHORT_MONTH_DAYS = 30;
	private static final int FEBRUARY = 2;
	private static final int FEB_NON_LEAP_YEAR_DAYS = 28;
	private static final int FEB_LEAP_YEAR_DAYS = 29;
	private static final int MAX_NUM_OF_MONTHS = 12;
	private static final int QUADRENNIAL = 4;
	private static final int CENTENNIAL = 100;
	private static final int QUARTER_CENTENNIAL = 400;
	
	/**
	Initializes the Date object with given date formatted string.
	@param date String containing the date to be initialized.
	*/
	public Date (String date) {
	    int slashIndex = date.indexOf("/");
		month = Integer.parseInt(date.substring(0, slashIndex));
		int secondSlashIndex = date.indexOf("/", slashIndex + 1);
		day = Integer.parseInt(date.substring(slashIndex + 1, secondSlashIndex));
		year = Integer.parseInt(date.substring(secondSlashIndex + 1));
	}
	
	/**
	Initializes the Date object with today's date.
	*/
	public Date() {
		Calendar todaysDate = Calendar.getInstance();
		month = todaysDate.get(Calendar.MONTH) + 1;
		day = todaysDate.get(Calendar.DAY_OF_MONTH);
		year = todaysDate.get(Calendar.YEAR);
	}

	/**
    Returns the year value of the calling Date object.
    @return the year of the calling Date object.
    */
    public int year() {
        return year;
    }
	
	/**
	Checks to see if the calling Date object has a long month (a month containing 31 days).
	@return true if it has long month, false otherwise.
	*/
	private boolean hasLongMonth() {
	    for(int i: LONG_MONTHS) {
	        if(i == this.month) {
	            return true;
	        }
	    }
	    return false;
	}
	
	/**
	Checks if the calling Date object is a valid date.
	@return true if the Date object is valid, false otherwise.
	*/
	public boolean isValid() {
	    if(year < 0) {
	        return false;
	    }
		boolean isLeapYear = false;
		if(this.year % QUADRENNIAL == 0 && this.year % CENTENNIAL != 0) {
			isLeapYear = true;
		} else if(this.year % QUADRENNIAL == 0 && this.year % CENTENNIAL == 0 && this.year % QUARTER_CENTENNIAL == 0) {
			isLeapYear = true;
		}
		if(month <= MAX_NUM_OF_MONTHS && month > 0) {
		    if(this.hasLongMonth()) {
		        if(day <= LONG_MONTH_DAYS && day > 0) {
		            return true;
		        }
		    } else {
		        if(this.month != FEBRUARY && this.day <= SHORT_MONTH_DAYS) {
		            return true;
		        } else {
		            if((isLeapYear && this.day <= FEB_LEAP_YEAR_DAYS) || (!isLeapYear && this.day <= FEB_NON_LEAP_YEAR_DAYS)) {
		                return true;
		            }
		        }
		    }
		}
		return false;
	}
	
	/**
	Compares two Date objects.
	@param date the Date being compared to the calling Date object.
	@return 1 if the calling Date object is chronologically later the than parameter date, 0 if they are equal, -1 otherwise.
	*/
	@Override
	public int compareTo(Date date) {
	    if(this.year > date.year) {
	        return 1;
	    } else if(this.year < date.year) {
	        return -1;
	    } else {
	        if(this.month > date.month) {
	            return 1;
	        } else if(this.month < date.month) {
	            return -1;
	        } else {
	            if(this.day > date.day) {
	                return 1;
	            } else if(this.day < date.day) {
	                return -1;
	            } else {
	                return 0;
	            }
	        }
	    }
	}
	
	/**
	Returns String representation of the Date object.
	@return String of the Date object.
	*/
	public String toString() {
	    return month + "/" + day + "/" + year;
	}
	
	/**
    Testbed main for the Date class.
    @param args String array to hold any argument from the command line.
    */
	public static void main(String[] args) {
	    //Test case 1
	    System.out.println("Testing invalid month: 0/12/2022");
	    Date testDate1 = new Date("0/12/2022");
	    System.out.println("Output: " + testDate1.isValid());
	    if(testDate1.isValid()) {
	        System.out.print("Test failed");
	    } else {
	        System.out.print("Test passed");
	    }
	    
	    //Test case 2
	    System.out.println("");
	    System.out.println("");
	    System.out.println("Testing invalid month: 13/12/2022");
        Date testDate2 = new Date("13/12/2022");
        System.out.println("Output: " + testDate2.isValid());
        if(testDate2.isValid()) {
            System.out.print("Test failed");
        } else {
            System.out.print("Test passed");
        }
        
        //Test case 3
        System.out.println("");
        System.out.println("");
        System.out.println("Testing invalid day: 1/32/2022");
        Date testDate3 = new Date("1/32/2022");
        System.out.println("Output: " + testDate3.isValid());
        if(testDate3.isValid()) {
            System.out.print("Test failed");
        } else {
            System.out.print("Test passed");
        }
        
        //Test case 4
        System.out.println("");
        System.out.println("");
        System.out.println("Testing valid day: 7/31/2022");
        Date testDate4 = new Date("7/31/2022");
        System.out.println("Output: " + testDate4.isValid());
        if(testDate4.isValid()) {
            System.out.print("Test passed");
        } else {
            System.out.print("Test failed");
        }
        
        //Test case 5
        System.out.println("");
        System.out.println("");
        System.out.println("Testing invalid February day: 2/29/2022");
        Date testDate5 = new Date("2/29/2022");
        System.out.println("Output: " + testDate5.isValid());
        if(testDate5.isValid()) {
            System.out.print("Test failed");
        } else {
            System.out.print("Test passed");
        }
        
        //Test case 6
        System.out.println("");
        System.out.println("");
        System.out.println("Testing valid February day: 2/28/2022");
        Date testDate6 = new Date("2/28/2022");
        System.out.println("Output: " + testDate6.isValid());
        if(testDate6.isValid()) {
            System.out.print("Test passed");
        } else {
            System.out.print("Test failed");
        }
        
        //Test case 7
        System.out.println("");
        System.out.println("");
        System.out.println("Testing valid leap year day: 2/29/2020");
        Date testDate7 = new Date("2/29/2020");
        System.out.println("Output: " + testDate7.isValid());
        if(testDate7.isValid()) {
            System.out.print("Test passed");
        } else {
            System.out.print("Test failed");
        }
        
        //Test case 8
        System.out.println("");
        System.out.println("");
        System.out.println("Testing invalid leap year day: 2/29/2100");
        Date testDate8 = new Date("2/29/2100");
        System.out.println("Output: " + testDate8.isValid());
        if(testDate8.isValid()) {
            System.out.print("Test failed");
        } else {
            System.out.print("Test passed");
        }
        
        //Test case 9
        System.out.println("");
        System.out.println("");
        System.out.println("Testing valid leap year day: 2/29/2000");
        Date testDate9 = new Date("2/29/2000");
        System.out.println("Output: " + testDate9.isValid());
        if(testDate9.isValid()) {
            System.out.print("Test passed");
        } else {
            System.out.print("Test failed");
        }
        
        //Test case 10
        System.out.println("");
        System.out.println("");
        System.out.println("Testing invalid short month day: 4/31/2022");
        Date testDate10 = new Date("4/31/2022");
        System.out.println("Output: " + testDate10.isValid());
        if(testDate10.isValid()) {
            System.out.print("Test failed");
        } else {
            System.out.print("Test passed");
        }
        
        //Test case 11
        System.out.println("");
        System.out.println("");
        System.out.println("Testing invalid year: 1/12/-2012");
        Date testDate11 = new Date("1/12/-2012");
        System.out.println("Output: " + testDate11.isValid());
        if(testDate11.isValid()) {
            System.out.print("Test failed");
        } else {
            System.out.print("Test passed");
        }
	}
}