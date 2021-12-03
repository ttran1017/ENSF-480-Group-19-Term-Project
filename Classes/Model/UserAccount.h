/**
 * Project Rental Properties System
 */


#ifndef _USERACCOUNT_H
#define _USERACCOUNT_H

#include "../Account.h"


class UserAccount: public Account {
public: 
    
/**
 * @param propType
 * @param propAddr
 * @param propQuad
 * @param numBed
 * @param numBath
 * @param isFurnished
 */
void registerProperty(String propType, String propAddr, String propQuad, int numBed, int numBath, boolean isFurnished);
    
void updateListing();
    
void uploadProperty();
    
void Operation1();
    
void changeSubscription();
    
void UserAccount();
    
ArrayList<Property> getOwnedProperties();
    
PropertyViewer getViewer();
    
Filter getFilter();
    
boolean getSubscription();
    
/**
 * @param ownedProps
 */
void setOwnedProperties(ArrayList<Property> ownedProps);
    
/**
 * @param viewer
 */
void setViewer(PropertyViewer viewer);
    
/**
 * @param filter
 */
void setFilter(Filter filter);
    
/**
 * @param subscription
 */
void setSubscription(boolean subscription);
    
void Operation11();
    
void Operation12();
private: 
    String username;
    ArrayList<Property> ownedProperties;
    PropertyViewer viewer;
    Filter filter;
    boolean subscription;
};

#endif //_USERACCOUNT_H