package apptmanager;

/**
This enum class defines 5 constants of different locations available for appointments.
Each constant has its own respective zip code and county.
@author Dharma Wijesinghe, Min Sun You
*/
public enum Location {
    BRIDGEWATER ("08807", "SOMERSET"),
    PISCATAWAY ("08854", "MIDDLESEX"),
    PRINCETON ("08542", "MERCER"),
    MORRISTOWN ("07960", "MORRIS"),
    UNION ("07083", "UNION");
    
    private final String zipCode;
    private final String county;
    
    /**
    Initializes the location constant's properties.
    @param zipCode the zip code.
    @param county the county.
    */
    Location(String zipCode, String county){
        this.zipCode = zipCode;
        this.county = county;
    }
    
    /**
    Returns the zip code of the location constant.
    @return the zip code of the location constant.
    */
    public String getZip() {
        return zipCode;
    }
    
    /**
    Returns the county of the location constant.
    @return county of the location constant.
    */
    public String getCounty() {
        return county;
    }
}