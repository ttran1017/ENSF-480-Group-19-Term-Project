/**
 * Project Rental Properties System
 */


#ifndef _MANAGERACCOUNT_H
#define _MANAGERACCOUNT_H

#include "../Account.h"


class ManagerAccount: public Account {
public: 
    
void updateFeePeriod();
    
void updateFees();
    
void generateSummary();
    
void editListingStatus();
    
void viewLandlordInfo();
    
void viewRenterInfo();
    
void ManagerAccount();
    
database getDatabaseController();
    
/**
 * @param controller
 */
void setDatabaseController(database controller);
private: 
    DatabaseController database;
};

#endif //_MANAGERACCOUNT_H