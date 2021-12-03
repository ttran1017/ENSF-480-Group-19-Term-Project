/**
 * Project Rental Properties System
 */


#ifndef _SUBJECT_H
#define _SUBJECT_H

class Subject {
public: 
    
/**
 * @param o
 */
void addObserver(Observer o);
    
/**
 * @param o
 */
void removeObserver(Observer o);
    
void notifyAllObservers();
};

#endif //_SUBJECT_H