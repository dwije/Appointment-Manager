package apptmanager;

/**
This class defines a time object which holds an hour and minute value.
It contains methods to check the validity of a Time object, compare two Time objects, and convert a Time object to a string.
@author Dharma Wijesinghe, Min Sun You
*/
public class Time implements Comparable<Time> {
    private int hour;
    private int minute;
    
    private static final int START_HOUR = 9;
    private static final int END_HOUR = 16;
    private static final int END_MINUTE = 45;
    private static final int MINUTES_IN_HOUR = 60;
    
    /**
    Creates a Time object from the given String.
    @param stringTime the String to be converted to a Time object.
    */
    public Time(String stringTime) { 
        int colonIndex = stringTime.indexOf(":");
        this.hour = Integer.parseInt(stringTime.substring(0, colonIndex));
        this.minute = Integer.parseInt(stringTime.substring(colonIndex + 1));
    }
    
    /**
    Checks if the calling Time object is valid and within available hours.
    @return true if Time is valid, false otherwise.
    */
    public boolean isValid() {
        boolean validInterval = false;
        if(minute % 15 == 0) { 
            validInterval = true;
        }
        boolean withinRange = false;
        if(hour >= START_HOUR && hour <= END_HOUR) {
            if(hour <= END_HOUR - 1) {
                if(minute >= 0 && minute < MINUTES_IN_HOUR) {
                    withinRange = true;
                }
            }
            else if(hour == END_HOUR) {
                if(minute >= 0 && minute <= END_MINUTE) {
                    withinRange = true;
                }
            }
        }
        return validInterval && withinRange;
    }
    
    /**
    Returns String representation of Time object.
    @return String of the Time object.
    */
    @Override
    public String toString() {
        if(minute < 10) {
            return hour + ":0" + minute;
        }
        else {
            return hour + ":" + minute;
        }
    }
    
    /**
    Compares two Time objects together.
    @param time the Time being compared to the calling Time object.
    @return 1 if the calling Time object is chronologically later than the parameter time, 0 if they are equal. -1 otherwise.
    */
    @Override
    public int compareTo(Time time) {
        if(this.hour > time.hour) {
            return 1;
        }
        else if(this.hour < time.hour) {
            return -1;
        }
        else {
            if(this.minute > time.minute) {
                return 1;
            }
            else if(this.minute < time.minute) {
                return -1;
            }
            else {
                return 0;
            }
        }
    }
}