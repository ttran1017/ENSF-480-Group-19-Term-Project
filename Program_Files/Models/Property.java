package Models;

import java.util.*;


public class Property {

    private int propertyID;
    public String ownerEmail;
    private String propertyType;
    private String propertyAddress;
    private String propertyQuadrant;
    private String propertyStatus;
    private int numBedrooms;
    private int numBathrooms;
    private boolean isFurnished;
    private int daysRemaining = 0;
    
    public Property() {
    }

    //normal constructor with all information
    public Property(
        String email, 
        String type, 
        String address, 
        String quad,
        String status, 
        int bed, 
        int bath, 
        boolean furnished, 
        int days)
        {
        this.ownerEmail = email;
        this.propertyType = type;
        this.propertyAddress = address;
        this.propertyQuadrant = quad;
        this.propertyStatus = status;
        this.numBedrooms = bed;
        this.numBathrooms = bath;
        this.isFurnished = furnished;
        this.daysRemaining = days;
    }

    public int getPropertyID() { return this.propertyID; }
    public String getOwnerEmail() {return this.ownerEmail; }
    public String getPropertyType() { return this.propertyType; }
    public String getPropertyAddress() { return this.propertyAddress; } 
    public String getPropertyQuadrant() { return this.getPropertyQuadrant(); }
    public int getNumBedrooms() { return this.numBedrooms; }
    public int getNumBathrooms() { return this.getNumBathrooms(); }
    public boolean getIsFurnished() { return this.isFurnished; }
    public int getDaysRemaining() { return daysRemaining; }
    public String getPropertyStatus() { return propertyStatus; }

    public void setPropertyId(int propID) { this.propertyID = propID; }
    public void setOwnerEmail(String email) { this.ownerEmail = email; }
    public void setPropertyType(String propType) { this.propertyType = propType; }
    public void setPropertyAddress(String address) { this.propertyAddress = address; }
    public void setPropertyQuadrant(String quad) { this.propertyQuadrant = quad; }
    public void setNumBedrooms(int bedrooms) { this.numBedrooms = bedrooms; }
    public void setNumBathrooms(int bathrooms) { this.numBathrooms = bathrooms; }
    public void setIsFurnished(boolean furnished) { this.isFurnished = furnished; }
    public void setDaysRemaining(int days) { this.daysRemaining = days; }
    public void setPropertyStatus(String status) { this.propertyStatus = status; }

}