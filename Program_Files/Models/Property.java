package Models;

import java.util.*;


public class Property {

    
    public Property() {
    }

    //normal constructor with all information
    public Property(int ID, String email, String type, String address, String quad, int bed, int bath, boolean furnished, int days){
        propertyID = id;
        ownerEmail = email;
        propertyType = type;
        propertyAddress = address;
        propertyQuadrant = quad;
        numBedrooms = bed;
        numBathrooms = bath;
        isFurnished = furnished;
        daysRemainingPosted = days;
    }

    
    private int propertyID;
    public String ownerEmail;
    private String propertyType;
    private String propertyAddress;
    private String propertyQuadrant;
    private int numBedrooms;
    private int numBathrooms;
    private boolean isFurnished;
    private int daysRemainingPosted = 0;


    
    public int getPropertyID() {
                return 0;
    }

    
    public String getOwnerEmail() {
                return "";
    }

    
    public String getPropertyType() {
                return "";
    }

    
    public String getPropertyAddress() {
                return "";
    }

    
    public String getPropertyQuadrant() {
                return "";
    }

    
    public int getNumBedrooms() {
                return 0;
    }

    
    public int getNumBathrooms() {
                return 0;
    }

    
    public boolean getIsFurnished() {
                return false;
    }

    
    public int getDaysRemainingPosted() {
                return 0;
    }

    
    public void setPropertyId(int propID) {
    }

    
    public void setOwnerEmail(String email) {
    }

    
    public void setPropertyType(String propType) {
    }

    
    public void setPropertyAddress(String address) {
    }

    
    public void setPropertyQuadrant(String quad) {
    }

    
    public void setNumBedrooms(int bedrooms) {
    }

    
    public void setNumBathrooms(int bathrooms) {
    }

    
    public void setIsFurnished(boolean furnished) {
    }

    
    public void setDaysRemainingPosted(int days) {
    }

}