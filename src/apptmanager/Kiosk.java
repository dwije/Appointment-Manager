package apptmanager;

import java.util.Scanner;

/**
This is the user interface class designed to process commands from the console.
It contains numerous methods to check the validity of inputs, carry out the necessary actions, and output the result.
@author Dharma Wijesinghe, Min Sun You
*/
public class Kiosk {
    private String[] sixInputs = new String[MAXSIZE];
    private Schedule schedule = new Schedule();
    
    private static final int MAXSIZE = 6;
    
    /**
    Checks if the inputted date and time is valid.
    This method is called automatically by run() when booking an appointment.
    @return true if date and time is valid, false otherwise.
    */
    private boolean isDateTimeValid() {
        Date birthDate = new Date(sixInputs[0]);
        Date dateAppointment = new Date(sixInputs[3]);
        Time timeAppointment = new Time(sixInputs[4]);
        if(!birthDate.isValid()) { //date is not a valid calendar date
            System.out.println("Invalid date of birth!");
            return false;
        }
        else if(birthDate.compareTo(new Date()) == 0 || birthDate.compareTo(new Date()) == 1) { //The date of birth is today or a future date.
            System.out.println("Date of birth invalid -> it is a future date.");
            return false;
        }
        else if(!dateAppointment.isValid()) {
            System.out.println("Invalid appointment date!");
            return false;
        }
        else if(dateAppointment.compareTo(new Date()) == 0 || dateAppointment.compareTo(new Date()) == -1) { //The appointment date is today or a date before today, or a date beyond this year.
            System.out.println("Appointment date invalid -> must be a future date.");
            return false;
        }
        else if(dateAppointment.year() > (new Date()).year()) {
            System.out.println("Invalid appointment date!");
            return false;
        }
        else if(!timeAppointment.isValid()) { //The time is not a 15-minute interval and outside of the range of the appointment times of the day.
            System.out.println("Invalid appointment time! Must enter a time between 9:00 and 16:45 with a 15-minute interval.");
            return false;
        }
        return true;
    }
    
    /**
    Checks if the appointment being added contains errors or causes inconsistencies.
    This method is called automatically by run() when booking an appointment.
    @return true if appointment can be added without errors or conflicts, false otherwise.
    */
    private boolean isAppointmentValid() {
        Patient patient = new Patient(sixInputs[1], sixInputs[2], new Date(sixInputs[0]));
        Timeslot slot = new Timeslot(new Date(sixInputs[3]), new Time(sixInputs[4]));
        Location location = locations();
        
        if(location == null) { //The location with the county name is not a valid location.
            System.out.println("Invalid location!");
            return false;
        }
        Appointment appointment = new Appointment(patient, slot, location);
        if(!schedule.apptExist(appointment)) { //An appointment with the same patient, timeslot and location is already in the schedule.
            System.out.println("Same appointment exists in the schedule.");
            return false;
        }
        if(schedule.timeSlotLocationApptExists(appointment)){ //The specified timeslot (same date and time) at the specified location has already been taken.
            System.out.println("Time slot has been taken at this location.");
            return false;
        }
        if(schedule.patientDateExists(appointment)) { //The user is booking an appointment with the same patient and date but a different location with an existing appointment.
            System.out.println("Same patient cannot book an appointment with the same date.");
            return false;
        }
        System.out.println("Appointment booked and added to the schedule.");
        return true;
    }
    
    /**
    Converts string from sixInputs array into a Location constant.
    @return The Location if the string from sixInputs matches one of the Location constants, null otherwise.
    */
    private Location locations() {
        switch(sixInputs[5].toUpperCase()) {
            case "SOMERSET":
                return Location.BRIDGEWATER;
            case "MIDDLESEX":
                return Location.PISCATAWAY;
            case "MERCER":
                return Location.PRINCETON;
            case "MORRIS":
                return Location.MORRISTOWN;
            case "UNION":
                return Location.UNION;
            default:
                return null;
        }
    }
    
    /**
    Cancels a desired appointment given from console.
    Does nothing if appointment does not exist.
    */
    private void cancelAppointment() {
        Appointment apptToRemove;
        Patient patient = new Patient(sixInputs[1], sixInputs[2], new Date(sixInputs[0]));
        Timeslot slot = new Timeslot(new Date(sixInputs[3]), new Time(sixInputs[4]));
        Location location = locations();
        apptToRemove = new Appointment(patient, slot, location);
        if(schedule.remove(apptToRemove)) {
            System.out.println("Appointment canceled.");
        }
        else {
            System.out.println("Not canceled, appointment does not exist.");
        }
    }
    
    /**
    Cancels all appointments for a specified patient.
    */
    private void cancelAppointmentsOfPatient() {
        Patient removedPatient = new Patient(sixInputs[1], sixInputs[2], new Date(sixInputs[0]));
        for(int i = 0; i < schedule.getNumAppts(); i++) {
            if(schedule.allAppointments()[i].getPatient().compareTo(removedPatient) == 0) {
                schedule.remove(schedule.allAppointments()[i]);
                i--; //repeat same iteration in case there is more to delete. Is not infinite because there will be a time when all desired appts are gone
            }
        }
        System.out.println("All appointments for " + removedPatient.toString() + " " + "have been canceled.");
    }
    
    /**
    Prints all current appointments in the schedule.
    */
    private void printAppointments() {
        System.out.println("*list of appointments in the schedule*");
        schedule.print();
        System.out.println("*end of schedule*");
    }
    
    /**
    Calls printByZip() in the Schedule class to print appointments ordered by zipcode and time slot.
    */   
    private void printByZip() {
        System.out.println("*list of appointments by zip and time slot.");
        schedule.printByZip();
        System.out.println("*end of schedule.");
    }
    
    /**
    Calls printByPatient() in the Schedule class to print all appointments ordered by patients.
    */
    private void printByPatient() {
        System.out.println("*list of appointments by patient.");
        schedule.printByPatient();
        System.out.println("*end of list");
    }
    
    /**
    Creates an appointment object from inputs in sixInputs array.
    @return An Appointment object containing the newly created appointment.
    */
    private Appointment newAppt() {
        Patient patient = new Patient(sixInputs[1], sixInputs[2], new Date(sixInputs[0]));
        Timeslot timeslot = new Timeslot(new Date(sixInputs[3]), new Time(sixInputs[4]));
        Location location = locations();
        Appointment appointment = new Appointment(patient, timeslot, location);
        return appointment;
    }
    
    /**
    Continuously reads from the console, listening for different commands to book and manage appointments.
    Exits if "Q" command is read from console.
    */
    public void run() {
        System.out.println("Kiosk running. Ready to process transactions.");
        Scanner sc = new Scanner(System.in);
        while(true) {
            String command = sc.next();
            if(command.equals("Q")) {
                System.out.println("Kiosk session ended.");
                break;
            }
            else if(command.equals("B")) { //Book
                for(int i = 0; i < MAXSIZE; i++) { 
                    sixInputs[i] = sc.next();
                }
                if(isDateTimeValid() && isAppointmentValid()) {
                    schedule.add(newAppt());                  
                }
            }
            else if(command.equals("C")) { //Cancel appointment
                for(int i = 0; i < MAXSIZE; i++) { 
                    sixInputs[i] = sc.next();
                }
                cancelAppointment();
            }
            else if(command.equals("CP")) { //Cancel all appointments for a patient
                for(int i = 0; i < 3; i++) {
                    sixInputs[i] = sc.next();
                }
                cancelAppointmentsOfPatient();
            }
            else if(command.equals("P")) { 
                printAppointments(); 
            }
            else if(command.equals("PZ")) { 
                printByZip(); 
            }
            else if(command.equals("PP")) { 
                printByPatient();
            }
            else { 
                System.out.println("Invalid command!"); 
            }
        }
        sc.close();
    }
}