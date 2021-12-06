package Models;

import Interfaces.PropertyQuadrant;
import Interfaces.PropertyStatus;
import Interfaces.PropertyType;

public class Property {

    private int propertyID;
    private int ownerID;
    public String ownerEmail;
    private PropertyType propertyType;
    private PropertyStatus propertyStatus;
    private PropertyQuadrant propertyQuadrant;
    private String propertyAddress;
    private int numBedrooms;
    private int numBathrooms;
    private Boolean isFurnished;
    private int daysRemaining;
    

    //TESTING TOOLS
    // private static int counter = 0;
    // public Property() {
    //     ownerEmail = ('A' + counter) + "@cashmoney.com";
    //     propertyType = PropertyType.values()[counter%5];
    //     propertyID = counter++;
    //     propertyAddress = "Active";
    //     propertyQuadrant = PropertyQuadrant.NE;
    //     propertyStatus = PropertyStatus.Active;
    //     numBedrooms = counter;
    //     numBathrooms = counter;
    //     daysRemaining = 50;
    //     isFurnished = true;
    // }

    public Property() { daysRemaining = 0; };

    //normal constructor with all information
    public Property(
        int ownerID, 
        String email, 
        PropertyType type, 
        String address, 
        PropertyQuadrant quad, 
        PropertyStatus status, 
        int bed, 
        int bath, 
        Boolean furnished, 
        int days)
    {
        this.ownerID = ownerID;
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
    public int getOwnerID() { return this.ownerID; }
    public String getOwnerEmail() { return this.ownerEmail; }
    public PropertyType getPropertyType() { return this.propertyType; }
    public PropertyQuadrant getPropertyQuadrant() { return this.propertyQuadrant; }
    public PropertyStatus getPropertyStatus() { return propertyStatus; }
    public String getPropertyAddress() { return this.propertyAddress; } 
    public int getNumBedrooms() { return this.numBedrooms; }
    public int getNumBathrooms() { return this.numBathrooms; }
    public boolean getIsFurnished() { return this.isFurnished; }
    public int getDaysRemaining() { return daysRemaining; }

    public void setPropertyId(int propID) { this.propertyID = propID; }
    public void setOwnerID(int ownerID) {this.ownerID = ownerID;}
    public void setOwnerEmail(String email) { this.ownerEmail = email; }
    public void setPropertyType(PropertyType propType) { this.propertyType = propType; }
    public void setPropertyQuadrant(PropertyQuadrant quad) { this.propertyQuadrant = quad; }
    public void setPropertyStatus(PropertyStatus status) { this.propertyStatus = status; }
    public void setPropertyAddress(String address) { this.propertyAddress = address; }
    public void setNumBedrooms(int bedrooms) { this.numBedrooms = bedrooms; }
    public void setNumBathrooms(int bathrooms) { this.numBathrooms = bathrooms; }
    public void setIsFurnished(boolean furnished) { this.isFurnished = furnished; }
    public void setDaysRemaining(int days) { this.daysRemaining = days; }

}