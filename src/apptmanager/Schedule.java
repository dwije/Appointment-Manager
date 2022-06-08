package apptmanager;

/**
This class defines a schedule object which contains an Appointment array containing a list of appointments and an integer of the number of appointments.
It contains methods for finding appointments in the list, adding and removing appointments, and printing appointments in a particular order.
@author Dharma Wijesinghe, Min Sun You
*/
public class Schedule {
    private Appointment [] appointments;
    private int numAppts;
    
    private static final int NOT_FOUND = -1;
    
    /**
    Initializes Appointment array with size four.
    Sets numAppts to zero.
    */
    public Schedule() {
        appointments = new Appointment[4];
        numAppts = 0;
    }
    
    /**
    Returns the numAppts instance variable.
    @return Integer containing the number of appointments in Appointment array.
    */
    public int getNumAppts() {
        return numAppts;
    }
    
    /**
    Returns Appointment array instance variable.
    @return Appointment[].
    */
    public Appointment[] allAppointments() {
        return appointments;
    }
    
    /**
    Checks if an appointment with a specific Timeslot and Location exists.
    @param appt the Appointment to get the Timeslot and Location.
    @return true if an Appointment with specified Timeslot and Location is found, false otherwise.
    */
    public boolean timeSlotLocationApptExists(Appointment appt) {
        for(int i = 0; i < numAppts; i++) {
            if(appointments[i].timeSlotLocationEquals(appt)) { //compare if current appointment has same timeslot and location
                return true;
            }
        }
        return false;
    }
    
    /**
    Calls the patientDateEquals() method to find a matching Patient and Date.
    @param appt the Appointment to match the Patient and Date.
    @return true if matching Patient and Date is found in Appointment array, false otherwise.
    */
    public boolean patientDateExists(Appointment appt) {
        for(int i = 0; i < numAppts; i++) {
            if(appointments[i].patientDateEquals(appt)) {
                return true;
            }
        }
        return false;
    }
    
    /**
    Finds a given Appointment.
    @param appt the Appointment to find.
    @return index of matching Appointment, -1 if not found.
    */
    private int find(Appointment appt) {
        if(numAppts <= 0) {
            return NOT_FOUND;
        }
        for(int i = 0; i < numAppts; i++) {
            Appointment currApp = appointments[i]; 
            if(appt.equals(currApp)) {
                return i;
            }
        }
        return NOT_FOUND;
    }
    
    /**
    Increases size of Appointment array.
    */
    private void grow() { 
        Appointment[] copy = new Appointment[appointments.length + 4]; 
        for(int i = 0; i < appointments.length; i++) {
            copy[i] = appointments[i];
        }
        appointments = copy;
    }
    
    /**
    Checks if an Appointment exists in array.
    @param appt the Appointment to find.
    @return true if Appointment is found, false otherwise.
    */
    public boolean apptExist(Appointment appt) {
        if(find(appt) != -1) { 
            return false;
        }
        return true;
    }
    
    /**
    Adds an Appointment to array.
    Automatically calls grow() to increase the size of the array if there is no space.
    @param appt the Appointment to add.
    @return true if Appointment was added, false otherwise.
    */
    public boolean add(Appointment appt) {
        if(appointments[appointments.length - 1] != null) {
            //There is no space left in the appointments array
            this.grow();
        }
        for(int i = 0; i < appointments.length; i++) {
            if(appointments[i] == null) {
                appointments[i] = appt;
                numAppts++;
                return true;
            }
        }
        return false;
    }
    
    /**
    Deletes the given Appointment from array.
    @param appt the Appointment to delete.
    @return true if Appointment was deleted, false otherwise.
    */
    public boolean remove(Appointment appt) {
        int foundIndex = find(appt);
        if(foundIndex != NOT_FOUND) {
            for(int j = foundIndex; j < appointments.length - 1; j++) {
                appointments[j] = appointments[j+1];
            }
            appointments[appointments.length - 1] = null;
            numAppts--;
            return true;
        } else return false;
    }
    
    /**
    Prints all appointments in current order.
    */
    public void print() { 
        for(int i = 0; i < numAppts; i++){
            System.out.println(appointments[i].toString());
        }
    }
    
    /**
    Prints all appointments by zip code and time.
    If zip codes are same, appointment with earlier time slot printed first.
    */
    public void printByZip() {
        for(int i = 0; i < numAppts - 1; i++) {
            int minIndex = i;
            for(int j = i + 1; j < numAppts; j++) {
                int currentIndexZip = Integer.parseInt(appointments[j].getLocation().getZip());
                int minIndexZip = Integer.parseInt(appointments[minIndex].getLocation().getZip());
                if(currentIndexZip < minIndexZip) {
                    minIndex = j;
                } else if(currentIndexZip == minIndexZip && appointments[j].getTimeslot().compareTo(appointments[minIndex].getTimeslot()) == -1) {
                    minIndex = j;
                }
            }
            Appointment temp = appointments[minIndex];
            appointments[minIndex] = appointments[i];
            appointments[i] = temp;            
        }
        for(int i = 0; i < numAppts; i++) {
            System.out.println(appointments[i].toString());
        }
    }
    
    /**
    Prints all appointments by Patient.
    Sorts by Patient first.
    */
    public void printByPatient() { //sort by patient and print all appointments
        //patientOne.compareTo(patientTwo) = 1 --> patientTwo comes alphabetically first.
        for(int i = 0; i < numAppts - 1; i++) {
            int minIndex = i;
            for(int j = i + 1; j < numAppts; j++) {
                if(appointments[j].getPatient().compareTo(appointments[minIndex].getPatient()) < 0) {
                    minIndex = j;
                }
            }

            Appointment temp = appointments[i];
            appointments[i] = appointments[minIndex];
            appointments[minIndex] = temp;
        }
        for(int i = 0; i < numAppts; i++) {
            System.out.println(appointments[i].toString());
        }
    }
}