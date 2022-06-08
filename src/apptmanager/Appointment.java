package apptmanager;

/**
This class defines an appointment object that is comprised of a Patient, Timeslot, and Location object.
It contains methods to compare two Appointment objects and to convert an Appointment object to a string.
@author Dharma Wijesinghe, Min Sun You
*/
public class Appointment {

    private Patient patient;
    private Timeslot slot;
    private Location location;
    
    /**
    Returns the Patient for the calling Appointment object.
    @return Patient
    */
    public Patient getPatient() {
        return patient;
    }
    
    /**
    Initializes Appointment object with given Patient, Timeslot, and Location.
    @param patient the patient for appointment.
    @param timeslot the timeslot for appointment.
    @param location the location for appointment.
    */
    public Appointment(Patient patient, Timeslot timeslot, Location location) {
        this.patient = patient;
        this.slot = timeslot;
        this.location = location;
    }
    
    /**
    Returns the Patient for the calling Appointment object.
    @return Patient.
    */
    public Patient patients() {
        return patient;
    }
    
    /**
    Returns the Location of the calling Appointment object.
    @return Location.
    */
    public Location getLocation() {
        return location;
    }
    
    /**
    Returns the Timeslot of the calling Appointment object.
    @return Timeslot. 
    */
    public Timeslot getTimeslot() { 
        return slot;
    }
    
    /**
    Checks to see if the calling Appointment is the same as the given Appointment in terms of Timeslot and Location.
    @param appt the Appointment to compare Timeslot and Location.
    @return true if the two appointments are the same in terms of Timeslot and Location, false otherwise.
    */
    public boolean timeSlotLocationEquals(Appointment appt) {
        if(this.slot.compareTo(appt.getTimeslot()) == 0) { //two slots are same
            if(this.location == appt.getLocation()) { //locations are same
                return true;
            }
        }
        return false;
    }
    
    /**
    Checks to see if two appointments are equal in terms of Patient and Date.
    @param appt the Appointment to compare Patient and Date.
    @return true if two appointments are same in terms of Patient and Date, false otherwise.
    */
    public boolean patientDateEquals(Appointment appt) {
        if(this.patient.compareTo(appt.patients()) == 0) { //two patients are same
            if(this.slot.getDate().compareTo(appt.slot.getDate()) == 0) { //two dates are same
                if(this.location != appt.getLocation()) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
    Compares two appointments to see if their contents are equal.
    @param obj the Appointment object to compare.
    @return true if two appointments are exactly the same.
    */
    @Override
    public boolean equals(Object obj) {
        Appointment appt = (Appointment)obj;
        if(this.patient.compareTo(appt.patient) == 0 && this.slot.compareTo(appt.slot) == 0 && this.location.getZip().equals(appt.location.getZip())) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
    Returns a String representation of Appointment with all necessary details.
    @return String.
    */
    @Override
    public String toString() {
        return patient.toString() + ", Appointment detail: " + slot.toString() + "" + location.name().substring(0,1) + location.name().substring(1).toLowerCase() + " " + location.getZip() + ", " + location.getCounty();
    }
}