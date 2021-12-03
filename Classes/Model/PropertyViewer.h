/**
 * Project Rental Properties System
 */


#ifndef _PROPERTYVIEWER_H
#define _PROPERTYVIEWER_H

#include "../Observer.h"


class PropertyViewer: public Observer {
public: 
    
static void staticViewProperties();
    
/**
 * @param filter
 */
void viewProperties(Filter filter);
    
void update();
    
ArrayList<Property> getViewableProperties();
    
void PropertyViewer();
    
/**
 * @param viewProps
 */
void setViewableProperties(ArrayList<Property> viewProps);
private: 
    ArrayList<Property> viewableProperties;
};

#endif //_PROPERTYVIEWER_H