/**
 * Project Rental Properties System
 */


#ifndef _PROPERTY_H
#define _PROPERTY_H

class Property {
public: 
    String ownerEmail;
    
void Property();
    
int getPropertyID();
    
String getOwnerEmail();
    
String getPropertyType();
    
String getPropertyAddress();
    
String getPropertyQuadrant();
    
int getNumBedrooms();
    
int getNumBathrooms();
    
boolean getIsFurnished();
    
int getDaysRemainingPosted();
    
/**
 * @param propID
 */
void setPropertyId(int propID);
    
/**
 * @param email
 */
void setOwnerEmail(String email);
    
/**
 * @param propType
 */
void setPropertyType(String propType);
    
/**
 * @param address
 */
void setPropertyAddress(String address);
    
/**
 * @param quad
 */
void setPropertyQuadrant(String quad);
    
/**
 * @param bedrooms
 */
void setNumBedrooms(int bedrooms);
    
/**
 * @param bathrooms
 */
void setNumBathrooms(int bathrooms);
    
/**
 * @param furnished
 */
void setIsFurnished(boolean furnished);
    
/**
 * @param days
 */
void setDaysRemainingPosted(int days);
private: 
    int propertyID;
    String propertyType;
    String propertyAddress;
    String propertyQuadrant;
    int numBedrooms;
    int numBathrooms;
    boolean isFurnished;
    int daysRemainingPosted = 0;
};

#endif //_PROPERTY_H