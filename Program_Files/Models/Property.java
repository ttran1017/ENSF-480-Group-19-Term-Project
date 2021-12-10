/**
 * FileName: Property.java
 * Authors: Tyler Tran, Sina Tavakol Moghaddam, Noel Thomas, Tommy Tran
 * Course: ENSF 480
 * Professor: M. Moussavi
 */

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

    /**
     * Property Constructor
     * @param ownerID ID number of the owner
     * @param email email address of the owner
     * @param type Describes the type of property
     * @param address holds the address of the property
     * @param quad contains the quadrant in which the property is located
     * @param status shows the status of the property
     * @param bed number of bedrooms in the property
     * @param bath number of bathrooms in the propert
     * @param Furnished whether property is furnished or not
     * @param days number of days that the property is allowed to be posted
     */
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

    
    //GETTER METHODS
    /**
     * Getter method for propertyId
     * @return the object's propertyID
     */
    public int getPropertyID() { return this.propertyID; }
    /**
     * Getter method for ownerID
     * @return the object's ownerID
     */
    public int getOwnerID() { return this.ownerID; }
    /**
     * Getter method for ownerEmail
     * @return the object's ownerEmail
     */
    public String getOwnerEmail() { return this.ownerEmail; }
    /**
     * Getter method for propertyType
     * @return the object's propertyType
     */
    public PropertyType getPropertyType() { return this.propertyType; }
    /**
     * Getter method for propertyQuadrant
     * @return the object's propertyQuadrant
     */
    public PropertyQuadrant getPropertyQuadrant() { return this.propertyQuadrant; }
    /**
     * Getter method for propertyStatus
     * @return the object's propertyStatus
     */
    public PropertyStatus getPropertyStatus() { return propertyStatus; }
    /**
     * Getter method for propertyAddress
     * @return the object's propertyAddress
     */
    public String getPropertyAddress() { return this.propertyAddress; } 
    /**
     * Getter method for the numBedrooms integer
     * @return the object's numBedrooms integer
     */
    public int getNumBedrooms() { return this.numBedrooms; }
    /**
     * Getter method for the numBathrooms integer
     * @return the object's numBathrooms
     */
    public int getNumBathrooms() { return this.numBathrooms; }
    /**
     * Getter method for the isFurnished boolean
     * @return the object's isFurnished boolean
     */
    public boolean getIsFurnished() { return this.isFurnished; }
    /**
     * Getter method for daysRemaining
     * @return the object's daysRemaining integer
     */
    public int getDaysRemaining() { return daysRemaining; }

    //SETTER METHODS
    /**
     * Setter method for propertyID
     * @param int propID to be inserted into the object
     */
    public void setPropertyId(int propID) { this.propertyID = propID; }
    /**
     * Setter method for ownerID
     * @param int ownerID to be inserted into the object
     */
    public void setOwnerID(int ownerID) {this.ownerID = ownerID;}
    /**
     * Setter method for ownerEmail
     * @param String email to be inserted into the object
     */
    public void setOwnerEmail(String email) { this.ownerEmail = email; }
    /**
     * Setter method for propertyType
     * @param PropertyType propType to be inserted into the object
     */
    public void setPropertyType(PropertyType propType) { this.propertyType = propType; }
    /**
     * Setter method for propertyQuadrant
     * @param PropertyQuadrant quad to be inserted into the object
     */
    public void setPropertyQuadrant(PropertyQuadrant quad) { this.propertyQuadrant = quad; }
    /**
     * Setter method for propertyStatus
     * @param PropertyStatus status to be inserted into the object
     */
    public void setPropertyStatus(PropertyStatus status) { this.propertyStatus = status; }
    /**
     * Setter method for address
     * @param String address to be inserted into the object
     */
    public void setPropertyAddress(String address) { this.propertyAddress = address; }
    /**
     * Setter method for numBedrooms
     * @param int bedrooms to be inserted into the object
     */
    public void setNumBedrooms(int bedrooms) { this.numBedrooms = bedrooms; }
    /**
     * Setter method for numBathroooms
     * @param int bathrooms to be inserted into the object
     */
    public void setNumBathrooms(int bathrooms) { this.numBathrooms = bathrooms; }
    /**
     * Setter method for isFurnished
     * @param boolean furnished to be inserted into the object
     */
    public void setIsFurnished(boolean furnished) { this.isFurnished = furnished; }
    /**
     * Setter method for daysRemaining
     * @param int days to be inserted into the object
     */
    public void setDaysRemaining(int days) { this.daysRemaining = days; }

}