package apptmanager;

/**
This class defines a Patient object, which contain a first name, last name, and a Date object for date of birth.
It has methods to compare two different Patient objects and to convert a Patient object to a string.
@author Dharma Wijesinghe, Min Sun You
*/
public class Patient implements Comparable<Patient> {
    
    private String fname;
    private String lname;
    private Date dob;
    
    /**
    Creates a Patient object with the given name and date of birth.
    @param firstName the first name of the patient.
    @param lastName the last name of the patient.
    @param dob the date of birth of the patient.
    */
    public Patient(String firstName, String lastName, Date dob) {
        fname = firstName;
        lname = lastName;
        this.dob = dob;
    }
    
    /**
    Returns String representation of Patient.
    Outputs the first and last name of the Patient followed by their date of birth.
    @return String.
    */
    @Override
    public String toString() {
        return fname + " " + lname + ", DOB: " + dob.toString(); 
    }
    
    /**
    Compares two patients.
    @param patient the Patient object being compared to the calling Patient object.
    @return -1 if the calling Patient alphabetically precedes the parameter patient, 1 otherwise.
    If both patients have the same name, return -1 if the calling Patient has an earlier birthday, 0 if they have the same birthday, 1 otherwise. 
    */
    @Override
    public int compareTo(Patient patient) {
        if(this.lname.compareTo(patient.lname) < 0) {
            return -1;  //This patient alphebetically precedes argument patient's last name
        } else if(this.lname.compareTo(patient.lname) > 0) {
            return 1;   //Argument patient alphebetically precedes this patient's last name
        } else {
            //Both patients have the same last name
            if(this.fname.compareTo(patient.fname) < 0) {
                return -1;  //This patient alphebetically precedes argument patient's first name
            } else if(this.fname.compareTo(patient.fname) > 0) {
                return 1;   //Argument patient alphebetically precedes this patient's first name
            } else {
                //Both patients have the same first and last name
                if(this.dob.compareTo(patient.dob) < 0) {
                    return -1;
                } else if(this.dob.compareTo(patient.dob) > 0) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }
    }
}