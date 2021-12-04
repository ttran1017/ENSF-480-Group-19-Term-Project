package Models;

import java.util.*;


public class Property {

    
    public Property() {
    }

    //normal constructor with all information
    public Property(int ID, String email, String type, String address, String quad, int bed, int bath, boolean furnished, int days){
        this.propertyID = ID;
        this.ownerEmail = email;
        this.propertyType = type;
        this.propertyAddress = address;
        this.propertyQuadrant = quad;
        this.numBedrooms = bed;
        this.numBathrooms = bath;
        this.isFurnished = furnished;
        this.daysRemainingPosted = days;
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
        return propertyID;
    }

}