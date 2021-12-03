/**
 * Project Rental Properties System
 */


#ifndef _PROPERTYHUB_H
#define _PROPERTYHUB_H

#include "../Subject.h"


class PropertyHub: public Subject {
public: 
    
/**
 * @param propType
 * @param propAddr
 * @param propQuad
 * @param numBed
 * @param numBath
 * @param isFurnished
 */
void createProperty(String propType, String propAddr, String propQuad, int numBed, int numBath, boolean isFurnished);
    
/**
 * @param property
 */
void addToDatabase(Property property);
    
/**
 * @param o
 */
void addObserver(Observer o);
    
/**
 * @param o
 */
void removeObserver(Observer o);
    
void notifyAllObserver();
    
void updateSystemBalance();
    
ArrayList<Property> getPropertyList();
    
/**
 * @param id
 */
Property getPropertyByID(int id);
private: 
    ArrayList<Property> propertyList;
    DatabaseController database;
    ArrayList<PropertyViewer> viewers;
};

#endif //_PROPERTYHUB_H