/**
 * Project Rental Properties System
 */


#ifndef _FILTER_H
#define _FILTER_H

class Filter {
public: 
    
/**
 * @param prop
 */
boolean checkProperty(Property prop);
    
void updateFilter();
    
void Filter();
    
String getPropertyType();
    
String getPropertyQuad();
    
int getMinBedroom();
    
int getMaxBedroom();
    
boolean getIsFurnished();
    
/**
 * @param propType
 */
void setPropertyType(String propType);
    
/**
 * @param quad
 */
void setPropertyQuad(String quad);
    
/**
 * @param min
 */
void setMinBedroom(int min);
    
/**
 * @param max
 */
void setMaxBedroom(int max);
    
/**
 * @param furnished
 */
void setIsFurnished(boolean furnished);
private: 
    String propertyType;
    String propertyQuad;
    int minBedroom;
    int maxBedroom;
    boolean isFurnished;
};

#endif //_FILTER_H